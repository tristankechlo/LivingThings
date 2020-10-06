package com.tristankechlo.livingthings.entities;

import java.util.Random;

import com.tristankechlo.livingthings.init.ModEntityTypes;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AgeableEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.attributes.AttributeModifierMap;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.passive.IFlyingAnimal;
import net.minecraft.entity.passive.ShoulderRidingEntity;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

public class OwlEntity extends ShoulderRidingEntity implements IFlyingAnimal {

	public OwlEntity(EntityType<? extends OwlEntity> type, World worldIn) {
		super(type, worldIn);
	}

	@Override
	public AgeableEntity func_241840_a(ServerWorld world, AgeableEntity entity) {
		return ModEntityTypes.OWL_ENTITY.create(world);
	}

	public static AttributeModifierMap.MutableAttribute getAttributes() {
		return MobEntity.func_233666_p_()
				.createMutableAttribute(Attributes.MAX_HEALTH, 6D)
				.createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.25D)
				.createMutableAttribute(Attributes.FLYING_SPEED, 0.5D);
	}

	public static boolean canOwlSpawn(EntityType<OwlEntity> parrotIn, IWorld worldIn, SpawnReason reason, BlockPos pos, Random random) {
		BlockState blockstate = worldIn.getBlockState(pos.down());
		return (blockstate.isIn(BlockTags.LEAVES) || blockstate.isIn(Blocks.GRASS_BLOCK)
				|| blockstate.isIn(BlockTags.LOGS) || blockstate.isIn(Blocks.AIR))
				&& worldIn.getLightSubtracted(pos, 0) > 8;
	}

}
