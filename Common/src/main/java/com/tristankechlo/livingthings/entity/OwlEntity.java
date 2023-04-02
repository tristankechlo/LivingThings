package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.entity.OwlConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.IMobVariants;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class OwlEntity extends TamableAnimal implements FlyingAnimal, IMobVariants {

    private static final EntityDataAccessor<Byte> OWL_VARIANT = SynchedEntityData.defineId(OwlEntity.class, EntityDataSerializers.BYTE);
    public float flap;
    public float flapSpeed;
    public float oFlapSpeed;
    public float oFlap;
    private float flapping = 1.0F;

    public OwlEntity(EntityType<? extends OwlEntity> type, Level worldIn) {
        super(type, worldIn);
        this.moveControl = new FlyingMoveControl(this, 10, false);
        this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
        this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        OwlEntity child = ModEntityTypes.OWL.get().create(world);
        child.setVariant(this.getVariantFromParents(this, entity));
        return child;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, OwlConfig.health())
                .add(Attributes.MOVEMENT_SPEED, OwlConfig.movementSpeed())
                .add(Attributes.FLYING_SPEED, OwlConfig.flyingSpeed());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn, MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
        int colorBrownWeight = OwlConfig.get().colorBrownWeight.get();
        int colorWhiteWeight = OwlConfig.get().colorWhiteWeight.get();
        int colorBlackWeight = OwlConfig.get().colorBlackWeight.get();
        byte variant = this.getRandomVariant(random, new byte[]{0, 1, 2}, new int[]{colorBrownWeight, colorWhiteWeight, colorBlackWeight});
        this.setVariant(variant);
        return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, OwlConfig.temptationItems(), false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
        this.goalSelector.addGoal(8, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(OWL_VARIANT, (byte) 0);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        if (compound.contains("OwlVariant")) {
            this.setVariant(compound.getByte("OwlVariant"));
        } else {
            this.setVariant((byte) 0);
        }
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putByte("OwlVariant", this.getVariant());
    }

    @Override
    public void aiStep() {
        super.aiStep();
        this.calculateFlapping();
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!this.isTame() && OwlConfig.tamingItems().test(stack)) {
            if (!this.level.isClientSide()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                if (this.random.nextInt(5) == 0) {
                    this.tame(player);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte) 6);
                }
            }
            return InteractionResult.sidedSuccess(this.level.isClientSide());

        } else if (!this.isFlying() && this.isTame() && this.isOwnedBy(player) && OwlConfig.tamingItems().test(stack)) {

            this.setOrderedToSit(!this.isOrderedToSit());
            return InteractionResult.sidedSuccess(this.level.isClientSide());

        } else {
            return super.mobInteract(player, hand);
        }
    }

    private void calculateFlapping() {
        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = (float) ((double) this.flapSpeed + (double) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping = (float) ((double) this.flapping * 0.9D);
        Vec3 vector3d = this.getDeltaMovement();
        if (!this.onGround && vector3d.y < 0.0D) {
            this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
        }

        this.flap += this.flapping * 2.0F;
    }

    public static boolean checkOwlSpawnRules(EntityType<OwlEntity> animal, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.OWL_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    protected PathNavigation createNavigation(Level worldIn) {
        FlyingPathNavigation navigation = new FlyingPathNavigation(this, worldIn);
        navigation.setCanOpenDoors(false);
        navigation.setCanFloat(true);
        navigation.setCanPassDoors(true);
        return navigation;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return OwlConfig.temptationItems().test(stack);
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return OwlConfig.maxSpawnedInChunk();
    }

    @Override
    protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
        return this.isBaby() ? 0.45F : 0.9F;
    }

    @Override
    public boolean causeFallDamage(float p_149683_, float p_149684_, DamageSource p_149685_) {
        return false;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.OWL_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return ModSounds.OWL_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.OWL_DEATH.get();
    }

    @Override
    protected void onFlap() {
        this.playSound(ModSounds.OWL_FLY.get(), 0.15F, 1.0F);
    }
    /*
     * removed in 1.17.1?
     *
     * @Override protected boolean makeFlySound() { return true; }
     */

    @Override
    protected void doPush(Entity entityIn) {
        if (!(entityIn instanceof Player)) {
            super.doPush(entityIn);
        }
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (this.isInvulnerableTo(source)) {
            return false;
        } else {
            this.setOrderedToSit(false);
            return super.hurt(source, amount);
        }
    }

    public boolean isFlying() {
        return !this.onGround;
    }

    @Override
    public byte getVariant() {
        return this.entityData.get(OWL_VARIANT);
    }

    @Override
    public void setVariant(byte variant) {
        this.entityData.set(OWL_VARIANT, variant);
    }

}
