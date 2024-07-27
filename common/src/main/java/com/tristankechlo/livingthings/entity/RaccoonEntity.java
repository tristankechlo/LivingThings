package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.entity.RaccoonConfig;
import com.tristankechlo.livingthings.entity.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entity.ai.BreakOstrichEggGoal;
import com.tristankechlo.livingthings.entity.ai.BreakTurtleEggGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.LexiconEntries;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

import java.util.UUID;

public class RaccoonEntity extends Animal implements NeutralMob, ILexiconEntry {

    private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
    private int angerTime;
    private UUID angerTarget;

    public RaccoonEntity(EntityType<? extends Animal> type, Level worldIn) {
        super(type, worldIn);
    }

    public static boolean checkRaccoonSpawnRules(EntityType<RaccoonEntity> animal, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.RACCOON_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        return ModEntityTypes.RACCOON.get().create(world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, RaccoonConfig.health())
                .add(Attributes.MOVEMENT_SPEED, RaccoonConfig.movementSpeed())
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, RaccoonConfig.attackDamage());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.25D, false, RaccoonConfig::canAttack));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, RaccoonConfig.temptationItems(), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new BreakOstrichEggGoal(this, 1.0D, 3, 100, false));
        this.goalSelector.addGoal(5, new BreakTurtleEggGoal(this, 1.0D, 3));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        this.addPersistentAngerSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.readPersistentAngerSaveData(this.level(), compound);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return RaccoonConfig.temptationItems().test(stack);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.RACCOON_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.RACCOON_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.RACCOON_DEATH.get();
    }

    @Override
    public int getAmbientSoundInterval() {
        return 300;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.93F;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return RaccoonConfig.maxSpawnedInChunk();
    }

    @Override
    protected int calculateFallDamage(float distance, float damageMultiplier) {
        return super.calculateFallDamage(distance, (damageMultiplier * 0.3F));
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
        this.setRemainingPersistentAngerTime(rangedInteger.sample(this.random));
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.RACCOON;
    }

}
