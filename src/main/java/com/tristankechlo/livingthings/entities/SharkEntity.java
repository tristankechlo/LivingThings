package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;

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
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class SharkEntity extends WaterMobEntity implements IAngerable {

	private static final RangedInteger rangedInteger = TickRangeConverter.convertRange(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public SharkEntity(EntityType<? extends SharkEntity> type, World worldIn) {
		super(type, worldIn);
		this.setPathPriority(PathNodeType.WATER, 0.0F);
		this.moveController = new SharkEntity.MoveHelperController(this);
		this.lookController = new DolphinLookController(this, 10);
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.SHARK.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.05F)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 25.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, LivingThingsConfig.SHARK.health.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FindWaterGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.05D, false) {
				@Override
				public double getAttackReachSqr(LivingEntity attackTarget) {
				      return (double)(this.attacker.getWidth() * 1.25F * this.attacker.getWidth() * 1.25F + attackTarget.getWidth());
				}
			});
		this.goalSelector.addGoal(2, new RandomSwimmingGoal(this, 1.0D, 1));
		this.goalSelector.addGoal(3, new FollowBoatGoal(this));
	    this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, true, null));
		this.targetSelector.addGoal(2, new ResetAngerGoal<>(this, true));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		this.writeAngerNBT(compound);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(this.world instanceof ServerWorld) {
			this.readAngerNBT((ServerWorld) this.world, compound);
		}
	}

	@Override
	public void tick() {
		super.tick();

		//random moving when on land
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
	public int getMaxSpawnedInChunk() {
		return LivingThingsConfig.SHARK.maxSpawns.get();
	}
	
	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new SwimmerPathNavigator(this, worldIn);
	}

	@Override
	public void travel(Vector3d vector) {
		if (this.isServerWorld() && this.isInWater()) {
			this.moveRelative(this.getAIMoveSpeed(), vector);
			this.move(MoverType.SELF, this.getMotion());
			this.setMotion(this.getMotion().scale(0.9D));
			if (this.getAttackTarget() == null) {
				this.setMotion(this.getMotion().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(vector);
		}
	}

	public static boolean canSharkSpawn(EntityType<SharkEntity> entity, IWorld world, SpawnReason reason, BlockPos pos, Random random) {
	      return world.getBlockState(pos).isIn(Blocks.WATER) && world.getBlockState(pos.up()).isIn(Blocks.WATER);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	public int getAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setAngerTime(int time) {
		this.angerTime = time;
		
	}

	@Override
	public UUID getAngerTarget() {
		return this.angerTarget;
	}

	@Override
	public void setAngerTarget(UUID target) {
		this.angerTarget = target;
		
	}

	@Override
	public void func_230258_H__() {
		this.setAngerTime(rangedInteger.getRandomWithinRange(this.rand));		
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
				this.shark.setMotion(this.shark.getMotion().add(0.0D, 0.005D, 0.0D));
			}

			if (this.action == MovementController.Action.MOVE_TO && !this.shark.getNavigator().noPath()) {
				double d0 = this.posX - this.shark.getPosX();
				double d1 = this.posY - this.shark.getPosY();
				double d2 = this.posZ - this.shark.getPosZ();
				double d3 = d0 * d0 + d1 * d1 + d2 * d2;
				if (d3 < (double) 2.5000003E-7F) {
					this.mob.setMoveForward(0.0F);
				} else {
					float f = (float) (MathHelper.atan2(d2, d0) * (double) (180F / (float) Math.PI)) - 90.0F;
					this.shark.rotationYaw = this.limitAngle(this.shark.rotationYaw, f, 10.0F);
					this.shark.renderYawOffset = this.shark.rotationYaw;
					this.shark.rotationYawHead = this.shark.rotationYaw;
					float f1 = (float) (this.speed * this.shark.getAttributeValue(Attributes.MOVEMENT_SPEED));
					if (this.shark.isInWater()) {
						this.shark.setAIMoveSpeed(f1 * 0.02F);
						float f2 = -((float) (MathHelper.atan2(d1, (double) MathHelper.sqrt(d0 * d0 + d2 * d2))	* (double) (180F / (float) Math.PI)));
						f2 = MathHelper.clamp(MathHelper.wrapDegrees(f2), -85.0F, 85.0F);
						this.shark.rotationPitch = this.limitAngle(this.shark.rotationPitch, f2, 5.0F);
						float f3 = MathHelper.cos(this.shark.rotationPitch * ((float) Math.PI / 180F));
						float f4 = MathHelper.sin(this.shark.rotationPitch * ((float) Math.PI / 180F));
						this.shark.moveForward = f3 * f1;
						this.shark.moveVertical = -f4 * f1;
					} else {
						this.shark.setAIMoveSpeed(f1 * 0.1F);
					}

				}
			} else {
				this.shark.setAIMoveSpeed(0.0F);
				this.shark.setMoveStrafing(0.0F);
				this.shark.setMoveVertical(0.0F);
				this.shark.setMoveForward(0.0F);
			}
		}
	}

}
