package com.tristankechlo.livingthings.entities;

import java.util.UUID;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entities.misc.AbstractTameableChestedEntity;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ElephantEntity extends AbstractTameableChestedEntity implements IAngerable, ILexiconEntry {

	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.WHEAT);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/elephant");
	private static final RangedInteger rangedInteger = TickRangeConverter.rangeOfSeconds(20, 39);
	private int angerTime;
	private int attackTimer;
	private UUID angerTarget;

	public ElephantEntity(EntityType<? extends ElephantEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		this.maxUpStep = 1.0F;
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity parent) {
		ElephantEntity child = ModEntityTypes.ELEPHANT_ENTITY.get().create(this.level);
		if (this.isTame() || ((ElephantEntity) parent).isTame()) {
			child.setTame(true);
		}
		return child;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.ELEPHANT.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.ELEPHANT.speed.get())
				.add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.ELEPHANT.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false, () -> {
			return LivingThingsConfig.ELEPHANT.canAttack.get();
		}));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new ElephantEntity.NewHurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetAngerGoal<>(this, true));
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (this.level instanceof ServerWorld) {
			this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		this.attackTimer = 10;
		this.level.broadcastEntityEvent(this, (byte) 4);
		boolean flag = target.hurt(DamageSource.mobAttack(this),
				(float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
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
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.ELEPHANT.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isBaby() ? 1.3F : 2.25F;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 2.45D;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ELEPHANT_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.ELEPHANT_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ELEPHANT_DEATH.get();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		} else {
			super.handleEntityEvent(id);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setRemainingPersistentAngerTime(int time) {
		this.angerTime = time;
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
		this.setRemainingPersistentAngerTime(rangedInteger.randomValue(this.random));
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	static class NewHurtByTargetGoal extends HurtByTargetGoal {

		public NewHurtByTargetGoal(CreatureEntity creatureIn) {
			super(creatureIn);
		}

		@Override
		public boolean canUse() {
			LivingEntity livingentity = this.mob.getLastHurtByMob();
			if (livingentity instanceof PlayerEntity) {
				UUID ownerID = ((AbstractTameableChestedEntity) this.mob).getOwnerUniqueId();
				if (ownerID != null) {
					if (ownerID == ((PlayerEntity) livingentity).getUUID()) {
						return false;
					}
				}
			}
			return super.canUse();
		}

	}

}
