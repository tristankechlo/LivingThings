package com.tristankechlo.livingthings.entities;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.Util;

import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.passive.fish.AbstractGroupFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;

public class SeahorseEntity extends AbstractGroupFishEntity implements ILexiconEntry, IMobVariants {

	private static final ResourceLocation LEXICON = new ResourceLocation(LivingThings.MOD_ID, "passive_mobs/seahorse");
	private static final DataParameter<Byte> VARIANT = EntityDataManager.defineId(SeahorseEntity.class,
			DataSerializers.BYTE);

	public SeahorseEntity(EntityType<SeahorseEntity> type, World world) {
		super(type, world);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.SEAHORSE.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.SEAHORSE.speed.get());
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, (byte) 0);
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT tag) {
		super.addAdditionalSaveData(tag);
		tag.putByte("SeahorseVariant", getVariant());
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT tag) {
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
	protected float getStandingEyeHeight(Pose pose, EntitySize size) {
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
	protected ItemStack getBucketItemStack() {
		return new ItemStack(ModItems.SEAHORSE_BUCKET.get());
	}

	@Override
	protected void saveToBucketTag(ItemStack stack) {
		super.saveToBucketTag(stack);
		CompoundNBT compoundnbt = stack.getOrCreateTag();
		compoundnbt.putByte("BucketSeahorseVariantTag", this.getVariant());
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason spawnReason,
			ILivingEntityData entityData, CompoundNBT tag) {
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

	private static final class SeahorseData extends AbstractGroupFishEntity.GroupData {

		private final byte variant;

		public SeahorseData(AbstractGroupFishEntity fish, byte variant) {
			super(fish);
			this.variant = variant;
		}

	}

}
