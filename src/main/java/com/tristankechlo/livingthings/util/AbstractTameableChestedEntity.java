package com.tristankechlo.livingthings.util;

import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nullable;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.init.ModSounds;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.item.Item;
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
import net.minecraft.util.Hand;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractTameableChestedEntity extends AnimalEntity {

	private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container." + LivingThings.MOD_ID + ".elephant");
	private static final DataParameter<Boolean> IS_SADDLED = EntityDataManager.createKey(AbstractTameableChestedEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> HAS_CHEST = EntityDataManager.createKey(AbstractTameableChestedEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> IS_TAMED = EntityDataManager.createKey(AbstractTameableChestedEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Optional<UUID>> OWNER_UNIQUE_ID = EntityDataManager.createKey(AbstractTameableChestedEntity.class, DataSerializers.OPTIONAL_UNIQUE_ID);
	private static final Ingredient BREEDING_ITEMS = Ingredient.fromItems(Items.WHEAT);
	private static final Ingredient TAMING_ITEMS = Ingredient.fromItems(Items.APPLE);
	protected Inventory entityInventory;
	private int tameAmount;

	protected AbstractTameableChestedEntity(EntityType<? extends AbstractTameableChestedEntity> type, World worldIn) {
		super(type, worldIn);
		this.initInventory();
		this.tameAmount = 0;
	}

	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(IS_SADDLED, false);
		this.dataManager.register(HAS_CHEST, false);
		this.dataManager.register(IS_TAMED, false);
		this.dataManager.register(OWNER_UNIQUE_ID, Optional.empty());
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setSaddled(compound.getBoolean("Saddled"));
		this.setHasChest(compound.getBoolean("Chested"));
		this.setTame(compound.getBoolean("Tamed"));
		this.setTameAmount(compound.getInt("TameAmount"));

		this.entityInventory.read(compound.getList("Inventory", 10));
		this.initInventory();

		UUID uuid;
		if (compound.hasUniqueId("Owner")) {
			uuid = compound.getUniqueId("Owner");
		} else {
			String string = compound.getString("Owner");
			uuid = PreYggdrasilConverter.convertMobOwnerIfNeeded(this.getServer(), string);
		}
		if (uuid != null) {
			this.setOwnerUniqueId(uuid);
		}
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("Saddled", this.isSaddled());
		compound.putBoolean("Chested", this.hasChest());
		compound.putBoolean("Tamed", this.isTame());
		compound.putInt("TameAmount", this.getTameAmount());
		compound.put("Inventory", this.entityInventory.write());
		if (this.getOwnerUniqueId() != null) {
			compound.putUniqueId("Owner", this.getOwnerUniqueId());
		}
	}

	protected void initInventory() {
		Inventory inventory = this.entityInventory;
		this.entityInventory = new Inventory(27);
		if (inventory != null) {
			int invSize = Math.min(inventory.getSizeInventory(), this.entityInventory.getSizeInventory());

			for (int i = 0; i < invSize; ++i) {
				ItemStack itemstack = inventory.getStackInSlot(i);
				if (!itemstack.isEmpty()) {
					this.entityInventory.setInventorySlotContents(i, itemstack.copy());
				}
			}
		}
	}

	public void openInventory(PlayerEntity player) {
		// elephant inv is a generic chest
		player.openContainer(new SimpleNamedContainerProvider((id, playerInv, playerIn) -> {
			return new ChestContainer(ContainerType.GENERIC_9X3, id, player.inventory, this.entityInventory, 3);
		}, CONTAINER_NAME));
	}

	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		if (hand == Hand.OFF_HAND) { // prevent offhand use
			return ActionResultType.PASS;
		}
		ItemStack stack = player.getHeldItemMainhand();

		if (stack.isEmpty() && this.isTame() && !this.isChild()) {

			if (player.isSneaking() && this.hasChest()) {
				// open inv
				this.openInventory(player);

			} else if (this.getPassengers().isEmpty() && this.isSaddled()) {
				// start riding
				this.mountRider(player);
			}
			return ActionResultType.func_233537_a_(this.world.isRemote);

		} else if (this.isBreedingItem(stack)) {

			if (this.isChild()) {
				// age up
				int age = this.getGrowingAge();
				this.consumeItemFromStack(player, stack);
				this.ageUp((int) ((float) (-age / 20) * 0.1F), true);
				return ActionResultType.func_233537_a_(this.world.isRemote);
			}

			if (this.isTame()) {

				// if needs health
				if (this.getHealth() < this.getMaxHealth()) {

					// heal entity
					if (!this.world.isRemote) {
						float healAmount = this.getHealingAmount(stack.getItem());
						this.heal(healAmount);
						this.consumeItemFromStack(player, stack);
						return ActionResultType.SUCCESS;
					}

					// if already full health
				} else {
					// set in love
					if (!this.world.isRemote && !this.isChild() && this.canBreed()) {
						this.consumeItemFromStack(player, stack);
						this.setInLove(player);
						return ActionResultType.SUCCESS;
					}
				}

			}

		} else if (this.isTamingItem(stack) && !this.isChild() && !this.isTame()) {

			// progress taming
			if (!this.world.isRemote) {
				this.addTameAmount(this.getTameAmountPerItem(stack.getItem()));
				this.consumeItemFromStack(player, stack);
				// if entity shall be set as tamed now
				if (this.getTameAmount() >= this.getMaxTameAmount()) {
					this.setTame(true);
					this.setOwnerUniqueId(player.getUniqueID());
					if (player instanceof ServerPlayerEntity) {
						CriteriaTriggers.TAME_ANIMAL.trigger((ServerPlayerEntity) player, this);
					}
					this.world.setEntityState(this, (byte) 6);
				} else {
					this.world.setEntityState(this, (byte) 7);
				}
			}
			return ActionResultType.SUCCESS;

		} else if (this.isTame() && isSaddleItem(stack) && !this.isChild()) {

			// saddle entity
			if (!this.world.isRemote && !this.isSaddled()) {
				this.consumeItemFromStack(player, stack);
				this.setSaddled(true);
				this.playSound(ModSounds.ELEPHANT_EQUIP_SADDLE.get(), 0.9F, 0.9F);
				return ActionResultType.SUCCESS;
			}

		} else if (this.isTame() && this.isSaddled() && isChestItem(stack) && !this.isChild()) {

			// add chest to entity
			if (!this.world.isRemote && !this.hasChest()) {
				this.consumeItemFromStack(player, stack);
				this.setHasChest(true);
				this.playSound(ModSounds.ELEPHANT_EQUIP_CHEST.get(), 0.9F, 0.9F);
				return ActionResultType.SUCCESS;
			}
		}

		return ActionResultType.PASS;
	}

	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		switch (id) {
		case 6: // entity tamed
			this.spawnParticle(ParticleTypes.ENCHANTED_HIT);
			this.spawnParticle(ParticleTypes.FIREWORK);
			break;
		case 7: // progress while taming
			// this.spawnParticle(ModParticle.ARROW_UP_GREEN.get());
			this.spawnParticle(ParticleTypes.COMPOSTER);
			break;

		default:
			super.handleStatusUpdate(id);
			break;
		}
	}

	@OnlyIn(Dist.CLIENT)
	private void spawnParticle(IParticleData particle) {
		if (particle != null) {
			for (int i = 0; i < 7; ++i) {
				double d0 = this.rand.nextGaussian() * 0.03D;
				double d1 = this.rand.nextGaussian() * 0.03D;
				double d2 = this.rand.nextGaussian() * 0.03D;
				this.world.addParticle(particle, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D,
						this.getPosZRandom(1.0D), d0, d1, d2);
			}
		}
	}

	protected void mountRider(PlayerEntity player) {
		if (!this.world.isRemote) {
			player.rotationYaw = this.rotationYaw;
			player.rotationPitch = this.rotationPitch;
			player.startRiding(this);
		}
	}

	@Override
	protected boolean isMovementBlocked() {
		return super.isMovementBlocked() && this.isBeingRidden();
	}

	@Override
	protected void dropInventory() {
		super.dropInventory();
		if (this.entityInventory != null) {
			for (int i = 0; i < this.entityInventory.getSizeInventory(); ++i) {
				ItemStack itemstack = this.entityInventory.getStackInSlot(i);
				if (!itemstack.isEmpty() && !EnchantmentHelper.hasVanishingCurse(itemstack)) {
					this.entityDropItem(itemstack);
				}
			}
		}
		if (this.isSaddled() && this.rand.nextBoolean()) {
			this.entityDropItem(this.getSaddleItem());
		}
		if (this.hasChest() && this.rand.nextBoolean()) {
			this.entityDropItem(this.getChestItem());
		}
	}

	@Override
	public boolean canBeSteered() {
		return this.getControllingPassenger() instanceof PlayerEntity;
	}

	@Override
	public void travel(Vector3d travelVector) {
		if (this.isAlive()) {
			if (this.isBeingRidden() && this.canBeSteered() && this.isSaddled()) {
				LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
				this.rotationYaw = livingentity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch = -0.0436332312F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.renderYawOffset = this.rotationYaw;
				this.rotationYawHead = this.renderYawOffset;
				float sideSpeed = livingentity.moveStrafing * 0.4F;
				float forwardSpeed = livingentity.moveForward * 0.7F;

				// if moving backwards -> move slower
				if (forwardSpeed <= 0.0F) {
					forwardSpeed *= 0.2F;
				}

				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
				if (this.canPassengerSteer()) {
					this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
					super.travel(new Vector3d(sideSpeed, travelVector.y, forwardSpeed));
				} else if (livingentity instanceof PlayerEntity) {
					this.setMotion(Vector3d.ZERO);
				}

				this.func_233629_a_(this, false);
			} else {
				this.jumpMovementFactor = 0.02F;
				super.travel(travelVector);
			}
		}
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		return BREEDING_ITEMS.test(stack);
	}

	public boolean isTamingItem(ItemStack stack) {
		return TAMING_ITEMS.test(stack);
	}

	@Nullable
	public Entity getControllingPassenger() {
		return this.getPassengers().isEmpty() ? null : this.getPassengers().get(0);
	}

	public boolean isSaddled() {
		return this.dataManager.get(IS_SADDLED);
	}

	public void setSaddled(boolean saddled) {
		this.dataManager.set(IS_SADDLED, saddled);
	}

	public boolean hasChest() {
		return this.dataManager.get(HAS_CHEST);
	}

	public void setHasChest(boolean chested) {
		this.dataManager.set(HAS_CHEST, chested);
	}

	public boolean isTame() {
		return this.dataManager.get(IS_TAMED);
	}

	public void setTame(boolean tamed) {
		this.dataManager.set(IS_TAMED, tamed);
	}

	@Nullable
	public UUID getOwnerUniqueId() {
		return this.dataManager.get(OWNER_UNIQUE_ID).orElse(null);
	}

	public void setOwnerUniqueId(@Nullable UUID uniqueId) {
		this.dataManager.set(OWNER_UNIQUE_ID, Optional.ofNullable(uniqueId));
	}

	/** how much the entity shall be healed */
	protected float getHealingAmount(Item item) {
		return 3.0F;
	}

	/**
	 * if current taming amount is higher than this, entity is considered as tamed
	 */
	protected int getMaxTameAmount() {
		return 1000;
	}

	/** how much the taming shall progress */
	protected int getTameAmountPerItem(Item item) {
		return 200;
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

	/** which item is considered the saddle item when used */
	protected Item getSaddleItem() {
		return Items.SADDLE;
	}

	/** which item is considered the chest item when used */
	protected Item getChestItem() {
		return Items.CHEST;
	}

	/** check if item is correct saddle item */
	protected boolean isSaddleItem(ItemStack stack) {
		return stack.getItem() == this.getSaddleItem();
	}

	/** check if item is correct chest item */
	protected boolean isChestItem(ItemStack stack) {
		return stack.getItem() == this.getChestItem();
	}

}
