package com.tristankechlo.livingthings.entities;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import com.tristankechlo.livingthings.misc.ILexiconEntry;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.CreatureEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.Pose;
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
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.server.management.PreYggdrasilConverter;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Hand;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class ElephantEntity extends AnimalEntity implements IAngerable, ILexiconEntry {

	private static final Ingredient BREEDING_ITEMS = Ingredient.of(Items.WHEAT);
	private static final Ingredient TAMING_ITEMS = Ingredient.of(Items.APPLE);
	private static final ResourceLocation LEXICON_ENTRY = new ResourceLocation(LivingThings.MOD_ID,
			"neutral_mobs/elephant");
	private static final RangedInteger rangedInteger = TickRangeConverter.rangeOfSeconds(20, 39);
	private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent(
			"container." + LivingThings.MOD_ID + ".elephant");
	private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.defineId(ElephantEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> HAS_CHEST = EntityDataManager.defineId(ElephantEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_TAMED = EntityDataManager.defineId(ElephantEntity.class,
			DataSerializers.BOOLEAN);
	private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager
			.defineId(ElephantEntity.class, DataSerializers.OPTIONAL_UUID);
	protected Inventory entityInventory;
	private int tameAmount;
	private int angerTime;
	private int attackTimer;
	private UUID angerTarget;

	public ElephantEntity(EntityType<? extends ElephantEntity> entityType, World worldIn) {
		super(entityType, worldIn);
		this.maxUpStep = 1.0F;
		this.initInventory();
		this.tameAmount = 0;
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.getEntityData().define(IS_SADDLED, false);
		this.getEntityData().define(HAS_CHEST, false);
		this.getEntityData().define(IS_TAMED, false);
		this.getEntityData().define(OWNER_UNIQUE_ID, Optional.empty());
	}

	@Override
	public AgeableEntity getBreedOffspring(ServerWorld world, AgeableEntity parent) {
		ElephantEntity child = ModEntityTypes.ELEPHANT_ENTITY.get().create(this.level);
		if (this.isTame() || ((ElephantEntity) parent).isTame()) {
			child.setTame(true);
		}
		return child;
	}

	public static AttributeModifierMap.MutableAttribute createAttributes() {
		return MobEntity.createMobAttributes().add(Attributes.MAX_HEALTH, LivingThingsConfig.ELEPHANT.health.get())
				.add(Attributes.MOVEMENT_SPEED, LivingThingsConfig.ELEPHANT.speed.get())
				.add(Attributes.FOLLOW_RANGE, 16.0D)
				.add(Attributes.ATTACK_DAMAGE, LivingThingsConfig.ELEPHANT.damage.get());
	}

	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false, () -> {
			return LivingThingsConfig.ELEPHANT.canAttack.get();
		}));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new ElephantEntity.NewHurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetAngerGoal<>(this, true));
	}

	@Override
	public void readAdditionalSaveData(CompoundNBT compound) {
		super.readAdditionalSaveData(compound);
		if (this.level instanceof ServerWorld) {
			this.readPersistentAngerSaveData((ServerWorld) this.level, compound);
		}
		this.setSaddled(compound.getBoolean("Saddled"));
		this.setHasChest(compound.getBoolean("Chested"));
		this.setTame(compound.getBoolean("Tamed"));
		this.setTameAmount(compound.getInt("TameAmount"));

		this.entityInventory.fromTag(compound.getList("Inventory", 10));
		this.initInventory();

		UUID uuid;
		if (compound.hasUUID("Owner")) {
			uuid = compound.getUUID("Owner");
		} else {
			String string = compound.getString("Owner");
			uuid = PreYggdrasilConverter.convertMobOwnerIfNecessary(this.getServer(), string);
		}
		if (uuid != null) {
			this.setOwnerUniqueId(uuid);
		}
	}

	@Override
	public void addAdditionalSaveData(CompoundNBT compound) {
		super.addAdditionalSaveData(compound);
		this.addPersistentAngerSaveData(compound);
		compound.putBoolean("Saddled", this.isSaddled());
		compound.putBoolean("Chested", this.hasChest());
		compound.putBoolean("Tamed", this.isTame());
		compound.putInt("TameAmount", this.getTameAmount());
		compound.put("Inventory", this.entityInventory.createTag());
		if (this.getOwnerUniqueId() != null) {
			compound.putUUID("Owner", this.getOwnerUniqueId());
		}
	}

	protected void initInventory() {
		Inventory inventory = this.entityInventory;
		this.entityInventory = new Inventory(27);
		if (inventory != null) {
			int invSize = Math.min(inventory.getContainerSize(), this.entityInventory.getContainerSize());

			for (int i = 0; i < invSize; ++i) {
				ItemStack itemstack = inventory.getItem(i);
				if (!itemstack.isEmpty()) {
					this.entityInventory.setItem(i, itemstack.copy());
				}
			}
		}
	}

	public void openInventory(PlayerEntity player) {
		// elephant inv is a generic chest
		player.openMenu(new SimpleNamedContainerProvider((id, playerInv, playerIn) -> {
			return new ChestContainer(ContainerType.GENERIC_9x3, id, player.inventory, this.entityInventory, 3);
		}, CONTAINER_NAME));
	}

	@Override
	public boolean isFood(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	@Override
	public boolean doHurtTarget(Entity target) {
		this.attackTimer = 10;
		this.level.broadcastEntityEvent(this, (byte) 4);
		boolean flag = target.hurt(DamageSource.mobAttack(this),
				(float) this.getAttributeValue(Attributes.ATTACK_DAMAGE));
		if (flag) {
			// throw target in the air
			target.setDeltaMovement(target.getDeltaMovement().add(0.0D, 0.7D, 0.0D));
			this.doEnchantDamageEffects(this, target);
		}
		return flag;
	}

	@Override
	public void tick() {
		super.tick();
		if (this.attackTimer > 0) {
			--this.attackTimer;
		}
	}

	@Override
	public int getMaxSpawnClusterSize() {
		return LivingThingsConfig.ELEPHANT.maxSpawnedInChunk.get();
	}

	@Override
	protected float getStandingEyeHeight(Pose poseIn, EntitySize sizeIn) {
		return this.isBaby() ? 1.3F : 2.25F;
	}

	@Override
	public double getPassengersRidingOffset() {
		return 2.45D;
	}

	@Override
	protected SoundEvent getAmbientSound() {
		return ModSounds.ELEPHANT_AMBIENT.get();
	}

	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return ModSounds.ELEPHANT_HURT.get();
	}

	@Override
	protected SoundEvent getDeathSound() {
		return ModSounds.ELEPHANT_DEATH.get();
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void handleEntityEvent(byte id) {
		switch (id) {
			case 4: // start attack animation
				this.attackTimer = 10;
				break;
			case 6: // entity tamed
				this.spawnParticle(ParticleTypes.ENCHANTED_HIT);
				this.spawnParticle(ParticleTypes.FIREWORK);
				break;
			case 7: // progress while taming
				this.spawnParticle(ParticleTypes.COMPOSTER);
				break;
			default:
				super.handleEntityEvent(id);
				break;
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void spawnParticle(IParticleData particle) {
		if (particle != null) {
			for (int i = 0; i < 7; ++i) {
				double d0 = this.random.nextGaussian() * 0.03D;
				double d1 = this.random.nextGaussian() * 0.03D;
				double d2 = this.random.nextGaussian() * 0.03D;
				this.level.addParticle(particle, this.getRandomX(1.0D), this.getRandomY() + 0.5D, this.getRandomZ(1.0D),
						d0, d1, d2);
			}
		}
	}

	private void doPlayerRide(PlayerEntity player) {
		if (!this.level.isClientSide()) {
			player.yRot = this.yRot;
			player.xRot = this.xRot;
			player.startRiding(this);
		}
	}

	@Override
	protected boolean isImmobile() {
		return super.isImmobile() && this.isVehicle();
	}

	@Override
	protected void dropEquipment() {
		super.dropEquipment();
		if (this.entityInventory != null) {
			for (int i = 0; i < this.entityInventory.getContainerSize(); ++i) {
				ItemStack itemstack = this.entityInventory.getItem(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.spawnAtLocation(itemstack);
				}
			}
		}
		if (this.isSaddled() && this.random.nextBoolean()) {
			this.spawnAtLocation(Items.SADDLE);
		}
		if (this.hasChest() && this.random.nextBoolean()) {
			this.spawnAtLocation(Items.CHEST);
		}
	}

	@Override
	public boolean isControlledByLocalInstance() {
		return this.getControllingPassenger() instanceof PlayerEntity;
	}

	@Override
	public void travel(Vector3d travelVector) {
		if (this.isAlive()) {
			if (this.isVehicle() && this.isControlledByLocalInstance() && this.isSaddled()) {
				LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
				this.yRot = livingentity.yRot;
				this.yRotO = this.yRot;
				this.xRot = livingentity.xRot * 0.5F;
				this.setRot(this.yRot, this.xRot);
				this.yBodyRot = this.yRot;
				this.yHeadRot = this.yBodyRot;
				float sideSpeed = livingentity.xxa * 0.4F;
				float forwardSpeed = livingentity.zza * 0.7F;

				// if moving backwards -> move slower
				if (forwardSpeed <= 0.0F) {
					forwardSpeed *= 0.2F;
				}

				this.flyingSpeed = this.getSpeed() * 0.1F;
				if (this.isControlledByLocalInstance()) {
					this.setSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
					super.travel(new Vector3d(sideSpeed, travelVector.y, forwardSpeed));
				} else if (livingentity instanceof PlayerEntity) {
					this.setDeltaMovement(Vector3d.ZERO);
				}

				this.calculateEntityAnimation(this, false);
			} else {
				this.flyingSpeed = 0.02F;
				super.travel(travelVector);
			}
		}
	}

	public boolean isTamingItem(ItemStack stack) {
		return TAMING_ITEMS.test(stack);
	}

	@Nullable
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	public boolean isSaddled() {
		return this.getEntityData().get(IS_SADDLED);
	}

	/** get the current taming amount */
	public int getTameAmount() {
		return this.tameAmount;
	}

	/** override the taming progress */
	public void setTameAmount(int amount) {
		this.tameAmount = amount;
	}

	/** add to the current taming amount */
	public void addTameAmount(int amount) {
		this.tameAmount = this.getTameAmount() + amount;
	}

	public void setSaddled(boolean saddled) {
		this.getEntityData().set(IS_SADDLED, saddled);
	}

	public boolean hasChest() {
		return this.getEntityData().get(HAS_CHEST);
	}

	public void setHasChest(boolean chested) {
		this.getEntityData().set(HAS_CHEST, chested);
	}

	public boolean isTame() {
		return this.getEntityData().get(IS_TAMED);
	}

	public void setTame(boolean tamed) {
		this.getEntityData().set(IS_TAMED, tamed);
	}

	@Nullable
	public UUID getOwnerUniqueId() {
		return this.getEntityData().get(OWNER_UNIQUE_ID).orElse(null);
	}

	public void setOwnerUniqueId(@Nullable UUID uniqueId) {
		this.getEntityData().set(OWNER_UNIQUE_ID, Optional.ofNullable(uniqueId));
	}

	@OnlyIn(Dist.CLIENT)
	public int getAttackTimer() {
		return this.attackTimer;
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
	public ResourceLocation getLexiconEntry() {
		return LEXICON_ENTRY;
	}

	@Override
	public ActionResultType mobInteract(PlayerEntity player, Hand hand) {
		if (hand == Hand.OFF_HAND) { // prevent offhand use
			return ActionResultType.PASS;
		}
		ItemStack stack = player.getMainHandItem();

		if (stack.isEmpty() && this.isTame() && !this.isBaby()) {

			if (player.isCrouching() && this.hasChest()) {
				// open inv
				this.openInventory(player);

			} else if (this.getPassengers().isEmpty() && this.isSaddled()) {
				// start riding
				this.doPlayerRide(player);
			}
			return ActionResultType.sidedSuccess(this.level.isClientSide());

		} else if (stack.getItem() == ModItems.LEXICON.get()) {

			// prevent any use when item is lexicon
			return ActionResultType.PASS;

		} else if (this.isFood(stack)) {

			if (this.isBaby()) {
				// age up
				int age = this.getAge();
				this.usePlayerItem(player, stack);
				this.ageUp((int) ((float) (-age / 20) * 0.1F), true);
				return ActionResultType.sidedSuccess(this.level.isClientSide());
			}

			if (this.isTame()) {

				// if needs health
				if (this.getHealth() < this.getMaxHealth()) {

					// heal entity
					if (!this.level.isClientSide()) {
						float healAmount = 3.0F;
						this.heal(healAmount);
						this.usePlayerItem(player, stack);
						return ActionResultType.SUCCESS;
					}

					// if already full health
				} else {
					// set in love
					if (!this.level.isClientSide() && !this.isBaby() && this.canBreed()) {
						this.usePlayerItem(player, stack);
						this.setInLove(player);
						return ActionResultType.SUCCESS;
					}
				}

			}

		} else if (this.isTamingItem(stack) && !this.isBaby() && !this.isTame()) {

			// progress taming
			if (!this.level.isClientSide()) {
				this.addTameAmount(200);
				this.usePlayerItem(player, stack);
				// if entity shall be set as tamed now
				if (this.getTameAmount() >= 1000) {
					this.setTame(true);
					this.setOwnerUniqueId(player.getUUID());
					if (player instanceof ServerPlayerEntity) {
						CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayerEntity) player, this);
					}
					this.level.broadcastEntityEvent(this, (byte) 6);
				} else {
					this.level.broadcastEntityEvent(this, (byte) 7);
				}
			}
			return ActionResultType.SUCCESS;

		} else if (this.isTame() && stack.getItem() == Items.SADDLE && !this.isBaby()) {

			// saddle entity
			if (!this.level.isClientSide() && !this.isSaddled()) {
				this.usePlayerItem(player, stack);
				this.setSaddled(true);
				this.playSound(ModSounds.ELEPHANT_EQUIP_SADDLE.get(), 0.9F, 0.9F);
				return ActionResultType.SUCCESS;
			}

		} else if (this.isTame() && this.isSaddled() && stack.getItem() == Items.CHEST && !this.isBaby()) {

			// add chest to entity
			if (!this.level.isClientSide() && !this.hasChest()) {
				this.usePlayerItem(player, stack);
				this.setHasChest(true);
				this.playSound(ModSounds.ELEPHANT_EQUIP_CHEST.get(), 0.9F, 0.9F);
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	private static class NewHurtByTargetGoal extends HurtByTargetGoal {

		public NewHurtByTargetGoal(CreatureEntity creatureIn) {
			super(creatureIn);
		}

		@Override
		public boolean canUse() {
			LivingEntity livingentity = this.mob.getLastHurtByMob();
			if (livingentity instanceof PlayerEntity) {
				UUID ownerID = ((ElephantEntity) this.mob).getOwnerUniqueId();
				if (ownerID != null) {
					if (ownerID == ((PlayerEntity) livingentity).getUUID()) {
						return false;
					}
				}
			}
			return super.canUse();
		}

	}

}
