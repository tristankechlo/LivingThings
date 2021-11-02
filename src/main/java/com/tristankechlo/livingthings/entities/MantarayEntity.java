package com.tristankechlo.livingthings.entities;

import java.util.Optional;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.entities.misc.IScaleableMob;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.WeighedRandom;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FollowFlockLeaderGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class MantarayEntity extends AbstractSchoolingFish implements IMobVariants, IScaleableMob, ILexiconEntry {

	private static final EntityDataAccessor<Byte> MANTARAY_VARIANT = SynchedEntityData.defineId(MantarayEntity.class,
			EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> MANTARAY_SCALING = SynchedEntityData.defineId(MantarayEntity.class,
			EntityDataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"passive_mobs/mantaray");

	public MantarayEntity(EntityType<? extends MantarayEntity> type, Level worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
		this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
		this.lookControl = new SmoothSwimmingLookControl(this, 10);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.MANTARAY.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.MANTARAY.speed.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.55D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, SharkEntity.class, 16.0F, 1.3D, 1.45D));
		this.goalSelector.addGoal(3, new FollowFlockLeaderGoal(this));
		this.goalSelector.addGoal(4, new MantarayEntity.SwimGoal(this));
		this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MANTARAY_VARIANT, (byte) 0);
		this.entityData.define(MANTARAY_SCALING, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("MantarayVariant", this.getVariant());
		compound.putByte("MantarayScaling", this.getScaling());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getByte("MantarayVariant"));
		this.setScaling(compound.getByte("MantarayScaling"));
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
			MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
		this.setVariant(MantarayEntity.getWeightedRandomColorVariant(this.random));
		this.setScaling(MantarayEntity.getWeightedRandomScaling(this.random));
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public static byte getWeightedRandomColorVariant(Random random) {
		int color1Weight = LivingThingsConfig.MANTARAY.color1Weight.get();
		int color2Weight = LivingThingsConfig.MANTARAY.color2Weight.get();
		if (color1Weight <= 0 && color2Weight <= 0) {
			return 0;
		}
		Optional<WeightedMobVariant> variant = WeighedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
						new WeightedMobVariant(Math.max(0, color2Weight), (byte) 1)));
		return variant.get().variant;
	}

	public static byte getWeightedRandomScaling(Random random) {
		int scaling1Weight = LivingThingsConfig.MANTARAY.scaling1Weight.get();
		int scaling2Weight = LivingThingsConfig.MANTARAY.scaling2Weight.get();
		int scaling3Weight = LivingThingsConfig.MANTARAY.scaling3Weight.get();
		int scaling4Weight = LivingThingsConfig.MANTARAY.scaling4Weight.get();
		if (scaling1Weight <= 0 && scaling2Weight <= 0 && scaling3Weight <= 0 && scaling4Weight <= 0) {
			return 0;
		}
		Optional<WeightedMobScaling> scaling = WeighedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobScaling(Math.max(0, scaling1Weight), (byte) -2),
						new WeightedMobScaling(Math.max(0, scaling2Weight), (byte) 0),
						new WeightedMobScaling(Math.max(0, scaling3Weight), (byte) 2),
						new WeightedMobScaling(Math.max(0, scaling4Weight), (byte) 6)));
		return scaling.get().scaling;
	}

	public static boolean canMantaraySpawn(EntityType<MantarayEntity> entity, LevelAccessor world, MobSpawnType reason,
			BlockPos pos, Random random) {
		return world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
	}

	@Override
	public void tick() {
		super.tick();
		// random moving when on land
		if (!this.isInWaterRainOrBubble()) {
			if (this.onGround) {
				this.setDeltaMovement(this.getDeltaMovement().add(((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F),
						0.3D, ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
				this.setYRot(this.random.nextFloat() * 360.0F);
				this.onGround = false;
				this.hasImpulse = true;
			}
		}
	}

	@Override
	public boolean canBeLeashed(Player player) {
		return true;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.MANTARAY.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return 0.4F;
	}

	@Override
	protected InteractionResult mobInteract(Player player, InteractionHand hand) {
		return InteractionResult.PASS;
	}

	@Override
	public byte getVariant() {
		return this.entityData.get(MANTARAY_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.entityData.set(MANTARAY_VARIANT, variant);
	}

	@Override
	public byte getScaling() {
		return this.entityData.get(MANTARAY_SCALING);
	}

	@Override
	public void setScaling(byte scaling) {
		this.entityData.set(MANTARAY_SCALING, scaling);
	}

	@Override
	public ItemStack getBucketItemStack() {
		// required by AbstractFishEntity
		// not used, because we are overriding the rightclick method
		return new ItemStack(Items.BUCKET);
	}

	@Override
	protected SoundEvent getFlopSound() {
		// required by AbstractFishEntity
		return null;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	static class SwimGoal extends RandomSwimmingGoal {
		private final MantarayEntity fish;

		public SwimGoal(MantarayEntity fish) {
			super(fish, 1.0D, 40);
			this.fish = fish;
		}

		/**
		 * Returns whether execution should begin. You can also read and cache any state
		 * necessary for execution in this method as well.
		 */
		@Override
		public boolean canUse() {
			return this.fish.canRandomSwim() && super.canUse();
		}
	}

}
