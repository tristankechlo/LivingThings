package com.tristankechlo.livingthings.entities;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Nullable;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entities.misc.IGenderedMob;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
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
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;

public class LionEntity extends Animal implements NeutralMob, IMobVariants, IGenderedMob, ILexiconEntry {

	private static final EntityDataAccessor<Boolean> MALE = SynchedEntityData.defineId(LionEntity.class,
			EntityDataSerializers.BOOLEAN);
	private static final EntityDataAccessor<Byte> LION_VARIANT = SynchedEntityData.defineId(LionEntity.class,
			EntityDataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"hostile_mobs/lion");
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.BEEF, Items.CHICKEN, Items.RABBIT);
	private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public LionEntity(EntityType<? extends LionEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public static boolean checkLionSpawnRules(EntityType<LionEntity> animal, LevelAccessor world, MobSpawnType reason,
			BlockPos pos, Random random) {
		return world.getBlockState(pos.below()).is(LivingThingsTags.LION_SPAWNABLE_ON)
				&& isBrightEnoughToSpawn(world, pos);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entityIn) {
		LionEntity entityChild = ModEntityTypes.LION.get().create(this.level);
		entityChild.setGender(LionEntity.getWeightedRandomGender(this.random));
		entityChild.setVariant(this.getVariantFromParents(this, entityIn));
		return entityChild;
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor worldIn, DifficultyInstance difficultyIn,
			MobSpawnType reason, @Nullable SpawnGroupData spawnDataIn, @Nullable CompoundTag dataTag) {
		this.setGender(LionEntity.getWeightedRandomGender(this.random));
		this.setVariant(LionEntity.getWeightedRandomColorVariant(this.random));
		return super.finalizeSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}

	public static Gender getWeightedRandomGender(Random random) {
		int maleWeight = LivingThingsConfig.LION.genderMaleWeight.get();
		int femaleWeight = LivingThingsConfig.LION.genderFemaleWeight.get();
		if (maleWeight <= 0 && femaleWeight <= 0) {
			return random.nextBoolean() ? Gender.MALE : Gender.FEMALE;
		}
		Optional<WeightedGender> gender = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedGender(Math.max(0, maleWeight), Gender.MALE),
						new WeightedGender(Math.max(0, femaleWeight), Gender.FEMALE)));
		return gender.get().gender;
	}

	public static byte getWeightedRandomColorVariant(Random random) {
		int color1Weight = LivingThingsConfig.LION.color1Weight.get();
		int albinoWeight = LivingThingsConfig.LION.colorAlbinoWeight.get();
		if (color1Weight <= 0 && albinoWeight <= 0) {
			return 0;
		}
		Optional<WeightedMobVariant> variant = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
						new WeightedMobVariant(Math.max(0, albinoWeight), (byte) 15)));
		return variant.get().variant;
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.LION.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.LION.speed.get()).add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.LION.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.1D, false, () -> {
			return LivingThingsConfig.LION.canAttack.get();
		}) {
			@Override
			public double getAttackReachSqr(LivingEntity attackTarget) {
				return (double) (this.mob.getBbWidth() * 1.8F * this.mob.getBbWidth() * 1.8F
						+ attackTarget.getBbWidth());
			}
		});
		this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Player.class, 7.0F));
		this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, 10, true, true, null));
		this.targetSelector.addGoal(2, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MALE, false);
		this.entityData.define(LION_VARIANT, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		if (this.getGender() == Gender.MALE) {
			compound.putBoolean("IsMale", true);
		}
		compound.putByte("LionVariant", this.getVariant());
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.getBoolean("IsMale")) {
			this.setGender(Gender.MALE);
		} else {
			this.setGender(Gender.FEMALE);
		}
		this.setVariant(compound.getByte("LionVariant"));
		if (this.level instanceof ServerLevel) {
			this.readPersistentAngerSaveData((ServerLevel) this.level, compound);
		}
	}

	@Override
	public boolean canMate(Animal otherAnimal) {
		if (otherAnimal == this) {
			return false;
		}
		if (otherAnimal instanceof LionEntity) {
			LionEntity otherLion = (LionEntity) otherAnimal;
			if (this.getGender() != otherLion.getGender()) {
				return (this.isInLove() && otherLion.isInLove());
			}
		}
		return false;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.85F;
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.LION_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.LION_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.LION_DEATH.get();
	}

	@Override
	public double getEyeY() {
		return this.getY() + 1.4D;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.LION.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.7F : 1.45F;
	}

	@Override
	public Gender getGender() {
		if (this.getEntityData().get(MALE)) {
			return Gender.MALE;
		}
		return Gender.FEMALE;
	}

	@Override
	public void setGender(Gender gender) {
		if (gender == Gender.MALE) {
			this.getEntityData().set(MALE, true);
		} else {
			this.getEntityData().set(MALE, false);
		}
	}

	@Override
	public byte getVariant() {
		return this.getEntityData().get(LION_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.getEntityData().set(LION_VARIANT, variant);
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
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
}
