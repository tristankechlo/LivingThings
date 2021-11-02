package com.tristankechlo.livingthings.entities;

import java.util.Optional;
import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.WeighedRandom;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.TamableAnimal;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowMobGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.event.ForgeEventFactory;

public class OwlEntity extends TamableAnimal implements FlyingAnimal, IMobVariants, ILexiconEntry {

	private static final EntityDataAccessor<Byte> OWL_VARIANT = SynchedEntityData.defineId(OwlEntity.class,
			EntityDataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID, "passive_mobs/owl");
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.MELON_SEEDS, Items.PUMPKIN_SEEDS,
			Items.BEETROOT_SEEDS);
	private static final Ingredient TAMING_ITEMS = Ingredient.of(Items.WHEAT_SEEDS);
	public float flap;
	public float flapSpeed;
	public float oFlapSpeed;
	public float oFlap;
	private float flapping = 1.0F;

	public OwlEntity(EntityType<? extends OwlEntity> type, Level worldIn) {
		super(type, worldIn);
		this.moveControl = new FlyingMoveControl(this, 10, false);
		this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
		this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		OwlEntity child = ModEntityTypes.OWL.get().create(world);
		child.setVariant(this.getVariantFromParents(this, entity));
		return child;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.OWL.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.OWL.speed.get())
				.add(Attributes.FLYING_SPEED, LivingThingsConfig.OWL.flyingSpeed.get());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
			MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
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
		Optional<WeightedMobVariant> variant = WeighedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, colorBrownWeight), (byte) 0),
						new WeightedMobVariant(Math.max(0, colorWhiteWeight), (byte) 1),
						new WeightedMobVariant(Math.max(0, colorBlackWeight), (byte) 2)));
		return variant.get().variant;
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.0D, BREEDING_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.0D));
		this.goalSelector.addGoal(5, new FollowOwnerGoal(this, 1.0D, 5.0F, 1.0F, true));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomFlyingGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new FollowMobGoal(this, 1.0D, 3.0F, 7.0F));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(OWL_VARIANT, (byte) 0);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("OwlVariant")) {
			this.setVariant(compound.getByte("OwlVariant"));
		} else {
			this.setVariant((byte) 0);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("OwlVariant", this.getVariant());
	}

	@Override
	public void aiStep() {
		super.aiStep();
		this.calculateFlapping();
	}

	@Override
	public InteractionResult mobInteract(Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!this.isTame() && TAMING_ITEMS.test(stack)) {
			if (!this.level.isClientSide()) {
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
				if (this.random.nextInt(5) == 0 && !ForgeEventFactory.onAnimalTame(this, player)) {
					this.tame(player);
					this.level.broadcastEntityEvent(this, (byte) 7);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 6);
				}
			}
			return InteractionResult.sidedSuccess(this.level.isClientSide());

		} else if (!this.isFlying() && this.isTame() && this.isOwnedBy(player) && TAMING_ITEMS.test(stack)) {

			this.setOrderedToSit(!this.isCrouching());
			return InteractionResult.sidedSuccess(this.level.isClientSide());

		} else {
			return super.mobInteract(player, hand);
		}
	}

	private void calculateFlapping() {
		this.oFlap = this.flap;
		this.oFlapSpeed = this.flapSpeed;
		this.flapSpeed = (float) ((double) this.flapSpeed
				+ (double) (!this.onGround && !this.isPassenger() ? 4 : -1) * 0.3D);
		this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
		if (!this.onGround && this.flapping < 1.0F) {
			this.flapping = 1.0F;
		}

		this.flapping = (float) ((double) this.flapping * 0.9D);
		Vec3 vector3d = this.getDeltaMovement();
		if (!this.onGround && vector3d.y < 0.0D) {
			this.setDeltaMovement(vector3d.multiply(1.0D, 0.6D, 1.0D));
		}

		this.flap += this.flapping * 2.0F;
	}

	public static boolean canOwlSpawn(EntityType<OwlEntity> parrotIn, LevelAccessor worldIn, MobSpawnType reason,
			BlockPos pos, Random random) {
		BlockState blockstate = worldIn.getBlockState(pos.below());
		return (blockstate.is(BlockTags.LEAVES) || blockstate.is(Blocks.GRASS_BLOCK) || blockstate.is(BlockTags.LOGS)
				|| blockstate.is(Blocks.AIR)) && worldIn.getRawBrightness(pos, 0) > 8;
	}

	@Override
	protected PathNavigation createNavigation(Level worldIn) {
		FlyingPathNavigation flyingpathnavigator = new FlyingPathNavigation(this, worldIn);
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
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.45F : 0.9F;
	}

	@Override
	public boolean causeFallDamage(float p_149683_, float p_149684_, DamageSource p_149685_) {
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
	protected void onFlap() {
		this.playSound(ModSounds.OWL_FLY.get(), 0.15F, 1.0F);
	}
	/*
	 * removed in 1.17.1?
	 * 
	 * @Override protected boolean makeFlySound() { return true; }
	 */

	@Override
	protected void doPush(Entity entityIn) {
		if (!(entityIn instanceof Player)) {
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
