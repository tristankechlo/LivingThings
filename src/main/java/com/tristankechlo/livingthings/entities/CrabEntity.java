package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.util.IMobVariants;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class CrabEntity extends CreatureEntity implements IMobVariants {

	private static final DataParameter<Byte> CRAB_VARIANT = EntityDataManager.createKey(CrabEntity.class, DataSerializers.BYTE);
	
	public CrabEntity(EntityType<? extends CrabEntity> type, World worldIn) {
		super(type, worldIn);
		this.stepHeight = 1.0F;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.CRAB.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 1.05F)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 25.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, LivingThingsConfig.CRAB.damage.get());
	}

	public static boolean canCrabSpawn(EntityType<? extends CrabEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		BlockState state = worldIn.getBlockState(pos.down());
		if(state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.SAND) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.RED_SAND)) {
			return  worldIn.getLightSubtracted(pos, 0) > 6;
		}
		return false;
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		MobVariant variant = WeightedRandom.getRandomItem(this.rand, ImmutableList.of(
				new MobVariant(LivingThingsConfig.CRAB.color1Weight.get(), (byte) 0),
				new MobVariant(LivingThingsConfig.CRAB.color2Weight.get(), (byte) 1),
				new MobVariant(LivingThingsConfig.CRAB.colorAlbinoWeight.get(), (byte) 15)));
		this.setVariant(variant.variant);
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(CRAB_VARIANT, (byte)0);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte("CrabVariant", this.getVariant());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("CrabVariant")) {
			this.setVariant(compound.getByte("CrabVariant"));
		} else {
			this.setVariant((byte) 0);
		}
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public byte getVariant() {
		return this.dataManager.get(CRAB_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.dataManager.set(CRAB_VARIANT, variant);
	}

}
