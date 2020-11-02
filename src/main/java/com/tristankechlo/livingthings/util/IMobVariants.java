package com.tristankechlo.livingthings.util;

import java.util.Random;

import net.minecraft.entity.AgeableEntity;
import net.minecraft.util.WeightedRandom;

public interface IMobVariants {

	byte getVariant();

	/**
	 * byte 0 should always be the normal one<br/>
	 * byte 15 should be albino
	 */
	void setVariant(byte variant);

	default byte getVariantFromParents(AgeableEntity entity1, AgeableEntity entity2) {

		if ((entity1 instanceof IMobVariants) && (entity2 instanceof IMobVariants)) {

			byte parent1 = ((IMobVariants) entity1).getVariant();
			byte parent2 = ((IMobVariants) entity2).getVariant();

			if (parent1 == parent2) {
				return parent1;
			} else {

				if (new Random().nextBoolean()) {
					return parent1;
				} else {
					return parent2;
				}

			}
		} else {
			return (byte) 0;
		}
	}

	class WeightedMobVariant extends WeightedRandom.Item {

		public final byte variant;

		public WeightedMobVariant(int weight, byte variant) {
			super(weight);
			this.variant = variant;
		}

	}

}
