package com.tristankechlo.livingthings.config.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.util.GsonHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class EntityConfig implements IConfig {

    public static final double MIN_HEALTH = 1.0D;
    public static final double MAX_HEALTH = Short.MAX_VALUE;
    public static final double MIN_SPEED = 0.05D;
    public static final double MAX_SPEED = 10.0D;
    public static final double MIN_DAMAGE = 1.0D;
    public static final double MAX_DAMAGE = Short.MAX_VALUE;

    private final List<IConfig> children = new ArrayList<>();
    private final List<Pair<String, List<IConfig>>> categories = new ArrayList<>();
    private final String fileName;

    public EntityConfig(String id) {
        this.fileName = id + ".json";
    }

    protected void registerConfigValues(IConfig... configs) {
        this.children.addAll(Arrays.asList(configs));
    }

    protected void registerForCategory(String category, IConfig... configs) {
        this.categories.add(Pair.of(category, Arrays.asList(configs)));
    }

    @Override
    public void setToDefault() {
        for (IConfig child : this.children) {
            child.setToDefault();
        }
        categories.forEach((pair) -> pair.getSecond().forEach(IConfig::setToDefault));
    }

    @Override
    public void deserialize(JsonObject json) {
        for (IConfig child : this.children) {
            child.deserialize(json);
        }
        categories.forEach((pair) -> {
            String category = pair.getFirst();
            List<IConfig> configs = pair.getSecond();
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
        categories.forEach((pair) -> {
            JsonObject categoryJson = new JsonObject();
            pair.getSecond().forEach(config -> config.serialize(categoryJson));
            json.add(pair.getFirst(), categoryJson);
        });
        return json;
    }

    public String getFileName() {
        return this.fileName;
    }

}
