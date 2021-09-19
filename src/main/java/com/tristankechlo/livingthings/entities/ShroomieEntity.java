package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.ShroomiePlantMushroomGoal;
import com.tristankechlo.livingthings.entities.misc.IMobVariants;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

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
import net.minecraft.entity.ai.goal.BreedGoal;
import net.minecraft.entity.ai.goal.FollowParentGoal;
import net.minecraft.entity.ai.goal.LookAtGoal;
import net.minecraft.entity.ai.goal.LookRandomlyGoal;
import net.minecraft.entity.ai.goal.PanicGoal;
import net.minecraft.entity.ai.goal.SwimGoal;
import net.minecraft.entity.ai.goal.TemptGoal;
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
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class ShroomieEntity extends AnimalEntity implements ILexiconEntry, IMobVariants {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"passive_mobs/shroomie");
	private static final DataParameter<Byte> VARIANT = EntityDataManager.defineId(ShroomieEntity.class,
			DataSerializers.BYTE);
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.WHEAT);
	private static final RangedInteger RANGED_INTEGER = TickRangeConverter.rangeOfSeconds(30, 60);
	private boolean canPlantMushroom;
	private int mushroomCooldown;

	public ShroomieEntity(EntityType<? extends ShroomieEntity> entityType, World world) {
		super(entityType, world);
		canPlantMushroom = false;
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT tag) {
		super.readAdditionalSaveData(tag);
		this.setVariant(tag.getByte("ShroomieType"));
		this.mushroomCooldown = tag.getInt("MushroomCooldown");
		this.canPlantMushroom = tag.getBoolean("CanPlantMushroom");
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT tag) {
		super.addAdditionalSaveData(tag);
		tag.putByte("ShroomieType", getVariant());
		tag.putInt("MushroomCooldown", this.mushroomCooldown);
		tag.putBoolean("CanPlantMushroom", this.canPlantMushroom);
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, BREEDING_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new ShroomiePlantMushroomGoal(this));
		this.goalSelector.addGoal(7, new WaterAvoidingRandomWalkingGoal(this, 1.0D));
		this.goalSelector.addGoal(8, new LookAtGoal(this, PlayerEntity.class, 6.0F));
		this.goalSelector.addGoal(9, new LookRandomlyGoal(this));
	}

	@Override
	public void tick() {
		super.tick();
		if (!this.level.isClientSide && this.mushroomCooldown > 0) {
			this.mushroomCooldown--;
		}
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(VARIANT, (byte) 0);
	}

	@Override
	public ILivingEntityData finalizeSpawn(IServerWorld world, DifficultyInstance difficulty, SpawnReason reason,
			ILivingEntityData data, CompoundNBT tag) {
		final int brownWeight = LivingThingsConfig.SHROOMIE.brownWeight.get();
		final int redWeight = LivingThingsConfig.SHROOMIE.redWeight.get();
		this.setVariant(this.getRandomVariant(random, new byte[] { 0, 1 }, new int[] { brownWeight, redWeight }));
		return super.finalizeSpawn(world, difficulty, reason, data, tag);
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.SHROOMIE.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose p_213348_1_, EntitySize p_213348_2_) {
		return 0.55F;
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if ((this.getVariant() == 1 && stack.getItem().equals(Items.RED_MUSHROOM))
				|| (this.getVariant() == 0 && stack.getItem().equals(Items.BROWN_MUSHROOM))) {
			if (this.level.isClientSide) {
				return ActionResultType.CONSUME;
			} else {
				if (!this.canPlantMushroom) {
					this.usePlayerItem(player, stack);
					this.mushroomCooldown += 100;
					this.canPlantMushroom = true;
					return ActionResultType.SUCCESS;
				} else {
					return ActionResultType.FAIL;
				}
			}
		}
		return super.mobInteract(player, hand);
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.SHROOMIE.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.SHROOMIE.speed.get());
	}

	public static boolean canShroomieSpawn(EntityType<? extends AnimalEntity> type, IWorld world, SpawnReason reason,
			BlockPos pos, Random random) {
		BlockState state = world.getBlockState(pos.below());
		return (state.is(Blocks.GRASS_BLOCK) || state.is(Blocks.MYCELIUM)) && world.getRawBrightness(pos, 0) > 7;
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity parent) {
		ShroomieEntity shroomie = ModEntityTypes.SHROOMIE_ENTITY.get().create(world);
		shroomie.setVariant(this.getVariantFromParents(this, parent));
		return shroomie;
	}

	public boolean canPlantMushroom() {
		return this.canPlantMushroom && this.mushroomCooldown <= 0;
	}

	public void plantedMushroom() {
		// 50% chance to plant another mushroom after the cooldown
		if (this.random.nextBoolean()) {
			this.canPlantMushroom = false;
		}
		this.mushroomCooldown = RANGED_INTEGER.randomValue(random);
	}

	@Override
	public byte getVariant() {
		return this.entityData.get(VARIANT);
	}

	@Override
	public void setVariant(byte type) {
		if (type > 1 || type < 0) {
			return;
		}
		this.entityData.set(VARIANT, type);
	}

}
