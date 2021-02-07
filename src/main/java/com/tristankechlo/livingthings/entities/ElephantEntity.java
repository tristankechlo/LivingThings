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

	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.WHEAT);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID, "neutral_mobs/elephant");
	private static final RangedInteger rangedInteger = TickRangeConverter.convertRange(20, 39);
	private int angerTime;
	private int attackTimer;
	private UUID angerTarget;

	public ElephantEntity(EntityType<? extends ElephantEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		this.stepHeight = 1.0F;
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		ElephantEntity child = ModEntityTypes.ELEPHANT_ENTITY.get().create(this.world);
		if (this.isTame() || ((ElephantEntity) parent).isTame()) {
			child.setTame(true);
		}
		return child;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.ELEPHANT.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, LivingThingsConfig.ELEPHANT.speed.get())
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 16.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, LivingThingsConfig.ELEPHANT.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new ElephantEntity.NewHurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetAngerGoal<>(this, true));
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (this.world instanceof ServerWorld) {
			this.readAngerNBT((ServerWorld) this.world, compound);
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		this.writeAngerNBT(compound);
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
		this.attackTimer = 10;
		this.world.setEntityState(this, (byte) 4);
		boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
		if (flag) {
			// throw target in the air
			target.setMotion(target.getMotion().add(0.0D, 0.7D, 0.0D));
			this.applyEnchantments(this, target);
		}
		return flag;
	}

	@Override
	public void livingTick() {
		super.livingTick();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return LivingThingsConfig.ELEPHANT.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isChild() ? 1.3F : 2.25F;
	}

	@Override
	public double getMountedYOffset() {
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
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.attackTimer = 10;
		} else {
			super.handleStatusUpdate(id);
		}
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public int getAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setAngerTime(int time) {
		this.angerTime = time;
	}

	@Override
	public UUID getAngerTarget() {
		return this.angerTarget;
	}

	@Override
	public void setAngerTarget(UUID target) {
		this.angerTarget = target;
	}

	@Override
	public void func_230258_H__() {
		this.setAngerTime(rangedInteger.getRandomWithinRange(this.rand));
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
		public boolean shouldExecute() {
			LivingEntity livingentity = this.goalOwner.getRevengeTarget();
			if (livingentity instanceof PlayerEntity) {
				UUID ownerID = ((AbstractTameableChestedEntity) this.goalOwner).getOwnerUniqueId();
				if (ownerID != null) {
					if (ownerID == ((PlayerEntity) livingentity).getUniqueID()) {
						return false;
					}
				}
			}
			return super.shouldExecute();
		}

	}

}
