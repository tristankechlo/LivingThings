package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.util.SpawnData;
import com.tristankechlo.livingthings.config.values.ListValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

public final class KoalaConfig extends EntityConfig {

    private static final KoalaConfig INSTANCE = new KoalaConfig();

    public final DoubleValue health = new DoubleValue("health", 10.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.17D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue temptRange = new DoubleValue("temptRange", 10.0D, MIN_TEMPT, MAX_TEMPT);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final ListValue<SpawnData> spawnBiomes = new ListValue<>("spawnBiomes", createDefaultSpawns(), SpawnData::serialize, SpawnData::deserialize);

    private KoalaConfig() {
        super("koala");
        this.registerConfigValues(health, movementSpeed, temptRange, maxSpawnedInChunk, spawnBiomes);
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

    public static double temptRange() {
        return INSTANCE.temptRange.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    private static List<SpawnData> createDefaultSpawns() {
        return List.of(
                new SpawnData(50, 3, 8, new ResourceKey[]{Biomes.JUNGLE, Biomes.SPARSE_JUNGLE}),
                new SpawnData(25, 3, 8, new ResourceKey[]{Biomes.BAMBOO_JUNGLE}),
                new SpawnData(10, 3, 8, new ResourceKey[]{Biomes.SAVANNA_PLATEAU})
        );
    }

}
