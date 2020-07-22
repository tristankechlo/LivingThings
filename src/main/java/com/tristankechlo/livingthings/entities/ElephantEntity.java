package com.tristankechlo.livingthings.entities;

import java.util.UUID;
import java.util.function.Predicate;

import com.tristankechlo.livingthings.init.ModEntities;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureAttribute;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ElephantEntity extends AnimalEntity implements IAngerable {

	private static final RangedInteger rangedInteger = TickRangeConverter.func_233037_a_(20, 39);
	private int integer;
	private int attackTimer;
	private UUID uuid;
	
	public ElephantEntity(EntityType<ElephantEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		//TODO return different Model
		return ModEntities.ELEPHANT_ENTITY.get().create(this.world);
	}
	
	public static AttributeModifierMap.MutableAttribute func_234200_m_() {
		return MobEntity.func_233666_p_()
				.func_233815_a_(Attributes.field_233819_b_, 50.0D)		// follow_range
				.func_233815_a_(Attributes.field_233818_a_, 75.0D)		// max_health
				.func_233815_a_(Attributes.field_233821_d_, 0.25D)		// movement_speed
				.func_233815_a_(Attributes.field_233823_f_, 15.0D);		// attack_damage
	}

	@Override
	protected void registerGoals() {
	    this.goalSelector.addGoal(0, new SwimGoal(this));
	    this.goalSelector.addGoal(1, new WaterAvoidingRandomWalkingGoal(this, 0.5D));
	    this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
	    this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, Ingredient.fromItems(Items.WHEAT), false));
		this.goalSelector.addGoal(4, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(5, new LookRandomlyGoal(this));
	    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, SheepEntity.class, 1000, true, true, (Predicate<LivingEntity>)null));
		super.registerGoals();
	}
			
	@Override
	public void livingTick() {
	      super.livingTick();
	      if (this.attackTimer > 0) {
	         --this.attackTimer;
	      }
	}
	
	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
	      if (id == 4) {
	          this.attackTimer = 10;
	          this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
	       } else {
	          super.handleStatusUpdate(id);
	       }
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return false;
	}
		
	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		//drop Inventory or something else
		ItemStack stack = new ItemStack(Items.LEATHER, 4);
		ItemEntity itementity = entityDropItem(stack);
		if (itementity != null) {
			itementity.setNoDespawn();
		}
	}

	@Override
	public CreatureAttribute getCreatureAttribute() {
		return CreatureAttribute.UNDEFINED;
	}
	
	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
	}

	@Override
	public int func_230256_F__() {
		return this.integer;
	}

	@Override
	public void func_230260_a__(int integer) {
		this.integer = integer;
	}

	@Override
	public UUID func_230257_G__() {
		return this.uuid;
	}

	@Override
	public void func_230259_a_(UUID uuid) {
		this.uuid = uuid;
	}

	@Override
	public void func_230258_H__() {
	      this.func_230260_a__(rangedInteger.func_233018_a_(this.rand));
		
	}
				
}
