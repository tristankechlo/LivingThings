package com.tristankechlo.livingthings.entities;

import java.util.EnumSet;

import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class FlamingoEntity extends AnimalEntity {

	private static final DataParameter<Boolean> LEFT_LEG_UP = EntityDataManager.createKey(FlamingoEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> RIGHT_LEG_UP = EntityDataManager.createKey(FlamingoEntity.class, DataSerializers.BOOLEAN);
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.COD, Items.SALMON);
	protected DeepWaterAvoidingRandomWalkingGoal randomWalkingGoal;

	public FlamingoEntity(EntityType<? extends FlamingoEntity> type, World worldIn) {
		super(type, worldIn);
	    this.stepHeight = 1.0F;
	    this.setPathPriority(PathNodeType.WATER, 1.0F);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
		return ModEntityTypes.FLAMINGO_ENTIY.create(world);
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 16.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}
	
	@Override
	protected void registerGoals() {
		this.randomWalkingGoal = new FlamingoEntity.DeepWaterAvoidingRandomWalkingGoal(this, 1.0D, 60);
		
		this.goalSelector.addGoal(0, new FlamingoEntity.SwimInDeepWaterGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.1D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.2D));
		this.goalSelector.addGoal(5, new FlamingoEntity.LiftLegsGoal(this, 10));
		this.goalSelector.addGoal(5, this.randomWalkingGoal);
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookAtGoal(this, FlamingoEntity.class, 8.0F));
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(LEFT_LEG_UP, false);
		this.dataManager.register(RIGHT_LEG_UP, false);
	}
			
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 0.6F : 1.2F;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.98F;
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 5;
	}
	
	@OnlyIn(Dist.CLIENT)
	public boolean isLeftLegUp() {
		return this.dataManager.get(LEFT_LEG_UP);
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isRightLegUp() {
		return this.dataManager.get(RIGHT_LEG_UP);
	}
	
	public void setLeftLegUp(boolean up) {
		this.dataManager.set(LEFT_LEG_UP, up);
	}
	
	public void setRightLegUp(boolean up) {
		this.dataManager.set(RIGHT_LEG_UP, up);
	}
		
	static class LiftLegsGoal extends Goal {
		
		private final FlamingoEntity flamingo;
		private final int chance;
		private int rightLegCounter;
		private int leftLegCounter;
		
		public LiftLegsGoal(FlamingoEntity flamingo, int chance) {
			this.flamingo = flamingo;
			this.chance = chance;
		    this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean shouldExecute() {
			if(this.flamingo.getRNG().nextInt(this.chance) != 0) {
				return false;
			}
			return !this.flamingo.isRightLegUp() && !this.flamingo.isLeftLegUp() && this.flamingo.getNavigator().noPath();
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return this.flamingo.getNavigator().noPath() && (this.flamingo.isRightLegUp() || this.flamingo.isLeftLegUp());
		}
		
		@Override
		public void resetTask() {
			this.leftLegCounter = 0;
			this.rightLegCounter = 0;
			this.flamingo.setLeftLegUp(false);
			this.flamingo.setRightLegUp(false);
		}
		
		@Override
		public void tick() {
			if(this.leftLegCounter > 0) {
				this.leftLegCounter--;
			} else if(this.leftLegCounter <= 0) {
				this.leftLegCounter = 0;
				this.flamingo.setLeftLegUp(false);
			}
			
			if(this.rightLegCounter > 0) {
				this.rightLegCounter--;
			} else if(this.rightLegCounter <= 0) {
				this.rightLegCounter = 0;
				this.flamingo.setRightLegUp(false);
			}
						
			if(this.rightLegCounter == 0 && this.leftLegCounter == 0 && Math.random() < 0.1F) {
				if(Math.random() < 0.5F) {
					this.leftLegCounter = 500;
					this.flamingo.setLeftLegUp(true);
				} else {
					this.rightLegCounter = 500;
					this.flamingo.setRightLegUp(true);
				}
			}
			
		}
		
	}
	
	static class SwimInDeepWaterGoal extends SwimGoal {
		
		private final FlamingoEntity flamingo;

		public SwimInDeepWaterGoal(FlamingoEntity entityIn) {
			super(entityIn);
			this.flamingo = entityIn;
		}
		
		@Override
		public boolean shouldExecute() {
			return this.flamingo.eyesInWater ? super.shouldExecute() : false;
		}
		
		@Override
		public void tick() {
			//find a new position on land
			this.flamingo.randomWalkingGoal.makeUpdate();
			//start swimming
			super.tick();
		}
				
	}
	
	static class DeepWaterAvoidingRandomWalkingGoal extends RandomWalkingGoal {
		
		private final FlamingoEntity flamingo;

		public DeepWaterAvoidingRandomWalkingGoal(FlamingoEntity creatureIn, double speedIn, int chance) {
			super(creatureIn, speedIn, chance);
			this.flamingo = creatureIn;
		}
		
		@Override
		protected Vector3d getPosition() {
		      if (this.flamingo.eyesInWater) {
		          Vector3d vector3d = RandomPositionGenerator.getLandPos(this.creature, 15, 7);
		          return vector3d == null ? super.getPosition() : vector3d;
		       } else {
		    	      return RandomPositionGenerator.findRandomTarget(this.creature, 10, 7);
		       }
		}
		
	}
	
}
