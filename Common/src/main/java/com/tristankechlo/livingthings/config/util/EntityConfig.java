package com.tristankechlo.livingthings.config.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.util.GsonHelper;

import java.util.*;

public abstract class EntityConfig implements IConfig {

    public static final double MIN_HEALTH = 1.0D;
    public static final double MAX_HEALTH = Short.MAX_VALUE;
    public static final double MIN_SPEED = 0.05D;
    public static final double MAX_SPEED = 10.0D;
    public static final double MIN_DAMAGE = 1.0D;
    public static final double MAX_DAMAGE = Short.MAX_VALUE;

    private final List<IConfig> children = new ArrayList<>();
    private final Map<String, List<IConfig>> categories = new HashMap<>();
    private final String fileName;

    public EntityConfig(String id) {
        this.fileName = id + ".json";
    }

    protected void registerConfigValues(IConfig... configs) {
        this.children.addAll(Arrays.asList(configs));
    }

    protected void registerForCategory(String category, IConfig... configs) {
        this.categories.computeIfAbsent(category, k -> new ArrayList<>()).addAll(Arrays.asList(configs));
    }

    @Override
    public void setToDefault() {
        for (IConfig child : this.children) {
            child.setToDefault();
        }
        categories.forEach((category, configs) -> configs.forEach(IConfig::setToDefault));
    }

    @Override
    public void deserialize(JsonObject json) {
        for (IConfig child : this.children) {
            child.deserialize(json);
        }
        categories.forEach((category, configs) -> {
            if (GsonHelper.isObjectNode(json, category)) {
                JsonObject categoryJson = GsonHelper.getAsJsonObject(json, category);
                configs.forEach(config -> config.deserialize(categoryJson));
            } else {
                LivingThings.LOGGER.warn("Category '{}' not found in config file '{}'", category, this.fileName);
            }
        });
    }

    @Override
    public JsonElement serialize(JsonObject json) {
        for (IConfig child : this.children) {
            child.serialize(json);
        }
        categories.forEach((category, configs) -> {
            JsonObject categoryJson = new JsonObject();
            configs.forEach(config -> config.serialize(categoryJson));
            json.add(category, categoryJson);
        });
        return json;
    }

    public String getFileName() {
        return this.fileName;
    }

}
