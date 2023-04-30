package com.tristankechlo.livingthings.entity.misc;

import net.minecraft.util.random.Weight;
import net.minecraft.util.random.WeightedEntry;

public interface IGenderedMob {

    Gender getGender();

    void setGender(Gender gender);

    enum Gender {
        MALE, FEMALE;
    }

    class WeightedGender implements WeightedEntry {

        public final Gender gender;
        public final Weight weight;

        public WeightedGender(int weight, Gender gender) {
            this.gender = gender;
            this.weight = Weight.of(weight);
        }

        @Override
        public Weight getWeight() {
            return this.weight;
        }

    }
}
