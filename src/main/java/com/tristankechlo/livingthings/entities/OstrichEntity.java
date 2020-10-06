package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.tristankechlo.livingthings.blocks.OstrichNestBlock;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.BoostHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IRideable;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomWalkingGoal;
import net.minecraft.entity.item.ExperienceOrbEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OstrichEntity extends AnimalEntity implements IRideable {

	private static final DataParameter<Boolean> HAS_EGG = EntityDataManager.createKey(OstrichEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_BUILDING_NEST = EntityDataManager.createKey(OstrichEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_LAYING_EGG = EntityDataManager.createKey(OstrichEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(OstrichEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.createKey(OstrichEntity.class, DataSerializers.VARINT);
	private final BoostHelper boostHelper = new BoostHelper(this.dataManager, BOOST_TIME, SADDLED);
	private int nestBuildingCounter;
	private int layingEggCounter;
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.fromItems(Items.WHEAT);

	public OstrichEntity(EntityType<? extends OstrichEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity parent) {
		return ModEntityTypes.OSTRICH_ENTIY.create(world);
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.OSTRICH.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
		this.goalSelector.addGoal(2, new OstrichEntity.BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new OstrichEntity.LayEggGoal(this, 1.1D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.3D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookAtGoal(this, OstrichEntity.class, 8.0F));
		this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
	}
	
	@Override
	protected void registerData() {
		super.registerData();
	    this.dataManager.register(HAS_EGG, false);
	    this.dataManager.register(IS_BUILDING_NEST, false);
	    this.dataManager.register(IS_LAYING_EGG, false);
	    this.dataManager.register(SADDLED, false);
	    this.dataManager.register(BOOST_TIME, 0);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
	    compound.putBoolean("HasEgg", this.hasEgg());
	    this.boostHelper.setSaddledToNBT(compound);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
	    this.setHasEgg(compound.getBoolean("HasEgg"));
	    this.boostHelper.setSaddledFromNBT(compound);
	}
	
	@Override
	public void livingTick() {
		super.livingTick();
		if (this.isAlive() && this.isBuildingNest() && this.nestBuildingCounter >= 1 && this.nestBuildingCounter % 7 == 0) {
			BlockPos pos = this.getPosition();
			this.world.playEvent(2001, pos, Block.getStateId(Blocks.SAND.getDefaultState()));
		}
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return LivingThingsConfig.OSTRICH.maxSpawns.get();
	}
	
	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}
	
	@Override
	public boolean canBeSteered() {
		if(LivingThingsConfig.OSTRICH.canBeRidden.get()) {
			Entity entity = this.getControllingPassenger();
			if (entity instanceof PlayerEntity) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void notifyDataManagerChange(DataParameter<?> key) {
		if (BOOST_TIME.equals(key) && this.world.isRemote) {
			this.boostHelper.updateData();
		}
	    super.notifyDataManagerChange(key);
	}
		
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}
	
	
	@Override
	protected void dropInventory() {
		super.dropInventory();
	    if(this.hasEgg()) {
	    	this.entityDropItem(ModItems.OSTRICH_EGG.get());
	    }
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return (this.isChild()) ? 0.8F : 1.75F;
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return false;
	}
	
	public boolean isBuildingNest() {
		return this.dataManager.get(IS_BUILDING_NEST);
	}
	
	public void setBuildingNest(boolean building) {
		this.nestBuildingCounter = building ? 1 : 0;
		this.dataManager.set(IS_BUILDING_NEST, building);
	}
	
	public boolean isLayingEgg() {
		return this.dataManager.get(IS_LAYING_EGG);
	}
	
	public void setLayingEgg(boolean layingEgg) {
		this.layingEggCounter = layingEgg ? 1 : 0;
		this.dataManager.set(IS_LAYING_EGG, layingEgg);
	}
	
	public boolean hasEgg() {
		return this.dataManager.get(HAS_EGG);
	}
	
	public void setHasEgg(boolean hasEgg) {
		this.dataManager.set(HAS_EGG, hasEgg);
	}
		
	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.OSTRICH_AMBIENT.get();
	}

	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		boolean breedingItem = this.isBreedingItem(player.getHeldItem(hand));
		if (!breedingItem && !this.isBeingRidden() && !this.isChild() && !player.isSecondaryUseActive() && player.getHeldItemMainhand().getItem() == Items.AIR) {
			if (!this.world.isRemote && LivingThingsConfig.OSTRICH.canBeRidden.get()) {
				player.startRiding(this);
			}
			return ActionResultType.func_233537_a_(this.world.isRemote);
		} else {
			return super.func_230254_b_(player, hand);
		}
	}
	
	@Override
	public void travel(Vector3d travelVector) {
		this.ride(this, this.boostHelper, travelVector);
	}

	@Override
	public boolean boost() {
	    return this.boostHelper.boost(this.getRNG());
	}

	@Override
	public void travelTowards(Vector3d travelVec) {
	    super.travel(travelVec);
	}

	@Override
	public float getMountedSpeed() {
		return (float)this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.9F;
	}
	
	@Override
	public double getMountedYOffset() {
		return 1.0D;
	}
		
	static class BreedGoal extends net.minecraft.entity.ai.goal.BreedGoal {
		
		private final OstrichEntity ostrich;

		public BreedGoal(OstrichEntity animal, double moveSpeed) {
			super(animal, moveSpeed);
			this.ostrich = animal;
		}
		
		@Override
		public boolean shouldExecute() {
			return super.shouldExecute() && !this.ostrich.hasEgg();
		}
		
		@Override
		protected void spawnBaby() {
	         ServerPlayerEntity serverplayerentity = this.animal.getLoveCause();
	         if (serverplayerentity == null && this.targetMate.getLoveCause() != null) {
	            serverplayerentity = this.targetMate.getLoveCause();
	         }

	         if (serverplayerentity != null) {
	            serverplayerentity.addStat(Stats.ANIMALS_BRED);
	            CriteriaTriggers.BRED_ANIMALS.trigger(serverplayerentity, this.animal, this.targetMate, (AgeableEntity)null);
	         }
	         
	         this.ostrich.setHasEgg(true);
	         this.animal.resetInLove();
	         this.targetMate.resetInLove();
	         this.animal.setGrowingAge(6000);
	         this.targetMate.setGrowingAge(6000);
	         Random random = this.animal.getRNG();
	         if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
	            this.world.addEntity(new ExperienceOrbEntity(this.world, this.animal.getPosX(), this.animal.getPosY(), this.animal.getPosZ(), random.nextInt(7) + 1));
	         }
		}
		
	}
	
	
	static class LayEggGoal extends MoveToBlockGoal {
		
		private final OstrichEntity ostrich;
		private boolean isAboveDestination;

		public LayEggGoal(OstrichEntity creature, double speedIn) {
			super(creature, speedIn, 16);
			this.ostrich = creature;
		}
		
		@Override
		public boolean shouldExecute() {
			return (this.ostrich.hasEgg() || this.ostrich.isBuildingNest() || this.ostrich.isLayingEgg()) ? super.shouldExecute() : false;
		}
		
		@Override
		public boolean shouldContinueExecuting() {
			return super.shouldContinueExecuting() && (this.ostrich.hasEgg() || this.ostrich.isBuildingNest() || this.ostrich.isLayingEgg());
		}
				
		@Override
		protected void func_220725_g() {
			BlockPos blockpos = this.func_241846_j();
			Path path = this.creature.getNavigator().getPathToPos(blockpos.getX() + 0.5D, blockpos.getY(), blockpos.getZ() + 0.5D, 0);
			this.creature.getNavigator().setPath(path, this.movementSpeed);
		}
		
		@Override
		protected BlockPos func_241846_j() {
			if(this.ostrich.world.getBlockState(this.destinationBlock).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
				return this.destinationBlock;
			}
			return this.destinationBlock.up();
		}
		
		@Override
		public void tick() {
			BlockPos blockpos = this.func_241846_j();
			if (!blockpos.withinDistance(this.creature.getPositionVec(), this.getTargetDistanceSq())) {
				this.isAboveDestination = false;
				++this.timeoutCounter;
				if (this.shouldMove()) {
					Path path = this.creature.getNavigator().getPathToPos(blockpos.getX() + 0.5D, blockpos.getY(), blockpos.getZ() + 0.5D, 0);
					this.creature.getNavigator().setPath(path, this.movementSpeed);
				}
			} else {
				this.isAboveDestination = true;
				--this.timeoutCounter;
			}

			if (!this.ostrich.isInWater() && this.getIsAboveDestination()) {
				World world = this.ostrich.world;
				if (world.getBlockState(this.destinationBlock).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
					
					BlockState state = world.getBlockState(this.destinationBlock);
					
					if (!state.get(OstrichNestBlock.EGG)) {

						//lay egg animation
						if(this.ostrich.layingEggCounter < 1) {							
							this.ostrich.setLayingEgg(true);							
						} else if (this.ostrich.layingEggCounter > 150) {							
							world.playSound(null, this.destinationBlock, ModSounds.OSTRICH_EGG_LAYING.get(), SoundCategory.BLOCKS, 0.5F, 0.9F);
							world.setBlockState(this.destinationBlock, state.with(OstrichNestBlock.EGG, true), 3);
							this.ostrich.setHasEgg(false);
							this.ostrich.setLayingEgg(false);
						}						
						if(this.ostrich.isLayingEgg()) {
							this.ostrich.layingEggCounter++;
						}
						
					}
				} else {
					//nest building animation
					if(this.ostrich.nestBuildingCounter < 1) {						
						this.ostrich.setBuildingNest(true);						
					} else if(this.ostrich.nestBuildingCounter > 100) {						
						world.playSound(null, this.destinationBlock, SoundEvents.BLOCK_LILY_PAD_PLACE, SoundCategory.BLOCKS, 0.9F, 0.9F);
						world.setBlockState(this.destinationBlock.up(), ModBlocks.OSTRICH_NEST.get().getDefaultState(), 3);
						this.destinationBlock = this.destinationBlock.up();
						this.ostrich.setBuildingNest(false);
					}					
					if(ostrich.isBuildingNest()) {
						this.ostrich.nestBuildingCounter++;
					}				
				}
			} else {
				this.ostrich.setLayingEgg(false);
				this.ostrich.setBuildingNest(false);
			}
		}
		
		@Override
		public void resetTask() {
			super.resetTask();
			this.ostrich.setLayingEgg(false);
			this.ostrich.setBuildingNest(false);
		}
		
		@Override
		protected boolean getIsAboveDestination() {
			return this.isAboveDestination;
		}
		
		@Override
		protected boolean shouldMoveTo(IWorldReader worldIn, BlockPos pos) {
			
			if(worldIn.getBlockState(pos).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
				
				return !worldIn.getBlockState(pos).get(OstrichNestBlock.EGG);
				
			} else if(worldIn.getBlockState(pos).getBlock() == Blocks.SAND) {
				if(worldIn.isAirBlock(pos.up())) {
					return true;
				}
				return false;
			}
			return false;
		}
				
	}

}
