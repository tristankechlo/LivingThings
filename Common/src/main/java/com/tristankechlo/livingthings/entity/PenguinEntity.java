package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.init.ModEntities;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class PenguinEntity extends Animal {

    private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH);

    public PenguinEntity(EntityType<? extends PenguinEntity> entityType, Level worldIn) {
        super(entityType, worldIn);
    }

    public static boolean checkPenguinSpawnRules(EntityType<? extends Mob> animal, ServerLevelAccessor world, MobSpawnType reason, BlockPos pos, RandomSource random) {
        return world.getBlockState(pos.below()).is(LivingThingsTags.PENGUIN_SPAWNABLE_ON) && isBrightEnoughToSpawn(world, pos);
    }

    @Override
    public AgeableMob getBreedOffspring(ServerLevel worldIn, AgeableMob parent) {
        return ModEntities.PENGUIN.get().create(worldIn);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.25);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PolarBear.class, 8.0F, 1.0D, 1.2D));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, PenguinEntity.class, 6.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return TEMPTATION_ITEMS.test(stack);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    @Override
    protected float getWaterSlowDown() {
        return 0.98F;
    }

    @Override
    public int getAmbientSoundInterval() {
        return 180;
    }

    @Override
    protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
        return this.isBaby() ? 0.6F : 1.3F;
    }

    @Override
    public int getMaxSpawnClusterSize() {
        //return LivingThingsConfig.PENGUIN.maxSpawnedInChunk.get();
        return 9;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        //return ModSounds.PENGUIN_AMBIENT.get();
        return super.getAmbientSound();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        //return ModSounds.PENGUIN_HURT.get();
        return super.getHurtSound(damageSourceIn);
    }

    @Override
    protected SoundEvent getDeathSound() {
        //return ModSounds.PENGUIN_DEATH.get();
        return super.getDeathSound();
    }

}
