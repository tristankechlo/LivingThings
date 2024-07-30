package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.entity.SeahorseConfig;
import com.tristankechlo.livingthings.entity.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModItems;
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
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

import java.util.Random;

public class SeahorseEntity extends AbstractSchoolingFish implements IMobVariants, ILexiconEntry {

    private static final EntityDataAccessor<Byte> VARIANT = SynchedEntityData.defineId(SeahorseEntity.class, EntityDataSerializers.BYTE);

    public SeahorseEntity(EntityType<SeahorseEntity> type, Level world) {
        super(type, world);
    }

    public static boolean checkSeahorseSpawnRules(EntityType<? extends Mob> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, Random random) {
        return world.getFluidState(pos).is(LivingThingsTags.SEAHORSE_SPAWNABLE_ON) && world.getFluidState(pos.above()).is(LivingThingsTags.SEAHORSE_SPAWNABLE_ON);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, SeahorseConfig.health())
                .add(Attributes.MOVEMENT_SPEED, SeahorseConfig.movementSpeed());
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, (byte) 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putByte("SeahorseVariant", getVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        super.readAdditionalSaveData(tag);
        this.setVariant(tag.getByte("SeahorseVariant"));
    }

    @Override
    protected SoundEvent getFlopSound() {
        return ModSounds.SEAHORSE_FLOP.get();
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return SeahorseConfig.maxSpawnedInChunk();
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.85F;
    }

    @Override
    public byte getVariant() {
        return this.entityData.get(VARIANT);
    }

    @Override
    public void setVariant(byte variant) {
        this.entityData.set(VARIANT, Mth.clamp(variant, (byte) 0, (byte) 4));
    }

    @Override
    public ItemStack getBucketItemStack() {
        return new ItemStack(ModItems.SEAHORSE_BUCKET.get());
    }

    @Override
    public void saveToBucketTag(ItemStack stack) {
        super.saveToBucketTag(stack);
        CompoundTag compoundnbt = stack.getOrCreateTag();
        compoundnbt.putByte("BucketSeahorseVariantTag", this.getVariant());
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.SEAHORSE;
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType spawnReason, SpawnGroupData entityData, CompoundTag tag) {
        entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData, tag);
        if (tag != null && tag.contains("BucketSeahorseVariantTag", 1)) {
            this.setVariant(tag.getByte("BucketSeahorseVariantTag"));
            return entityData;
        } else {
            byte variant = 0;
            if (entityData instanceof SeahorseData) {
                variant = ((SeahorseData) entityData).variant;
                entityData = new SeahorseData(this, variant);
            } else {
                final int blueWeight = SeahorseConfig.get().colorBlueWeight.get();
                final int greenWeight = SeahorseConfig.get().colorGreenWeight.get();
                final int purpleWeight = SeahorseConfig.get().colorPurpleWeight.get();
                final int yellowWeight = SeahorseConfig.get().colorYellowWeight.get();
                final int redWeight = SeahorseConfig.get().colorRedWeight.get();
                variant = getRandomVariant(random, new byte[]{0, 1, 2, 3, 4}, new int[]{blueWeight, greenWeight, purpleWeight, yellowWeight, redWeight});
            }
            this.setVariant(variant);
        }
        return entityData;
    }

    private static final class SeahorseData extends AbstractSchoolingFish.SchoolSpawnGroupData {

        private final byte variant;

        public SeahorseData(AbstractSchoolingFish fish, byte variant) {
            super(fish);
            this.variant = variant;
        }

    }

}
