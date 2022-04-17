package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class MonkeyEntity extends TamableAnimal implements ILexiconEntry {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/monkey");
	private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(MonkeyEntity.class,
			EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> CLIMBING = SynchedEntityData.defineId(MonkeyEntity.class,
			EntityDataSerializers.BYTE);
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.APPLE);
	private BlockPos jukeboxPosition;
	private boolean partying;

	public MonkeyEntity(EntityType<? extends MonkeyEntity> type, Level worldIn) {
		super(type, worldIn);
	}

	private static final Ingredient getBreedingItems() {
		Ingredient bananas = Ingredient.of(LivingThingsTags.BANANAS);
		return Ingredient.merge(ImmutableList.of(bananas, BREEDING_ITEMS));
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		MonkeyEntity monkey = ModEntityTypes.MONKEY.get().create(world);
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			monkey.setOwnerUUID(uuid);
			monkey.setTame(true);
		} else if (entity instanceof TamableAnimal) {
			UUID uuid2 = ((TamableAnimal) entity).getOwnerUUID();
			if (uuid2 != null) {
				monkey.setOwnerUUID(uuid2);
				monkey.setTame(true);
			}
		}
		return monkey;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new FloatGoal(this));
		this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
		this.goalSelector.addGoal(3, new BetterMeleeAttackGoal(this, 1, true, () -> {
			return LivingThingsConfig.MONKEY.canAttack.get();
		}));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, MonkeyEntity.getBreedingItems(), true));
		this.goalSelector.addGoal(5, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1));
		this.goalSelector.addGoal(7, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(8, new RandomStrollGoal(this, 1.0D, 60));
		this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SITTING, false);
		this.entityData.define(CLIMBING, (byte) 0);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.MONKEY.health.get())
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.MONKEY.damage.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.MONKEY.speed.get());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Sitting", this.isCrouching());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setSitting(compound.getBoolean("Sitting"));
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level.isClientSide()) {
			this.setBesideClimbableBlock(this.horizontalCollision);
		}
	}

	@Override
	public void aiStep() {
		if (this.jukeboxPosition == null || !this.jukeboxPosition.closerToCenterThan(this.position(), 3.46D)
				|| !this.level.getBlockState(this.jukeboxPosition).is(Blocks.JUKEBOX)) {
			this.partying = false;
			this.jukeboxPosition = null;
		}
		super.aiStep();
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return MonkeyEntity.getBreedingItems().test(stack);
	}

	public static boolean isBananaItem(ItemStack stack) {
		return stack.is(LivingThingsTags.BANANAS);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.MONKEY.maxSpawnedInChunk.get();
	}

	@Override
	public boolean onClimbable() {
		return this.isBesideClimbableBlock();
	}

	@Override
	public boolean isAlliedTo(Entity entityIn) {
		if (this.isTame()) {
			LivingEntity livingentity = this.getOwner();
			if (entityIn == livingentity) {
				return true;
			}
			if (entityIn instanceof TamableAnimal) {
				return ((TamableAnimal) entityIn).isOwnedBy(livingentity);
			}
			if (livingentity != null) {
				return livingentity.isAlliedTo(entityIn);
			}
		}
		return super.isAlliedTo(entityIn);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void setRecordPlayingNearby(BlockPos pos, boolean isPartying) {
		this.jukeboxPosition = pos;
		this.partying = isPartying;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isPartying() {
		return this.partying;
	}

	@Override
	public boolean isCrouching() {
		return this.entityData.get(SITTING);
	}

	public void setSitting(boolean sitting) {
		this.entityData.set(SITTING, sitting);
		this.navigation.stop();
		this.setTarget((LivingEntity) null);
	}

	public boolean isBesideClimbableBlock() {
		return (this.entityData.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte b0 = this.entityData.get(CLIMBING);
		if (climbing) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}
		this.entityData.set(CLIMBING, b0);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.31F : 0.67F;
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		return new WallClimberNavigation(this, worldIn);
	}

	@Override
	public void travel(Vec3 travelVector) {
		if (this.isCrouching()) {
			if (this.getNavigation().getPath() != null) {
				this.getNavigation().stop();
			}
			travelVector = Vec3.ZERO;
		}
		super.travel(travelVector);
	}

	@Override
	protected int calculateFallDamage(float distance, float damageMultiplier) {
		return (int) (super.calculateFallDamage(distance, (damageMultiplier)) * 0.5D);
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();
		if (this.level.isClientSide()) {
			if (ILexiconEntry.isLexicon(stack)) {
				return InteractionResult.PASS;
			}
			boolean flag = this.isOwnedBy(player) || this.isTame() || this.isFood(stack) && !this.isTame();
			return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
		} else {
			if (this.isTame()) {
				if (this.isFood(stack) && this.getHealth() < this.getMaxHealth()) {
					if (!player.getAbilities().instabuild) {
						stack.shrink(1);
					}
					this.heal(item.getFoodProperties().getNutrition());
					return InteractionResult.SUCCESS;
				} else if (stack.isEmpty()) {
					this.setSitting(!this.isCrouching());
					return InteractionResult.SUCCESS;
				}
			} else if (this.isFood(stack)) {
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
				if (this.random.nextInt(4) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
					this.tame(player);
					this.setSitting(true);
					this.level.broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 6);
				}
				return InteractionResult.SUCCESS;
			}
			return super.mobInteract(player, hand);
		}
	}

	public static boolean checkMonkeySpawnRules(EntityType<MonkeyEntity> animal, LevelAccessor world,
			MobSpawnType reason, BlockPos pos, Random random) {
		return world.getBlockState(pos.below()).is(LivingThingsTags.MONKEY_SPAWNABLE_ON)
				&& isBrightEnoughToSpawn(world, pos);
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
