package com.tristankechlo.livingthings.config.values;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.util.IConfig;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;

public final class IngredientValue implements IConfig.Value<Ingredient> {

    private final String identifier;
    private final Ingredient defaultValue;
    private Ingredient value;

    public IngredientValue(String identifier, Item... items) {
        this(identifier, Ingredient.of(items));
    }

    public IngredientValue(String identifier, Ingredient defaultValue) {
        this.identifier = identifier;
        this.defaultValue = defaultValue;
    }

    @Override
    public void setToDefault() {
        this.value = this.defaultValue;
    }

    @Override
    public JsonElement serialize(JsonObject json) {
        JsonElement element = this.get().toJson();
        json.add(this.getIdentifier(), element);
        return json;
    }

    @Override
    public void deserialize(JsonObject json) {
        if (json.has(this.getIdentifier())) {
            try {
                JsonElement element = json.get(this.getIdentifier());
                this.value = Ingredient.fromJson(element);
            } catch (Exception e) {
                LivingThings.LOGGER.warn("Failed to parse value '{}' as Ingredient, using default value.", this.getIdentifier());
                this.setToDefault();
            }
        } else {
            LivingThings.LOGGER.warn("No value '{}' was found, using default value.", this.getIdentifier());
            this.setToDefault();
        }
    }

    @Override
    public Ingredient get() {
        return this.value;
    }

    @Override
    public String getIdentifier() {
        return this.identifier;
    }

}
