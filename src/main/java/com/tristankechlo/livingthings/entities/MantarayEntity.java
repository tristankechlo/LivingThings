package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.entities.misc.IScaleableMob;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.FollowSchoolLeaderGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class MantarayEntity extends AbstractGroupFishEntity implements IMobVariants, IScaleableMob, ILexiconEntry {

	private static final DataParameter<Byte> MANTARAY_VARIANT = EntityDataManager.defineId(MantarayEntity.class,
			DataSerializers.BYTE);
	private static final DataParameter<Byte> MANTARAY_SCALING = EntityDataManager.defineId(MantarayEntity.class,
			DataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"passive_mobs/mantaray");

	public MantarayEntity(EntityType<? extends MantarayEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.moveControl = new MantarayEntity.MoveHelperController(this);
		this.lookControl = new DolphinLookController(this, 10);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.MANTARAY.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.MANTARAY.speed.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FindWaterGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.55D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, SharkEntity.class, 16.0F, 1.3D, 1.45D));
		this.goalSelector.addGoal(3, new FollowSchoolLeaderGoal(this));
		this.goalSelector.addGoal(4, new MantarayEntity.SwimGoal(this));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MANTARAY_VARIANT, (byte) 0);
		this.entityData.define(MANTARAY_SCALING, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("MantarayVariant", this.getVariant());
		compound.putByte("MantarayScaling", this.getScaling());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getByte("MantarayVariant"));
		this.setScaling(compound.getByte("MantarayScaling"));
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
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
		WeightedMobVariant variant = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
						new WeightedMobVariant(Math.max(0, color2Weight), (byte) 1)));
		return variant.variant;
	}

	public static byte getWeightedRandomScaling(Random random) {
		int scaling1Weight = LivingThingsConfig.MANTARAY.scaling1Weight.get();
		int scaling2Weight = LivingThingsConfig.MANTARAY.scaling2Weight.get();
		int scaling3Weight = LivingThingsConfig.MANTARAY.scaling3Weight.get();
		int scaling4Weight = LivingThingsConfig.MANTARAY.scaling4Weight.get();
		if (scaling1Weight <= 0 && scaling2Weight <= 0 && scaling3Weight <= 0 && scaling4Weight <= 0) {
			return 0;
		}
		WeightedMobScaling scaling = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobScaling(Math.max(0, scaling1Weight), (byte) -2),
						new WeightedMobScaling(Math.max(0, scaling2Weight), (byte) 0),
						new WeightedMobScaling(Math.max(0, scaling3Weight), (byte) 2),
						new WeightedMobScaling(Math.max(0, scaling4Weight), (byte) 6)));
		return scaling.scaling;
	}

	public static boolean canMantaraySpawn(EntityType<MantarayEntity> entity, IWorld world, SpawnReason reason,
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
				this.yRot = this.random.nextFloat() * 360.0F;
				this.onGround = false;
				this.hasImpulse = true;
			}
		}
	}

	@Override
	public boolean canBeLeashed(PlayerEntity player) {
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
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.4F;
	}

	@Override
	protected ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		return ActionResultType.PASS;
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
	protected ItemStack getBucketItemStack() {
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
			super(fish, 1.0D, 1);
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

	static class MoveHelperController extends MovementController {
		private final MantarayEntity mantaray;

		public MoveHelperController(MantarayEntity mantarayIn) {
			super(mantarayIn);
			this.mantaray = mantarayIn;
		}

		@Override
		public void tick() {
			if (this.mantaray.isInWater()) {
				this.mantaray.setDeltaMovement(this.mantaray.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.mantaray.getNavigation().isDone()) {
				double d0 = this.wantedX - this.mantaray.getX();
				double d1 = this.wantedY - this.mantaray.getY();
				double d2 = this.wantedZ - this.mantaray.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setZza(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.mantaray.yRot = this.rotlerp(this.mantaray.yRot, f, 10.0F);
					this.mantaray.yBodyRot = this.mantaray.yRot;
					this.mantaray.yHeadRot = this.mantaray.yRot;
					float f1 = (float) (this.speedModifier * this.mantaray.getAttributeValue(Attributes.MOVEMENT_SPEED));
					if (this.mantaray.isInWater()) {
						this.mantaray.setSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, MathHelper.sqrt(d0 * d0 + d2 * d2))
								* 0.0174532925F));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.mantaray.xRot = this.rotlerp(this.mantaray.xRot, f2, 5.0F);
						float f3 = MathHelper.cos(this.mantaray.xRot * 0.0174532925F);
						float f4 = MathHelper.sin(this.mantaray.xRot * 0.0174532925F);
						this.mantaray.zza = f3 * f1;
						this.mantaray.yya = -f4 * f1;
					} else {
						this.mantaray.setSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.mantaray.setSpeed(0.0F);
				this.mantaray.setXxa(0.0F);
				this.mantaray.setYya(0.0F);
				this.mantaray.setZza(0.0F);
			}
		}
	}
}
