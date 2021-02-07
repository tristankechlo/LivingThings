package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.OwnerHurtByTargetGoal;
import net.minecraft.entity.ai.goal.OwnerHurtTargetGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.ClimberPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.event.ForgeEventFactory;

public class MonkeyEntity extends TameableEntity implements ILexiconEntry {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID, "neutral_mobs/monkey");
	private static final DataParameter<Boolean> SITTING = EntityDataManager.createKey(MonkeyEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Byte> CLIMBING = EntityDataManager.createKey(MonkeyEntity.class, DataSerializers.BYTE);
	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.APPLE);
	private BlockPos jukeboxPosition;
	private boolean partying;

	public MonkeyEntity(EntityType<? extends MonkeyEntity> type, World worldIn) {
		super(type, worldIn);
	}
	
	private static final Ingredient getBreedingItems() {
		Ingredient bananas = Ingredient.fromTag(ItemTags.getCollection().get(LivingThingsTags.BANANAS));
		return Ingredient.merge(ImmutableList.of(bananas, BREEDING_ITEMS));
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
		MonkeyEntity monkey = ModEntityTypes.MONKEY_ENTITY.get().create(world);
		UUID uuid = this.getOwnerId();
		if (uuid != null) {
			monkey.setOwnerId(uuid);
			monkey.setTamed(true);
		} else if (entity instanceof TameableEntity) {
			UUID uuid2 = ((TameableEntity) entity).getOwnerId();
			if (uuid2 != null) {
				monkey.setOwnerId(uuid2);
				monkey.setTamed(true);
			}
		}
		return monkey;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(1, new SwimGoal(this));
		this.goalSelector.addGoal(2, new SitGoal(this));
		this.goalSelector.addGoal(3, new BetterMeleeAttackGoal(this, 1, true));
		this.goalSelector.addGoal(4, new TemptGoal(this, 1.1D, MonkeyEntity.getBreedingItems(), true));
		this.goalSelector.addGoal(5, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new FollowParentGoal(this, 1.1));
		this.goalSelector.addGoal(7, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F, false));
		this.goalSelector.addGoal(8, new RandomWalkingGoal(this, 1.0D, 60));
		this.goalSelector.addGoal(10, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(10, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
		this.targetSelector.addGoal(3, (new HurtByTargetGoal(this)).setCallsForHelp());
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(SITTING, false);
		this.dataManager.register(CLIMBING, (byte) 0);
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.MONKEY.health.get())
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, LivingThingsConfig.MONKEY.damage.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, LivingThingsConfig.MONKEY.speed.get());
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("Sitting", this.isSitting());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setSitting(compound.getBoolean("Sitting"));
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.world.isRemote) {
			this.setBesideClimbableBlock(this.collidedHorizontally);
		}
	}

	@Override
	public void livingTick() {
		if (this.jukeboxPosition == null || !this.jukeboxPosition.withinDistance(this.getPositionVec(), 3.46D)
				|| !this.world.getBlockState(this.jukeboxPosition).isIn(Blocks.JUKEBOX)) {
			this.partying = false;
			this.jukeboxPosition = null;
		}
		super.livingTick();
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return MonkeyEntity.getBreedingItems().test(stack);
	}

	public static boolean isBananaItem(ItemStack stack){
    	return ItemTags.getCollection().get(LivingThingsTags.BANANAS).contains(stack.getItem());
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return LivingThingsConfig.MONKEY.maxSpawnedInChunk.get();
	}

	@Override
	public boolean isOnLadder() {
		return this.isBesideClimbableBlock();
	}

	@Override
	public boolean isOnSameTeam(Entity entityIn) {
		if (this.isTamed()) {
			LivingEntity livingentity = this.getOwner();
			if (entityIn == livingentity) {
				return true;
			}
			if (entityIn instanceof TameableEntity) {
				return ((TameableEntity) entityIn).isOwner(livingentity);
			}
			if (livingentity != null) {
				return livingentity.isOnSameTeam(entityIn);
			}
		}
		return super.isOnSameTeam(entityIn);
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void setPartying(BlockPos pos, boolean isPartying) {
		this.jukeboxPosition = pos;
		this.partying = isPartying;
	}

	@OnlyIn(Dist.CLIENT)
	public boolean isPartying() {
		return this.partying;
	}

	@Override
	public boolean isSitting() {
		return this.dataManager.get(SITTING);
	}

	public void setSitting(boolean sitting) {
		this.dataManager.set(SITTING, sitting);
		this.navigator.clearPath();
		this.setAttackTarget((LivingEntity) null);
	}

	public boolean isBesideClimbableBlock() {
		return (this.dataManager.get(CLIMBING) & 1) != 0;
	}

	public void setBesideClimbableBlock(boolean climbing) {
		byte b0 = this.dataManager.get(CLIMBING);
		if (climbing) {
			b0 = (byte) (b0 | 1);
		} else {
			b0 = (byte) (b0 & -2);
		}
		this.dataManager.set(CLIMBING, b0);
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isChild() ? 0.31F : 0.67F;
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		return new ClimberPathNavigator(this, worldIn);
	}

	@Override
	public void travel(Vector3d travelVector) {
		if (this.isSitting()) {
			if (this.getNavigator().getPath() != null) {
				this.getNavigator().clearPath();
			}
			travelVector = Vector3d.ZERO;
		}
		super.travel(travelVector);
	}

	@Override
	protected int calculateFallDamage(float distance, float damageMultiplier) {
		return super.calculateFallDamage(distance, (damageMultiplier * 0.5F));
	}

	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getHeldItem(hand);
		Item item = stack.getItem();
		if (this.world.isRemote) {
			if (isLexicon(stack)) {
				return ActionResultType.PASS;
			}
			boolean flag = this.isOwner(player) || this.isTamed() || this.isBreedingItem(stack) && !this.isTamed();
			return flag ? ActionResultType.CONSUME : ActionResultType.PASS;
		} else {
			if (this.isTamed()) {
				if (this.isBreedingItem(stack) && this.getHealth() < this.getMaxHealth()) {
					if (!player.abilities.isCreativeMode) {
						stack.shrink(1);
					}
					this.heal(item.getFood().getHealing());
					return ActionResultType.SUCCESS;
				} else if (stack.isEmpty()) {
					this.setSitting(!this.isSitting());
					return ActionResultType.SUCCESS;
				}
			} else if (this.isBreedingItem(stack)) {
				if (!player.abilities.isCreativeMode) {
					stack.shrink(1);
				}
				if (this.rand.nextInt(4) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
					this.setTamedBy(player);
					this.setSitting(true);
					this.world.setEntityState(this, (byte) 7);
				} else {
					this.world.setEntityState(this, (byte) 6);
				}
				return ActionResultType.SUCCESS;
			}
			return super.func_230254_b_(player, hand);
		}
	}

	public static boolean canMonkeySpawn(EntityType<MonkeyEntity> parrotIn, IWorld worldIn, SpawnReason reason,
			BlockPos pos, Random random) {
		BlockState blockstate = worldIn.getBlockState(pos.down());
		return (blockstate.isIn(BlockTags.LEAVES) || blockstate.isIn(Blocks.GRASS_BLOCK)
				|| blockstate.isIn(BlockTags.LOGS) || blockstate.isIn(Blocks.AIR))
				&& worldIn.getLightSubtracted(pos, 0) > 8;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
