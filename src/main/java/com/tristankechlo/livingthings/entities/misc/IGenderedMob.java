package com.tristankechlo.livingthings.entities.misc;

import net.minecraft.util.WeighedRandom;

public interface IGenderedMob {

	Gender getGender();

	void setGender(Gender gender);

	enum Gender {
		MALE, FEMALE;
	}

	class WeightedGender extends WeighedRandom.WeighedRandomItem {

		public final Gender gender;

		public WeightedGender(int weight, Gender gender) {
			super(weight);
			this.gender = gender;
		}

	}
}
