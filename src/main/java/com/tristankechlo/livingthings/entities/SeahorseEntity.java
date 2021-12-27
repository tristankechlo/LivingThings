package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.LivingThingsTags;
import com.tristankechlo.livingthings.misc.Util;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.animal.AbstractSchoolingFish;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.material.Fluid;

public class SeahorseEntity extends AbstractSchoolingFish implements ILexiconEntry, IMobVariants {

	private static final ResourceLocation LEXICON = new ResourceLocation(LivingThings.MOD_ID, "passive_mobs/seahorse");
	private static final EntityDataAccessor<Byte> VARIANT = SynchedEntityData.defineId(SeahorseEntity.class,
			EntityDataSerializers.BYTE);
	private static Tag<Fluid> spawnableOn = null;

	public SeahorseEntity(EntityType<SeahorseEntity> type, Level world) {
		super(type, world);
	}

	public static boolean checkSeahorseSpawnRules(EntityType<SeahorseEntity> entity, LevelAccessor world, MobSpawnType reason,
			BlockPos pos, Random random) {
		if (spawnableOn == null) {
			spawnableOn = FluidTags.getAllTags().getTagOrEmpty(LivingThingsTags.SEAHORSE_SPAWNABLE_ON);
		}
		return spawnableOn.contains(world.getFluidState(pos).getType())
				&& spawnableOn.contains(world.getFluidState(pos.above()).getType());
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.SEAHORSE.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.SEAHORSE.speed.get());
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 6.0F));
		this.goalSelector.addGoal(9, new RandomLookAroundGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundTag tag) {
		super.addAdditionalSaveData(tag);
		tag.putByte("SeahorseVariant", getVariant());
	}

	@Override
	public void readAdditionalSaveData(CompoundTag tag) {
		super.readAdditionalSaveData(tag);
		this.setVariant(tag.getByte("SeahorseVariant"));
	}

	@Override
	protected SoundEvent getFlopSound() {
		return ModSounds.SEAHORSE_FLOP.get();
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.SEAHORSE.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose pose, EntityDimensions size) {
		return 0.6F;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON;
	}

	@Override
	public byte getVariant() {
		return this.entityData.get(VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.entityData.set(VARIANT, Util.clamp(variant, (byte) 0, (byte) 4));
	}

	@Override
	public ItemStack getBucketItemStack() {
		return new ItemStack(ModItems.SEAHORSE_BUCKET.get());
	}

	@Override
	public void saveToBucketTag(ItemStack stack) {
		super.saveToBucketTag(stack);
		CompoundTag compoundnbt = stack.getOrCreateTag();
		compoundnbt.putByte("BucketSeahorseVariantTag", this.getVariant());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty,
			MobSpawnType spawnReason, SpawnGroupData entityData, CompoundTag tag) {
		entityData = super.finalizeSpawn(world, difficulty, spawnReason, entityData, tag);
		if (tag != null && tag.contains("BucketSeahorseVariantTag", 1)) {
			this.setVariant(tag.getByte("BucketSeahorseVariantTag"));
			return entityData;
		} else {
			byte variant = 0;
			if (entityData instanceof SeahorseData) {
				variant = ((SeahorseData) entityData).variant;
				entityData = new SeahorseData(this, variant);
			} else {
				final int blueWeight = LivingThingsConfig.SEAHORSE.blueWeight.get();
				final int greenWeight = LivingThingsConfig.SEAHORSE.greenWeight.get();
				final int purpleWeight = LivingThingsConfig.SEAHORSE.purpleWeight.get();
				final int yellowWeight = LivingThingsConfig.SEAHORSE.yellowWeight.get();
				final int redWeight = LivingThingsConfig.SEAHORSE.redWeight.get();
				variant = getRandomVariant(random, new byte[] { 0, 1, 2, 3, 4 },
						new int[] { blueWeight, greenWeight, purpleWeight, yellowWeight, redWeight });
			}
			this.setVariant(variant);
		}
		return entityData;
	}

	private static final class SeahorseData extends AbstractSchoolingFish.SchoolSpawnGroupData {

		private final byte variant;

		public SeahorseData(AbstractSchoolingFish fish, byte variant) {
			super(fish);
			this.variant = variant;
		}

	}

}
