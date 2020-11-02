package com.tristankechlo.livingthings.util;

import net.minecraft.util.WeightedRandom;

public interface IScaleableMob {

	byte getScaling();

	void setScaling(byte scaling);

	class WeightedMobScaling extends WeightedRandom.Item {

		public final byte scaling;

		public WeightedMobScaling(int weight, byte scaling) {
			super(weight);
			this.scaling = scaling;
		}

	}
}
