package com.tristankechlo.livingthings.util;

import javax.annotation.Nullable;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.player.PlayerEntity;
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
	private static final DataParameter<Boolean> SADDLED = EntityDataManager.createKey(AbstractTameableChestedEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> CHESTED = EntityDataManager.createKey(AbstractTameableChestedEntity.class, DataSerializers.BOOLEAN);
	private static final DataParameter<Boolean> TAMED = EntityDataManager.createKey(AbstractTameableChestedEntity.class, DataSerializers.BOOLEAN);
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
		this.dataManager.register(SADDLED, false);
		this.dataManager.register(CHESTED, false);
		this.dataManager.register(TAMED, false);
	}

	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		this.setSaddled(compound.getBoolean("Saddled"));
		this.setChested(compound.getBoolean("Chested"));
		this.setTame(compound.getBoolean("Tamed"));
		this.setTameAmount(compound.getInt("TameAmount"));
		
		this.entityInventory.read(compound.getList("Inventory", 10));
		this.initInventory();
	}

	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putBoolean("Saddled", this.isSaddled());
		compound.putBoolean("Chested", this.isChested());
		compound.putBoolean("Tamed", this.isTame());
		compound.putInt("TameAmount", this.getTameAmount());
		compound.put("Inventory", this.entityInventory.write());
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
		player.openContainer(new SimpleNamedContainerProvider((id, playerInv, playerIn) -> {
			return new ChestContainer(ContainerType.GENERIC_9X3, id, player.inventory, this.entityInventory, 3);
		}, CONTAINER_NAME));
	}

	@Override
	public ActionResultType func_230254_b_(PlayerEntity player, Hand hand) {
		if(hand == Hand.OFF_HAND) {
			return ActionResultType.PASS;
		}
		ItemStack stack = player.getHeldItemMainhand();

		if (stack.isEmpty() && this.isTame() && !this.isChild()) {

			if(player.isSneaking() && this.isChested()) {
				//open inv
				this.openInventory(player);
				
			} else if(this.getPassengers().isEmpty() && this.isSaddled()) {
				//start riding
				this.mountRider(player);;
			}
            return ActionResultType.func_233537_a_(this.world.isRemote);
			
		} else if (this.isBreedingItem(stack)) {
			
			if(this.isChild()) {
				//age up
		        int age = this.getGrowingAge();
	            this.consumeItemFromStack(player, stack);
	            this.ageUp((int)((float)(-age / 20) * 0.1F), true);
	            return ActionResultType.func_233537_a_(this.world.isRemote);
			}
			
			if(this.isTame()) {

				//if needs health
				if(this.getHealth() < this.getMaxHealth()) {
					
					//heal entity
					float healAmount = this.getHealingAmount(stack.getItem());
					if(!this.world.isRemote) {
						this.heal(healAmount);
						this.consumeItemFromStack(player, stack);
						return ActionResultType.SUCCESS;
					}
				
				//if already full health
				} else {
					//set in love
					if (!this.world.isRemote && !this.isChild() && this.canBreed()) {
						this.consumeItemFromStack(player, stack);
						this.setInLove(player);
						return ActionResultType.SUCCESS;
					}					
				}
				
			}
			
		} else if (this.isTamingItem(stack) && !this.isChild() && !this.isTame()) {
			
			//progress taming
			if(!this.world.isRemote) {
				this.addTameAmount(this.getTameAmountPerItem(stack.getItem()));
				this.consumeItemFromStack(player, stack);
				//if entity shall be set as tamed now
				if(this.getTameAmount() >= this.getMaxTameAmount()) {
					this.setTame(true);
				    this.world.setEntityState(this, (byte)6);
				} else {
				    this.world.setEntityState(this, (byte)7);
				}
			}
			return ActionResultType.SUCCESS;
			
		} else if(this.isTame() && stack.getItem() == this.getSaddleItem() && !this.isChild()) {
			
			//saddle entity
			if(!this.world.isRemote && !this.isSaddled()) {
				this.consumeItemFromStack(player, stack);
				this.setSaddled(true);
				return ActionResultType.SUCCESS;
			}
			
		} else if(this.isTame() && this.isSaddled() && stack.getItem() == this.getChestItem() && !this.isChild()) {

			//add chest to entity
			if(!this.world.isRemote && !this.isChested()) {
				this.consumeItemFromStack(player, stack);
				this.setChested(true);
				return ActionResultType.SUCCESS;
			}
		}
		
		return ActionResultType.PASS;
	}
	
	@OnlyIn(Dist.CLIENT)
	public void handleStatusUpdate(byte id) {
		switch (id) {
			case 6: //entity tamed
				this.spawnParticle(ParticleTypes.HAPPY_VILLAGER);
				break;
			case 7: //progress while taming
				this.spawnParticle(ParticleTypes.ENCHANT);
				break;
	
			default:
				super.handleStatusUpdate(id);
				break;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	private void spawnParticle(IParticleData particle) {
		if(particle != null) {
			for (int i = 0; i < 7; ++i) {
				double d0 = this.rand.nextGaussian() * 0.02D;
				double d1 = this.rand.nextGaussian() * 0.02D;
				double d2 = this.rand.nextGaussian() * 0.02D;
				this.world.addParticle(particle, this.getPosXRandom(1.0D), this.getPosYRandom() + 0.5D,	this.getPosZRandom(1.0D), d0, d1, d2);
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
		if(this.isSaddled() && this.rand.nextBoolean()) {
			this.entityDropItem(this.getSaddleItem());
		}
		if(this.isChested() && this.rand.nextBoolean()) {
			this.entityDropItem(this.getChestItem());
		}
	}
		
	@Override
	public boolean canBeSteered() {
		Entity entity = this.getControllingPassenger();
		if (entity instanceof PlayerEntity) {
			return true;
		}
		return false;
	}
	
	@Override
	public void updatePassenger(Entity passenger) {
		super.updatePassenger(passenger);
	}
	
	@Override
	public void travel(Vector3d travelVector) {
		if (this.isAlive()) {
			if (this.isBeingRidden() && this.canBeSteered() && this.isSaddled()) {
				LivingEntity livingentity = (LivingEntity) this.getControllingPassenger();
				this.rotationYaw = livingentity.rotationYaw;
				this.prevRotationYaw = this.rotationYaw;
				this.rotationPitch =  -0.0436332312F;
				this.setRotation(this.rotationYaw, this.rotationPitch);
				this.renderYawOffset = this.rotationYaw;
				this.rotationYawHead = this.renderYawOffset;
				float sideSpeed = livingentity.moveStrafing * 0.4F;
				float forwardSpeed = livingentity.moveForward * 0.9F;
				
				//if moving backwards -> move slower
				if (forwardSpeed <= 0.0F) {
					forwardSpeed *= 0.2F;
				}

				this.jumpMovementFactor = this.getAIMoveSpeed() * 0.1F;
				if (this.canPassengerSteer()) {
					this.setAIMoveSpeed((float) this.getAttributeValue(Attributes.MOVEMENT_SPEED));
					super.travel(new Vector3d((double) sideSpeed, travelVector.y, (double) forwardSpeed));
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
		return this.dataManager.get(SADDLED);
	}

	public void setSaddled(boolean saddled) {
		this.dataManager.set(SADDLED, saddled);
	}
	
	public boolean isChested() {
		return this.dataManager.get(CHESTED);
	}
	
	public void setChested(boolean chested) {
		this.dataManager.set(CHESTED, chested);
	}

	public boolean isTame() {
		return this.dataManager.get(TAMED);
	}

	public void setTame(boolean tamed) {
		this.dataManager.set(TAMED, tamed);
	}
	
	/* how much the entity shall be healed */
	protected float getHealingAmount(Item item) {
		return 4.0F;
	}
	
	protected int getMaxTameAmount() {
		return 1000;
	}
	
	protected int getTameAmountPerItem(Item item) {
		return 200;
	}
	
	public int getTameAmount() {
		return this.tameAmount;
	}
	
	public void setTameAmount(int amount) {
		this.tameAmount = amount;
	}
	
	public void addTameAmount(int amount) {
		this.tameAmount = this.getTameAmount() + amount;
	}

	/* which item is considered the saddle */
	protected Item getSaddleItem() {
		return Items.SADDLE;
	}
	
	protected Item getChestItem() {
		return Items.CHEST;
	}
	
}
