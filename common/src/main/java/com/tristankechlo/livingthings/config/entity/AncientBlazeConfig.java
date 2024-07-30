package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;

public final class AncientBlazeConfig extends EntityConfig {

    private static final AncientBlazeConfig INSTANCE = new AncientBlazeConfig();

    public final DoubleValue health = new DoubleValue("health", 250.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.25D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue attackDamage = new DoubleValue("attackDamage", 6.0D, MIN_DAMAGE, MAX_DAMAGE);
    public final IntegerValue chargingTime = new IntegerValue("chargingTime", 200, 10, Short.MAX_VALUE);
    public final IntegerValue largeFireballAmount = new IntegerValue("largeFireballAmount", 6, 0, 10);
    public final IntegerValue largeFireballChance = new IntegerValue("largeFireballChance", 20, 0, 100);
    public final IntegerValue blazeSpawnCount = new IntegerValue("blazeSpawnCount", 4, 0, 16);
    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final BooleanValue canSpawn = new BooleanValue("canSpawn", true);
    public final BooleanValue peacefulDespawn = new BooleanValue("peacefulDespawn", true);

    private AncientBlazeConfig() {
        super("ancient_blaze");
        this.registerConfigValues(health, movementSpeed, attackDamage, chargingTime, largeFireballAmount,
                largeFireballChance, blazeSpawnCount, canAttack, canSpawn, peacefulDespawn);
    }

    public static AncientBlazeConfig get() {
        return INSTANCE;
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

    public static int chargingTime() {
        return INSTANCE.chargingTime.get();
    }

    public static int largeFireballAmount() {
        return INSTANCE.largeFireballAmount.get();
    }

    public static int largeFireballChance() {
        return INSTANCE.largeFireballChance.get();
    }

    public static int blazeSpawnCount() {
        return INSTANCE.blazeSpawnCount.get();
    }

    public static boolean canAttack() {
        return INSTANCE.canAttack.get();
    }

    public static boolean canSpawn() {
        return INSTANCE.canSpawn.get();
    }

    public static boolean peacefulDespawn() {
        return INSTANCE.peacefulDespawn.get();
    }

}
