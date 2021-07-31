package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
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
import net.minecraft.util.RangedInteger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GiraffeEntity extends AnimalEntity implements IAngerable, IMobVariants, ILexiconEntry {

	private static final DataParameter<Byte> GIRAFFE_VARIANT = EntityDataManager.defineId(GiraffeEntity.class,
			DataSerializers.BYTE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/giraffe");
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.WHEAT);
	private static final RangedInteger rangedInteger = TickRangeConverter.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public GiraffeEntity(EntityType<? extends GiraffeEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity entityIn) {
		GiraffeEntity entityChild = ModEntityTypes.GIRAFFE_ENTITY.get().create(this.level);
		entityChild.setVariant(this.getVariantFromParents(this, entityIn));
		return entityChild;
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason,
			ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
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
		WeightedMobVariant variant = WeightedRandom.getRandomItem(random,
				ImmutableList.of(new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
						new WeightedMobVariant(Math.max(0, color2Weight), (byte) 1),
						new WeightedMobVariant(Math.max(0, albinoWeight), (byte) 15)));
		return variant.variant;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.GIRAFFE.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.GIRAFFE.speed.get())
				.add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.GIRAFFE.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false, () -> {
			return LivingThingsConfig.GIRAFFE.canAttack.get();
		}));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetAngerGoal<>(this, true));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(GIRAFFE_VARIANT, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		compound.putByte("GiraffeVariant", this.getVariant());
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		this.setVariant(compound.getByte("GiraffeVariant"));
		if (this.level instanceof ServerWorld) {
			this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
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
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
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
		this.setRemainingPersistentAngerTime(rangedInteger.randomValue(this.random));
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
