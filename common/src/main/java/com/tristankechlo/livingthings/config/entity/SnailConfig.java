package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.util.SpawnData;
import com.tristankechlo.livingthings.config.values.ListValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

public final class SnailConfig extends EntityConfig {

    private static final SnailConfig INSTANCE = new SnailConfig();

    public final DoubleValue health = new DoubleValue("health", 4.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.1D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue temptRange = new DoubleValue("temptRange", 10.0D, MIN_TEMPT, MAX_TEMPT);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 6, 1, 15);
    public final ListValue<SpawnData> spawnBiomes = new ListValue<>("spawnBiomes", createDefaultSpawns(), SpawnData::serialize, SpawnData::deserialize);

    private SnailConfig() {
        super("snail");
        this.registerConfigValues(health, movementSpeed, temptRange, maxSpawnedInChunk, spawnBiomes);
    }

    public static SnailConfig get() {
        return INSTANCE;
    }

    public static double health() {
        return INSTANCE.health.get();
    }

    public static double movementSpeed() {
        return INSTANCE.movementSpeed.get();
    }

    public static double temptRange() {
        return INSTANCE.temptRange.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    private static List<SpawnData> createDefaultSpawns() {
        return List.of(
                new SpawnData(12, 3, 5, new ResourceKey[]{Biomes.PLAINS, Biomes.SUNFLOWER_PLAINS, Biomes.SWAMP, Biomes.FOREST,
                        Biomes.BIRCH_FOREST, Biomes.FLOWER_FOREST, Biomes.RIVER, Biomes.CHERRY_GROVE}),
                new SpawnData(10, 4, 6, new ResourceKey[]{Biomes.MUSHROOM_FIELDS})
        );
    }

}
