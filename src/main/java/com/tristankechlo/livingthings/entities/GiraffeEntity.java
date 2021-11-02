package com.tristankechlo.livingthings.entities;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

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
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class GiraffeEntity extends Animal implements NeutralMob, IMobVariants, ILexiconEntry {

	private static final EntityDataAccessor<Byte> GIRAFFE_VARIANT = SynchedEntityData.defineId(GiraffeEntity.class,
			EntityDataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/giraffe");
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.WHEAT);
	private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public GiraffeEntity(EntityType<? extends GiraffeEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entityIn) {
		GiraffeEntity entityChild = ModEntityTypes.GIRAFFE.get().create(this.level);
		entityChild.setVariant(this.getVariantFromParents(this, entityIn));
		return entityChild;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
			MobSpawnType reason, SpawnGroupData spawnDataIn, CompoundTag dataTag) {
		this.setVariant(GiraffeEntity.getWeightedRandomColorVariant(this.random));
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public static byte getWeightedRandomColorVariant(Random random) {
		int color1Weight = LivingThingsConfig.GIRAFFE.color1Weight.get();
		int color2Weight = LivingThingsConfig.GIRAFFE.color2Weight.get();
		int albinoWeight = LivingThingsConfig.GIRAFFE.colorAlbinoWeight.get();
		if (color1Weight <= 0 && color2Weight <= 0 && albinoWeight <= 0) {
			return 0;
		}
		Optional<WeightedMobVariant> variant = WeighedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
						new WeightedMobVariant(Math.max(0, color2Weight), (byte) 1),
						new WeightedMobVariant(Math.max(0, albinoWeight), (byte) 15)));
		return variant.get().variant;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.GIRAFFE.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.GIRAFFE.speed.get())
				.add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.GIRAFFE.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false, () -> {
			return LivingThingsConfig.GIRAFFE.canAttack.get();
		}));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(GIRAFFE_VARIANT, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("GiraffeVariant", this.getVariant());
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getByte("GiraffeVariant"));
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
		return LivingThingsConfig.GIRAFFE.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 1.55F : 3.15F;
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
	public byte getVariant() {
		return this.getEntityData().get(GIRAFFE_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.getEntityData().set(GIRAFFE_VARIANT, variant);
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
