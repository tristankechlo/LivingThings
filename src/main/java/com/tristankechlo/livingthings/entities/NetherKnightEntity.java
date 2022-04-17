package com.tristankechlo.livingthings.entities;

import java.util.List;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.SpawnGroupData;
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
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.shapes.CollisionContext;

public class NetherKnightEntity extends Monster implements ILexiconEntry {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"hostile_mobs/nether_knight");

	public NetherKnightEntity(EntityType<? extends Monster> type, Level world) {
		super(type, world);
		this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
		this.setPathfindingMalus(BlockPathTypes.LAVA, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.NETHER_KNIGHT.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.NETHER_KNIGHT.speed.get())
				.add(Attributes.FOLLOW_RANGE, 48.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.NETHER_KNIGHT.damage.get());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficultyInstance,
			MobSpawnType spawnReason, SpawnGroupData data, CompoundTag nbt) {
		this.setCanPickUpLoot(false);
		this.setLeftHanded(world.getRandom().nextBoolean());
		this.populateDefaultEquipmentSlots(difficultyInstance);
		return super.finalizeSpawn(world, difficultyInstance, spawnReason, data, nbt);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
		EquipmentSlot first = this.random.nextBoolean() ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
		EquipmentSlot second = first == EquipmentSlot.MAINHAND ? EquipmentSlot.OFFHAND : EquipmentSlot.MAINHAND;
		this.setItemSlot(first, createMainHandItem());
		this.setItemSlot(second, createOffHandItem());
	}

	@Override
	protected void dropEquipment() {
		final int dropChance = Math.max(1, LivingThingsConfig.NETHER_KNIGHT.weaponDropChance.get());
		ItemStack mainHand = this.getMainHandItem();
		if (mainHand != null && random.nextInt(dropChance) == 0) {
			mainHand.setDamageValue(500 + random.nextInt(500));
			this.spawnAtLocation(mainHand);
		}
		ItemStack offHand = this.getOffhandItem();
		if (offHand != null && random.nextInt(dropChance) == 0) {
			offHand.setDamageValue(500 + random.nextInt(500));
			this.spawnAtLocation(offHand);
		}
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new BetterMeleeAttackGoal(this, 1.2D, false, () -> {
			return LivingThingsConfig.NETHER_KNIGHT.canAttack.get();
		}));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(NetherKnightEntity.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEAD;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	private ItemStack createMainHandItem() {
		ItemStack stack = new ItemStack(Items.NETHERITE_SWORD);
		List<? extends String> names = LivingThingsConfig.NETHER_KNIGHT.swords.get();
		String name = names.get(random.nextInt(names.size()));
		if (random.nextInt(1000) == 0) {
			name = "Buecher_wurm's War Sword";
			stack.enchant(Enchantments.SHARPNESS, 4 + random.nextInt(6));
			stack.enchant(Enchantments.FIRE_ASPECT, 1 + random.nextInt(2));
			stack.enchant(Enchantments.KNOCKBACK, 1 + random.nextInt(2));
			stack.enchant(Enchantments.UNBREAKING, 1 + random.nextInt(3));
		} else {
			stack.enchant(Enchantments.SHARPNESS, 2 + random.nextInt(3));
		}
		stack.enchant(Enchantments.MOB_LOOTING, 1);
		stack.setHoverName(new TextComponent(name));
		return stack;
	}

	private ItemStack createOffHandItem() {
		ItemStack stack = new ItemStack(Items.NETHERITE_AXE);
		List<? extends String> names = LivingThingsConfig.NETHER_KNIGHT.axes.get();
		String name = names.get(this.random.nextInt(names.size()));
		if (this.random.nextInt(1000) == 0) {
			name = "Buecher_wurm's War Axe";
			stack.enchant(Enchantments.MENDING, 1);
			stack.enchant(Enchantments.BLOCK_EFFICIENCY, 3 + random.nextInt(3));
		} else {
			stack.enchant(Enchantments.BLOCK_EFFICIENCY, 1 + random.nextInt(3));
		}
		stack.enchant(Enchantments.SHARPNESS, 2);
		stack.setHoverName(new TextComponent(name));
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
					&& !this.level.getFluidState(this.blockPosition().above()).is(FluidTags.LAVA)) {
				this.onGround = true;
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

}
