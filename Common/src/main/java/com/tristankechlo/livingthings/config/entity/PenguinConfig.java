package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class PenguinConfig extends EntityConfig {

    public static final PenguinConfig INSTANCE = new PenguinConfig();

    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.25D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 6, 1, 15);
    public final IntegerValue talkInterval = new IntegerValue("talkInterval", 180, 0, Integer.MAX_VALUE);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Items.COD, Items.SALMON, Items.TROPICAL_FISH);

    private PenguinConfig() {
        super("penguin");
        this.registerConfigValues(health, movementSpeed, maxSpawnedInChunk, talkInterval, temptationItems);
    }

    public static PenguinConfig get() {
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

    public static int talkInterval() {
        return INSTANCE.talkInterval.get();
    }

}
