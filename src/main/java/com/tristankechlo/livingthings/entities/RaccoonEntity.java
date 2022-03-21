package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.entities.ai.BreakOstrichEggGoal;
import com.tristankechlo.livingthings.entities.ai.BreakTurtleEggGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;
import com.tristankechlo.livingthings.misc.LivingThingsTags;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.NeutralMob;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowParentGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.TemptGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

public class RaccoonEntity extends Animal implements NeutralMob, ILexiconEntry {

	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/raccoon");
	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.WHEAT, Items.APPLE, Items.CARROT, Items.POTATO,
			Items.BEETROOT);
	private static final UniformInt rangedInteger = TimeUtil.rangeOfSeconds(20, 39);
	private int angerTime;
	private UUID angerTarget;

	public RaccoonEntity(EntityType<? extends Animal> type, Level worldIn) {
		super(type, worldIn);
	}

	public static boolean checkRaccoonSpawnRules(EntityType<RaccoonEntity> animal, LevelAccessor world,
			MobSpawnType reason, BlockPos pos, Random random) {
		return world.getBlockState(pos.below()).is(LivingThingsTags.RACCOON_SPAWNABLE_ON)
				&& isBrightEnoughToSpawn(world, pos);
	}

	@Override
	public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob entity) {
		return ModEntityTypes.RACCOON.get().create(world);
	}

	public static AttributeSupplier.Builder createAttributes() {
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.RACCOON.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.RACCOON.speed.get())
				.add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.RACCOON.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new FloatGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.25D, false, () -> {
			return LivingThingsConfig.RACCOON.canAttack.get();
		}));
		this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(3, new TemptGoal(this, 1.1D, BREEDING_ITEMS, false));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
		this.goalSelector.addGoal(5, new BreakOstrichEggGoal(this, 1.0D, 3, 100, false));
		this.goalSelector.addGoal(5, new BreakTurtleEggGoal(this, 1.0D, 3));
		this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));
		this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 8.0F));
		this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetUniversalAngerTargetGoal<>(this, true));
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (this.level instanceof ServerLevel) {
			this.readPersistentAngerSaveData((ServerLevel) this.level, compound);
		}
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.RACCOON_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.RACCOON_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.RACCOON_DEATH.get();
	}

	@Override
	public int getAmbientSoundInterval() {
		return 300;
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntityDimensions sizeIn) {
		return this.isBaby() ? 0.35F : 0.7F;
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.RACCOON.maxSpawnedInChunk.get();
	}

	@Override
	protected int calculateFallDamage(float distance, float damageMultiplier) {
		return super.calculateFallDamage(distance, (damageMultiplier * 0.3F));
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
