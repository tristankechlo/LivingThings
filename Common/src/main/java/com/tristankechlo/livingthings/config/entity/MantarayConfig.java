package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;

public final class MantarayConfig extends EntityConfig {

    private static final MantarayConfig INSTANCE = new MantarayConfig();

    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 1.0D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);

    public final IntegerValue colorBlueVariant = new IntegerValue("colorBlueVariant", 50, 0, Integer.MAX_VALUE);
    public final IntegerValue colorBrownVariant = new IntegerValue("colorBrownVariant", 50, 0, Integer.MAX_VALUE);

    public final IntegerValue scalingNormalWeight = new IntegerValue("scalingNormalWeight", 30, 0, Integer.MAX_VALUE);
    public final IntegerValue scalingSmallVariant = new IntegerValue("scalingSmallVariant", 30, 0, Integer.MAX_VALUE);
    public final IntegerValue scalingLargeVariant = new IntegerValue("scalingLargeVariant", 25, 0, Integer.MAX_VALUE);
    public final IntegerValue scalingExtraLargeWeight = new IntegerValue("scalingExtraLargeWeight", 15, 0, Integer.MAX_VALUE);

    private MantarayConfig() {
        super("mantaray");
        this.registerConfigValues(health, movementSpeed, maxSpawnedInChunk);
        this.registerForCategory("colorVariants", colorBlueVariant, colorBrownVariant);
        this.registerForCategory("scalingVariants", scalingNormalWeight, scalingSmallVariant, scalingLargeVariant, scalingExtraLargeWeight);
    }

    public static MantarayConfig get() {
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
