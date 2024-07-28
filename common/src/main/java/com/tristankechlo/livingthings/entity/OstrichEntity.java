package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.block.OstrichNestBlock;
import com.tristankechlo.livingthings.config.entity.OstrichConfig;
import com.tristankechlo.livingthings.entity.ai.OstrichBreedGoal;
import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModEntityTypes;
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
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

public class OstrichEntity extends Animal implements ItemSteerable, ILexiconEntry {

    private static final EntityDataAccessor<Boolean> HAS_EGG = SynchedEntityData.defineId(OstrichEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_BUILDING_NEST = SynchedEntityData.defineId(OstrichEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> IS_LAYING_EGG = SynchedEntityData.defineId(OstrichEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> SADDLED = SynchedEntityData.defineId(OstrichEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> BOOST_TIME = SynchedEntityData.defineId(OstrichEntity.class, EntityDataSerializers.INT);
    private final ItemBasedSteering boostHelper = new ItemBasedSteering(this.entityData, BOOST_TIME, SADDLED);
    private int nestBuildingCounter;
    private int layingEggCounter;

    public OstrichEntity(EntityType<? extends OstrichEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public static boolean checkOstrichSpawnRules(EntityType<OstrichEntity> animal, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.OSTRICH_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob parent) {
        return ModEntityTypes.OSTRICH.get().create(world);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, OstrichConfig.health())
                .add(Attributes.MOVEMENT_SPEED, OstrichConfig.movementSpeed());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new OstrichBreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new OstrichEntity.LayEggGoal(this, 1.1D));
        this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, OstrichConfig.temptationItems(), false));
        this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.3D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, OstrichEntity.class, 8.0F));
        this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HAS_EGG, false);
        builder.define(IS_BUILDING_NEST, false);
        builder.define(IS_LAYING_EGG, false);
        builder.define(SADDLED, true);
        builder.define(BOOST_TIME, 0);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putBoolean("HasEgg", this.hasEgg());
        this.boostHelper.addAdditionalSaveData(compound);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.setHasEgg(compound.getBoolean("HasEgg"));
        this.boostHelper.readAdditionalSaveData(compound);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.isAlive() && this.isBuildingNest() && this.nestBuildingCounter >= 1 && this.nestBuildingCounter % 7 == 0) {
            BlockPos pos = this.blockPosition();
            this.level().levelEvent(2001, pos, Block.getId(Blocks.SAND.defaultBlockState()));
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return OstrichConfig.maxSpawnedInChunk();
    }

    @Override
    public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
        if (BOOST_TIME.equals(key) && this.level().isClientSide) {
            this.boostHelper.onSynced();
        }
        super.onSyncedDataUpdated(key);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return OstrichConfig.temptationItems().test(stack);
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.hasEgg()) {
            this.spawnAtLocation(ModItems.OSTRICH_EGG.get());
        }
    }

    public boolean isBuildingNest() {
        return this.entityData.get(IS_BUILDING_NEST);
    }

    public void setBuildingNest(boolean building) {
        this.nestBuildingCounter = building ? 1 : 0;
        this.entityData.set(IS_BUILDING_NEST, building);
    }

    public boolean isLayingEgg() {
        return this.entityData.get(IS_LAYING_EGG);
    }

    public void setLayingEgg(boolean layingEgg) {
        this.layingEggCounter = layingEgg ? 1 : 0;
        this.entityData.set(IS_LAYING_EGG, layingEgg);
    }

    public boolean hasEgg() {
        return this.entityData.get(HAS_EGG);
    }

    public void setHasEgg(boolean hasEgg) {
        this.entityData.set(HAS_EGG, hasEgg);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.OSTRICH_AMBIENT.get();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        boolean breedingItem = this.isFood(player.getItemInHand(hand));
        boolean isLexicon = player.getMainHandItem().getItem() == ModItems.LEXICON.get();
        if (!breedingItem && !isLexicon && !this.isVehicle() && !this.isBaby() && !player.isSecondaryUseActive()) {
            if (!this.level().isClientSide && OstrichConfig.canBeRidden()) {
                player.startRiding(this);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide);
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        if (passenger instanceof Player) {
            return (Player) passenger;
        }
        return null;
    }

    @Override
    public boolean boost() {
        return this.boostHelper.boost(this.getRandom());
    }

    @Override
    protected void tickRidden(Player player, Vec3 vec3) {
        super.tickRidden(player, vec3);
        this.setRot(player.getYRot(), player.getXRot() * 0.5F);
        this.yRotO = this.yBodyRot = this.yHeadRot = this.getYRot();
        this.boostHelper.tickBoost();
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity rider, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0F, dimensions.height() * 0.7F * scale, 0.0F);
    }

    @Override
    protected Vec3 getRiddenInput(Player player, Vec3 travelVec) {
        return new Vec3(0.0D, 0.0D, 1.0D);
    }

    @Override
    protected float getRiddenSpeed(Player player) {
        return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.9F;
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.OSTRICH;
    }

    private static class LayEggGoal extends MoveToBlockGoal {

        private final OstrichEntity ostrich;
        private boolean isAboveDestination;

        public LayEggGoal(OstrichEntity creature, double speedIn) {
            super(creature, speedIn, 16);
            this.ostrich = creature;
        }

        @Override
        public boolean canUse() {
            return (this.ostrich.hasEgg() || this.ostrich.isBuildingNest() || this.ostrich.isLayingEgg()) && super.canUse();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && (this.ostrich.hasEgg() || this.ostrich.isBuildingNest() || this.ostrich.isLayingEgg());
        }

        @Override
        protected void moveMobToBlock() {
            BlockPos blockpos = this.getMoveToTarget();
            Path path = this.mob.getNavigation().createPath(blockpos.getX() + 0.5D, blockpos.getY(), blockpos.getZ() + 0.5D, 0);
            this.mob.getNavigation().moveTo(path, this.speedModifier);
        }

        @Override
        protected BlockPos getMoveToTarget() {
            if (this.ostrich.level().getBlockState(this.blockPos).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
                return this.blockPos;
            }
            return this.blockPos.above();
        }

        @Override
        public void tick() {
            BlockPos blockpos = this.getMoveToTarget();
            if (!blockpos.closerToCenterThan(this.mob.position(), this.acceptedDistance())) {
                this.isAboveDestination = false;
                ++this.tryTicks;
                if (this.shouldRecalculatePath()) {
                    Path path = this.mob.getNavigation().createPath(blockpos.getX() + 0.5D, blockpos.getY(), blockpos.getZ() + 0.5D, 0);
                    this.mob.getNavigation().moveTo(path, this.speedModifier);
                }
            } else {
                this.isAboveDestination = true;
                --this.tryTicks;
            }
            if (!this.ostrich.isInWater() && this.isReachedTarget()) {
                Level world = this.ostrich.level();
                if (world.getBlockState(this.blockPos).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
                    BlockState state = world.getBlockState(this.blockPos);
                    if (!state.getValue(OstrichNestBlock.EGG)) {
                        // lay egg animation
                        if (this.ostrich.layingEggCounter < 1) {
                            this.ostrich.setLayingEgg(true);
                        } else if (this.ostrich.layingEggCounter > 150) {
                            world.playSound(null, this.blockPos, ModSounds.OSTRICH_EGG_LAYING.get(), SoundSource.BLOCKS, 0.5F, 0.9F);
                            world.setBlock(this.blockPos, state.setValue(OstrichNestBlock.EGG, true), 3);
                            this.ostrich.setHasEgg(false);
                            this.ostrich.setLayingEgg(false);
                        }
                        if (this.ostrich.isLayingEgg()) {
                            this.ostrich.layingEggCounter++;
                        }
                    }
                } else {
                    // nest building animation
                    if (this.ostrich.nestBuildingCounter < 1) {
                        this.ostrich.setBuildingNest(true);
                    } else if (this.ostrich.nestBuildingCounter > 100) {
                        world.playSound(null, this.blockPos, SoundEvents.LILY_PAD_PLACE, SoundSource.BLOCKS, 0.9F, 0.9F);
                        world.setBlock(this.blockPos.above(), ModBlocks.OSTRICH_NEST.get().defaultBlockState(), 3);
                        this.blockPos = this.blockPos.above();
                        this.ostrich.setBuildingNest(false);
                    }
                    if (ostrich.isBuildingNest()) {
                        this.ostrich.nestBuildingCounter++;
                    }
                }
            } else {
                this.ostrich.setLayingEgg(false);
                this.ostrich.setBuildingNest(false);
            }
        }

        @Override
        public void stop() {
            super.stop();
            this.ostrich.setLayingEgg(false);
            this.ostrich.setBuildingNest(false);
        }

        @Override
        protected boolean isReachedTarget() {
            return this.isAboveDestination;
        }

        @Override
        protected boolean isValidTarget(LevelReader worldIn, BlockPos pos) {
            if (worldIn.getBlockState(pos).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
                return !worldIn.getBlockState(pos).getValue(OstrichNestBlock.EGG);
            } else if (worldIn.getBlockState(pos).getBlock() == Blocks.SAND) {
                return worldIn.isEmptyBlock(pos.above());
            }
            return false;
        }

    }

}
