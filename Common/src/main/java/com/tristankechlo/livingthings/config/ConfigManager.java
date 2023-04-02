package com.tristankechlo.livingthings.config;

import com.google.gson.*;
import com.google.gson.stream.JsonWriter;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.entity.*;
import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.platform.IPlatformHelper;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

public final class ConfigManager {

    private static final List<EntityConfig> CONFIGS;
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().serializeNulls().create();
    private static final File CONFIG_DIR = IPlatformHelper.INSTANCE.getConfigDirectory().resolve(LivingThings.MOD_ID).toFile();

    static {
        CONFIGS = List.of(GeneralConfig.get(), PenguinConfig.get(), ElephantConfig.get(), GiraffeConfig.get(), LionConfig.get(),
                SharkConfig.get(), OstrichConfig.get(), FlamingoConfig.get(), CrabConfig.get(), MantarayConfig.get(), RaccoonConfig.get(),
                OwlConfig.get(), AncientBlazeConfig.get(), KoalaConfig.get(), SnailConfig.get(), MonkeyConfig.get(), NetherKnightConfig.get());
    }

    public static void loadAndVerifyConfig() {
        ConfigManager.createConfigFolder();
        for (EntityConfig config : CONFIGS) {
            config.setToDefault();
            File configFile = new File(CONFIG_DIR, config.getFileName());
            if (configFile.exists()) {
                ConfigManager.loadConfigFromFile(configFile, config);
                ConfigManager.writeConfigToFile(configFile, config);
                LivingThings.LOGGER.info("Config '{}' was successfully loaded.", config.getFileName());
            } else {
                ConfigManager.writeConfigToFile(configFile, config);
                LivingThings.LOGGER.warn("No config '{}' was found, created a new one.", config.getFileName());
            }
        }
    }

    private static void writeConfigToFile(File file, EntityConfig config) {
        try {
            JsonElement json = config.serialize(new JsonObject());
            JsonWriter writer = new JsonWriter(new FileWriter(file));
            writer.setIndent("\t");
            GSON.toJson(json, writer);
            writer.close();
        } catch (Exception e) {
            LivingThings.LOGGER.error("There was an error writing the config to file: '{}'", config.getFileName());
            e.printStackTrace();
        }
    }

    private static void loadConfigFromFile(File file, EntityConfig config) {
        JsonObject json = null;
        try {
            JsonParser parser = new JsonParser();
            JsonElement jsonElement = parser.parse(new FileReader(file));
            json = jsonElement.getAsJsonObject();
        } catch (JsonSyntaxException e) {
            LivingThings.LOGGER.error("The config file '{}' is not valid json.", config.getFileName());
            e.printStackTrace();
        } catch (Exception e) {
            LivingThings.LOGGER.error("There was an error loading the config file: '{}'", config.getFileName());
            e.printStackTrace();
        }
        if (json != null) {
            config.deserialize(json);
            return;
        } else {
            LivingThings.LOGGER.error("Error loading config '{}', config hasn't been loaded.", config.getFileName());
        }
    }

    private static void createConfigFolder() {
        if (!CONFIG_DIR.exists()) {
            if (!CONFIG_DIR.mkdirs()) {
                throw new RuntimeException("Could not create config folder: " + CONFIG_DIR.getAbsolutePath());
            }
        }
    }

}
