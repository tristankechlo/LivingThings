package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.entity.GiraffeConfig;
import com.tristankechlo.livingthings.entity.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entity.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
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
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
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
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Random;
import java.util.UUID;

public class GiraffeEntity extends Animal implements NeutralMob, IMobVariants, ILexiconEntry {

    private static final EntityDataAccessor<Byte> GIRAFFE_VARIANT = SynchedEntityData.defineId(GiraffeEntity.class, EntityDataSerializers.BYTE);
    private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
    private int angerTime;
    private UUID angerTarget;

    public GiraffeEntity(EntityType<? extends GiraffeEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public static boolean checkGiraffeSpawnRules(EntityType<? extends Mob> animal, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.GIRAFFE_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entityIn) {
        GiraffeEntity entityChild = ModEntityTypes.GIRAFFE.get().create(this.level);
        entityChild.setVariant(this.getVariantFromParents(this, entityIn));
        return entityChild;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
        int color1Weight = GiraffeConfig.get().color1Weight.get();
        int color2Weight = GiraffeConfig.get().color2Weight.get();
        int whiteWeight = GiraffeConfig.get().colorWhiteWeight.get();
        byte variant = this.getRandomVariant(random, new byte[]{0, 1, 2}, new int[]{color1Weight, color2Weight, whiteWeight});
        this.setVariant(variant);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, GiraffeConfig.health())
                .add(Attributes.MOVEMENT_SPEED, GiraffeConfig.movementSpeed())
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, GiraffeConfig.attackDamage());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false, GiraffeConfig::canAttack));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(GIRAFFE_VARIANT, (byte) 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("GiraffeVariant", this.getVariant());
        this.addPersistentAngerSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getByte("GiraffeVariant"));
        if (this.level instanceof ServerLevel) {
            this.readPersistentAngerSaveData((ServerLevel) this.level, compound);
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return GiraffeConfig.temptationItems().test(stack);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return GiraffeConfig.maxSpawnedInChunk();
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.98F;
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
    public byte getVariant() {
        return this.getEntityData().get(GIRAFFE_VARIANT);
    }

    @Override
    public void setVariant(byte variant) {
        this.getEntityData().set(GIRAFFE_VARIANT, variant);
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.GIRAFFE;
    }

}
