package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.util.SpawnData;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.ListValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

public final class CrabConfig extends EntityConfig {

    private static final CrabConfig INSTANCE = new CrabConfig();

    public final BooleanValue canAttack = new BooleanValue("canAttack", true);
    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.25D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue attackDamage = new DoubleValue("attackDamage", 2.0D, MIN_DAMAGE, MAX_DAMAGE);
    public final DoubleValue temptRange = new DoubleValue("temptRange", 10.0D, MIN_TEMPT, MAX_TEMPT);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 4, 1, 15);
    public final ListValue<SpawnData> spawnBiomes = new ListValue<>("spawnBiomes", createDefaultSpawns(), SpawnData::serialize, SpawnData::deserialize);

    public final IntegerValue colorRedWeight = new IntegerValue("colorRedWeight", 45, 0, Integer.MAX_VALUE);
    public final IntegerValue colorBlueWeight = new IntegerValue("colorBlueWeight", 10, 0, Integer.MAX_VALUE);
    public final IntegerValue colorWhiteWeight = new IntegerValue("colorWhiteWeight", 45, 0, Integer.MAX_VALUE);

    public final IntegerValue scalingNormalWeight = new IntegerValue("scalingNormalWeight", 30, 0, Integer.MAX_VALUE);
    public final IntegerValue scalingSmallWeight = new IntegerValue("scalingSmallWeight", 30, 0, Integer.MAX_VALUE);
    public final IntegerValue scalingLargeWeight = new IntegerValue("scalingLargeWeight", 25, 0, Integer.MAX_VALUE);
    public final IntegerValue scalingExtraLargeWeight = new IntegerValue("scalingExtraLargeWeight", 15, 0, Integer.MAX_VALUE);

    private CrabConfig() {
        super("crab");
        this.registerConfigValues(canAttack, health, movementSpeed, attackDamage, temptRange, maxSpawnedInChunk, spawnBiomes);
        this.registerForCategory("colorVariants", colorRedWeight, colorBlueWeight, colorWhiteWeight);
        this.registerForCategory("scalingVariants", scalingNormalWeight, scalingSmallWeight, scalingLargeWeight, scalingExtraLargeWeight);
    }

    public static CrabConfig get() {
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

    public static double temptRange() {
        return INSTANCE.temptRange.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    private static List<SpawnData> createDefaultSpawns() {
        return List.of(new SpawnData(60, 5, 8, new ResourceKey[]{Biomes.RIVER, Biomes.BEACH, Biomes.SWAMP, Biomes.MANGROVE_SWAMP}));
    }

}
