package com.tristankechlo.livingthings.config.entity;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.util.SpawnData;
import com.tristankechlo.livingthings.config.values.IngredientValue;
import com.tristankechlo.livingthings.config.values.ListValue;
import com.tristankechlo.livingthings.config.values.NumberValue.DoubleValue;
import com.tristankechlo.livingthings.config.values.NumberValue.IntegerValue;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;

public final class PeacockConfig extends EntityConfig {

    private static final PeacockConfig INSTANCE = new PeacockConfig();

    public final DoubleValue health = new DoubleValue("health", 8.0D, MIN_HEALTH, MAX_HEALTH);
    public final DoubleValue movementSpeed = new DoubleValue("movementSpeed", 0.25D, MIN_SPEED, MAX_SPEED);
    public final IntegerValue maxSpawnedInChunk = new IntegerValue("maxSpawnedInChunk", 5, 1, 15);
    public final IngredientValue temptationItems = new IngredientValue("temptationItems", Items.WHEAT);
    public final ListValue<SpawnData> spawnBiomes = new ListValue<>("spawnBiomes", createDefaultSpawns(), SpawnData::serialize, SpawnData::deserialize);

    private PeacockConfig() {
        super("peacock");
        this.registerConfigValues(health, movementSpeed, maxSpawnedInChunk, temptationItems, spawnBiomes);
    }

    public static PeacockConfig get() {
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

    public static Ingredient temptationItems() {
        return INSTANCE.temptationItems.get();
    }

    private static List<SpawnData> createDefaultSpawns() {
        return List.of(
                new SpawnData(15, 3, 5, new ResourceKey[]{Biomes.SAVANNA, Biomes.SAVANNA_PLATEAU, Biomes.WINDSWEPT_SAVANNA}),
                new SpawnData(50, 4, 7, new ResourceKey[]{Biomes.JUNGLE, Biomes.WINDSWEPT_SAVANNA, Biomes.SPARSE_JUNGLE}),
                new SpawnData(25, 4, 7, new ResourceKey[]{Biomes.BAMBOO_JUNGLE})
        );
    }

}
