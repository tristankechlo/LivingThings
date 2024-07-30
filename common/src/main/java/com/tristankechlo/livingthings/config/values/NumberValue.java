package com.tristankechlo.livingthings.config.values;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.util.IConfig;
import net.minecraft.util.GsonHelper;
import net.minecraft.util.Mth;

public abstract class NumberValue<T extends Number> implements IConfig.Value<T> {

    protected final T defaultValue;
    protected T value;
    protected final T minValue;
    protected final T maxValue;
    protected final String identifier;

    public NumberValue(String id, T defaultValue, T minValue, T maxValue) {
        this.identifier = id;
        this.defaultValue = defaultValue;
        this.value = this.clamp(defaultValue, minValue, maxValue); // make sure default value is in range
        this.minValue = minValue;
        this.maxValue = maxValue;
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
        if (GsonHelper.isNumberValue(json, this.getIdentifier())) {
            Number temp = json.get(this.identifier).getAsNumber();
            this.value = this.clamp(temp, this.minValue, this.maxValue);
            return;
        }
        LivingThings.LOGGER.warn("Config value '{}' is missing or not a primitive value, using default value '{}'", this.getIdentifier(), this.defaultValue);
        this.setToDefault();
    }

    protected abstract T clamp(Number value, T minValue, T maxValue);

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

    @Override
    public T get() {
        return this.value;
    }

    public static final class IntegerValue extends NumberValue<Integer> {

        public IntegerValue(String id, int defaultValue) {
            this(id, defaultValue, Integer.MIN_VALUE, Integer.MAX_VALUE);
        }

        public IntegerValue(String id, int defaultValue, int minValue, int maxValue) {
            super(id, defaultValue, minValue, maxValue);
        }

        @Override
        protected Integer clamp(Number value, Integer minValue, Integer maxValue) {
            return Mth.clamp(value.intValue(), minValue, maxValue);
        }

    }

    public static final class DoubleValue extends NumberValue<Double> {

        public DoubleValue(String id, double defaultValue) {
            this(id, defaultValue, Double.MIN_VALUE, Double.MAX_VALUE);
        }

        public DoubleValue(String id, double defaultValue, double minValue, double maxValue) {
            super(id, defaultValue, minValue, maxValue);
        }

        @Override
        protected Double clamp(Number value, Double minValue, Double maxValue) {
            return Mth.clamp(value.doubleValue(), minValue, maxValue);
        }

    }

}
