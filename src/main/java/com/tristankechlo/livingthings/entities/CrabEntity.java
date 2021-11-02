package com.tristankechlo.livingthings.entities;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.entities.misc.IScaleableMob;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.WeighedRandom;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
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

public class CrabEntity extends Animal implements IMobVariants, NeutralMob, IScaleableMob, ILexiconEntry {

	private static final EntityDataAccessor<Byte> CRAB_VARIANT = SynchedEntityData.defineId(CrabEntity.class,
			EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> CRAB_SCALING = SynchedEntityData.defineId(CrabEntity.class,
			EntityDataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/crab");
	private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.COD);
	private int angerTime;
	private UUID angerTarget;

	public CrabEntity(EntityType<? extends CrabEntity> type, Level worldIn) {
		super(type, worldIn);
		this.maxUpStep = 1.0F;
		this.setPathfindingMalus(BlockPathTypes.WATER, 1.0F);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		CrabEntity entityChild = ModEntityTypes.CRAB.get().create(this.level);
		entityChild.setVariant(this.getVariantFromParents(this, entity));
		entityChild.setScaling(CrabEntity.getWeightedRandomScaling(this.random));

		double health = LivingThingsConfig.CRAB.health.get();
		if (health > 0.0D) {
			entityChild.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health + entityChild.getScaling());
			entityChild.setHealth(entityChild.getMaxHealth());
		}

		return entityChild;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.CRAB.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.CRAB.speed.get()).add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.CRAB.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new BetterMeleeAttackGoal(this, 1.05D, false, () -> {
			return LivingThingsConfig.CRAB.canAttack.get();
		}));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.1D));
		this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(3, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	public static boolean canCrabSpawn(EntityType<CrabEntity> animal, LevelAccessor world, MobSpawnType reason,
			BlockPos pos, Random random) {
		BlockState state = world.getBlockState(pos.below());
		return (world.isWaterAt(pos))
				|| (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.SAND) || state.is(Blocks.GRAVEL));
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
			MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
		this.setVariant(CrabEntity.getWeightedRandomColorVariant(this.random));
		this.setScaling(CrabEntity.getWeightedRandomScaling(this.random));

		double health = LivingThingsConfig.CRAB.health.get();
		if (health > 0.0D) {
			this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health + this.getScaling());
			this.setHealth(this.getMaxHealth());
		}

		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public static byte getWeightedRandomColorVariant(Random random) {
		int color1Weight = LivingThingsConfig.CRAB.color1Weight.get();
		int color2Weight = LivingThingsConfig.CRAB.color2Weight.get();
		int albinoWeight = LivingThingsConfig.CRAB.colorAlbinoWeight.get();
		if (color1Weight <= 0 && color2Weight <= 0 && albinoWeight <= 0) {
			return 0;
		}
		Optional<WeightedMobVariant> variant = WeighedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
						new WeightedMobVariant(Math.max(0, color2Weight), (byte) 1),
						new WeightedMobVariant(Math.max(0, albinoWeight), (byte) 15)));
		return variant.get().variant;
	}

	public static byte getWeightedRandomScaling(Random random) {
		int scaling1Weight = LivingThingsConfig.CRAB.scaling1Weight.get();
		int scaling2Weight = LivingThingsConfig.CRAB.scaling2Weight.get();
		int scaling3Weight = LivingThingsConfig.CRAB.scaling3Weight.get();
		int scaling4Weight = LivingThingsConfig.CRAB.scaling4Weight.get();
		if (scaling1Weight <= 0 && scaling2Weight <= 0 && scaling3Weight <= 0 && scaling4Weight <= 0) {
			return 0;
		}
		Optional<WeightedMobScaling> scaling = WeighedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobScaling(Math.max(0, scaling1Weight), (byte) 0),
						new WeightedMobScaling(Math.max(0, scaling2Weight), (byte) 2),
						new WeightedMobScaling(Math.max(0, scaling3Weight), (byte) -2),
						new WeightedMobScaling(Math.max(0, scaling4Weight), (byte) 6)));
		return scaling.get().scaling;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CRAB_VARIANT, (byte) 0);
		this.entityData.define(CRAB_SCALING, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("CrabVariant", this.getVariant());
		compound.putByte("CrabScaling", this.getScaling());
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getByte("CrabVariant"));
		this.setScaling(compound.getByte("CrabScaling"));
		if (this.level instanceof ServerLevel) {
			this.readPersistentAngerSaveData((ServerLevel) this.level, compound);
		}
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.CRAB.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		if (this.isBaby()) {
			return 0.175F;
		}
		return this.getDimensions(poseIn).height * 0.9F;
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.98F;
	}

	@Override
	public byte getVariant() {
		return this.entityData.get(CRAB_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.entityData.set(CRAB_VARIANT, variant);
	}

	@Override
	public byte getScaling() {
		return this.entityData.get(CRAB_SCALING);
	}

	@Override
	public void setScaling(byte scaling) {
		this.entityData.set(CRAB_SCALING, scaling);
		this.reapplyPosition();// center bounding box
		this.refreshDimensions();
		this.xpReward = Math.abs(scaling) * this.random.nextInt(2);
	}

	@Override
	public void onSyncedDataUpdated(EntityDataAccessor<?> key) {
		if (CRAB_SCALING.equals(key)) {
			this.refreshDimensions();
		}
		super.onSyncedDataUpdated(key);
	}

	@Override
	public void refreshDimensions() {
		double d0 = this.getX();
		double d1 = this.getY();
		double d2 = this.getZ();
		super.refreshDimensions();
		this.setPos(d0, d1, d2);
	}

	@Override
	public EntityDimensions getDimensions(Pose poseIn) {
		if (this.isBaby()) {
			return super.getDimensions(poseIn);
		}
		return super.getDimensions(poseIn).scale(0.85F + (0.1F * this.getScaling()));
	}

	@Override
	public int getRemainingPersistentAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setRemainingPersistentAngerTime(int time) {
		this.angerTime = time;
	}

	@Override
	public UUID getPersistentAngerTarget() {
		return this.angerTarget;
	}

	@Override
	public void setPersistentAngerTarget(UUID target) {
		this.angerTarget = target;
	}

	@Override
	public void startPersistentAngerTimer() {
		this.setRemainingPersistentAngerTime(rangedInteger.sample(this.random));
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
