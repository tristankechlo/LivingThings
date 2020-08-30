package com.tristankechlo.livingthings.entities;

import java.util.Random;
import java.util.UUID;

import com.tristankechlo.livingthings.config.LivingThingsConfig;
import com.tristankechlo.livingthings.entities.ai.BetterMeleeAttackGoal;
import com.tristankechlo.livingthings.init.ModEntityTypes;
import com.tristankechlo.livingthings.util.IMobVariants;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.IAngerable;
import net.minecraft.entity.ILivingEntityData;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
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
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.RangedInteger;
import net.minecraft.util.TickRangeConverter;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class GiraffeEntity extends AnimalEntity implements IAngerable, IMobVariants {

	private static final DataParameter<Byte> GIRAFFE_VARIANT = EntityDataManager.createKey(LionEntity.class, DataSerializers.BYTE);
	private static final RangedInteger rangedInteger = TickRangeConverter.convertRange(20, 39);
	private int angerTime;
	private UUID angerTarget;
	
	public GiraffeEntity(EntityType<? extends GiraffeEntity> entityType, World worldIn) {
		super(entityType, worldIn);
	}
	
	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entityIn) {	
		GiraffeEntity entityChild = ModEntityTypes.GIRAFFE_ENTITY.create(this.world);
		entityChild.setVariant(this.getVariantFromParents(this, entityIn));
		return entityChild;
	}
	
	@Override
	public ILivingEntityData onInitialSpawn(IServerWorld worldIn, DifficultyInstance difficultyIn, SpawnReason reason, ILivingEntityData spawnDataIn, CompoundNBT dataTag) {
		int albinoChance = LivingThingsConfig.SERVER.giraffeAlbinoChance.get();
		if(Math.random() < ((double)albinoChance / 100.0D)) {
			this.setVariant((byte) 15);
		} else if(new Random().nextBoolean()) {
			this.setVariant((byte) 1);
		} else {
			this.setVariant((byte) 0);			
		}		
		return super.onInitialSpawn(worldIn, difficultyIn, reason, spawnDataIn, dataTag);
	}
	
	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 30.0D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.3D)
				.createMutableAttribute(Attributes.FOLLOW_RANGE, 20.0D)
				.createMutableAttribute(Attributes.ATTACK_DAMAGE, 8.0D);
	}
	
	@Override
	protected void registerGoals() {
		this.goalSelector.addGoal(0, new SwimGoal(this));
		this.goalSelector.addGoal(1, new BetterMeleeAttackGoal(this, 1.2D, false));
		this.goalSelector.addGoal(2, new WaterAvoidingRandomWalkingGoal(this, 0.9D));
		this.goalSelector.addGoal(3, new BreedGoal(this, 1.0D));
		this.goalSelector.addGoal(4, new FollowParentGoal(this, 0.95D));
		this.goalSelector.addGoal(5, new LookAtGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.addGoal(6, new LookRandomlyGoal(this));

		this.targetSelector.addGoal(0, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(1, new ResetAngerGoal<>(this, true));
	}
	
	@Override
	protected void registerData() {
		super.registerData();
		this.dataManager.register(GIRAFFE_VARIANT, (byte)0);
	}
	
	@Override
	public void writeAdditional(CompoundNBT compound) {
		super.writeAdditional(compound);
		compound.putByte("GiraffeVariant", this.getVariant());
	}
	
	@Override
	public void readAdditional(CompoundNBT compound) {
		super.readAdditional(compound);
		if (compound.contains("GiraffeVariant")) {
			this.setVariant(compound.getByte("GiraffeVariant"));
		} else {
			this.setVariant((byte) 0);
		}
	}

	@Override
	public boolean isBreedingItem(ItemStack stack) {
		Item item = stack.getItem();
		return (item == Items.WHEAT);
	}
	
	@Override
	public int getMaxSpawnedInChunk() {
		return 4;
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
		this.setAngerTime(rangedInteger.func_233018_a_(this.rand));		
	}

	@Override
	public byte getVariant() {
		return this.getDataManager().get(GIRAFFE_VARIANT);
	}

	@Override
	public void setVariant(byte variant) {
		this.getDataManager().set(GIRAFFE_VARIANT, variant);		
	}
		
}
