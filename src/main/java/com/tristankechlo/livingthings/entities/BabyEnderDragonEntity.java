package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.tristankechlo.livingthings.entities.ai.CustomSitWhenOrderedToSitGoal;
import com.tristankechlo.livingthings.entities.misc.CustomDragonFireball;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

//TODO patchouli
public class BabyEnderDragonEntity extends TamableAnimal implements NeutralMob, RangedAttackMob, FlyingAnimal {

	private static final EntityDataAccessor<Integer> COLLAR_COLOR = SynchedEntityData
			.defineId(BabyEnderDragonEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Integer> REMAINING_ANGER_TIME = SynchedEntityData
			.defineId(BabyEnderDragonEntity.class, EntityDataSerializers.INT);
	private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(BabyEnderDragonEntity.class,
			EntityDataSerializers.BOOLEAN);
	private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
	private static final Ingredient TAMING_ITEMS = Ingredient.of(Items.CHORUS_FRUIT);
	@Nullable
	private UUID persistentAngerTarget;

	public BabyEnderDragonEntity(EntityType<? extends BabyEnderDragonEntity> entity, Level level) {
		super(entity, level);
		this.setTame(false);

		this.moveControl = new FlyingMoveControl(this, 10, true);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new CustomSitWhenOrderedToSitGoal(this));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, TAMING_ITEMS, false));
		this.goalSelector.addGoal(4, new RangedAttackGoal(this, 1.1D, 120, 240, 25.0F));
		this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.3D, 10.0F, 2.0F, true));
		this.goalSelector.addGoal(8, new WaterAvoidingRandomFlyingGoal(this, 1.2D));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, true, false));
		this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(COLLAR_COLOR, DyeColor.RED.getId());
		this.entityData.define(REMAINING_ANGER_TIME, 0);
		this.entityData.define(SITTING, false);
	}

	@Override
	protected void customServerAiStep() {
		// slow falling
		if (!this.onGround && this.getDeltaMovement().y < 0.0D) {
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
		}
		// float down when ordered to sit
		if (this.navigation.isDone() && this.isOrderedToSit()) {
			this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05D, 0));
		}
		super.customServerAiStep();
	}

	@Override
	protected PathNavigation createNavigation(Level level) {
		FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);
		navigation.setCanFloat(true);
		navigation.setCanOpenDoors(true);
		navigation.setCanPassDoors(true);
		return navigation;
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack itemstack = player.getItemInHand(hand);
		Item item = itemstack.getItem();
		if (this.level.isClientSide) {
			boolean flag = this.isOwnedBy(player) || this.isTame()
					|| TAMING_ITEMS.test(itemstack) && !this.isTame() && !this.isAngry();
			return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if (this.isFood(itemstack) && this.getHealth() < this.getMaxHealth()) {
					if (!player.getAbilities().instabuild) {
						itemstack.shrink(1);
					}
					this.heal((float) (item.getFoodProperties().getNutrition() / 2));
					this.gameEvent(GameEvent.MOB_INTERACT, this.eyeBlockPosition());
					return InteractionResult.SUCCESS;
				}
				if (this.isOwnedBy(player) && (item instanceof DyeItem)) {
					DyeColor dyecolor = ((DyeItem) item).getDyeColor();
					if (dyecolor != this.getCollarColor()) {
						this.setCollarColor(dyecolor);
						if (!player.getAbilities().instabuild) {
							itemstack.shrink(1);
						}
						return InteractionResult.SUCCESS;
					}
				}
				if (itemstack.isEmpty() && this.isOwnedBy(player)) {
					this.setOrderedToSit(!this.isOrderedToSit());
					this.jumping = false;
					this.navigation.stop();
					this.setTarget((LivingEntity) null);
					return InteractionResult.SUCCESS;
				}
			} else if (TAMING_ITEMS.test(itemstack) && !this.isAngry()) {
				if (!player.getAbilities().instabuild) {
					itemstack.shrink(1);
				}
				if (this.random.nextInt(5) == 0) {
					this.tame(player);
					this.navigation.stop();
					this.setTarget((LivingEntity) null);
					this.setOrderedToSit(true);
					this.level.broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 6);
				}
				return InteractionResult.SUCCESS;
			}
			return InteractionResult.PASS;
		}
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return TAMING_ITEMS.test(stack);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag nbt) {
		super.readAdditionalSaveData(nbt);
		this.setCollarColor(DyeColor.byId(nbt.getInt("CollarColor")));
		this.setOrderedToSit(nbt.getBoolean("Sitting"));
		this.readPersistentAngerSaveData(level, nbt);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag nbt) {
		super.addAdditionalSaveData(nbt);
		nbt.putShort("CollarColor", (short) this.getCollarColor().getId());
		nbt.putBoolean("Sitting", this.isOrderedToSit());
		this.addPersistentAngerSaveData(nbt);
	}

	@Override
	public void setOrderedToSit(boolean sitting) {
		super.setOrderedToSit(sitting);
		this.entityData.set(SITTING, sitting);
	}

	@Override
	public boolean isOrderedToSit() {
		return this.entityData.get(SITTING);
	}

	public static AttributeSupplier.Builder createAttributes() {
		// TODO attributes
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.3D)
				.add(Attributes.FLYING_SPEED, 0.5D);
	}

	public static boolean checkBabyEnderDragonSpawnRules(EntityType<BabyEnderDragonEntity> entityType,
			LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, Random random) {
		// TODO spawn rules
		return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON);
	}

	protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter p_186210_, BlockPos p_186211_) {
		return p_186210_.getRawBrightness(p_186211_, 0) > 8;
	}

	public DyeColor getCollarColor() {
		return DyeColor.byId(this.entityData.get(COLLAR_COLOR));
	}

	public void setCollarColor(DyeColor dyeColor) {
		this.entityData.set(COLLAR_COLOR, dyeColor.getId());
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.entityData.get(REMAINING_ANGER_TIME);
	}

	@Override
	public void setRemainingPersistentAngerTime(int time) {
		this.entityData.set(REMAINING_ANGER_TIME, time);
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
	}

	@Nullable
	@Override
	public UUID getPersistentAngerTarget() {
		return this.persistentAngerTarget;
	}

	@Override
	public void setPersistentAngerTarget(@Nullable UUID uuid) {
		this.persistentAngerTarget = uuid;
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		BabyEnderDragonEntity child = ModEntityTypes.BABY_ENDER_DRAGON.get().create(world);
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			child.setOwnerUUID(uuid);
			child.setTame(true);
		}
		return child;
	}

	@Override
	public void performRangedAttack(LivingEntity entity, float distanceFactor) {
		// TODO check ambientmode
		Vec3 vec = this.getViewVector(1.0F);
		double d1 = this.getX() - vec.x * 10D;
		double d2 = this.getY();
		double d3 = this.getZ() - vec.z * 10D;
		double d4 = entity.getX() - d1;
		double d5 = entity.getY(0.1) - d2;
		double d6 = entity.getZ() - d3;
		CustomDragonFireball dragonfireball = new CustomDragonFireball(this.level, this, d4, d5, d6);
		dragonfireball.moveTo(d1, d2, d3, 0.0F, 0.0F);
		this.level.addFreshEntity(dragonfireball);
		if (!this.level.isClientSide() && !this.isSilent()) {
			// TODO play shoot sound
		}
	}

	@Override
	public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
		return false;
	}

	@Override
	protected void checkFallDamage(double p_20990_, boolean p_20991_, BlockState p_20992_, BlockPos p_20993_) {
		return;
	}

	@Override
	public boolean isFlying() {
		return !this.onGround;
	}

}