package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class BabyEnderDragonConfig extends EntityConfig {

    private static final BabyEnderDragonConfig INSTANCE = new BabyEnderDragonConfig();

    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.3D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue flyingSpeed = new DoubleValue("flyingSpeed", 0.5D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue followRange = new DoubleValue("followRange", 64.0D, 0.0D, 2048.0D); // twice the default FOLLOW_RANGE
    public final BooleanValue canSpawn = new BooleanValue("canSpawn", true);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Items.CHORUS_FRUIT);

    private BabyEnderDragonConfig() {
        super("baby_ender_dragon");
        this.registerConfigValues(canAttack, health, movementSpeed, flyingSpeed, followRange, canSpawn, maxSpawnedInChunk, temptationItems);
    }

    public static BabyEnderDragonConfig get() {
        return INSTANCE;
    }

    public static boolean canAttack() {
        return INSTANCE.canAttack.get();
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

    public static boolean canSpawn() {
        return INSTANCE.canSpawn.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    public static Ingredient temptationItems() {
        return INSTANCE.temptationItems.get();
    }

    public static double followRange() {
        return INSTANCE.followRange.get();
    }
}
