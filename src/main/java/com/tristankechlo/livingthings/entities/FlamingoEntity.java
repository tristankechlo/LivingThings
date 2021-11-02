package com.tristankechlo.livingthings.entities;

import java.util.EnumSet;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.util.LandRandomPos;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class FlamingoEntity extends Animal implements ILexiconEntry {

	private static final EntityDataAccessor<Boolean> LEFT_LEG_UP = SynchedEntityData.defineId(FlamingoEntity.class,
			EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Boolean> RIGHT_LEG_UP = SynchedEntityData.defineId(FlamingoEntity.class,
			EntityDataSerializers.BOOLEAN);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"passive_mobs/flamingo");
	private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.COD, Items.SALMON);
	protected DeepWaterAvoidingRandomWalkingGoal randomWalkingGoal;

	public FlamingoEntity(EntityType<? extends FlamingoEntity> type, Level worldIn) {
		super(type, worldIn);
		this.maxUpStep = 1.0F;
		this.setPathfindingMalus(BlockPathTypes.WATER, 1.0F);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		return ModEntityTypes.FLAMINGO.get().create(world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.FLAMINGO.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.FLAMINGO.speed.get());
	}

	@Override
	protected void registerGoals() {
		this.randomWalkingGoal = new FlamingoEntity.DeepWaterAvoidingRandomWalkingGoal(this, 1.0D, 50);

		this.goalSelector.addGoal(0, new FlamingoEntity.SwimInDeepWaterGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.2D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.1D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.2D));
		this.goalSelector.addGoal(5, new FlamingoEntity.LiftLegsGoal(this, 15));
		this.goalSelector.addGoal(5, this.randomWalkingGoal);
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, FlamingoEntity.class, 8.0F));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(LEFT_LEG_UP, false);
		this.entityData.define(RIGHT_LEG_UP, false);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.6F : 1.2F;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.98F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.FLAMINGO.maxSpawnedInChunk.get();
	}

	public boolean isLeftLegUp() {
		return this.entityData.get(LEFT_LEG_UP);
	}

	public boolean isRightLegUp() {
		return this.entityData.get(RIGHT_LEG_UP);
	}

	public void setLeftLegUp(boolean up) {
		this.entityData.set(LEFT_LEG_UP, up);
	}

	public void setRightLegUp(boolean up) {
		this.entityData.set(RIGHT_LEG_UP, up);
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	static class LiftLegsGoal extends Goal {

		private final FlamingoEntity flamingo;
		private final int chance;
		private int rightLegCounter;
		private int leftLegCounter;

		public LiftLegsGoal(FlamingoEntity flamingo, int chance) {
			this.flamingo = flamingo;
			this.chance = chance;
			this.setFlags(EnumSet.of(Goal.Flag.MOVE));
		}

		@Override
		public boolean canUse() {
			if (this.flamingo.getRandom().nextInt(this.chance) != 0) {
				return false;
			}
			return !this.flamingo.isRightLegUp() && !this.flamingo.isLeftLegUp()
					&& this.flamingo.getNavigation().isDone();
		}

		@Override
		public boolean canContinueToUse() {
			return this.flamingo.getNavigation().isDone()
					&& (this.flamingo.isRightLegUp() || this.flamingo.isLeftLegUp());
		}

		@Override
		public void stop() {
			this.leftLegCounter = 0;
			this.rightLegCounter = 0;
			this.flamingo.setLeftLegUp(false);
			this.flamingo.setRightLegUp(false);
		}

		@Override
		public void tick() {
			if (this.leftLegCounter > 0) {
				this.leftLegCounter--;
			} else if (this.leftLegCounter <= 0) {
				this.leftLegCounter = 0;
				this.flamingo.setLeftLegUp(false);
			}

			if (this.rightLegCounter > 0) {
				this.rightLegCounter--;
			} else if (this.rightLegCounter <= 0) {
				this.rightLegCounter = 0;
				this.flamingo.setRightLegUp(false);
			}

			if (this.rightLegCounter == 0 && this.leftLegCounter == 0 && Math.random() < 0.1F) {
				if (Math.random() < 0.5F) {
					this.leftLegCounter = 500;
					this.flamingo.setLeftLegUp(true);
				} else {
					this.rightLegCounter = 500;
					this.flamingo.setRightLegUp(true);
				}
			}

		}

	}

	static class SwimInDeepWaterGoal extends FloatGoal {

		private final FlamingoEntity flamingo;

		public SwimInDeepWaterGoal(FlamingoEntity entityIn) {
			super(entityIn);
			this.flamingo = entityIn;
		}

		@Override
		public boolean canUse() {
			return this.flamingo.wasEyeInWater && super.canUse();
		}

		@Override
		public void tick() {
			// find a new position on land
			this.flamingo.randomWalkingGoal.trigger();
			// start swimming
			super.tick();
		}

	}

	static class DeepWaterAvoidingRandomWalkingGoal extends RandomStrollGoal {

		private final FlamingoEntity flamingo;

		public DeepWaterAvoidingRandomWalkingGoal(FlamingoEntity creatureIn, double speedIn, int chance) {
			super(creatureIn, speedIn, chance);
			this.flamingo = creatureIn;
		}

		@Override
		protected Vec3 getPosition() {
			if (this.flamingo.wasEyeInWater) {
				Vec3 vector3d = LandRandomPos.getPos(this.mob, 15, 7);
				return vector3d == null ? super.getPosition() : vector3d;
			} else {
				return LandRandomPos.getPos(this.mob, 10, 7);
			}
		}

	}

}
