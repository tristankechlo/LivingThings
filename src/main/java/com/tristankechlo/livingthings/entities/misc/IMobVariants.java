package com.tristankechlo.livingthings.entities.misc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import net.minecraft.util.WeighedRandom;
import net.minecraft.world.entity.AgeableMob;

public interface IMobVariants {

	byte getVariant();

	/**
	 * byte 0 should always be the normal one<br/>
	 * byte 15 should be albino
	 */
	void setVariant(byte variant);

	default byte getVariantFromParents(AgeableMob entity1, AgeableMob entity2) {
		if ((entity1 instanceof IMobVariants) && (entity2 instanceof IMobVariants)) {
			byte parent1 = ((IMobVariants) entity1).getVariant();
			byte parent2 = ((IMobVariants) entity2).getVariant();
			if (parent1 == parent2) {
				return parent1;
			} else {
				if (entity1.getRandom().nextBoolean()) {
					return parent1;
				} else {
					return parent2;
				}
			}
		} else {
			return (byte) 0;
		}
	}

	default byte getRandomVariant(Random random, byte[] variants, int[] weights) {
		if (weights.length != variants.length) {
			throw new IllegalArgumentException("Weights and Variants must have the same length.");
		}
		if (weights.length <= 0 || weights.length > Byte.MAX_VALUE) {
			return 0;
		}
		List<WeightedMobVariant> weightedList = new ArrayList<>();
		for (int i = 0; i < weights.length; i++) {
			if (weights[i] <= 0 || variants[i] < 0) {
				continue;
			}
			weightedList.add(new WeightedMobVariant(weights[i], variants[i]));
		}
		return WeighedRandom.getRandomItem(random, weightedList).get().variant;
	}

	class WeightedMobVariant extends WeighedRandom.WeighedRandomItem {

		public final byte variant;

		public WeightedMobVariant(int weight, byte variant) {
			super(weight);
			this.variant = variant;
		}

	}

}
