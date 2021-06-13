package com.tristankechlo.livingthings.entities;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.blocks.OstrichNestBlock;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.OstrichBreedGoal;
import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

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
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OstrichEntity extends AnimalEntity implements IRideable, ILexiconEntry {

	private static final DataParameter<Boolean> HAS_EGG = EntityDataManager.defineId(OstrichEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_BUILDING_NEST = EntityDataManager.defineId(OstrichEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_LAYING_EGG = EntityDataManager.defineId(OstrichEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.defineId(OstrichEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Integer> BOOST_TIME = EntityDataManager.defineId(OstrichEntity.class,
			DataSerializers.INT);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"passive_mobs/ostrich");
	private final BoostHelper boostHelper = new BoostHelper(this.entityData, BOOST_TIME, SADDLED);
	private int nestBuildingCounter;
	private int layingEggCounter;
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.WHEAT);

	public OstrichEntity(EntityType<? extends OstrichEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity parent) {
		return ModEntityTypes.OSTRICH_ENTITY.get().create(world);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.OSTRICH.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.OSTRICH.speed.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
		this.goalSelector.addGoal(2, new OstrichBreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new OstrichEntity.LayEggGoal(this, 1.1D));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, false, TEMPTATION_ITEMS));
		this.goalSelector.addGoal(5, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomWalkingGoal(this, 1.3D));
		this.goalSelector.addGoal(7, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(8, new LookAtGoal(this, OstrichEntity.class, 8.0F));
		this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(HAS_EGG, false);
		this.entityData.define(IS_BUILDING_NEST, false);
		this.entityData.define(IS_LAYING_EGG, false);
		this.entityData.define(SADDLED, false);
		this.entityData.define(BOOST_TIME, 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putBoolean("HasEgg", this.hasEgg());
		this.boostHelper.addAdditionalSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setHasEgg(compound.getBoolean("HasEgg"));
		this.boostHelper.readAdditionalSaveData(compound);
	}

	@Override
	public void aiStep() {
		super.aiStep();
		if (this.isAlive() && this.isBuildingNest() && this.nestBuildingCounter >= 1
				&& this.nestBuildingCounter % 7 == 0) {
			BlockPos pos = this.blockPosition();
			this.level.levelEvent(2001, pos, Block.getId(Blocks.SAND.defaultBlockState()));
		}
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.OSTRICH.maxSpawnedInChunk.get();
	}

	@Override
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	@Override
	public boolean canBeControlledByRider() {
		if (LivingThingsConfig.OSTRICH.canBeRidden.get()) {
			Entity entity = this.getControllingPassenger();
			return (entity instanceof PlayerEntity);
		}
		return false;
	}

	@Override
	public void onSyncedDataUpdated(DataParameter<?> key) {
		if (BOOST_TIME.equals(key) && this.level.isClientSide) {
			this.boostHelper.onSynced();
		}
		super.onSyncedDataUpdated(key);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		if (this.hasEgg()) {
			this.spawnAtLocation(ModItems.OSTRICH_EGG.get());
		}
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return (this.isBaby()) ? 0.8F : 1.75F;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return false;
	}

	public boolean isBuildingNest() {
		return this.entityData.get(IS_BUILDING_NEST);
	}

	public void setBuildingNest(boolean building) {
		this.nestBuildingCounter = building ? 1 : 0;
		this.entityData.set(IS_BUILDING_NEST, building);
	}

	public boolean isLayingEgg() {
		return this.entityData.get(IS_LAYING_EGG);
	}

	public void setLayingEgg(boolean layingEgg) {
		this.layingEggCounter = layingEgg ? 1 : 0;
		this.entityData.set(IS_LAYING_EGG, layingEgg);
	}

	public boolean hasEgg() {
		return this.entityData.get(HAS_EGG);
	}

	public void setHasEgg(boolean hasEgg) {
		this.entityData.set(HAS_EGG, hasEgg);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.OSTRICH_AMBIENT.get();
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		boolean breedingItem = this.isFood(player.getItemInHand(hand));
		boolean isLexicon = player.getMainHandItem().getItem() == ModItems.LEXICON.get();
		if (!breedingItem && !isLexicon && !this.isVehicle() && !this.isBaby() && !player.isSecondaryUseActive()) {
			if (!this.level.isClientSide && LivingThingsConfig.OSTRICH.canBeRidden.get()) {
				player.startRiding(this);
			}
			return ActionResultType.sidedSuccess(this.level.isClientSide);
		} else {
			return super.mobInteract(player, hand);
		}
	}

	@Override
	public void travel(Vector3d travelVector) {
		this.travel(this, this.boostHelper, travelVector);
	}

	@Override
	public boolean boost() {
		return this.boostHelper.boost(this.getRandom());
	}

	@Override
	public void travelWithInput(Vector3d travelVec) {
		super.travel(travelVec);
	}

	@Override
	public float getSteeringSpeed() {
		return (float) this.getAttributeValue(Attributes.MOVEMENT_SPEED) * 0.9F;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 1.0D;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	static class LayEggGoal extends MoveToBlockGoal {

		private final OstrichEntity ostrich;
		private boolean isAboveDestination;

		public LayEggGoal(OstrichEntity creature, double speedIn) {
			super(creature, speedIn, 16);
			this.ostrich = creature;
		}

		@Override
		public boolean canUse() {
			return (this.ostrich.hasEgg() || this.ostrich.isBuildingNest() || this.ostrich.isLayingEgg())
					&& super.canUse();
		}

		@Override
		public boolean canContinueToUse() {
			return super.canContinueToUse()
					&& (this.ostrich.hasEgg() || this.ostrich.isBuildingNest() || this.ostrich.isLayingEgg());
		}

		@Override
		protected void moveMobToBlock() {
			BlockPos blockpos = this.getMoveToTarget();
			Path path = this.mob.getNavigation().createPath(blockpos.getX() + 0.5D, blockpos.getY(),
					blockpos.getZ() + 0.5D, 0);
			this.mob.getNavigation().moveTo(path, this.speedModifier);
		}

		@Override
		protected BlockPos getMoveToTarget() {
			if (this.ostrich.level.getBlockState(this.blockPos).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
				return this.blockPos;
			}
			return this.blockPos.above();
		}

		@Override
		public void tick() {
			BlockPos blockpos = this.getMoveToTarget();
			if (!blockpos.closerThan(this.mob.position(), this.acceptedDistance())) {
				this.isAboveDestination = false;
				++this.tryTicks;
				if (this.shouldRecalculatePath()) {
					Path path = this.mob.getNavigation().createPath(blockpos.getX() + 0.5D, blockpos.getY(),
							blockpos.getZ() + 0.5D, 0);
					this.mob.getNavigation().moveTo(path, this.speedModifier);
				}
			} else {
				this.isAboveDestination = true;
				--this.tryTicks;
			}
			if (!this.ostrich.isInWater() && this.isReachedTarget()) {
				World world = this.ostrich.level;
				if (world.getBlockState(this.blockPos).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
					BlockState state = world.getBlockState(this.blockPos);
					if (!state.getValue(OstrichNestBlock.EGG)) {
						// lay egg animation
						if (this.ostrich.layingEggCounter < 1) {
							this.ostrich.setLayingEgg(true);
						} else if (this.ostrich.layingEggCounter > 150) {
							world.playSound(null, this.blockPos, ModSounds.OSTRICH_EGG_LAYING.get(),
									SoundCategory.BLOCKS, 0.5F, 0.9F);
							world.setBlock(this.blockPos, state.setValue(OstrichNestBlock.EGG, true), 3);
							this.ostrich.setHasEgg(false);
							this.ostrich.setLayingEgg(false);
						}
						if (this.ostrich.isLayingEgg()) {
							this.ostrich.layingEggCounter++;
						}
					}
				} else {
					// nest building animation
					if (this.ostrich.nestBuildingCounter < 1) {
						this.ostrich.setBuildingNest(true);
					} else if (this.ostrich.nestBuildingCounter > 100) {
						world.playSound(null, this.blockPos, SoundEvents.LILY_PAD_PLACE, SoundCategory.BLOCKS, 0.9F,
								0.9F);
						world.setBlock(this.blockPos.above(), ModBlocks.OSTRICH_NEST.get().defaultBlockState(), 3);
						this.blockPos = this.blockPos.above();
						this.ostrich.setBuildingNest(false);
					}
					if (ostrich.isBuildingNest()) {
						this.ostrich.nestBuildingCounter++;
					}
				}
			} else {
				this.ostrich.setLayingEgg(false);
				this.ostrich.setBuildingNest(false);
			}
		}

		@Override
		public void stop() {
			super.stop();
			this.ostrich.setLayingEgg(false);
			this.ostrich.setBuildingNest(false);
		}

		@Override
		protected boolean isReachedTarget() {
			return this.isAboveDestination;
		}

		@Override
		protected boolean isValidTarget(IWorldReader worldIn, BlockPos pos) {
			if (worldIn.getBlockState(pos).getBlock() == ModBlocks.OSTRICH_NEST.get()) {
				return !worldIn.getBlockState(pos).getValue(OstrichNestBlock.EGG);
			} else if (worldIn.getBlockState(pos).getBlock() == Blocks.SAND) {
				return worldIn.isEmptyBlock(pos.above());
			}
			return false;
		}

	}

}
