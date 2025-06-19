package com.tristankechlo.livingthings.entity.misc;

public interface IScaleableMob {

    byte getScaling();

    void setScaling(byte scaling);

    record WeightedMobScaling(int weight, byte scaling) {}

}
