package com.tristankechlo.livingthings.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockAndTintGetter;

public class Util {

	public static byte clamp(byte value, byte min, byte max) {
		if (value < min) {
			return min;
		} else {
			return value > max ? max : value;
		}
	}

	protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter blockAndTintGetter, BlockPos pos) {
		return blockAndTintGetter.getRawBrightness(pos, 0) > 8;
	}

}
