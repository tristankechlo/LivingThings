package com.tristankechlo.livingthings.entities;

import java.util.Random;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;

//TODO patchouli
public class BabyEnderDragonEntity extends PathfinderMob {

	public BabyEnderDragonEntity(EntityType<? extends BabyEnderDragonEntity> entity, Level level) {
		super(entity, level);
	}

	public static AttributeSupplier.Builder createAttributes() {
		// TODO attributes
		return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10).add(Attributes.MOVEMENT_SPEED, 0.2D);
	}

	public static boolean checkBabyEnderDragonSpawnRules(EntityType<? extends BabyEnderDragonEntity> p_27578_,
			LevelAccessor p_27579_, MobSpawnType p_27580_, BlockPos p_27581_, Random p_27582_) {
		return p_27579_.getBlockState(p_27581_.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON)
				&& isBrightEnoughToSpawn(p_27579_, p_27581_);
	}

	protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter p_186210_, BlockPos p_186211_) {
		return p_186210_.getRawBrightness(p_186211_, 0) > 8;
	}

}
