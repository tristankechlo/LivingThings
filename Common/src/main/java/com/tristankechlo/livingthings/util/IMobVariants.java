package com.tristankechlo.livingthings.util;

import net.minecraft.util.RandomSource;
import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;
import net.minecraft.util.random.WeightedRandom;
import net.minecraft.world.entity.AgeableMob;

import java.util.ArrayList;
import java.util.List;

public interface IMobVariants {

    byte getVariant();

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

    default byte getRandomVariant(RandomSource random, byte[] variants, int[] weights) {
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
        return WeightedRandom.getRandomItem(random, weightedList).get().variant;
    }

    class WeightedMobVariant implements WeightedEntry {

        public final byte variant;
        public final Weight weight;

        public WeightedMobVariant(int weight, byte variant) {
            this.variant = variant;
            this.weight = Weight.of(weight);
        }

        @Override
        public Weight getWeight() {
            return this.weight;
        }

    }

}
