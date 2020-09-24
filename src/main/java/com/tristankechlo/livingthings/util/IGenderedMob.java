package com.tristankechlo.livingthings.util;

import net.minecraft.util.WeightedRandom;

public interface IGenderedMob {
	
	Gender getGender();
	
	void setGender(Gender gender);

	public enum Gender {
		MALE, FEMALE;
	}

	static class WeightedGender extends WeightedRandom.Item {
		
		public final Gender gender;

		public WeightedGender(int weight, Gender gender) {
			super(weight);
			this.gender = gender;
		}
		
	}
}
