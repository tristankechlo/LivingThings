package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class KoalaConfig extends EntityConfig {

    private static final KoalaConfig INSTANCE = new KoalaConfig();

    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.17D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Items.WHEAT);

    private KoalaConfig() {
        super("koala");
        this.registerConfigValues(health, movementSpeed, maxSpawnedInChunk, temptationItems);
    }

    public static KoalaConfig get() {
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
