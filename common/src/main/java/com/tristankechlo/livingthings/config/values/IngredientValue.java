package com.tristankechlo.livingthings.config.values;

import com.google.gson.*;
import com.mojang.datafixers.util.Either;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.config.util.IConfig;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapedRecipe;

import java.util.ArrayList;
import java.util.List;

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
        this.value = defaultValue;
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
                this.value = deserialize(element);
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

    private Ingredient deserialize(JsonElement jsonElement) {
        if (jsonElement == null || jsonElement.isJsonNull()) {
            throw new JsonSyntaxException("Ingredient '" + this.getIdentifier() + "' cannot be null");
        }
        if (jsonElement.isJsonObject()) {
            return deserializeSingle(GsonHelper.convertToJsonObject(jsonElement, getIdentifier()));
        }
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            if (jsonArray.size() == 0) {
                throw new JsonSyntaxException("Item array cannot be empty, at least one item must be defined");
            }
            List<Item> list = new ArrayList<>();
            jsonArray.forEach((element) -> {
                JsonObject json = GsonHelper.convertToJsonObject(element, "item");
                if (!json.has("item")) {
                    LivingThings.LOGGER.warn("Element '{}' is missing 'item' key, skipping...", json);
                    return;
                }
                Item item = ShapedRecipe.itemFromJson(json);
                list.add(item);
            });
            return Ingredient.of(list.toArray(new Item[0]));
        }
        throw new JsonSyntaxException("Expected item to be object or array of objects");
    }

    private static Ingredient deserializeSingle(JsonObject jsonObject) {
        Either<Item, TagKey<Item>> either = deserializeO(jsonObject);
        if (either.left().isPresent()) {
            return Ingredient.of(either.left().get());
        }
        return Ingredient.of(either.right().get());
    }

    private static Either<Item, TagKey<Item>> deserializeO(JsonObject json) {
        if (json.has("item") && json.has("tag")) {
            throw new JsonParseException("An ingredient entry is either a tag or an item, not both");
        }
        if (json.has("item")) {
            return Either.left(ShapedRecipe.itemFromJson(json));
        }
        if (json.has("tag")) {
            ResourceLocation resourceLocation = new ResourceLocation(GsonHelper.getAsString(json, "tag"));
            TagKey<Item> tagKey = TagKey.create(Registry.ITEM_REGISTRY, resourceLocation);
            return Either.right(tagKey);
        }
        throw new JsonParseException("An ingredient entry needs either a tag or an item");
    }

}
