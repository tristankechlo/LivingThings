package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.entity.PeacockConfig;
import com.tristankechlo.livingthings.entity.ai.PeacockDestroyCropBlocks;
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
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

public class PeacockEntity extends Animal implements ILexiconEntry {

    private static final EntityDataAccessor<Boolean> PANIC = SynchedEntityData.defineId(PeacockEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> FLUFFED = SynchedEntityData.defineId(PeacockEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> DESTROYING_CROPS = SynchedEntityData.defineId(PeacockEntity.class, EntityDataSerializers.BOOLEAN);
    private static final UniformInt FLUFFED_TIME = TimeUtil.rangeOfSeconds(10, 60);
    private int fluffedTime;

    public PeacockEntity(EntityType<? extends Animal> type, Level level) {
        super(type, level);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, PeacockConfig.health())
                .add(Attributes.MOVEMENT_SPEED, PeacockConfig.movementSpeed());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PeacockPanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, PeacockConfig.temptationItems(), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new PeacockDestroyCropBlocks(this));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(PANIC, false);
        this.entityData.define(FLUFFED, false);
        this.entityData.define(DESTROYING_CROPS, false);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.entityData.set(FLUFFED, nbt.getBoolean("Fluffed"));
        this.fluffedTime = nbt.getInt("FluffedTime");
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putBoolean("Fluffed", this.entityData.get(FLUFFED));
        nbt.putInt("FluffedTime", this.fluffedTime);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return PeacockConfig.temptationItems().test(stack);
    }

    public static <T extends Animal> boolean checkPeacockSpawnRules(EntityType<T> entityType, ServerLevelAccessor world, MobSpawnType mobSpawnType, BlockPos pos, RandomSource randomSource) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.PEACOCK_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob parent) {
        return ModEntityTypes.PEACOCK.get().create(level);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return PeacockConfig.maxSpawnedInChunk();
    }

    public void setInPanic(boolean panic) {
        this.entityData.set(PANIC, panic);
    }

    public boolean isInPanic() {
        return this.entityData.get(PANIC) || this.getLastHurtByMob() != null || this.isFreezing() || this.isOnFire();
    }

    public boolean isTailFluffed() {
        return this.isInPanic() || this.entityData.get(FLUFFED);
    }

    public void startFluffing() {
        this.fluffedTime = FLUFFED_TIME.sample(this.random);
        this.entityData.set(FLUFFED, true);
    }

    @Override
    public void setInLove(Player player) {
        super.setInLove(player);
        // gets called when a player feeds the peacock, with its food
        this.entityData.set(FLUFFED, true);
    }

    @Override
    public void resetLove() {
        super.resetLove();
        // gets called when the peacock is finished mating
        this.startFluffing();
    }

    @Override
    public void aiStep() {
        super.aiStep();
        //spawn particles when eating
        if (this.isDestroyingCrops() && this.random.nextInt(5) == 0) {
            BlockPos pos = this.blockPosition();
            this.level().levelEvent(2001, pos, Block.getId(Blocks.FARMLAND.defaultBlockState()));
        }
        //if currently fluffed, count down and 'unfluff' when done
        if (this.fluffedTime > 0) {
            this.fluffedTime--;
            if (this.fluffedTime == 0) {
                this.entityData.set(FLUFFED, false);
                return;
            }
        }
        //if not fluffed, randomly start fluffing
        if (!this.entityData.get(FLUFFED) && this.random.nextInt(4000) == 0) {
            this.startFluffing();
        }
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.PEACOCK;
    }

    @Override
    public int getAmbientSoundInterval() {
        return PeacockConfig.get().talkInterval.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.PEACOCK_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.PEACOCK_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.PEACOCK_DEATH.get();
    }

    public boolean isDestroyingCrops() {
        return this.entityData.get(DESTROYING_CROPS);
    }

    public void setDestroyingCrops(boolean eating) {
        this.entityData.set(DESTROYING_CROPS, eating);
    }

    private static class PeacockPanicGoal extends PanicGoal {

        public PeacockPanicGoal(PathfinderMob mob, double speed) {
            super(mob, speed);
        }

        @Override
        public void start() {
            super.start();
            ((PeacockEntity) this.mob).setInPanic(true);
        }

        @Override
        public void stop() {
            super.stop();
            ((PeacockEntity) this.mob).setInPanic(false);
        }

    }

}
