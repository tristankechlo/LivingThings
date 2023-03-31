package com.tristankechlo.livingthings.config.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
    private final String fileName;

    public EntityConfig(String id) {
        this.fileName = id + ".json";
    }

    protected void registerConfigValues(IConfig... configs) {
        this.children.addAll(Arrays.asList(configs));
    }

    @Override
    public void setToDefault() {
        for (IConfig child : this.children) {
            child.setToDefault();
        }
    }

    @Override
    public void deserialize(JsonObject json) {
        for (IConfig child : this.children) {
            child.deserialize(json);
        }
    }

    @Override
    public JsonElement serialize(JsonObject json) {
        for (IConfig child : this.children) {
            child.serialize(json);
        }
        return json;
    }

    public String getFileName() {
        return this.fileName;
    }

}
