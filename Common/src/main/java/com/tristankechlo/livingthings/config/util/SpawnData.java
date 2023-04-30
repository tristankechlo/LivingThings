package com.tristankechlo.livingthings.config.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class SpawnData {

    private final List<ResourceLocation> biomes = new ArrayList<>();
    private final int weight;
    private final int minCount;
    private final int maxCount;

    public SpawnData(int weight, int minCount, int maxCount, ResourceKey<Biome>[] biomes) {
        this(weight, minCount, maxCount, Arrays.stream(biomes).map(ResourceKey::location).toList());
    }

    public SpawnData(int weight, int minCount, int maxCount, List<ResourceLocation> biomes) {
        this.weight = weight;
        this.minCount = minCount;
        this.maxCount = maxCount;
        this.biomes.addAll(biomes);
    }

    public MobSpawnSettings.SpawnerData asSpawnerData(EntityType<?> entityType) {
        return new MobSpawnSettings.SpawnerData(entityType, weight, minCount, maxCount);
    }

    public List<ResourceLocation> getBiomes() {
        return biomes;
    }

    public static JsonElement serialize(SpawnData spawnData) {
        JsonObject json = new JsonObject();
        json.addProperty("weight", spawnData.weight);
        json.addProperty("minGroupSize", spawnData.minCount);
        json.addProperty("maxGroupSize", spawnData.maxCount);

        JsonArray biomes = new JsonArray();
        spawnData.biomes.forEach((biome) -> {
            JsonPrimitive primitive = new JsonPrimitive(biome.toString());
            biomes.add(primitive);
        });
        json.add("biomes", biomes);

        return json;
    }

    public static SpawnData deserialize(JsonElement jsonElement) {
        JsonObject json = GsonHelper.convertToJsonObject(jsonElement, "spawnData");
        int weight = GsonHelper.getAsInt(json, "weight");
        int minCount = GsonHelper.getAsInt(json, "minGroupSize");
        int maxCount = GsonHelper.getAsInt(json, "maxGroupSize");

        List<ResourceLocation> biomes = new ArrayList<>();
        JsonArray biomesArray = GsonHelper.getAsJsonArray(json, "biomes");
        for (JsonElement biomeElement : biomesArray) {
            String biomeString = GsonHelper.convertToString(biomeElement, "biome");
            ResourceLocation biomeLoc = ResourceLocation.tryParse(biomeString);
            if (biomeLoc == null) {
                LivingThings.LOGGER.error("Ignoring invalid biome: {}", biomeString);
                continue;
            }
            Biome biome = BuiltinRegistries.BIOME.get(biomeLoc); // only to check if valid biome
            if (biome == null) {
                LivingThings.LOGGER.error("Ignoring unknown biome: {}", biomeString);
                continue;
            }
            biomes.add(biomeLoc);
        }

        return new SpawnData(weight, minCount, maxCount, biomes);
    }

}
