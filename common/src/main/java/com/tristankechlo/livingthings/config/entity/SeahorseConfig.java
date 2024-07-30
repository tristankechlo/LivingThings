package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.util.SpawnData;
import com.tristankechlo.livingthings.config.values.ListValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

public final class SeahorseConfig extends EntityConfig {

    private static final SeahorseConfig INSTANCE = new SeahorseConfig();

    public final DoubleValue health = new DoubleValue("health", 4.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.2D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 6, 1, 15);
    public final ListValue<SpawnData> spawnBiomes = new ListValue<>("spawnBiomes", createDefaultSpawns(), SpawnData::serialize, SpawnData::deserialize);

    public final IntegerValue colorRedWeight = new IntegerValue("colorRedWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorBlueWeight = new IntegerValue("colorBlueWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorGreenWeight = new IntegerValue("colorGreenWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorYellowWeight = new IntegerValue("colorYellowWeight", 20, 0, Integer.MAX_VALUE);
    public final IntegerValue colorPurpleWeight = new IntegerValue("colorPurpleWeight", 20, 0, Integer.MAX_VALUE);

    private SeahorseConfig() {
        super("seahorse");
        this.registerConfigValues(health, movementSpeed, maxSpawnedInChunk, spawnBiomes);
        this.registerForCategory("colorWeights", colorRedWeight, colorBlueWeight, colorGreenWeight, colorYellowWeight, colorPurpleWeight);
    }

    public static SeahorseConfig get() {
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

    private static List<SpawnData> createDefaultSpawns() {
        return List.of(new SpawnData(19, 4, 7, new ResourceKey[]{Biomes.WARM_OCEAN, Biomes.LUKEWARM_OCEAN, Biomes.DEEP_LUKEWARM_OCEAN,
                Biomes.OCEAN, Biomes.DEEP_OCEAN}));
    }

}
