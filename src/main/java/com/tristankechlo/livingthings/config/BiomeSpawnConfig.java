package com.tristankechlo.livingthings.config;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.function.Supplier;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.entity.CrabConfig;
import com.tristankechlo.livingthings.config.entity.ElephantConfig;
import com.tristankechlo.livingthings.config.entity.FlamingoConfig;
import com.tristankechlo.livingthings.config.entity.GiraffeConfig;
import com.tristankechlo.livingthings.config.entity.KoalaConfig;
import com.tristankechlo.livingthings.config.entity.LionConfig;
import com.tristankechlo.livingthings.config.entity.MantarayConfig;
import com.tristankechlo.livingthings.config.entity.MonkeyConfig;
import com.tristankechlo.livingthings.config.entity.OstrichConfig;
import com.tristankechlo.livingthings.config.entity.OwlConfig;
import com.tristankechlo.livingthings.config.entity.PenguinConfig;
import com.tristankechlo.livingthings.config.entity.RaccoonConfig;
import com.tristankechlo.livingthings.config.entity.SeahorseConfig;
import com.tristankechlo.livingthings.config.entity.SharkConfig;
import com.tristankechlo.livingthings.config.entity.ShroomieConfig;
import com.tristankechlo.livingthings.config.entity.SnailConfig;
import com.tristankechlo.livingthings.config.misc.SpawnData;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraftforge.registries.ForgeRegistries;

public final class BiomeSpawnConfig {

	private static Map<String, List<SpawnData>> entityBasedSpawnData;
	private static Map<String, Supplier<List<SpawnData>>> entityConfigEntries = getEntityConfigEntries();
	private static Type type = new TypeToken<Map<String, List<SpawnData>>>() {}.getType();

	private BiomeSpawnConfig() {}

	public static void setToDefault() {
		entityBasedSpawnData = new HashMap<>();
		for (Map.Entry<String, Supplier<List<SpawnData>>> entry : entityConfigEntries.entrySet()) {
			entityBasedSpawnData.put(entry.getKey(), entry.getValue().get());
		}
	}

	public static JsonObject serialize() {
		return new Gson().toJsonTree(entityBasedSpawnData, type).getAsJsonObject();
	}

	public static void deserialize(JsonObject json) {
		entityBasedSpawnData = new Gson().fromJson(json, type);
		// remove all entries where the entity is not from this mod
		for (Iterator<Entry<String, List<SpawnData>>> it = entityBasedSpawnData.entrySet().iterator(); it.hasNext();) {
			Entry<String, List<SpawnData>> entry = it.next();
			ResourceLocation name = new ResourceLocation(entry.getKey());
			if (!name.getNamespace().equals(LivingThings.MOD_ID)) {
				it.remove();
			}
		}
		// make sure for every entity is some data registered
		// checks only if the key is found
		// so a key with an empty array as value will be processed,
		// as if the spawning for that entity is disabled
		for (Map.Entry<String, Supplier<List<SpawnData>>> entry : entityConfigEntries.entrySet()) {
			if (!entityBasedSpawnData.containsKey(entry.getKey())) {
				entityBasedSpawnData.put(entry.getKey(), entry.getValue().get());
			}
		}
	}

	/**
	 * call only after the ForgeRegistries are finished loading
	 * 
	 * @return a Map with the biome-ResourceLocation as the key
	 */
	public static Map<ResourceLocation, Map<EntityClassification, List<Spawners>>> getBiomeSpawnMap() {
		Map<ResourceLocation, Map<EntityClassification, List<Spawners>>> biomeSpawnData = new HashMap<>();
		for (Map.Entry<String, List<SpawnData>> entry : entityBasedSpawnData.entrySet()) {
			ResourceLocation name = new ResourceLocation(entry.getKey());
			EntityType<?> entityType = ForgeRegistries.ENTITIES.getValue(name);
			if (entityType == null || !name.getNamespace().equals(LivingThings.MOD_ID)) {
				continue;
			}
			for (SpawnData spawnData : entry.getValue()) {
				for (String biomeString : spawnData.biomes) {
					ResourceLocation biome = new ResourceLocation(biomeString);
					if (!ForgeRegistries.BIOMES.containsKey(biome)) {
						continue;
					}
					Map<EntityClassification, List<Spawners>> classificationMap = biomeSpawnData.get(biome);
					if (classificationMap == null) {
						classificationMap = new HashMap<>();
					}
					EntityClassification entityClassification = entityType.getCategory();
					List<Spawners> spawnersMap = classificationMap.get(entityClassification);
					if (spawnersMap == null) {
						spawnersMap = new ArrayList<>();
					}
					int weight = BiomeSpawnConfig.checkRange(spawnData.weight, 1, Short.MAX_VALUE, 15);
					int minCount = BiomeSpawnConfig.checkRange(spawnData.minCount, 1, Short.MAX_VALUE, 3);
					int maxCount = BiomeSpawnConfig.checkRange(spawnData.maxCount, 1, Short.MAX_VALUE, 5);
					Spawners spawners = new Spawners(entityType, weight, minCount, maxCount);
					spawnersMap.add(spawners);
					classificationMap.put(entityClassification, spawnersMap);
					biomeSpawnData.put(biome, classificationMap);
				}
			}
		}
		return biomeSpawnData;
	}

	private static Map<String, Supplier<List<SpawnData>>> getEntityConfigEntries() {
		Map<String, Supplier<List<SpawnData>>> names = new HashMap<>();
		names.put("livingthings:crab", CrabConfig::getDefaultSpawns);
		names.put("livingthings:elephant", ElephantConfig::getDefaultSpawns);
		names.put("livingthings:flamingo", FlamingoConfig::getDefaultSpawns);
		names.put("livingthings:giraffe", GiraffeConfig::getDefaultSpawns);
		names.put("livingthings:koala", KoalaConfig::getDefaultSpawns);
		names.put("livingthings:lion", LionConfig::getDefaultSpawns);
		names.put("livingthings:mantaray", MantarayConfig::getDefaultSpawns);
		names.put("livingthings:monkey", MonkeyConfig::getDefaultSpawns);
		names.put("livingthings:ostrich", OstrichConfig::getDefaultSpawns);
		names.put("livingthings:owl", OwlConfig::getDefaultSpawns);
		names.put("livingthings:penguin", PenguinConfig::getDefaultSpawns);
		names.put("livingthings:raccoon", RaccoonConfig::getDefaultSpawns);
		names.put("livingthings:shark", SharkConfig::getDefaultSpawns);
		names.put("livingthings:snail", SnailConfig::getDefaultSpawns);
		names.put("livingthings:shroomie", ShroomieConfig::getDefaultSpawns);
		names.put("livingthings:seahorse", SeahorseConfig::getDefaultSpawns);
		return names;
	}

	public static int checkRange(int checkMe, int min, int max, int defaultValue) {
		if (checkMe >= min && checkMe <= max) {
			return checkMe;
		}
		return defaultValue;
	}

}
