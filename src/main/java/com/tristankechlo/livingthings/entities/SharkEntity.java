package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.DolphinLookController;
import net.minecraft.entity.ai.controller.MovementController;
import net.minecraft.entity.ai.goal.FindWaterGoal;
import net.minecraft.entity.ai.goal.FollowBoatGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.passive.WaterMobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.pathfinding.SwimmerPathNavigator;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SharkEntity extends WaterMobEntity implements IAngerable, ILexiconEntry {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"hostile_mobs/shark");
	private static final RangedInteger rangedInteger = TickRangeConverter.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public SharkEntity(EntityType<? extends SharkEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathfindingMalus(PathNodeType.WATER, 0.0F);
		this.moveControl = new SharkEntity.MoveHelperController(this);
		this.lookControl = new DolphinLookController(this, 10);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.SHARK.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.SHARK.speed.get())
				.add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.SHARK.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FindWaterGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.05D, false, () -> {
			return LivingThingsConfig.SHARK.canAttack.get();
		}) {
			@Override
			public double getAttackReachSqr(LivingEntity attackTarget) {
				return (this.mob.getBbWidth() * 1.25F * this.mob.getBbWidth() * 1.25F + attackTarget.getBbWidth());
			}
		});
		this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 1));
		this.goalSelector.addGoal(3, new FollowBoatGoal(this));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1,
				new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, true, null));
		this.targetSelector.addGoal(2, new ResetAngerGoal<>(this, true));
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (this.level instanceof ServerWorld) {
			this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
		}
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
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.SHARK.maxSpawnedInChunk.get();
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	@Override
	public void travel(Vector3d vector) {
		if (this.isEffectiveAi() && this.isInWater()) {
			this.moveRelative(this.getSpeed(), vector);
			this.move(MoverType.SELF, this.getDeltaMovement());
			this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
			if (this.getTarget() == null) {
				this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(vector);
		}
	}

	public static boolean canSharkSpawn(EntityType<SharkEntity> entity, IWorld world, SpawnReason reason, BlockPos pos,
			Random random) {
		return world.getBlockState(pos).is(Blocks.WATER) && world.getBlockState(pos.above()).is(Blocks.WATER);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setRemainingPersistentAngerTime(int time) {
		this.angerTime = time;
	}

	@Override
	public UUID getPersistentAngerTarget() {
		return this.angerTarget;
	}

	@Override
	public void setPersistentAngerTarget(UUID target) {
		this.angerTarget = target;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(rangedInteger.randomValue(this.random));
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	static class MoveHelperController extends MovementController {
		private final SharkEntity shark;

		public MoveHelperController(SharkEntity sharkIn) {
			super(sharkIn);
			this.shark = sharkIn;
		}

		@Override
		public void tick() {
			if (this.shark.isInWater()) {
				this.shark.setDeltaMovement(this.shark.getDeltaMovement().add(0.0D, 0.005D, 0.0D));
			}

			if (this.operation == MovementController.Action.MOVE_TO && !this.shark.getNavigation().isDone()) {
				double d0 = this.wantedX - this.shark.getX();
				double d1 = this.wantedY - this.shark.getY();
				double d2 = this.wantedZ - this.shark.getZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setZza(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.shark.yRot = this.rotlerp(this.shark.yRot, f, 10.0F);
					this.shark.yBodyRot = this.shark.yRot;
					this.shark.yHeadRot = this.shark.yRot;
					float f1 = (float) (this.speedModifier * this.shark.getAttributeValue(Attributes.MOVEMENT_SPEED));
					if (this.shark.isInWater()) {
						this.shark.setSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, MathHelper.sqrt(d0 * d0 + d2 * d2))
								* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.shark.xRot = this.rotlerp(this.shark.xRot, f2, 5.0F);
						float f3 = MathHelper.cos(this.shark.xRot * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.shark.xRot * ((float) Math.PI / 180F));
						this.shark.zza = f3 * f1;
						this.shark.yya = -f4 * f1;
					} else {
						this.shark.setSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.shark.setSpeed(0.0F);
				this.shark.setXxa(0.0F);
				this.shark.setYya(0.0F);
				this.shark.setZza(0.0F);
			}
		}
	}

}
