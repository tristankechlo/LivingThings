package com.tristankechlo.livingthings.config.misc;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.BiomeSpawnConfig;

import net.minecraftforge.fml.loading.FMLPaths;

public abstract class ConfigManager {

	public static void setup() {
		BiomeSpawnConfig.setToDefault();
		File dir = FMLPaths.CONFIGDIR.get().resolve(LivingThings.MOD_ID).toFile();
		if (!dir.exists()) {
			dir.mkdirs();
		}
		File configFile = new File(dir, "entity_spawn_biomes.json");
		if (configFile.exists()) {
			ConfigManager.loadConfigFromFile(configFile);
			ConfigManager.writeConfigToFile(configFile);
			LivingThings.LOGGER.debug("Saved the checked/corrected BiomeSpawnConfig");
		} else {
			ConfigManager.writeConfigToFile(configFile);
			LivingThings.LOGGER.debug("No BiomeSpawnConfig was found, created a new one.");
		}

	}

	private static void writeConfigToFile(File file) {
		JsonObject jsonObject = BiomeSpawnConfig.serialize();
		String jsonString = ConfigManager.getGson().toJson(jsonObject);
		try {
			FileWriter writer = new FileWriter(file);
			writer.write(jsonString);
			writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void loadConfigFromFile(File file) {
		JsonObject json = null;
		try {
			JsonParser parser = new JsonParser();
			JsonElement jsonElement = parser.parse(new FileReader(file));
			json = jsonElement.getAsJsonObject();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (json != null) {
			BiomeSpawnConfig.deserialize(json);
			LivingThings.LOGGER.debug("BiomeSpawnConfig was successfully loaded.");
		} else {
			LivingThings.LOGGER.debug("Error loading BiomeSpawnConfig, config hasn't been loaded.");
		}
	}

	public static Gson getGson() {
		GsonBuilder gson = new GsonBuilder();
		gson.setPrettyPrinting();
		gson.serializeNulls();
		gson.disableHtmlEscaping();
		return gson.create();
	}

}
