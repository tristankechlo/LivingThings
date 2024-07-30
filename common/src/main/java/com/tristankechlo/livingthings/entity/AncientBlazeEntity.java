package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.GeneralConfig;
import com.tristankechlo.livingthings.config.entity.AncientBlazeConfig;
import com.tristankechlo.livingthings.entity.ai.AncientBlazeChargeUpGoal;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.LexiconEntries;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerBossEvent;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.BossEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Blaze;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class AncientBlazeEntity extends Monster implements PowerableMob, RangedAttackMob, ILexiconEntry {

    private static final EntityDataAccessor<Byte> SHOOTS = SynchedEntityData.defineId(AncientBlazeEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Integer> INVULNERABLE_TIME = SynchedEntityData.defineId(AncientBlazeEntity.class, EntityDataSerializers.INT);
    private final ServerBossEvent bossInfo = new ServerBossEvent(this.getDisplayName(), BossEvent.BossBarColor.YELLOW, BossEvent.BossBarOverlay.PROGRESS);

    public AncientBlazeEntity(EntityType<? extends AncientBlazeEntity> type, Level world) {
        super(type, world);
        this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
        this.xpReward = 30;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, AncientBlazeConfig.health())
                .add(Attributes.MOVEMENT_SPEED, AncientBlazeConfig.movementSpeed())
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, AncientBlazeConfig.attackDamage());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
        this.setInvulnerableTime(AncientBlazeConfig.chargingTime());
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new AncientBlazeChargeUpGoal(this));
        this.goalSelector.addGoal(1, new RangedAttackGoal(this, 1.0D, 30, 20.0F));
        this.goalSelector.addGoal(2, new MoveTowardsRestrictionGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D, 0.0F));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(INVULNERABLE_TIME, 0);
        this.entityData.define(SHOOTS, (byte) 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("ChargedTime", this.getInvulnerableTime());
        compound.putByte("Shoots", this.getShoots());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setInvulnerableTime(compound.getInt("ChargedTime"));
        this.setShoots(compound.getByte("Shoots"));
        if (this.hasCustomName()) {
            this.bossInfo.setName(this.getDisplayName());
        }
    }

    @Override
    public void setCustomName(Component name) {
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
                        this.getSoundSource(), 1.0F + this.random.nextFloat(), this.random.nextFloat() * 0.7F + 0.3F, false);
            }
            // smoke particles
            for (int i = 0; i < 2; ++i) {
                this.level.addParticle(ParticleTypes.LARGE_SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
            }
        }
        super.aiStep();
    }

    @Override
    protected void customServerAiStep() {
        super.customServerAiStep();
        this.bossInfo.setProgress(this.getHealth() / this.getMaxHealth());
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
        if (this.getInvulnerableTime() > 0 && source.typeHolder().is(DamageTypes.OUT_OF_WORLD.location())) {
            return false;
            // catch large fireballs
        } else if (source.getDirectEntity() instanceof LargeFireball && source.getEntity() instanceof Player) {
            int shoots = this.getShoots();
            if (shoots < AncientBlazeConfig.largeFireballAmount()) {
                this.setShoots((byte) (shoots + 1));
                return false;
            }
            return true;
            // random chance for arrows, tridents,.. to be blocked
        } else if (source.isIndirect()) {
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
        boolean ambientMode = GeneralConfig.get().ambientMode.get();
        if (peaceful || ambientMode || !AncientBlazeConfig.canAttack()) {
            return;
        }

        double d1 = target.getX() - this.getX();
        double d2 = target.getY(0.5D) - this.getY(0.5D);
        double d3 = target.getZ() - this.getZ();

        int shoots = this.getShoots();
        double chance = (double) AncientBlazeConfig.largeFireballChance() / 100.0D;

        if (this.random.nextDouble() < chance && shoots > 0) {
            this.setShoots((byte) (shoots - 1));
            LargeFireball fireballentity = new LargeFireball(this.level, this, d1, d2, d3, 1);
            fireballentity.setPos(fireballentity.getX(), this.getY(0.5D) + 0.5D, fireballentity.getZ());
            this.level.addFreshEntity(fireballentity);
        } else {
            SmallFireball smallfireballentity = new SmallFireball(this.level, this, d1, d2, d3);
            smallfireballentity.setPos(smallfireballentity.getX(), this.getY(0.5D) + 0.5D, smallfireballentity.getZ());
            this.level.addFreshEntity(smallfireballentity);
        }
        if (!this.level.isClientSide()) {
            this.level.playSound(null, this.blockPosition(), ModSounds.ANCIENT_BLAZE_SHOOT.get(), SoundSource.HOSTILE, 2.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        }
    }

    @Override
    public void remove(Entity.RemovalReason reason) {
        int amount = AncientBlazeConfig.blazeSpawnCount();
        if (!this.level.isClientSide() && amount >= 1 && this.isDeadOrDying()) {
            for (int i = 0; i < amount; i++) {
                Blaze blaze = new Blaze(EntityType.BLAZE, this.level);
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
        super.remove(reason);
    }

    @Override
    public float getLightLevelDependentMagicValue() {
        return 1.0F;
    }

    @Override
    protected boolean shouldDespawnInPeaceful() {
        return AncientBlazeConfig.peacefulDespawn() && super.shouldDespawnInPeaceful();
    }

    @Override
    public boolean isSensitiveToWater() {
        return true;
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public boolean causeFallDamage(float p_149683_, float p_149684_, DamageSource p_149685_) {
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
    public void startSeenByPlayer(ServerPlayer player) {
        super.startSeenByPlayer(player);
        this.bossInfo.addPlayer(player);
    }

    @Override
    public void stopSeenByPlayer(ServerPlayer player) {
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
        return LexiconEntries.ANCIENT_BLAZE;
    }

}
