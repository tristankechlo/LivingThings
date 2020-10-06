package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.google.common.collect.ImmutableList;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.util.IMobVariants;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.ShoulderRidingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.WeightedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OwlEntity extends ShoulderRidingEntity implements IFlyingAnimal, IMobVariants {

	private static final DataParameter<Byte> OWL_VARIANT = EntityDataManager.createKey(OwlEntity.class, DataSerializers.BYTE);
	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
	
	public OwlEntity(EntityType<? extends OwlEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
		OwlEntity child = ModEntityTypes.OWL_ENTITY.create(world);
		child.setVariant(this.getVariantFromParents(this, entity));
		return child;
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, LivingThingsConfig.OWL.health.get())
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.FLYING_SPEED, 0.5D);
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		if(reason == SpawnReason.SPAWN_EGG) {
			this.setVariant(OwlEntity.getWeightedRandomColorVariant(this.rand));
		//get color depending on spawnbiome
		} else {
			this.setVariant((byte)0);
		}
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	public static byte getWeightedRandomColorVariant(Random random) {
		int colorBrownWeight = LivingThingsConfig.OWL.colorBrownWeight.get();
		int colorWhiteWeight = LivingThingsConfig.OWL.colorWhiteWeight.get();
		int colorBlackWeight = LivingThingsConfig.OWL.colorBlackWeight.get();
		if(colorBrownWeight <= 0 && colorWhiteWeight <= 0 && colorBlackWeight <= 0) {
			return 0;
		}
		WeightedMobVariant variant = WeightedRandom.getRandomItem(random, ImmutableList.of(
				new WeightedMobVariant(Math.max(0, colorBrownWeight), (byte) 0),
				new WeightedMobVariant(Math.max(0, colorWhiteWeight), (byte) 1),
				new WeightedMobVariant(Math.max(0, colorBlackWeight), (byte) 2)));
		return variant.variant;
	}
	
	@Override
	protected void registerGoals() {
		
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(OWL_VARIANT, (byte)0);
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("OwlVariant")) {
			this.setVariant(compound.getByte("OwlVariant"));
		} else {
			this.setVariant((byte) 0);
		}
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte("OwlVariant", this.getVariant());
	}

	public static boolean canOwlSpawn(EntityType<OwlEntity> parrotIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		BlockState blockstate = worldIn.getBlockState(pos.down());
		return (blockstate.isIn(BlockTags.LEAVES) || blockstate.isIn(Blocks.GRASS_BLOCK)
				|| blockstate.isIn(BlockTags.LOGS) || blockstate.isIn(Blocks.AIR))
				&& worldIn.getLightSubtracted(pos, 0) > 8;
	}
	
	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return LivingThingsConfig.OWL.maxSpawns.get();
	}
	
	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
        return this.isChild() ? 0.45F : 0.9F;
	}

	@Override
	public byte getVariant() {
		return this.dataManager.get(OWL_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.dataManager.set(OWL_VARIANT, variant);
	}

}
