package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

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
import net.minecraftforge.event.ForgeEventFactory;

public class OwlEntity extends TameableEntity implements IFlyingAnimal, IMobVariants, ILexiconEntry {

	private static final DataParameter<Byte> OWL_VARIANT = EntityDataManager.defineId(OwlEntity.class,
			DataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID, "passive_mobs/owl");
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.MELON_SEEDS, Items.PUMPKIN_SEEDS,
			Items.BEETROOT_SEEDS);
	private static final Ingredient TAMING_ITEMS = Ingredient.of(Items.WHEAT_SEEDS);
	public float flap;
	public float flapSpeed;
	public float oFlapSpeed;
	public float oFlap;
	private float flapping = 1.0F;

	public OwlEntity(EntityType<? extends OwlEntity> type, World worldIn) {
		super(type, worldIn);
		this.moveControl = new FlyingMovementController(this, 10, false);
		this.setPathfindingMalus(PathNodeType.DANGER_FIRE, -1.0F);
		this.setPathfindingMalus(PathNodeType.DAMAGE_FIRE, -1.0F);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entity) {
		OwlEntity child = ModEntityTypes.OWL_ENTITY.get().create(world);
		child.setVariant(this.getVariantFromParents(this, entity));
		return child;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.OWL.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.OWL.speed.get())
				.add(Attributes.FLYING_SPEED, LivingThingsConfig.OWL.flyingSpeed.get());
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		this.setVariant(OwlEntity.getWeightedRandomColorVariant(this.random));
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
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
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(OWL_VARIANT, (byte) 0);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("OwlVariant")) {
			this.setVariant(compound.getByte("OwlVariant"));
		} else {
			this.setVariant((byte) 0);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("OwlVariant", this.getVariant());
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.calculateFlapping();
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!this.isTame() && TAMING_ITEMS.test(stack)) {
			if (!this.level.isClientSide()) {
				this.usePlayerItem(player, stack);
				if (this.random.nextInt(5) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
					this.tame(player);
					this.level.broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 6);
				}
			}
			return ActionResultType.sidedSuccess(this.level.isClientSide());

		} else if (!this.isFlying() && this.isTame() && this.isOwnedBy(player) && TAMING_ITEMS.test(stack)) {

			this.setOrderedToSit(!this.isCrouching());
			return ActionResultType.sidedSuccess(this.level.isClientSide());

		} else {
			return super.mobInteract(player, hand);
		}
	}

	private void calculateFlapping() {
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float) ((double) this.flapSpeed
				+ (double) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
		this.flapSpeed = MathHelper.clamp(this.flapSpeed, 0.0F, 1.0F);
		if (!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}

		this.flapping = (float) ((double) this.flapping * 0.9D);
		Vector3d vector3d = this.getDeltaMovement();
		if (!this.onGround && vector3d.y < 0.0D) {
			this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
		}

		this.flap += this.flapping * 2.0F;
	}

	public static boolean canOwlSpawn(EntityType<OwlEntity> parrotIn, IWorld worldIn, SpawnReason reason, BlockPos pos,
			Random random) {
		BlockState blockstate = worldIn.getBlockState(pos.below());
		return (blockstate.is(BlockTags.LEAVES) || blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(BlockTags.LOGS)
				|| blockstate.is(Blocks.AIR)) && worldIn.getRawBrightness(pos, 0) > 8;
	}

	@Override
	protected PathNavigator createNavigation(World worldIn) {
		FlyingPathNavigator flyingpathnavigator = new FlyingPathNavigator(this, worldIn);
		flyingpathnavigator.setCanOpenDoors(false);
		flyingpathnavigator.setCanFloat(true);
		flyingpathnavigator.setCanPassDoors(true);
		return flyingpathnavigator;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.OWL.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isBaby() ? 0.45F : 0.9F;
	}

	@Override
	public boolean causeFallDamage(float distance, float damageMultiplier) {
		super.causeFallDamage(distance, damageMultiplier);
		return false;
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
	protected void doPush(Entity entityIn) {
		if (!(entityIn instanceof PlayerEntity)) {
			super.doPush(entityIn);
		}
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (this.isInvulnerableTo(source)) {
			return false;
		} else {
			this.setOrderedToSit(false);
			return super.hurt(source, amount);
		}
	}

	public boolean isFlying() {
		return !this.onGround;
	}

	@Override
	public byte getVariant() {
		return this.entityData.get(OWL_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.entityData.set(OWL_VARIANT, variant);
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
