package com.tristankechlo.livingthings.util;

public interface IGenderedMob {
	
	Gender getGender();
	
	void setGender(Gender gender);

	public enum Gender {
		MALE, FEMALE;
	}
}
