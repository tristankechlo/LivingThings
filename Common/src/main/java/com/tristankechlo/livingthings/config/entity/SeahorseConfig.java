package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;

public final class SeahorseConfig extends EntityConfig {

    private static final SeahorseConfig INSTANCE = new SeahorseConfig();

    public final DoubleValue health = new DoubleValue("health", 4.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.2D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 6, 1, 15);

    public final IntegerValue colorRedWeight = new IntegerValue("colorRedWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorBlueWeight = new IntegerValue("colorBlueWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorGreenWeight = new IntegerValue("colorGreenWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorYellowWeight = new IntegerValue("colorYellowWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorPurpleWeight = new IntegerValue("colorPurpleWeight", 20, 0, Integer.MAX_VALUE);

    private SeahorseConfig() {
        super("seahorse");
        this.registerConfigValues(health, movementSpeed, maxSpawnedInChunk);
        this.registerForCategory("colorWeights", colorRedWeight, colorBlueWeight, colorGreenWeight, colorYellowWeight, colorPurpleWeight);
    }

    public static SeahorseConfig get() {
        return INSTANCE;
    }

    public static double health() {
        return INSTANCE.health.get();
    }

    public static double movementSpeed() {
        return INSTANCE.movementSpeed.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

}
