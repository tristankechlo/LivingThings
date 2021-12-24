package com.tristankechlo.livingthings.entities.misc;

import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;

public interface IScaleableMob {

	byte getScaling();

	void setScaling(byte scaling);

	class WeightedMobScaling implements WeightedEntry {

		public final byte scaling;
		public final Weight weight;

		public WeightedMobScaling(int weight, byte scaling) {
			this.weight = Weight.of(weight);
			this.scaling = scaling;
		}

		@Override
		public Weight getWeight() {
			return this.weight;
		}

	}
}
