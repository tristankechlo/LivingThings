package com.tristankechlo.livingthings.entity;

import com.tristankechlo.livingthings.config.entity.NetherKnightConfig;
import com.tristankechlo.livingthings.entity.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.LexiconEntries;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.shapes.CollisionContext;

import java.util.List;

public class NetherKnightEntity extends Monster implements ILexiconEntry {

    public NetherKnightEntity(EntityType<? extends Monster> type, Level world) {
        super(type, world);
        this.setPathfindingMalus(PathType.WATER, -1.0F);
        this.setPathfindingMalus(PathType.LAVA, 0.0F);
        this.setPathfindingMalus(PathType.DANGER_FIRE, 0.0F);
        this.setPathfindingMalus(PathType.DAMAGE_FIRE, 0.0F);
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, NetherKnightConfig.health())
                .add(Attributes.MOVEMENT_SPEED, NetherKnightConfig.movementSpeed())
                .add(Attributes.FOLLOW_RANGE, 48.0D)
                .add(Attributes.ATTACK_DAMAGE, NetherKnightConfig.attackDamage());
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficultyInstance, MobSpawnType spawnReason, SpawnGroupData data) {
        this.setCanPickUpLoot(false);
        this.setLeftHanded(world.getRandom().nextBoolean());
        this.populateDefaultEquipmentSlots(random, difficultyInstance);
        return super.finalizeSpawn(world, difficultyInstance, spawnReason, data);
    }

    @Override
    protected void populateDefaultEquipmentSlots(RandomSource random, DifficultyInstance $$1) {
        EquipmentSlot first = random.nextBoolean() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
        EquipmentSlot second = (first == EquipmentSlot.MAINHAND) ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
        this.setItemSlot(first, createMainHandItem(random));
        this.setItemSlot(second, createOffHandItem(random));
    }

    @Override
    protected void dropEquipment() {
        final double dropChance = NetherKnightConfig.weaponDropChance() / 100.0D;
        ItemStack mainHand = this.getMainHandItem();
        if (mainHand != null && random.nextDouble() < dropChance) {
            mainHand.setDamageValue(500 + random.nextInt(500));
            this.spawnAtLocation(mainHand);
        }
        ItemStack offHand = this.getOffhandItem();
        if (offHand != null && random.nextDouble() < dropChance) {
            offHand.setDamageValue(500 + random.nextInt(500));
            this.spawnAtLocation(offHand);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(5, new BetterMeleeAttackGoal(this, 1.2D, false, NetherKnightConfig::canAttack));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(NetherKnightEntity.class));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    private ItemStack createMainHandItem(RandomSource random) {
        ItemStack stack = new ItemStack(Items.NETHERITE_SWORD);
        List<? extends String> names = NetherKnightConfig.get().swordNames.get();
        String name = names.get(random.nextInt(names.size()));
        if (random.nextInt(1000) == 0) {
            name = "Buecher_wurm's Butter Knife";
            stack.enchant(Enchantments.SHARPNESS, 4 + random.nextInt(6));
            stack.enchant(Enchantments.FIRE_ASPECT, 1 + random.nextInt(2));
            stack.enchant(Enchantments.KNOCKBACK, 1 + random.nextInt(2));
            stack.enchant(Enchantments.UNBREAKING, 1 + random.nextInt(3));
        } else {
            stack.enchant(Enchantments.SHARPNESS, 2 + random.nextInt(3));
        }
        stack.enchant(Enchantments.LOOTING, 1);
        stack.set(DataComponents.CUSTOM_NAME, Component.literal(name));
        return stack;
    }

    private ItemStack createOffHandItem(RandomSource random) {
        ItemStack stack = new ItemStack(Items.NETHERITE_AXE);
        List<? extends String> names = NetherKnightConfig.get().axeNames.get();
        String name = names.get(random.nextInt(names.size()));
        if (random.nextInt(1000) == 0) {
            name = "Buecher_wurm's War Axe";
            stack.enchant(Enchantments.MENDING, 1);
            stack.enchant(Enchantments.EFFICIENCY, 3 + random.nextInt(3));
        } else {
            stack.enchant(Enchantments.EFFICIENCY, 1 + random.nextInt(3));
        }
        stack.enchant(Enchantments.SHARPNESS, 2);
        stack.set(DataComponents.CUSTOM_NAME, Component.literal(name));
        return stack;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.NETHER_KNIGHT_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.NETHER_KNIGHT_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.NETHER_KNIGHT_DEATH.get();
    }

    @Override
    protected void playStepSound(BlockPos p_180429_1_, BlockState p_180429_2_) {
        this.playSound(ModSounds.NETHER_KNIGHT_STEP.get(), 0.15F, 1.0F);
    }

    @Override
    public boolean isSensitiveToWater() {
        return false;
    }

    @Override
    public boolean canStandOnFluid(FluidState fluid) {
        return fluid.is(FluidTags.LAVA);
    }

    @Override
    public boolean isOnFire() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();
        // float in lava
        if (this.isInLava()) {
            CollisionContext iselectioncontext = CollisionContext.of(this);
            if (iselectioncontext.isAbove(LiquidBlock.STABLE_SHAPE, this.blockPosition(), true)
                    && !this.level().getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)) {
                this.setOnGround(true);
            } else {
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D).add(0.0D, 0.05D, 0.0D));
            }
        }
    }

    @Override
    protected void checkFallDamage(double p_184231_1_, boolean p_184231_3_, BlockState state, BlockPos pos) {
        // no fall damage on lava
        if (this.isInLava()) {
            this.fallDistance = 0.0F;
        } else {
            super.checkFallDamage(p_184231_1_, p_184231_3_, state, pos);
        }
    }

    @Override
    public boolean doHurtTarget(Entity entity) {
        if (!super.doHurtTarget(entity)) {
            return false;
        } else {
            if (entity instanceof LivingEntity) {
                ((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.WITHER, 100));
            }
            return true;
        }
    }

    @Override
    public boolean hurt(DamageSource source, float damage) {
        return super.hurt(source, damage);
    }

    @Override
    public boolean canBeAffected(MobEffectInstance effect) {
        return (effect.getEffect() == MobEffects.WITHER) ? false : super.canBeAffected(effect);
    }

    @Override
    public ResourceLocation getLexiconEntry() {
        return LexiconEntries.NETHER_KNIGHT;
    }

}
