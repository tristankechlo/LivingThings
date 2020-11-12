package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.util.ILexiconEntry;
import com.tristankechlo.livingthings.util.IMobVariants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.controller.FlyingMovementController;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowMobGoal;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SitGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
import net.minecraft.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.pathfinding.FlyingPathNavigator;
import net.minecraft.pathfinding.PathNavigator;
import net.minecraft.pathfinding.PathNodeType;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OwlEntity extends TameableEntity implements IFlyingAnimal, IMobVariants, ILexiconEntry {

	private static final DataParameter<Byte> OWL_VARIANT = EntityDataManager.createKey(OwlEntity.class, DataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID, "passive_mobs/owl");
	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
	private static final Ingredient TAMING_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS);
	public float flap;
	public float flapSpeed;
	public float oFlapSpeed;
	public float oFlap;
	private float flapping = 1.0F;

	public OwlEntity(EntityType<? extends OwlEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveController = new FlyingMovementController(this, 10, false);
		this.setPathPriority(PathNodeType.DANGER_FIRE, -1.0F);
		this.setPathPriority(PathNodeType.DAMAGE_FIRE, -1.0F);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
		OwlEntity child = ModEntityTypes.OWL_ENTITY.get().create(world);
		child.setVariant(this.getVariantFromParents(this, entity));
		return child;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.OWL.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, LivingThingsConfig.OWL.speed.get())
				.createMutableAttribute(Attributes.FLYING_SPEED, LivingThingsConfig.OWL.flyingSpeed.get());
	}

	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setVariant(OwlEntity.getWeightedRandomColorVariant(this.rand));
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public static byte getWeightedRandomColorVariant(Random random) {
		int colorBrownWeight = LivingThingsConfig.OWL.colorBrownWeight.get();
		int colorWhiteWeight = LivingThingsConfig.OWL.colorWhiteWeight.get();
		int colorBlackWeight = LivingThingsConfig.OWL.colorBlackWeight.get();
		if (colorBrownWeight <= 0 && colorWhiteWeight <= 0 && colorBlackWeight <= 0) {
			return 0;
		}
		WeightedMobVariant variant = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, colorBrownWeight), (byte) 0),
						new WeightedMobVariant(Math.max(0, colorWhiteWeight), (byte) 1),
						new WeightedMobVariant(Math.max(0, colorBlackWeight), (byte) 2)));
		return variant.variant;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new SitGoal(this));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, false, BREEDING_ITEMS));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
		this.goalSelector.addGoal(6, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(OWL_VARIANT, (byte) 0);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("OwlVariant")) {
			this.setVariant(compound.getByte("OwlVariant"));
		} else {
			this.setVariant((byte) 0);
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte("OwlVariant", this.getVariant());
	}

	@Override
	public void livingTick() {
		super.livingTick();
		this.calculateFlapping();
	}

	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		ItemStack itemstack = player.getHeldItem(hand);
		if (!this.isTamed() && TAMING_ITEMS.test(itemstack)) {
			if (!player.isCreative()) {
				itemstack.shrink(1);
			}
			if (!this.world.isRemote) {
				if (this.rand.nextInt(5) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
					this.setTamedBy(player);
					this.world.setEntityState(this, (byte) 7);
				} else {
					this.world.setEntityState(this, (byte) 6);
				}
			}
			return ActionResultType.func_233537_a_(this.world.isRemote);

		} else if (this.isBreedingItem(itemstack)) {
			if (!player.isCreative()) {
				itemstack.shrink(1);
			}
			if (!this.world.isRemote && this.canBreed() && this.getGrowingAge() == 0) {
				this.setInLove(player);
				return ActionResultType.SUCCESS;
			}
			return ActionResultType.func_233537_a_(this.world.isRemote);
		} else if (!this.isFlying() && this.isTamed() && this.isOwner(player)) {
			if (!this.world.isRemote) {
				this.func_233687_w_(!this.isSitting());
			}

			return ActionResultType.func_233537_a_(this.world.isRemote);
		} else {
			return super.func_230254_b_(player, hand);
		}
	}

	private void calculateFlapping() {
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float) ((double) this.flapSpeed + (double) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
		this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
		if (!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}

		this.flapping = (float) ((double) this.flapping * 0.9D);
		Vector3d vector3d = this.getMotion();
		if (!this.onGround && vector3d.y < 0.0D) {
			this.setMotion(vector3d.mul(1.0D, 0.6D, 1.0D));
		}

		this.flap += this.flapping * 2.0F;
	}

	public static boolean canOwlSpawn(EntityType<OwlEntity> parrotIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		BlockState blockstate = worldIn.getBlockState(pos.down());
		return (blockstate.isIn(BlockTags.LEAVES) || blockstate.isIn(Blocks.GRASS_BLOCK) || blockstate.isIn(BlockTags.LOGS) || blockstate.isIn(Blocks.AIR)) && worldIn.getLightSubtracted(pos, 0) > 8;
	}

	@Override
	protected PathNavigator createNavigator(World worldIn) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanSwim(true);
		flyingpathnavigator.setCanEnterDoors(true);
		return flyingpathnavigator;
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public int getMaxSpawnedInChunk() {
		return LivingThingsConfig.OWL.maxSpawns.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isChild() ? 0.45F : 0.9F;
	}

	@Override
	public boolean onLivingFall(float distance, float damageMultiplier) {
		return false;
	}

	@Override
	protected void updateFallState(double y, boolean onGroundIn, BlockState state, BlockPos pos) {
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.OWL_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.OWL_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.OWL_DEATH.get();
	}

	@Override
	protected float playFlySound(float volume) {
		this.playSound(ModSounds.OWL_FLY.get(), 0.15F, 1.0F);
		return volume + this.flapSpeed / 2.0F;
	}

	@Override
	protected boolean makeFlySound() {
		return true;
	}

	@Override
	protected void collideWithEntity(Entity entityIn) {
		if (!(entityIn instanceof PlayerEntity)) {
			super.collideWithEntity(entityIn);
		}
	}

	@Override
	public boolean attackEntityFrom(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			this.func_233687_w_(false);
			return super.attackEntityFrom(source, amount);
		}
	}

	public boolean isFlying() {
		return !this.onGround;
	}

	@Override
	public byte getVariant() {
		return this.dataManager.get(OWL_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.dataManager.set(OWL_VARIANT, variant);
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
