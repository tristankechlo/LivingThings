package com.tristankechlo.livingthings.entities;

import java.util.List;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class NetherKnightEntity extends MonsterEntity implements ILexiconEntry {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"hostile_mobs/nether_knight");

	public NetherKnightEntity(EntityType<? extends MonsterEntity> type, World world) {
		super(type, world);
		this.setPathfindingMalus(PathNodeType.WATER, -1.0F);
		this.setPathfindingMalus(PathNodeType.LAVA, 0.0F);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, 0.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, 0.0F);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.NETHER_KNIGHT.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.NETHER_KNIGHT.speed.get())
				.add(Attributes.FOLLOW_RANGE, 48.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.NETHER_KNIGHT.damage.get());
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficultyInstance,
			SpawnReason spawnReason, ILivingEntityData data, CompoundNBT nbt) {
		this.setCanPickUpLoot(false);
		this.setLeftHanded(world.getRandom().nextBoolean());
		this.populateDefaultEquipmentSlots(difficultyInstance);
		return super.finalizeSpawn(world, difficultyInstance, spawnReason, data, nbt);
	}

	@Override
	protected void populateDefaultEquipmentSlots(DifficultyInstance p_180481_1_) {
		EquipmentSlotType first = this.random.nextBoolean() ? EquipmentSlotType.MAINHAND : EquipmentSlotType.OFFHAND;
		EquipmentSlotType second = first == EquipmentSlotType.MAINHAND ? EquipmentSlotType.OFFHAND
				: EquipmentSlotType.MAINHAND;
		this.setItemSlot(first, createMainHandItem());
		this.setItemSlot(second, createOffHandItem());
	}

	@Override
	protected void dropEquipment() {
		ItemStack mainHand = this.getMainHandItem();
		if (mainHand != null) {
			mainHand.setDamageValue(500 + random.nextInt(500));
			this.spawnAtLocation(mainHand);
		}
		ItemStack offHand = this.getOffhandItem();
		if (offHand != null) {
			offHand.setDamageValue(500 + random.nextInt(500));
			this.spawnAtLocation(offHand);
		}
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(5, new BetterMeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers(NetherKnightEntity.class));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, true));
	}

	@Override
	public CreatureAttribute getMobType() {
		return CreatureAttribute.UNDEAD;
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
		stack.setHoverName(new StringTextComponent(name));
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
		stack.setHoverName(new StringTextComponent(name));
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
	public boolean canStandOnFluid(Fluid fluid) {
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
			ISelectionContext iselectioncontext = ISelectionContext.of(this);
			if (iselectioncontext.isAbove(FlowingFluidBlock.STABLE_SHAPE, this.blockPosition(), true)
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
				((LivingEntity) entity).addEffect(new EffectInstance(Effects.WITHER, 100));
			}
			return true;
		}
	}

	@Override
	public boolean hurt(DamageSource source, float damage) {
		return super.hurt(source, damage);
	}

	@Override
	public boolean canBeAffected(EffectInstance effect) {
		return (effect.getEffect() == Effects.WITHER) ? false : super.canBeAffected(effect);
	}

}
