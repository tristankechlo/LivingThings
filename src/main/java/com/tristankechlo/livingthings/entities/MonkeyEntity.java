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

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class MonkeyEntity extends TameableEntity implements ILexiconEntry {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/monkey");
	private static final DataParameter<Boolean> SITTING = EntityDataManager.defineId(MonkeyEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.defineId(MonkeyEntity.class,
			DataSerializers.BYTE);
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.APPLE);
	private BlockPos jukeboxPosition;
	private boolean partying;

	public MonkeyEntity(EntityType<? extends MonkeyEntity> type, World worldIn) {
		super(type, worldIn);
	}

	private static final Ingredient getBreedingItems() {
		Ingredient bananas = Ingredient.of(ItemTags.getAllTags().getTagOrEmpty(LivingThingsTags.BANANAS));
		return Ingredient.merge(ImmutableList.of(bananas, BREEDING_ITEMS));
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		MonkeyEntity monkey = ModEntityTypes.MONKEY_ENTITY.get().create(world);
		UUID uuid = this.getOwnerUUID();
		if (uuid != null) {
			monkey.setOwnerUUID(uuid);
			monkey.setTame(true);
		} else if (entity instanceof TameableEntity) {
			UUID uuid2 = ((TameableEntity) entity).getOwnerUUID();
			if (uuid2 != null) {
				monkey.setOwnerUUID(uuid2);
				monkey.setTame(true);
			}
		}
		return monkey;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new SitGoal(this));
		this.goalSelector.addGoal(3, new BetterMeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, MonkeyEntity.getBreedingItems(), true));
		this.goalSelector.addGoal(5, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1));
		this.goalSelector.addGoal(7, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 1.0D, 60));
		this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(10, new LookRandomlyGoal(this));

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

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.MONKEY.health.get())
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.MONKEY.damage.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.MONKEY.speed.get());
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("Sitting", this.isCrouching());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
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
		if (this.jukeboxPosition == null || !this.jukeboxPosition.closerThan(this.position(), 3.46D)
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
		return ItemTags.getAllTags().getTagOrEmpty(LivingThingsTags.BANANAS).contains(stack.getItem());
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
			if (entityIn instanceof TameableEntity) {
				return ((TameableEntity) entityIn).isOwnedBy(livingentity);
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
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isBaby() ? 0.31F : 0.67F;
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new ClimberPathNavigator(this, worldIn);
	}

	@Override
	public void travel(Vector3d travelVector) {
		if (this.isCrouching()) {
			if (this.getNavigation().getPath() != null) {
				this.getNavigation().stop();
			}
			travelVector = Vector3d.ZERO;
		}
		super.travel(travelVector);
	}

	@Override
	protected int calculateFallDamage(float distance, float damageMultiplier) {
		return super.calculateFallDamage(distance, (damageMultiplier * 0.5F));
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		Item item = stack.getItem();
		if (this.level.isClientSide()) {
			if (isLexicon(stack)) {
				return ActionResultType.PASS;
			}
			boolean flag = this.isOwnedBy(player) || this.isTame() || this.isFood(stack) && !this.isTame();
			return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
		} else {
			if (this.isTame()) {
				if (this.isFood(stack) && this.getHealth() < this.getMaxHealth()) {
					if (!player.abilities.instabuild) {
						stack.shrink(1);
					}
					this.heal(item.getFoodProperties().getNutrition());
					return ActionResultType.SUCCESS;
				} else if (stack.isEmpty()) {
					this.setSitting(!this.isCrouching());
					return ActionResultType.SUCCESS;
				}
			} else if (this.isFood(stack)) {
				if (!player.abilities.instabuild) {
					stack.shrink(1);
				}
				if (this.random.nextInt(4) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
					this.tame(player);
					this.setSitting(true);
					this.level.broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 6);
				}
				return ActionResultType.SUCCESS;
			}
			return super.mobInteract(player, hand);
		}
	}

	public static boolean canMonkeySpawn(EntityType<MonkeyEntity> parrotIn, IWorld worldIn, SpawnReason reason,
			BlockPos pos, Random random) {
		BlockState blockstate = worldIn.getBlockState(pos.below());
		return (blockstate.is(BlockTags.LEAVES) || blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(BlockTags.LOGS)
				|| blockstate.is(Blocks.AIR)) && worldIn.getRawBrightness(pos, 0) > 8;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
