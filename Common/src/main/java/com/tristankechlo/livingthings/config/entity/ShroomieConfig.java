package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class ShroomieConfig extends EntityConfig {

    private static final ShroomieConfig INSTANCE = new ShroomieConfig();

    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.2D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Items.WHEAT);

    public final IntegerValue colorBrownWeight = new IntegerValue("colorBrownWeight", 50, 0, Integer.MAX_VALUE);
    public final IntegerValue colorRedWeight = new IntegerValue("colorRedWeight", 50, 0, Integer.MAX_VALUE);

    private ShroomieConfig() {
        super("shroomie");
        this.registerConfigValues(health, movementSpeed, maxSpawnedInChunk, temptationItems);
        this.registerForCategory("colorWeights", colorBrownWeight, colorRedWeight);
    }

    public static ShroomieConfig get() {
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

    public static Ingredient temptationItems() {
        return INSTANCE.temptationItems.get();
    }

}
