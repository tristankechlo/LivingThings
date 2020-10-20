package com.tristankechlo.livingthings.entities;

import java.util.EnumSet;

import com.tristankechlo.livingthings.config.LivingThingsConfig;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;

public class AncientBlazeEntity extends MonsterEntity implements IChargeableMob, IRangedAttackMob {

	private static final DataParameter<Byte> SHIELDS = EntityDataManager.createKey(AncientBlazeEntity.class, DataSerializers.BYTE);
	private static final DataParameter<Integer> CHARGED_TIME = EntityDataManager.createKey(AncientBlazeEntity.class, DataSerializers.VARINT);
	private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.YELLOW, BossInfo.Overlay.PROGRESS);

	public AncientBlazeEntity(EntityType<? extends AncientBlazeEntity> type, World world) {
		super(type, world);
		this.setPathPriority(PathNodeType.WATER, -1.0F);
		this.setPathPriority(PathNodeType.LAVA, 8.0F);
		this.setPathPriority(PathNodeType.DANGER_FIRE, 0.0F);
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, 0.0F);
		this.experienceValue = 20;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.ANCIENT_BLAZE.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.23D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 48.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setChargedTime(LivingThingsConfig.ANCIENT_BLAZE.chargingTime.get());
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new AncientBlazeEntity.ChargeUpGoal(this));
	    this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 40, 20.0F));
	    this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
	    this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
	    this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
	    this.goalSelector.addGoal(4, new LookRandomlyGoal(this));
	    
	    this.targetSelector.addGoal(0, new HurtByTargetGoal(this));		
	    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, true));
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SHIELDS, (byte)4);
		this.dataManager.register(CHARGED_TIME, 0);
	}

	@Override
	public void livingTick() {
		//slow falling
	    if (!this.onGround && this.getMotion().y < 0.0D) {
	    	this.setMotion(this.getMotion().mul(1.0D, 0.6D, 1.0D));
	    }
	    //smoke particles
		if (this.world.isRemote && this.getChargedTime() == 0) {
			for (int i = 0; i < 2; ++i) {
				this.world.addParticle(ParticleTypes.LARGE_SMOKE, this.getPosXRandom(0.5D), this.getPosYRandom(), this.getPosZRandom(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}
		super.livingTick();
	}
	
	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		//dont get damaged while charging up
		if (this.getChargedTime() > 0 && source != DamageSource.OUT_OF_WORLD) {
			return false;
			
		//random chance for arrows, tridents,.. to be blocked
		} else if (source instanceof IndirectEntityDamageSource) {
			return this.rand.nextInt(5) != 0 && super.attackEntityFrom(source, amount);
			
		//normal damage handling
		} else {
			return super.attackEntityFrom(source, amount);
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putInt("ChargedTime", this.getChargedTime());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setChargedTime(compound.getInt("ChargedTime"));
	    if (this.hasCustomName()) {
	        this.bossInfo.setName(this.getDisplayName());
	    }
	}
	
	@Override
	public void setCustomName(ITextComponent name) {
		super.setCustomName(name);
	    this.bossInfo.setName(this.getDisplayName());
	}
	
	@Override
	protected void updateAITasks() {
		super.updateAITasks();
        this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override
	public void attackEntityWithRangedAttack(LivingEntity target, float distanceFactor) {
        double d1 = target.getPosX() - this.getPosX();
        double d2 = target.getPosYHeight(0.5D) - this.getPosYHeight(0.5D);
        double d3 = target.getPosZ() - this.getPosZ();
        
        SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.world, this, d1, d2, d3);
        smallfireballentity.setPosition(smallfireballentity.getPosX(), this.getPosYHeight(0.5D) + 0.5D, smallfireballentity.getPosZ());
        this.world.addEntity(smallfireballentity);
	}

	@Override
	public float getBrightness() {
		return 1.0F;
	}
	
	@Override
	protected boolean isDespawnPeaceful() {
		//TODO check config for despawn peaceful
		return super.isDespawnPeaceful();
	}

	@Override
	public boolean isWaterSensitive() {
		return true;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}
	
	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}
	
	@Override
	public boolean isOnLadder() {
		return false;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 1;
	}
	
	@Override
	public void addTrackingPlayer(ServerPlayerEntity player) {
		super.addTrackingPlayer(player);
	    this.bossInfo.addPlayer(player);
	}
	
	@Override
	public void removeTrackingPlayer(ServerPlayerEntity player) {
		super.removeTrackingPlayer(player);
	    this.bossInfo.removePlayer(player);
	}
	
	public int getChargedTime() {
		return this.dataManager.get(CHARGED_TIME);
	}
	
	public void setChargedTime(int chargedtime) {
		this.dataManager.set(CHARGED_TIME, chargedtime);
	}

	@Override
	public boolean isCharged() {
		return this.dataManager.get(CHARGED_TIME) > 0;
	}

	class ChargeUpGoal extends Goal {
		
		private AncientBlazeEntity blaze;
		
		public ChargeUpGoal(AncientBlazeEntity entity) {
			this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.JUMP, Goal.Flag.LOOK));
			this.blaze = entity;
		}
		@Override
		public boolean shouldExecute() {
			return this.blaze.getChargedTime() > 0;
		}
		@Override
		public void tick() {
			int chargedtime = this.blaze.getChargedTime();
			if(chargedtime > 0) {
				chargedtime--;
				int divider = LivingThingsConfig.ANCIENT_BLAZE.chargingTime.get() / 40;
				if(chargedtime % divider == 0 && this.blaze.getHealth() < this.blaze.getMaxHealth()) {
					float heal = (float) (LivingThingsConfig.ANCIENT_BLAZE.health.get() / 40);
					this.blaze.heal(heal);
				}
			}
			if(chargedtime == 0) {
				this.blaze.setHealth(this.blaze.getMaxHealth());
			}
			this.blaze.setChargedTime(chargedtime);
		}
	}

}
