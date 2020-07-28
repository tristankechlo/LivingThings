package com.tristankechlo.livingthings.entities;

import java.util.UUID;
import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class GiraffeEntity extends AnimalEntity implements IAngerable {

	private static final RangedInteger rangedInteger = TickRangeConverter.convertRange(20, 39);
	private int integer;
	private UUID uuid;
	
	public GiraffeEntity(EntityType<? extends GiraffeEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
	
	@Override
	public AgeableEntity createChild(AgeableEntity ageable) {
		return ModEntityTypes.GIRAFFE_ENTITY.create(this.world);
	}
	
	public static AttributeModifierMap.MutableAttribute func_234200_m_() {
		return MobEntity.func_233666_p_()
				.func_233815_a_(Attributes.MAX_HEALTH, 30.0D)
				.func_233815_a_(Attributes.MOVEMENT_SPEED, 0.3D)
				.func_233815_a_(Attributes.FOLLOW_RANGE, 50.0D)
				.func_233815_a_(Attributes.ATTACK_DAMAGE, 8.0D)
				.func_233815_a_(Attributes.ATTACK_KNOCKBACK, 0.25D)
				.func_233815_a_(Attributes.KNOCKBACK_RESISTANCE, 0.1D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(8, new ResetAngerGoal<>(this, true));
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		Item item = stack.getItem();
		return (item == Items.WHEAT);
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
	    this.world.setEntityState(this, (byte)4);
		boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.func_233637_b_(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.applyEnchantments(this, target);
		}
		return flag;
	}

	@Override
	public void livingTick() {
		super.livingTick();
	}
		
	@Override
	public boolean canBreatheUnderwater() {
		return false;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 4;
	}

	@Override
	protected void dropSpecialItems(DamageSource source, int looting, boolean recentlyHitIn) {
		// drop Inventory or something else
		ItemStack stack = new ItemStack(Items.LEATHER, 4);
		ItemEntity itementity = entityDropItem(stack);
		if (itementity != null) {
			itementity.setNoDespawn();
		}
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

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 4) {
			this.playSound(SoundEvents.ENTITY_IRON_GOLEM_ATTACK, 1.0F, 1.0F);
		} else {
			super.handleStatusUpdate(id);
		}
	}
		
}
