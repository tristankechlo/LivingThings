package com.tristankechlo.livingthings.entities;

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

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
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
import net.minecraft.util.DamageSource;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class LionEntity extends AnimalEntity implements IAngerable, IMobVariants, IGenderedMob, ILexiconEntry {

	private static final DataParameter<Boolean> MALE = EntityDataManager.defineId(LionEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Byte> LION_VARIANT = EntityDataManager.defineId(LionEntity.class,
			DataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"hostile_mobs/lion");
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.BEEF, Items.CHICKEN, Items.RABBIT);
	private static final RangedInteger rangedInteger = TickRangeConverter.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public LionEntity(EntityType<? extends LionEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entityIn) {
		LionEntity entityChild = ModEntityTypes.LION_ENTITY.get().create(this.level);
		entityChild.setGender(LionEntity.getWeightedRandomGender(this.random));
		entityChild.setVariant(this.getVariantFromParents(this, entityIn));
		return entityChild;
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			@Nullable ILivingEntityData spawnDataIn, @Nullable CompoundNBT dataTag) {
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
		WeightedGender gender = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedGender(Math.max(0, maleWeight), Gender.MALE),
						new WeightedGender(Math.max(0, femaleWeight), Gender.FEMALE)));
		return gender.gender;
	}

	public static byte getWeightedRandomColorVariant(Random random) {
		int color1Weight = LivingThingsConfig.LION.color1Weight.get();
		int albinoWeight = LivingThingsConfig.LION.colorAlbinoWeight.get();
		if (color1Weight <= 0 && albinoWeight <= 0) {
			return 0;
		}
		WeightedMobVariant variant = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
						new WeightedMobVariant(Math.max(0, albinoWeight), (byte) 15)));
		return variant.variant;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.LION.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.LION.speed.get()).add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.LION.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.1D, false) {
			@Override
			public double getAttackReachSqr(LivingEntity attackTarget) {
				return (double) (this.mob.getBbWidth() * 1.8F * this.mob.getBbWidth() * 1.8F
						+ attackTarget.getBbWidth());
			}
		});
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 7.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, (new HurtByTargetGoal(this)).setAlertOthers());
		this.targetSelector.addGoal(1,
				new NearestAttackableTargetGoal<>(this, PlayerEntity.class, 10, true, true, null));
		this.targetSelector.addGoal(2, new ResetAngerGoal<>(this, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(MALE, false);
		this.entityData.define(LION_VARIANT, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		if (this.getGender() == Gender.MALE) {
			compound.putBoolean("IsMale", true);
		}
		compound.putByte("LionVariant", this.getVariant());
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (compound.getBoolean("IsMale")) {
			this.setGender(Gender.MALE);
		} else {
			this.setGender(Gender.FEMALE);
		}
		this.setVariant(compound.getByte("LionVariant"));
		if (this.level instanceof ServerWorld) {
			this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
		}
	}

	@Override
	public boolean canMate(AnimalEntity otherAnimal) {
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
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
		this.setRemainingPersistentAngerTime(rangedInteger.randomValue(this.random));
	}
}
