package com.tristankechlo.livingthings.config.entity;

import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.ListValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;

import java.util.ArrayList;
import java.util.List;

public final class NetherKnightConfig extends EntityConfig {

    private static final NetherKnightConfig INSTANCE = new NetherKnightConfig();

    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final DoubleValue health = new DoubleValue("health", 100.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.26D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue attackDamage = new DoubleValue("attackDamage", 10.0D, MIN_DAMAGE, MAX_DAMAGE);
    public final DoubleValue weaponDropChance = new DoubleValue("weaponDropChance", 15, 0, 100);

    public final IntegerValue spawnWeight = new IntegerValue("spawnWeight", 15, 1, Short.MAX_VALUE);
    public final IntegerValue minSpawnCount = new IntegerValue("minSpawnCount", 1, 1, Short.MAX_VALUE);
    public final IntegerValue maxSpawnCount = new IntegerValue("maxSpawnCount", 2, 1, Short.MAX_VALUE);

    public final ListValue<String> swordNames = new ListValue<>("swordNames", getDefaultSwordNames(), JsonPrimitive::new, JsonElement::getAsString);
    public final ListValue<String> axeNames = new ListValue<>("axeNames", getDefaultAxeNames(), JsonPrimitive::new, JsonElement::getAsString);

    private NetherKnightConfig() {
        super("nether_knight");
        this.registerConfigValues(canAttack, health, movementSpeed, attackDamage, weaponDropChance);
        this.registerForCategory("spawning", spawnWeight, minSpawnCount, maxSpawnCount);
        this.registerForCategory("weaponNames", swordNames, axeNames);
    }

    public static NetherKnightConfig get() {
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

    public static double weaponDropChance() {
        return INSTANCE.weaponDropChance.get();
    }

    private static List<String> getDefaultSwordNames() {
        List<String> list = new ArrayList<>();
        list.add("Edge of Suffering");
        list.add("Dragon's Curse");
        list.add("The Soul Harvester");
        list.add("Bloodseeker");
        list.add("Soul Reaper");
        list.add("Doomblade");
        return list;
    }

    private static List<String> getDefaultAxeNames() {
        List<String> list = new ArrayList<>();
        list.add("Demonic Soul Collector");
        list.add("The Reaper's Scythe");
        list.add("The Soul Reaper");
        list.add("The Soul Collector");
        return list;
    }

}
