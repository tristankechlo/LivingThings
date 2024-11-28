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

public final class OstrichConfig extends EntityConfig {

    private static final OstrichConfig INSTANCE = new OstrichConfig();

    public final DoubleValue health = new DoubleValue("health", 20.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.25D, MIN_SPEED, MAX_SPEED);
    public final DoubleValue temptRange = new DoubleValue("temptRange", 10.0D, MIN_TEMPT, MAX_TEMPT);
    public final BooleanValue canBeRidden = new BooleanValue("canBeRidden", true);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final ListValue<SpawnData> spawnBiomes = new ListValue<>("spawnBiomes", createDefaultSpawns(), SpawnData::serialize, SpawnData::deserialize);

    private OstrichConfig() {
        super("ostrich");
        this.registerConfigValues(health, movementSpeed, temptRange, canBeRidden, maxSpawnedInChunk, spawnBiomes);
    }

    public static OstrichConfig get() {
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

    public static boolean canBeRidden() {
        return INSTANCE.canBeRidden.get();
    }

    public static int maxSpawnedInChunk() {
        return INSTANCE.maxSpawnedInChunk.get();
    }

    private static List<SpawnData> createDefaultSpawns() {
        return List.of(new SpawnData(15, 3, 5, new ResourceKey[]{Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA}));
    }

}
