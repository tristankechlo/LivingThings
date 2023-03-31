package com.tristankechlo.livingthings.config.values;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.util.IConfig;
import net.minecraft.util.GsonHelper;

public final class BooleanValue implements IConfig.Value<Boolean> {

    private final String identifier;
    private final boolean defaultValue;
    private boolean value;

    public BooleanValue(String identifier, boolean defaultValue) {
        this.identifier = identifier;
        this.defaultValue = defaultValue;
        this.value = defaultValue;
    }

    @Override
    public void setToDefault() {
        this.value = this.defaultValue;
    }

    @Override
    public JsonElement serialize(JsonObject json) {
        json.addProperty(this.getIdentifier(), this.get());
        return json;
    }

    @Override
    public void deserialize(JsonObject json) {
        if (GsonHelper.isBooleanValue(json, this.getIdentifier())) {
            this.value = GsonHelper.getAsBoolean(json, this.getIdentifier(), defaultValue);
            return;
        }
        LivingThings.LOGGER.warn("Config value {} is missing or not a primitive value, using default value {}", this.getIdentifier(), this.defaultValue);
        this.setToDefault();
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public Boolean get() {
        return this.value;
    }

}
