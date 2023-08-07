package com.tristankechlo.livingthings.entity;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.config.entity.LionConfig;
import com.tristankechlo.livingthings.entity.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entity.misc.IGenderedMob;
import com.tristankechlo.livingthings.entity.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.LexiconEntries;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Optional;
import java.util.UUID;

public class LionEntity extends Animal implements NeutralMob, IMobVariants, IGenderedMob, ILexiconEntry {

    private static final EntityDataAccessor<Boolean> MALE = SynchedEntityData.defineId(LionEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Byte> LION_VARIANT = SynchedEntityData.defineId(LionEntity.class, EntityDataSerializers.BYTE);
    private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
    private int angerTime;
    private UUID angerTarget;

    public LionEntity(EntityType<? extends LionEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public static boolean checkLionSpawnRules(EntityType<LionEntity> animal, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.LION_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entityIn) {
        LionEntity entityChild = ModEntityTypes.LION.get().create(this.level());
        entityChild.setGender(LionEntity.getWeightedRandomGender(this.random));
        entityChild.setVariant(this.getVariantFromParents(this, entityIn));
        return entityChild;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
        this.setGender(LionEntity.getWeightedRandomGender(this.random));
        int color1Weight = LionConfig.get().color1Weight.get();
        int colorWhiteWeight = LionConfig.get().colorWhiteWeight.get();
        byte variant = this.getRandomVariant(random, new byte[]{0, 1}, new int[]{color1Weight, colorWhiteWeight});
        this.setVariant(variant);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static Gender getWeightedRandomGender(RandomSource random) {
        int maleWeight = LionConfig.get().maleWeight.get();
        int femaleWeight = LionConfig.get().femaleWeight.get();
        if (maleWeight <= 0 && femaleWeight <= 0) {
            return random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
        }
        Optional<WeightedGender> gender = WeightedRandom.getRandomItem(random,
                ImmutableList.of(new WeightedGender(Math.max(0, maleWeight), Gender.MALE),
                        new WeightedGender(Math.max(0, femaleWeight), Gender.FEMALE)));
        return gender.get().gender;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LionConfig.health())
                .add(Attributes.MOVEMENT_SPEED, LionConfig.movementSpeed())
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, LionConfig.attackDamage());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.1D, false, LionConfig::canAttack) {
            @Override
            public double getAttackReachSqr(LivingEntity attackTarget) {
                return (double) (this.mob.getBbWidth() * 1.8F * this.mob.getBbWidth() * 1.8F
                        + attackTarget.getBbWidth());
            }
        });
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 7.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, true, null));
        this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MALE, false);
        this.entityData.define(LION_VARIANT, (byte) 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        if (this.getGender() == Gender.MALE) {
            compound.putBoolean("IsMale", true);
        }
        compound.putByte("LionVariant", this.getVariant());
        this.addPersistentAngerSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.getBoolean("IsMale")) {
            this.setGender(Gender.MALE);
        } else {
            this.setGender(Gender.FEMALE);
        }
        this.setVariant(compound.getByte("LionVariant"));
        this.readPersistentAngerSaveData(this.level(), compound);
    }

    @Override
    public boolean canMate(Animal otherAnimal) {
        if (otherAnimal == this) {
            return false;
        }
        if (otherAnimal instanceof LionEntity) {
            LionEntity otherLion = (LionEntity) otherAnimal;
            if (this.getGender() != otherLion.getGender()) {
                return (this.isInLove() && otherLion.isInLove());
            }
        }
        return false;
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.85F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        boolean isMeat = stack.getItem().isEdible() && stack.getItem().getFoodProperties().isMeat();
        return LionConfig.temptationItems().test(stack) || (LionConfig.allowAllMeatAsFood() && isMeat);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.LION_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.LION_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.LION_DEATH.get();
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return LionConfig.maxSpawnedInChunk();
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.96F;
    }

    @Override
    public Gender getGender() {
        if (this.getEntityData().get(MALE)) {
            return Gender.MALE;
        }
        return Gender.FEMALE;
    }

    @Override
    public void setGender(Gender gender) {
        if (gender == Gender.MALE) {
            this.getEntityData().set(MALE, true);
        } else {
            this.getEntityData().set(MALE, false);
        }
    }

    @Override
    public byte getVariant() {
        return this.getEntityData().get(LION_VARIANT);
    }

    @Override
    public void setVariant(byte variant) {
        this.getEntityData().set(LION_VARIANT, variant);
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
        return LexiconEntries.LION;
    }

}
