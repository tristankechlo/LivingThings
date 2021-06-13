package com.tristankechlo.livingthings.entities;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.AncientBlazeChargeUpGoal;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.IChargeableMob;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.RangedAttackGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.monster.BlazeEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.IndirectEntityDamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.BossInfo;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerBossInfo;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(value = Dist.CLIENT, _interface = IChargeableMob.class)
public class AncientBlazeEntity extends MonsterEntity implements IChargeableMob, IRangedAttackMob, ILexiconEntry {

	private static final DataParameter<Byte> SHOOTS = EntityDataManager.defineId(AncientBlazeEntity.class,
			DataSerializers.BYTE);
	private static final DataParameter<Integer> INVULNERABLE_TIME = EntityDataManager.defineId(AncientBlazeEntity.class,
			DataSerializers.INT);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"hostile_mobs/ancient_blaze");
	private final ServerBossInfo bossInfo = new ServerBossInfo(this.getDisplayName(), BossInfo.Color.YELLOW,
			BossInfo.Overlay.PROGRESS);

	public AncientBlazeEntity(EntityType<? extends AncientBlazeEntity> type, World world) {
		super(type, world);
		this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
		this.setPathfindingMalus(PathNodeType.LAVA, 8.0F);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
		this.xpReward = 30;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.ANCIENT_BLAZE.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.ANCIENT_BLAZE.speed.get())
				.add(Attributes.FOLLOW_RANGE, 48.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.ANCIENT_BLAZE.damage.get());
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setInvulnerableTime(LivingThingsConfig.ANCIENT_BLAZE.chargingTime.get());
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new AncientBlazeChargeUpGoal(this));
		this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 30, 20.0F));
		this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new WaterAvoidingRandomWalkingGoal(this, 1.0D, 0.0F));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(4, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(INVULNERABLE_TIME, 0);
		this.entityData.define(SHOOTS, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putInt("ChargedTime", this.getInvulnerableTime());
		compound.putByte("Shoots", this.getShoots());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setInvulnerableTime(compound.getInt("ChargedTime"));
		this.setShoots(compound.getByte("Shoots"));
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
	public void aiStep() {
		// slow falling
		if (!this.onGround && this.getDeltaMovement().y < 0.0D) {
			this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
		}
		if (this.level.isClientSide() && this.getInvulnerableTime() == 0) {
			// burn sound
			if (this.random.nextInt(24) == 0 && !this.isSilent()) {
				this.level.playLocalSound(this.getX(), this.getY(), this.getZ(), ModSounds.ANCIENT_BLAZE_BURN.get(),
						this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F,
						false);
			}
			// smoke particles
			for (int i = 0; i < 2; ++i) {
				this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY(),
						this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
			}
		}
		super.aiStep();
	}

	@Override
	protected void customServerAiStep() {
		super.customServerAiStep();
		this.bossInfo.setPercent(this.getHealth() / this.getMaxHealth());
	}

	@Override
	protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
		super.dropCustomDeathLoot(source, looting, recentlyHitIn);
		ItemEntity itementity = this.spawnAtLocation(ModItems.ANCIENT_HELMET.get());
		if (itementity != null) {
			itementity.setExtendedLifetime();
		}
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ANCIENT_BLAZE_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.ANCIENT_BLAZE_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ANCIENT_BLAZE_DEATH.get();
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		// dont get damaged while charging up
		if (this.getInvulnerableTime() > 0 && source != DamageSource.OUT_OF_WORLD) {
			return false;
			// catch large fireballs
		} else if (source.getDirectEntity() instanceof FireballEntity && source.getEntity() instanceof PlayerEntity) {
			int shoots = this.getShoots();
			if (shoots < LivingThingsConfig.ANCIENT_BLAZE.largeFireballAmount.get()) {
				this.setShoots((byte) (shoots + 1));
				return false;
			}
			return true;
			// random chance for arrows, tridents,.. to be blocked
		} else if (source instanceof IndirectEntityDamageSource) {
			return this.random.nextInt(4) != 0 && super.hurt(source, amount);
		} else {
			// normal damage handling
			return super.hurt(source, amount);
		}
	}

	@Override
	public void performRangedAttack(LivingEntity target, float distanceFactor) {
		// don't attack if disabled in config
		boolean peaceful = (this.level.getDifficulty() == Difficulty.PEACEFUL);
		boolean ambientMode = LivingThingsConfig.GENERAL.ambientMode.get();
		if (peaceful || ambientMode || !LivingThingsConfig.ANCIENT_BLAZE.canAttack.get()) {
			return;
		}

		double d1 = target.getX() - this.getX();
		double d2 = target.getY(0.5D) - this.getY(0.5D);
		double d3 = target.getZ() - this.getZ();

		int shoots = this.getShoots();
		double chance = (double) LivingThingsConfig.ANCIENT_BLAZE.largeFireballChance.get() / 100.0D;

		if (this.random.nextDouble() < chance && shoots > 0) {
			this.setShoots((byte) (shoots - 1));
			FireballEntity fireballentity = new FireballEntity(this.level, this, d1, d2, d3);
			fireballentity.setPos(fireballentity.getX(), this.getY(0.5D) + 0.5D, fireballentity.getZ());
			fireballentity.explosionPower = 1;
			this.level.addFreshEntity(fireballentity);
		} else {
			SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.level, this, d1, d2, d3);
			smallfireballentity.setPos(smallfireballentity.getX(), this.getY(0.5D) + 0.5D, smallfireballentity.getZ());
			this.level.addFreshEntity(smallfireballentity);
		}
		if (!this.level.isClientSide()) {
			this.level.playSound(null, this.blockPosition(), ModSounds.ANCIENT_BLAZE_SHOOT.get(), SoundCategory.HOSTILE,
					2.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
		}
	}

	@Override
	@SuppressWarnings("deprecation")
	public void remove(boolean keepData) {
		int amount = LivingThingsConfig.ANCIENT_BLAZE.blazeSpawnCount.get();
		if (!this.level.isClientSide() && amount >= 1 && this.isDeadOrDying() && !this.removed) {
			for (int i = 0; i < amount; i++) {
				BlazeEntity blaze = new BlazeEntity(EntityType.BLAZE, this.level);
				if (this.isPersistenceRequired()) {
					blaze.setPersistenceRequired();
				}
				blaze.setCustomName(this.getCustomName());
				blaze.setNoAi(this.isNoAi());
				blaze.setInvulnerable(this.isInvulnerable());
				blaze.moveTo(this.getX(), this.getY(), this.getZ(), this.random.nextFloat() * 360.0F, 0.0F);
				this.level.addFreshEntity(blaze);
			}
		}
		super.remove(keepData);
	}

	@Override
	public float getBrightness() {
		return 1.0F;
	}

	@Override
	protected boolean shouldDespawnInPeaceful() {
		return LivingThingsConfig.ANCIENT_BLAZE.peacefulDespawn.get() && super.shouldDespawnInPeaceful();
	}

	@Override
	public boolean isSensitiveToWater() {
		return true;
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		super.causeFallDamage(distance, damageMultiplier);
		return false;
	}

	@Override
	public boolean onClimbable() {
		return false;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return 1;
	}

	@Override
	public void startSeenByPlayer(ServerPlayerEntity player) {
		super.startSeenByPlayer(player);
		this.bossInfo.addPlayer(player);
	}

	@Override
	public void stopSeenByPlayer(ServerPlayerEntity player) {
		super.stopSeenByPlayer(player);
		this.bossInfo.removePlayer(player);
	}

	public int getInvulnerableTime() {
		return this.entityData.get(INVULNERABLE_TIME);
	}

	public void setInvulnerableTime(int time) {
		this.entityData.set(INVULNERABLE_TIME, time);
	}

	public byte getShoots() {
		return this.entityData.get(SHOOTS);
	}

	public void setShoots(byte shoots) {
		this.entityData.set(SHOOTS, shoots);
	}

	@Override
	public boolean isPowered() {
		return this.entityData.get(INVULNERABLE_TIME) > 0;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
