package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.util.IMobVariants;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.HurtByTargetGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.RandomWalkingGoal;
import net.minecraft.entity.ai.goal.ResetAngerGoal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class CrabEntity extends CreatureEntity implements IMobVariants, IAngerable {

	private static final DataParameter<Byte> CRAB_VARIANT = EntityDataManager.createKey(CrabEntity.class, DataSerializers.BYTE);
	private static final RangedInteger rangedInteger = TickRangeConverter.convertRange(20, 39);
	private int angerTime;
	private UUID angerTarget;
	
	public CrabEntity(EntityType<? extends CrabEntity> type, World worldIn) {
		super(type, worldIn);
		this.stepHeight = 1.0F;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.CRAB.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 25.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, LivingThingsConfig.CRAB.damage.get());
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new BetterMeleeAttackGoal(this, 1.05D, false));
		this.goalSelector.addGoal(1, new RandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new LookAtGoal(this, PlayerEntity.class, 5.0F));
		this.goalSelector.addGoal(3, new LookRandomlyGoal(this));
		
		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetAngerGoal<>(this, true));
	}

	public static boolean canCrabSpawn(EntityType<CrabEntity> animal, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		BlockState state = worldIn.getBlockState(pos.down());
		if(state.isIn(Blocks.GRASS_BLOCK) || state.isIn(Blocks.SAND) || state.isIn(Blocks.DIRT) || state.isIn(Blocks.RED_SAND)) {
			return worldIn.getLightSubtracted(pos, 0) > 6;
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
		this.setVariant(CrabEntity.getWeightedRandomColorVariant(this.rand));
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	public static byte getWeightedRandomColorVariant(Random random) {
		int color1Weight = LivingThingsConfig.CRAB.color1Weight.get();
		int color2Weight = LivingThingsConfig.CRAB.color2Weight.get();
		int albinoWeight = LivingThingsConfig.CRAB.colorAlbinoWeight.get();
		if(color1Weight <= 0 && color2Weight <= 0 && albinoWeight <= 0) {
			return 0;
		}
		WeightedMobVariant variant = WeightedRandom.getRandomItem(random, ImmutableList.of(
				new WeightedMobVariant(Math.max(0, color1Weight), (byte) 0),
				new WeightedMobVariant(Math.max(0, color2Weight), (byte) 1),
				new WeightedMobVariant(Math.max(0, albinoWeight), (byte) 15)));
		return variant.variant;
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
	    this.writeAngerNBT(compound);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("CrabVariant")) {
			this.setVariant(compound.getByte("CrabVariant"));
		} else {
			this.setVariant((byte) 0);
		}
		if(this.world instanceof ServerWorld) {
		    this.readAngerNBT((ServerWorld)this.world, compound);
		}
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 6;
	}
	
	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}
	
	@Override
	public boolean canBeLeashedTo(PlayerEntity player) {
		return true;
	}

	@Override
	public boolean canDespawn(double distanceToClosestPlayer) {
		return false;
	}
	
	@Override
	protected float getWaterSlowDown() {
		return 0.99F;
	}

	@Override
	public byte getVariant() {
		return this.dataManager.get(CRAB_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.dataManager.set(CRAB_VARIANT, variant);
	}

	@Override
	public int getAngerTime() {
		return this.angerTime;
	}

	@Override
	public void setAngerTime(int time) {
		this.angerTime = time;
		
	}

	@Override
	public UUID getAngerTarget() {
		return this.angerTarget;
	}

	@Override
	public void setAngerTarget(UUID target) {
		this.angerTarget = target;
		
	}

	@Override
	public void func_230258_H__() {
		this.setAngerTime(rangedInteger.getRandomWithinRange(this.rand));
	}
	
}
