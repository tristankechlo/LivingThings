package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class OwlConfig extends EntityConfig {

    private static final OwlConfig INSTANCE = new OwlConfig();

    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.25D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue flyingSpeed = new DoubleValue("flyingSpeed", 0.5D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 6, 1, 15);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    public final IngredientValue tamingItems = new IngredientValue("tamingItems", Items.WHEAT_SEEDS);

    public final IntegerValue colorWhiteWeight = new IntegerValue("colorWhiteWeight", 33, 0, Integer.MAX_VALUE);
    public final IntegerValue colorBrownWeight = new IntegerValue("colorBrownWeight", 33, 0, Integer.MAX_VALUE);
    public final IntegerValue colorBlackWeight = new IntegerValue("colorBlackWeight", 33, 0, Integer.MAX_VALUE);

    private OwlConfig() {
        super("owl");
        this.registerConfigValues(health, movementSpeed, flyingSpeed, maxSpawnedInChunk, temptationItems, tamingItems);
        this.registerForCategory("colorWeights", colorWhiteWeight, colorBrownWeight, colorBlackWeight);
    }

    public static OwlConfig get() {
        return INSTANCE;
    }

    public static double health() {
        return INSTANCE.health.get();
    }

    public static double movementSpeed() {
        return INSTANCE.movementSpeed.get();
    }

    public static double flyingSpeed() {
        return INSTANCE.flyingSpeed.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    public static Ingredient temptationItems() {
        return INSTANCE.temptationItems.get();
    }

    public static Ingredient tamingItems() {
        return INSTANCE.tamingItems.get();
    }

}
