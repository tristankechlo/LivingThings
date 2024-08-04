package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.entity.ElephantConfig;
import com.tristankechlo.livingthings.entity.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.LexiconEntries;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ChestMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;

import java.util.UUID;

public class ElephantEntity extends TamableAnimal implements NeutralMob, HasCustomInventoryScreen, ILexiconEntry {

    public static final int ANGER_TIME = 10;
    private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
    private static Component CONTAINER_NAME = null;
    private static final EntityDataAccessor<Boolean> IS_SADDLED = SynchedEntityData.defineId(ElephantEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> HAS_CHEST = SynchedEntityData.defineId(ElephantEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> REMAINING_ANGER_TIME = SynchedEntityData.defineId(ElephantEntity.class, EntityDataSerializers.INT);
    protected SimpleContainer entityInventory;
    private int tameAmount;
    private int attackTimer;
    private UUID angerTarget;

    public ElephantEntity(EntityType<ElephantEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
        //this.setMaxUpStep(1.0F);
        this.initInventory();
        this.tameAmount = 0;
    }

    public static boolean checkElephantSpawnRules(EntityType<ElephantEntity> animal, LevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.ELEPHANT_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IS_SADDLED, false);
        builder.define(HAS_CHEST, false);
        builder.define(REMAINING_ANGER_TIME, 0);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob parent) {
        ElephantEntity child = ModEntityTypes.ELEPHANT.get().create(this.level());
        if (this.isTame() || ((ElephantEntity) parent).isTame()) {
            child.setTame(true, false);
        }
        return child;
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, ElephantConfig.health())
                .add(Attributes.MOVEMENT_SPEED, ElephantConfig.movementSpeed())
                .add(Attributes.FOLLOW_RANGE, 16.0D)
                .add(Attributes.ATTACK_DAMAGE, ElephantConfig.attackDamage());
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false, ElephantConfig::canAttack));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.9D));
        this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(0, new ElephantEntity.NewHurtByTargetGoal(this));
        this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, true));
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.readPersistentAngerSaveData(this.level(), compound);
        this.setSaddled(compound.getBoolean("Saddled"));
        this.setHasChest(compound.getBoolean("Chested"));
        this.setTame(compound.getBoolean("Tamed"), false);
        this.tameAmount = compound.getInt("TameAmount");

        this.entityInventory.fromTag(compound.getList("Inventory", 10), this.registryAccess());
        this.initInventory();
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        this.addPersistentAngerSaveData(compound);
        compound.putBoolean("Saddled", this.isSaddled());
        compound.putBoolean("Chested", this.hasChest());
        compound.putBoolean("Tamed", this.isTame());
        compound.putInt("TameAmount", this.tameAmount);
        compound.put("Inventory", this.entityInventory.createTag(this.registryAccess()));
    }

    private void initInventory() {
        SimpleContainer inventory = this.entityInventory;
        this.entityInventory = new SimpleContainer(27);
        if (inventory == null) {
            return;
        }

        int invSize = Math.min(inventory.getContainerSize(), this.entityInventory.getContainerSize());
        for (int i = 0; i < invSize; ++i) {
            ItemStack itemstack = inventory.getItem(i);
            if (!itemstack.isEmpty()) {
                this.entityInventory.setItem(i, itemstack.copy());
            }
        }
    }

    private void openInventory(Player player) {
        if (CONTAINER_NAME == null) {
            CONTAINER_NAME = ModEntityTypes.ELEPHANT.get().getDescription();
        }
        // elephant inv is a generic chest
        player.openMenu(new SimpleMenuProvider((id, playerInv, playerIn) -> {
            return new ChestMenu(MenuType.GENERIC_9x3, id, player.getInventory(), this.entityInventory, 3);
        }, CONTAINER_NAME));
    }

    @Override
    public void openCustomInventoryScreen(Player player) {
        this.openInventory(player);
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return ElephantConfig.get().temptationItems.get().test(stack);
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        this.level().broadcastEntityEvent(this, (byte) 4);
        boolean flag = target.hurt(this.damageSources().mobAttack(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
        if (flag) {
            // throw target in the air
            target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.7D, 0.0D));
            this.doEnchantDamageEffects(this, target);
        }
        return flag;
    }

    @Override
    public void tick() {
        super.tick();
        if (this.attackTimer > 0) {
            --this.attackTimer;
        }
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!this.level().isClientSide) {
            this.updatePersistentAnger((ServerLevel) this.level(), true);
        }
    }

    @Override
    public int getMaxSpawnClusterSize() {
        return ElephantConfig.maxSpawnedInChunk();
    }

    @Override
    protected Vec3 getPassengerAttachmentPoint(Entity rider, EntityDimensions dimensions, float scale) {
        return new Vec3(0.0F, dimensions.height() * 0.975F * scale, 0.0F);
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.ELEPHANT_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.ELEPHANT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.ELEPHANT_DEATH.get();
    }

    @Override
    public void handleEntityEvent(byte id) {
        switch (id) {
            case 4 -> // start attack animation
                    this.attackTimer = ANGER_TIME;
            case 6 -> { // entity tamed
                this.spawnParticle(ParticleTypes.ENCHANTED_HIT);
                this.spawnParticle(ParticleTypes.FIREWORK);
            }
            case 7 -> // progress while taming
                    this.spawnParticle(ParticleTypes.COMPOSTER);
            default -> super.handleEntityEvent(id);
        }
    }

    private void spawnParticle(ParticleOptions particle) {
        if (particle != null) {
            for (int i = 0; i < 10; ++i) {
                double d0 = this.random.nextGaussian() * 0.03D;
                double d1 = this.random.nextGaussian() * 0.03D;
                double d2 = this.random.nextGaussian() * 0.03D;
                this.level().addParticle(particle, this.getRandomX(1.5D), this.getRandomY() + 0.5D, this.getRandomZ(1.5D), d0, d1, d2);
            }
        }
    }

    private void doPlayerRide(Player player) {
        if (!this.level().isClientSide()) {
            player.setYRot(this.getYRot());
            player.setXRot(this.getXRot());
            player.startRiding(this);
        }
    }

    @Override
    protected boolean isImmobile() {
        return super.isImmobile() && this.isVehicle();
    }

    @Override
    protected void dropEquipment() {
        super.dropEquipment();
        if (this.entityInventory != null) {
            for (int i = 0; i < this.entityInventory.getContainerSize(); ++i) {
                ItemStack itemstack = this.entityInventory.getItem(i);
                if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
                    this.spawnAtLocation(itemstack);
                }
            }
        }
        if (this.isSaddled() && this.random.nextBoolean()) {
            this.spawnAtLocation(Items.SADDLE);
        }
        if (this.hasChest() && this.random.nextBoolean()) {
            this.spawnAtLocation(Items.CHEST);
        }
    }

    @Override
    public void travel(Vec3 travelVector) {
        if (this.isAlive()) {
            if (this.isVehicle() && this.isControlledByLocalInstance() && this.isSaddled()) {
                LivingEntity livingentity = this.getControllingPassenger();
                this.setYRot(livingentity.getYRot());
                this.yRotO = this.getYRot();
                this.setXRot(livingentity.getXRot() * 0.5F);
                this.setRot(this.getYRot(), this.getXRot());
                this.yBodyRot = this.getYRot();
                this.yHeadRot = this.yBodyRot;
                float sideSpeed = livingentity.xxa * 0.4F;
                float forwardSpeed = livingentity.zza * 0.7F;

                // if moving backwards -> move slower
                if (forwardSpeed <= 0.0F) {
                    forwardSpeed *= 0.2F;
                }

                if (this.isControlledByLocalInstance()) {
                    this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
                    super.travel(new Vec3(sideSpeed, travelVector.y, forwardSpeed));
                } else if (livingentity instanceof Player) {
                    this.setDeltaMovement(Vec3.ZERO);
                }

                this.calculateEntityAnimation(false);
            } else {
                super.travel(travelVector);
            }
        }
    }

    public boolean isTamingItem(ItemStack stack) {
        return ElephantConfig.get().tamingItems.get().test(stack);
    }

    public LivingEntity getControllingPassenger() {
        Entity passenger = this.getFirstPassenger();
        if (passenger instanceof Player) {
            return (Player) passenger;
        }
        return null;
    }

    public boolean isSaddled() {
        return this.entityData.get(IS_SADDLED);
    }

    public void setSaddled(boolean saddled) {
        this.entityData.set(IS_SADDLED, saddled);
    }

    public boolean hasChest() {
        return this.entityData.get(HAS_CHEST);
    }

    public void setHasChest(boolean chested) {
        this.entityData.set(HAS_CHEST, chested);
    }

    public int getAttackTimer() {
        return this.attackTimer;
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
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getMainHandItem();

        if (stack.is(ModItems.LEXICON.get())) {
            return InteractionResult.PASS; // prevent any use when item is lexicon
        }

        if (stack.isEmpty() && this.isTame() && !this.isBaby()) {

            if (player.isCrouching() && this.hasChest()) {
                this.openInventory(player);
            } else if (this.getPassengers().isEmpty() && this.isSaddled()) {
                this.doPlayerRide(player);
            }
            return InteractionResult.sidedSuccess(this.level().isClientSide());

        } else if (this.isFood(stack)) {

            if (this.isBaby()) { // age up
                int age = this.getAge();
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                this.ageUp((int) ((float) (-age / 20) * 0.1F), true);
                return InteractionResult.sidedSuccess(this.level().isClientSide());
            }

            if (this.isTame()) {
                if (this.getHealth() < this.getMaxHealth()) { // if needs health
                    if (!this.level().isClientSide()) {
                        float healAmount = 3.0F;
                        this.heal(healAmount);
                        if (!player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }
                        return InteractionResult.SUCCESS;
                    }
                } else { // if already full health
                    if (!this.level().isClientSide() && !this.isBaby() && this.canBreed()) {
                        if (!player.getAbilities().instabuild) {
                            stack.shrink(1);
                        }
                        this.setInLove(player);
                        return InteractionResult.SUCCESS;
                    }
                }
            }

        } else if (this.isTamingItem(stack) && !this.isBaby() && !this.isTame()) {

            // progress taming
            if (!this.level().isClientSide()) {
                this.tameAmount += 200;
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                // mark as tamed if taming amount is reached
                if (this.tameAmount >= 1000) {
                    this.setTame(true, true);
                    this.setOwnerUUID(player.getUUID());
                    if (player instanceof ServerPlayer) {
                        CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayer) player, this);
                    }
                    this.level().broadcastEntityEvent(this, (byte) 6);
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 7);
                }
            }
            return InteractionResult.SUCCESS;

        } else if (this.isTame() && stack.getItem() == Items.SADDLE && !this.isBaby()) {

            // saddle entity
            if (!this.level().isClientSide() && !this.isSaddled()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                this.setSaddled(true);
                this.playSound(ModSounds.ELEPHANT_EQUIP_SADDLE.get(), 0.9F, 0.9F);
                return InteractionResult.SUCCESS;
            }

        } else if (this.isTame() && this.isSaddled() && stack.getItem() == Items.CHEST && !this.isBaby()) {

            // add chest to entity
            if (!this.level().isClientSide() && !this.hasChest()) {
                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }
                this.setHasChest(true);
                this.playSound(ModSounds.ELEPHANT_EQUIP_CHEST.get(), 0.9F, 0.9F);
                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    public boolean canBreed() {
        return true;
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.ELEPHANT;
    }

    private static class NewHurtByTargetGoal extends HurtByTargetGoal {

        public NewHurtByTargetGoal(PathfinderMob creatureIn) {
            super(creatureIn);
        }

        @Override
        protected boolean canAttack(LivingEntity entity, TargetingConditions conditions) {
            if (entity instanceof Player) {
                UUID ownerID = ((ElephantEntity) this.mob).getOwnerUUID();
                if (ownerID != null && entity.getUUID().equals(ownerID)) {
                    return false;
                }
            }
            return super.canAttack(entity, conditions);
        }

    }

}
