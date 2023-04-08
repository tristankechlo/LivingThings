package com.tristankechlo.livingthings.entity;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.config.entity.MantarayConfig;
import com.tristankechlo.livingthings.entity.misc.IMobVariants;
import com.tristankechlo.livingthings.entity.misc.IScaleableMob;
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
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

import java.util.Optional;

public class MantarayEntity extends AbstractSchoolingFish implements IMobVariants, IScaleableMob, ILexiconEntry {

    private static final EntityDataAccessor<Byte> MANTARAY_VARIANT = SynchedEntityData.defineId(MantarayEntity.class, EntityDataSerializers.BYTE);
    private static final EntityDataAccessor<Byte> MANTARAY_SCALING = SynchedEntityData.defineId(MantarayEntity.class, EntityDataSerializers.BYTE);

    public MantarayEntity(EntityType<? extends MantarayEntity> type, Level worldIn) {
        super(type, worldIn);
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 10);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, MantarayConfig.health())
                .add(Attributes.MOVEMENT_SPEED, MantarayConfig.movementSpeed());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.55D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, SharkEntity.class, 16.0F, 1.3D, 1.45D));
        this.goalSelector.addGoal(3, new FollowFlockLeaderGoal(this));
        this.goalSelector.addGoal(4, new MantarayEntity.SwimGoal(this));
        this.goalSelector.addGoal(5, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(MANTARAY_VARIANT, (byte) 0);
        this.entityData.define(MANTARAY_SCALING, (byte) 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("MantarayVariant", this.getVariant());
        compound.putByte("MantarayScaling", this.getScaling());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setVariant(compound.getByte("MantarayVariant"));
        this.setScaling(compound.getByte("MantarayScaling"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
        int colorBlueVariant = MantarayConfig.get().colorBlueVariant.get();
        int colorBrownVariant = MantarayConfig.get().colorBrownVariant.get();
        byte variant = this.getRandomVariant(random, new byte[]{0, 1}, new int[]{colorBlueVariant, colorBrownVariant});
        this.setVariant(variant);
        this.setScaling(MantarayEntity.getWeightedRandomScaling(this.random));
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    public static byte getWeightedRandomScaling(RandomSource random) {
        int scaling1Weight = MantarayConfig.get().scalingSmallVariant.get();
        int scaling2Weight = MantarayConfig.get().scalingNormalWeight.get();
        int scaling3Weight = MantarayConfig.get().scalingLargeVariant.get();
        int scaling4Weight = MantarayConfig.get().scalingExtraLargeWeight.get();
        if (scaling1Weight <= 0 && scaling2Weight <= 0 && scaling3Weight <= 0 && scaling4Weight <= 0) {
            return 0;
        }
        Optional<WeightedMobScaling> scaling = WeightedRandom.getRandomItem(random,
                ImmutableList.of(new WeightedMobScaling(Math.max(0, scaling1Weight), (byte) -2),
                        new WeightedMobScaling(Math.max(0, scaling2Weight), (byte) 0),
                        new WeightedMobScaling(Math.max(0, scaling3Weight), (byte) 2),
                        new WeightedMobScaling(Math.max(0, scaling4Weight), (byte) 6)));
        return scaling.get().scaling;
    }

    public static boolean checkMantaraySpawnRules(EntityType<MantarayEntity> entity, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getFluidState(pos).is(LivingThingsTags.MANTARAY_SPAWNABLE_ON) && world.getFluidState(pos.above()).is(LivingThingsTags.MANTARAY_SPAWNABLE_ON);
    }

    @Override
    public void tick() {
        super.tick();
        // random moving when on land
        if (!this.isInWaterRainOrBubble()) {
            if (this.onGround) {
                this.setDeltaMovement(this.getDeltaMovement().add(((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.3D, ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                this.setYRot(this.random.nextFloat() * 360.0F);
                this.onGround = false;
                this.hasImpulse = true;
            }
        }
    }

    @Override
    public boolean canBeLeashed(Player player) {
        return true;
    }

    @Override
    public boolean removeWhenFarAway(double distanceToClosestPlayer) {
        return false;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return MantarayConfig.maxSpawnedInChunk();
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return size.height * 0.5F;
    }

    @Override
    protected InteractionResult mobInteract(Player player, InteractionHand hand) {
        return InteractionResult.PASS;
    }

    @Override
    public byte getVariant() {
        return this.entityData.get(MANTARAY_VARIANT);
    }

    @Override
    public void setVariant(byte variant) {
        this.entityData.set(MANTARAY_VARIANT, variant);
    }

    @Override
    public byte getScaling() {
        return this.entityData.get(MANTARAY_SCALING);
    }

    @Override
    public void setScaling(byte scaling) {
        this.entityData.set(MANTARAY_SCALING, scaling);
    }

    @Override
    public ItemStack getBucketItemStack() {
        // required by AbstractFishEntity
        // not used, because we are overriding the rightclick method
        return new ItemStack(Items.BUCKET);
    }

    @Override
    protected SoundEvent getFlopSound() {
        // required by AbstractFishEntity
        return null;
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.MANTARAY;
    }

    static class SwimGoal extends RandomSwimmingGoal {
        private final MantarayEntity fish;

        public SwimGoal(MantarayEntity fish) {
            super(fish, 1.0D, 40);
            this.fish = fish;
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state
         * necessary for execution in this method as well.
         */
        @Override
        public boolean canUse() {
            return this.fish.canRandomSwim() && super.canUse();
        }
    }

}
