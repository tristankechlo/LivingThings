package com.tristankechlo.livingthings.entities;

import java.util.UUID;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
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
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class RaccoonEntity extends AnimalEntity implements IAngerable {

	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.WHEAT, Items.APPLE, Items.CARROT, Items.POTATO, Items.BEETROOT);
	private static final RangedInteger rangedInteger = TickRangeConverter.convertRange(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public RaccoonEntity(EntityType<? extends AnimalEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
		return ModEntityTypes.RACCOON_ENTITY.create(world);
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.RACCOON.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, LivingThingsConfig.RACCOON.damage.get());
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.05D, false));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.05D, false, BREEDING_ITEMS));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetAngerGoal<>(this, true));
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		this.writeAngerNBT(compound);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if(this.world instanceof ServerWorld) {
			this.readAngerNBT((ServerWorld) this.world, compound);
		}
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isChild() ? 0.35F : 0.7F;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
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

}
