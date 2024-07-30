package com.tristankechlo.livingthings.config.values;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.util.IConfig;
import net.minecraft.util.GsonHelper;

import java.util.List;
import java.util.function.Function;

public class ListValue<T> implements IConfig.Value<ImmutableList<T>> {

    private final ImmutableList<T> defaultValue;
    private ImmutableList<T> value;
    private final String identifier;
    private final Function<T, JsonElement> serializer;
    private final Function<JsonElement, T> deserializer;

    public ListValue(String id, List<T> defaultValue, Function<T, JsonElement> serializer, Function<JsonElement, T> deserializer) {
        this.identifier = id;
        this.defaultValue = ImmutableList.copyOf(defaultValue);
        this.value = ImmutableList.copyOf(defaultValue);
        this.serializer = serializer;
        this.deserializer = deserializer;
    }

    @Override
    public void setToDefault() {
        this.value = ImmutableList.copyOf(this.defaultValue);
    }

    @Override
    public JsonElement serialize(JsonObject json) {
        JsonArray array = new JsonArray();
        this.value.forEach(t -> {
            array.add(this.serializer.apply(t));
        });
        json.add(this.getIdentifier(), array);
        return json;
    }

    @Override
    public void deserialize(JsonObject json) {
        if (!GsonHelper.isArrayNode(json, this.getIdentifier())) {
            LivingThings.LOGGER.warn("Config value '{}' is missing or is not an array, using default value", this.getIdentifier());
            this.setToDefault();
            return;
        }
        try {
            JsonArray array = GsonHelper.getAsJsonArray(json, this.getIdentifier());
            ImmutableList.Builder<T> builder = ImmutableList.builder();
            for (JsonElement element : array) {
                builder.add(this.deserializer.apply(element));
            }
            this.value = builder.build();
        } catch (Exception e) {
            LivingThings.LOGGER.warn("Error while parsing config value '{}', using default value", this.getIdentifier());
            this.setToDefault();
        }
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public ImmutableList<T> get() {
        return this.value;
    }

}
