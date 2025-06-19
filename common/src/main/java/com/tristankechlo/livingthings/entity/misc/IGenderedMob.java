package com.tristankechlo.livingthings.entity.misc;

public interface IGenderedMob {

    Gender getGender();

    void setGender(Gender gender);

    enum Gender {
        MALE, FEMALE;
    }

    record WeightedGender(int weight, Gender gender) {}

}
