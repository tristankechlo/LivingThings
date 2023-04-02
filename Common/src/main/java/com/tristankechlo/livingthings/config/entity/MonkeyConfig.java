package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

public final class MonkeyConfig extends EntityConfig {

    private static final MonkeyConfig INSTANCE = new MonkeyConfig();

    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final DoubleValue health = new DoubleValue("health", 6.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.23D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue attackDamage = new DoubleValue("attackDamage", 3.0D, MIN_DAMAGE, MAX_DAMAGE);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 7, 1, 15);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Items.APPLE);

    private MonkeyConfig() {
        super("monkey");
        this.registerConfigValues(this.canAttack, this.health, this.movementSpeed, this.attackDamage, this.maxSpawnedInChunk, this.temptationItems);
    }

    public static MonkeyConfig get() {
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

    public static double attackDamage() {
        return INSTANCE.attackDamage.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    public static Ingredient temptationItems() {
        return INSTANCE.temptationItems.get();
    }

}
