package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.GeneralConfig;
import com.tristankechlo.livingthings.config.entity.BabyEnderDragonConfig;
import com.tristankechlo.livingthings.entity.ai.CustomSitWhenOrderedToSitGoal;
import com.tristankechlo.livingthings.entity.misc.CustomDragonFireball;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.LexiconEntries;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class BabyEnderDragonEntity extends TamableAnimal implements NeutralMob, RangedAttackMob, FlyingAnimal, ILexiconEntry {

    private static final EntityDataAccessor<Integer> COLLAR_COLOR = SynchedEntityData.defineId(BabyEnderDragonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> REMAINING_ANGER_TIME = SynchedEntityData.defineId(BabyEnderDragonEntity.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> SITTING = SynchedEntityData.defineId(BabyEnderDragonEntity.class, EntityDataSerializers.BOOLEAN);
    private static final UniformInt PERSISTENT_ANGER_TIME = TimeUtil.rangeOfSeconds(20, 39);
    private UUID persistentAngerTarget;

    public BabyEnderDragonEntity(EntityType<? extends BabyEnderDragonEntity> entity, Level level) {
        super(entity, level);
        this.setTame(false, false);
        this.moveControl = new FlyingMoveControl(this, 10, true);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new CustomSitWhenOrderedToSitGoal(this));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, BabyEnderDragonConfig.temptationItems(), false));
        this.goalSelector.addGoal(4, new RangedAttackGoal(this, 1.1D, 120, 240, 25.0F));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.3D, 10.0F, 3.0F)); // TODO check what boolean was for
        this.goalSelector.addGoal(8, new WaterAvoidingRandomFlyingGoal(this, 1.2D));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
        this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
        this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setAlertOthers());
        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, false, null));
        this.targetSelector.addGoal(8, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(COLLAR_COLOR, DyeColor.RED.getId());
        builder.define(REMAINING_ANGER_TIME, 0);
        builder.define(SITTING, false);
    }

    @Override
    protected void customServerAiStep() {
        // slow falling
        if (!this.onGround() && this.getDeltaMovement().y < 0.0D) {
            this.setDeltaMovement(this.getDeltaMovement().multiply(1.0D, 0.6D, 1.0D));
        }
        // float down when ordered to sit
        if (this.navigation.isDone() && this.isOrderedToSit()) {
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05D, 0));
        }
        super.customServerAiStep();
    }

    @Override
    protected PathNavigation createNavigation(Level level) {
        FlyingPathNavigation navigation = new FlyingPathNavigation(this, level);
        navigation.setCanFloat(true);
        navigation.setCanOpenDoors(true);
        navigation.setCanPassDoors(true);
        return navigation;
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        Item item = itemstack.getItem();
        if (this.level().isClientSide) {
            if (item == ModItems.LEXICON.get()) {
                return InteractionResult.PASS;
            }
            boolean flag = this.isOwnedBy(player) || this.isTame() || isFood(itemstack) && !this.isTame() && !this.isAngry();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                if (this.isFood(itemstack) && itemstack.has(DataComponents.FOOD) && this.getHealth() < this.getMaxHealth()) {
                    if (!player.getAbilities().instabuild) {
                        itemstack.shrink(1);
                    }
                    this.heal((float) (itemstack.get(DataComponents.FOOD).nutrition() / 2));
                    this.gameEvent(GameEvent.ENTITY_INTERACT, this);
                    return InteractionResult.SUCCESS;
                }
                if (this.isOwnedBy(player) && (item instanceof DyeItem)) {
                    DyeColor dyecolor = ((DyeItem) item).getDyeColor();
                    if (dyecolor != this.getCollarColor()) {
                        this.setCollarColor(dyecolor);
                        if (!player.getAbilities().instabuild) {
                            itemstack.shrink(1);
                        }
                        return InteractionResult.SUCCESS;
                    }
                }
                if (itemstack.isEmpty() && this.isOwnedBy(player)) {
                    this.setOrderedToSit(!this.isOrderedToSit());
                    this.jumping = false;
                    this.navigation.stop();
                    this.setTarget(null);
                    return InteractionResult.SUCCESS;
                }
            } else if (isFood(itemstack) && !this.isAngry()) {
                if (!player.getAbilities().instabuild) {
                    itemstack.shrink(1);
                }
                if (this.random.nextInt(5) == 0) {
                    this.tame(player);
                    this.navigation.stop();
                    this.setTarget(null);
                    this.setOrderedToSit(true);
                    this.level().broadcastEntityEvent(this, (byte) 7);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6);
                }
                return InteractionResult.SUCCESS;
            }
            return InteractionResult.PASS;
        }
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return BabyEnderDragonConfig.temptationItems().test(stack);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag nbt) {
        super.readAdditionalSaveData(nbt);
        this.setCollarColor(DyeColor.byId(nbt.getInt("CollarColor")));
        this.setOrderedToSit(nbt.getBoolean("Sitting"));
        this.readPersistentAngerSaveData(level(), nbt);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag nbt) {
        super.addAdditionalSaveData(nbt);
        nbt.putShort("CollarColor", (short) this.getCollarColor().getId());
        nbt.putBoolean("Sitting", this.isOrderedToSit());
        this.addPersistentAngerSaveData(nbt);
    }

    @Override
    public void setOrderedToSit(boolean sitting) {
        super.setOrderedToSit(sitting);
        this.entityData.set(SITTING, sitting);
    }

    @Override
    public boolean isOrderedToSit() {
        return this.entityData.get(SITTING);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, BabyEnderDragonConfig.health())
                .add(Attributes.MOVEMENT_SPEED, BabyEnderDragonConfig.movementSpeed())
                .add(Attributes.FLYING_SPEED, BabyEnderDragonConfig.flyingSpeed());
    }

    public static boolean checkBabyEnderDragonSpawnRules(EntityType<BabyEnderDragonEntity> entityType, LevelAccessor level, MobSpawnType spawnReason, BlockPos pos, RandomSource random) {
        return level.getBlockState(pos.below()).is(LivingThingsTags.BABY_ENDER_DRAGON_SPAWNABLE_ON);
    }

    public DyeColor getCollarColor() {
        return DyeColor.byId(this.entityData.get(COLLAR_COLOR));
    }

    public void setCollarColor(DyeColor dyeColor) {
        this.entityData.set(COLLAR_COLOR, dyeColor.getId());
    }

    @Override
    public int getRemainingPersistentAngerTime() {
        return this.entityData.get(REMAINING_ANGER_TIME);
    }

    @Override
    public void setRemainingPersistentAngerTime(int time) {
        this.entityData.set(REMAINING_ANGER_TIME, time);
    }

    @Override
    public void startPersistentAngerTimer() {
        this.setRemainingPersistentAngerTime(PERSISTENT_ANGER_TIME.sample(this.random));
    }

    @Override
    public UUID getPersistentAngerTarget() {
        return this.persistentAngerTarget;
    }

    @Override
    public void setPersistentAngerTarget(UUID uuid) {
        this.persistentAngerTarget = uuid;
    }

    @Override
    public boolean canMate(Animal animal) {
        return false;
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
        BabyEnderDragonEntity child = ModEntityTypes.BABY_ENDER_DRAGON.get().create(world);
        UUID uuid = this.getOwnerUUID();
        if (uuid != null) {
            child.setOwnerUUID(uuid);
            child.setTame(true, false);
        }
        return child;
    }

    @Override
    public void performRangedAttack(LivingEntity entity, float distanceFactor) {
        // don't attack if disabled in config
        boolean peaceful = (this.level().getDifficulty() == Difficulty.PEACEFUL);
        boolean ambientMode = GeneralConfig.get().ambientMode.get();
        if (peaceful || ambientMode || !BabyEnderDragonConfig.canAttack()) {
            return;
        }
        if (!this.canAttack(entity)) { // do not attack creative,... players
            return;
        }
        Vec3 vec = this.getViewVector(1.0F);
        double d1 = this.getX() - vec.x;
        double d2 = this.getY(0.5);
        double d3 = this.getZ() - vec.z;
        double d4 = entity.getX() - d1;
        double d5 = entity.getY(0.5) - d2;
        double d6 = entity.getZ() - d3;
        CustomDragonFireball dragonfireball = new CustomDragonFireball(this.level(), this, d4, d5, d6);
        dragonfireball.moveTo(d1, d2, d3, 0.0F, 0.0F);
        this.level().addFreshEntity(dragonfireball);
        if (!this.level().isClientSide() && !this.isSilent()) {
            this.level().playSound(null, this.blockPosition(), ModSounds.BABY_ENDER_DRAGON_SHOOT.get(),
                    SoundSource.HOSTILE, 2.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
        }
    }

    @Override
    public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
        return false;
    }

    @Override
    protected void checkFallDamage(double p_20990_, boolean p_20991_, BlockState p_20992_, BlockPos p_20993_) {
    }

    @Override
    public boolean isFlying() {
        return !this.onGround();
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return BabyEnderDragonConfig.maxSpawnedInChunk();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.BABY_ENDER_DRAGON_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource p_21239_) {
        return ModSounds.BABY_ENDER_DRAGON_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.BABY_ENDER_DRAGON_DEATH.get();
    }

    @Override
    public int getAmbientSoundInterval() {
        return 300;
    }

    @Override
    protected void onFlap() {
        this.level().playSound(null, this.blockPosition(), ModSounds.BABY_ENDER_DRAGON_FLAP.get(), SoundSource.AMBIENT,
                2.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        Entity target = source.getEntity();
        if ((target instanceof LivingEntity) && !this.isOwnedBy((LivingEntity) target)) {
            this.setOrderedToSit(false);
            this.setTarget((LivingEntity) target);
        }
        return super.hurt(source, damage);
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.BABY_ENDER_DRAGON;
    }

}
