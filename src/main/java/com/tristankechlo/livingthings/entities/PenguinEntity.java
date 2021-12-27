package com.tristankechlo.livingthings.entities;

import java.util.Random;

import javax.annotation.Nonnull;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.PolarBear;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;

public class PenguinEntity extends Animal implements ILexiconEntry {

	private static final Ingredient TEMPTATION_ITEMS = Ingredient.of(Items.COD, Items.SALMON, Items.TROPICAL_FISH);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"passive_mobs/penguin");
	private static Tag<Block> spawnableOn = null;

	public PenguinEntity(EntityType<? extends PenguinEntity> entityType, Level worldIn) {
		super(entityType, worldIn);
	}

	public static boolean checkPenguinSpawnRules(EntityType<PenguinEntity> animal, LevelAccessor world,
			MobSpawnType reason, BlockPos pos, Random random) {
		if (spawnableOn == null) {
			spawnableOn = BlockTags.getAllTags().getTagOrEmpty(LivingThingsTags.PENGUIN_SPAWNABLE_ON);
		}
		return spawnableOn.contains(world.getBlockState(pos.below()).getBlock()) && isBrightEnoughToSpawn(world, pos);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel worldIn, AgeableMob parent) {
		return ModEntityTypes.PENGUIN.get().create(worldIn);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.PENGUIN.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.PENGUIN.speed.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, PolarBear.class, 8.0F, 1.0D, 1.2D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, TEMPTATION_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new RandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, PenguinEntity.class, 6.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return TEMPTATION_ITEMS.test(stack);
	}

	@Override
	public boolean canBreatheUnderwater() {
		return true;
	}

	@Override
	protected float getWaterSlowDown() {
		return 0.98F;
	}

	@Override
	public int getAmbientSoundInterval() {
		return 180;
	}

	@Override
	protected float getStandingEyeHeight(@Nonnull Pose pose, @Nonnull EntityDimensions size) {
		return this.isBaby() ? 0.6F : 1.3F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.PENGUIN.maxSpawnedInChunk.get();
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.PENGUIN_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.PENGUIN_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.PENGUIN_DEATH.get();
	}

	@Override
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

}
