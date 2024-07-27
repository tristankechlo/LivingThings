package com.tristankechlo.livingthings.config.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.util.function.Supplier;

public interface IConfig {

    void setToDefault();

    JsonElement serialize(JsonObject json);

    void deserialize(JsonObject json);

    interface Value<T> extends IConfig, Supplier<T> {

        String getIdentifier();

    }

}
