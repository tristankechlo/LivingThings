package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;

public final class SharkConfig extends EntityConfig {

    private static final SharkConfig INSTANCE = new SharkConfig();

    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final DoubleValue health = new DoubleValue("health", 22.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 1.05D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue attackDamage = new DoubleValue("attackDamage", 6.0D, MIN_DAMAGE, MAX_DAMAGE);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 3, 1, 15);

    private SharkConfig() {
        super("shark");
        this.registerConfigValues(canAttack, health, movementSpeed, attackDamage, maxSpawnedInChunk);
    }

    public static SharkConfig get() {
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

}
