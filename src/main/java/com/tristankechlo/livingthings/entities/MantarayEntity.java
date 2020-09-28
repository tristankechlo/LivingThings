package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.util.IMobVariants;

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
import net.minecraft.tags.FluidTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class MantarayEntity extends AbstractGroupFishEntity implements IMobVariants {

	private static final DataParameter<Byte> MANTARAY_VARIANT = EntityDataManager.createKey(MantarayEntity.class, DataSerializers.BYTE);

	public MantarayEntity(EntityType<? extends MantarayEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.WATER, 0.0F);
		this.moveController = new MantarayEntity.MoveHelperController(this);
		this.lookController = new DolphinLookController(this, 10);
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.MANTARAY.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.0D);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FindWaterGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, SharkEntity.class, 12.0F, 1.1D, 1.2D));
		this.goalSelector.addGoal(4, new MantarayEntity.SwimGoal(this));
		this.goalSelector.addGoal(3, new FollowSchoolLeaderGoal(this));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(MANTARAY_VARIANT, (byte) 0);
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte("MantarayVariant", this.getVariant());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("MantarayVariant")) {
			this.setVariant(compound.getByte("MantarayVariant"));
		} else {
			this.setVariant((byte) 0);
		}
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setVariant(MantarayEntity.getWeightedRandomColorVariant(this.rand));
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public static byte getWeightedRandomColorVariant(Random random) {
		int color1Weight = LivingThingsConfig.MANTARAY.color1Weight.get();
		int color2Weight = LivingThingsConfig.MANTARAY.color2Weight.get();
		if (color1Weight <= 0 && color2Weight <= 0) {
			return 0;
		}
		WeightedMobVariant variant = WeightedRandom.getRandomItem(random, ImmutableList.of(
				new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
				new WeightedMobVariant(Math.max(0, color2Weight), (byte) 1),
				new WeightedMobVariant(Math.max(0, color3Weight), (byte) 2)));
		return variant.variant;
	}

	@SuppressWarnings("deprecation")
	public static boolean canMantaraySpawn(EntityType<MantarayEntity> entity, IWorld world, SpawnReason reason,	BlockPos pos, Random random) {
		if (pos.getY() > 35 && pos.getY() < world.getSeaLevel()) {
			return world.getFluidState(pos).isTagged(FluidTags.WATER);
		} else {
			return false;
		}
	}

	@Override
	public void tick() {
		super.tick();

		// random moving when on land
		if (!this.isInWaterRainOrBubbleColumn()) {
			if (this.onGround) {
				this.setMotion(this.getMotion().add((double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.3D, (double) ((this.rand.nextFloat() * 2.0F - 1.0F) * 0.2F)));
				this.rotationYaw = this.rand.nextFloat() * 360.0F;
				this.onGround = false;
				this.isAirBorne = true;
			}
		}
	}

	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return true;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 7;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return 0.4F;
	}

	@Override
	protected ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		return ActionResultType.PASS;
	}

	@Override
	public byte getVariant() {
		return this.dataManager.get(MANTARAY_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.dataManager.set(MANTARAY_VARIANT, variant);
	}

	@Override
	protected ItemStack getFishBucket() {
		// required by AbstractFishEntity
		// not used, because of we're overriding the rightclick method
		return new ItemStack(Items.BUCKET);
	}

	@Override
	protected SoundEvent getFlopSound() {
		// required by AbstractFishEntity
		return null;
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
		public boolean shouldExecute() {
			return this.fish.func_212800_dy() && super.shouldExecute();
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
				this.mantaray.setMotion(this.mantaray.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.mantaray.getNavigator().noPath()) {
				double d0 = this.posX - this.mantaray.getPosX();
				double d1 = this.posY - this.mantaray.getPosY();
				double d2 = this.posZ - this.mantaray.getPosZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setMoveForward(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.mantaray.rotationYaw = this.limitAngle(this.mantaray.rotationYaw, f, 10.0F);
					this.mantaray.renderYawOffset = this.mantaray.rotationYaw;
					this.mantaray.rotationYawHead = this.mantaray.rotationYaw;
					float f1 = (float) (this.speed * this.mantaray.getAttributeValue(Attributes.MOVEMENT_SPEED));
					if (this.mantaray.isInWater()) {
						this.mantaray.setAIMoveSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, (double) MathHelper.sqrt(d0 * d0 + d2 * d2))
								* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.mantaray.rotationPitch = this.limitAngle(this.mantaray.rotationPitch, f2, 5.0F);
						float f3 = MathHelper.cos(this.mantaray.rotationPitch * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.mantaray.rotationPitch * ((float) Math.PI / 180F));
						this.mantaray.moveForward = f3 * f1;
						this.mantaray.moveVertical = -f4 * f1;
					} else {
						this.mantaray.setAIMoveSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.mantaray.setAIMoveSpeed(0.0F);
				this.mantaray.setMoveStrafing(0.0F);
				this.mantaray.setMoveVertical(0.0F);
				this.mantaray.setMoveForward(0.0F);
			}
		}
	}
}
