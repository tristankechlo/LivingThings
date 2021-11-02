package com.tristankechlo.livingthings.entities.misc;

import net.minecraft.util.WeighedRandom;

public interface IScaleableMob {

	byte getScaling();

	void setScaling(byte scaling);

	class WeightedMobScaling extends WeighedRandom.WeighedRandomItem {

		public final byte scaling;

		public WeightedMobScaling(int weight, byte scaling) {
			super(weight);
			this.scaling = scaling;
		}

	}
}
