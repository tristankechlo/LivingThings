package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class LionEntity extends AnimalEntity implements IAngerable {

	private static final DataParameter<Boolean> IS_MALE = EntityDataManager.createKey(LionEntity.class, DataSerializers.BOOLEAN);
	private static final RangedInteger rangedInteger = TickRangeConverter.convertRange(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public LionEntity(EntityType<? extends LionEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld p_241840_1_, AgeableEntity p_241840_2_) {
		LionEntity entity = ModEntityTypes.LION_ENTITY.create(this.world);
		entity.setMale(new Random().nextBoolean());
		return entity;
	}
	
	@Override
	   public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, @Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
		boolean male = (Math.random() < 0.5) ? true : false;
		this.setMale(male);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.33D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 25.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 6.0D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new MeleeAttackGoal(this, 1.2D, true) {
			@Override
			public double getAttackReachSqr(LivingEntity attackTarget) {
			      return (double)(this.attacker.getWidth() * 1.8F * this.attacker.getWidth() * 1.8F + attackTarget.getWidth());
			}
		});
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 7.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setCallsForHelp());
	    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 1, true, true, null));
		this.targetSelector.addGoal(5, new ResetAngerGoal<>(this, true));
	}
		
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(IS_MALE, false);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
	      if (this.isMale()) {
	          compound.putBoolean("IsMale", true);
	       }
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
	      if (compound.getBoolean("IsMale")) {
	          this.setMale(true);
	      } else {
	    	  this.setMale(false);
	      }
	}
			
	@Override
	public boolean canBreatheUnderwater() {
		return false;
	}
	
	@Override
	public boolean canAttack(LivingEntity target) {
		boolean peaceful = (target.getEntityWorld().getDifficulty() == Difficulty.PEACEFUL) ? true : false;
		if(peaceful) {
			return false;
		}
		return super.canAttack(target);
	}
	
	@Override
	public boolean canMateWith(AnimalEntity otherAnimal) {
		if(otherAnimal == this) {
			return false;
		}
		if (otherAnimal instanceof LionEntity) {
	    	LionEntity otherLion = (LionEntity) otherAnimal;
	    	if(!this.isMale() && otherLion.isMale()) {
	            return this.isInLove() && otherLion.isInLove();
	    	} else if (this.isMale() && !otherLion.isMale()) {
	            return this.isInLove() && otherLion.isInLove();
	    	}
	    }
		return false;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.85F;
	}
	
	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
	      ItemStack itemstack = player.getHeldItem(hand);
	      if (this.isBreedingItem(itemstack)) {
	         int i = this.getGrowingAge();
	         if (!this.world.isRemote && i == 0 && this.canBreed()) {
	            this.consumeItemFromStack(player, itemstack);
	            this.setInLove(player);
	            return ActionResultType.SUCCESS;
	         }

	         if (this.isChild()) {
	            this.consumeItemFromStack(player, itemstack);
	            this.ageUp((int)((float)(-i / 20) * 0.1F), true);
	            return ActionResultType.func_233537_a_(this.world.isRemote);
	         }

	         if (this.world.isRemote) {
	            return ActionResultType.CONSUME;
	         }
	      }
	      return ActionResultType.PASS;
	}
		
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		final Ingredient breeding_Items = Ingredient.fromItems(Items.BEEF, Items.CHICKEN, Items.RABBIT);
		return breeding_Items.test(stack);
	}

	@Override
	public boolean attackEntityAsMob(Entity target) {
	    this.world.setEntityState(this, (byte)4);
		boolean flag = target.attackEntityFrom(DamageSource.causeMobDamage(this), (float) ((int) this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
		if (flag) {
			this.applyEnchantments(this, target);
		}
		return flag;
	}
	
	@Override
	public double getPosYEye() {
		return this.getPosY() + 1.4D;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}

	public boolean isMale(){
		return this.getDataManager().get(IS_MALE);
	}
	
	public void setMale(boolean male) {
		this.getDataManager().set(IS_MALE, male);
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
		this.setAngerTime(rangedInteger.func_233018_a_(this.rand));		
	}
}
