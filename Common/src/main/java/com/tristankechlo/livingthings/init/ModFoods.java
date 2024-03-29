package com.tristankechlo.livingthings.init;

import net.minecraft.world.food.FoodProperties;

public final class ModFoods {

    public static final FoodProperties CRAB = (new FoodProperties.Builder()).nutrition(2).saturationMod(0.2F).meat().build();
    public static final FoodProperties COOKED_CRAB = (new FoodProperties.Builder()).nutrition(5).saturationMod(0.5F).meat().build();
    public static final FoodProperties OSTRICH = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.4F).meat().build();
    public static final FoodProperties COOKED_OSTRICH = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.7F).meat().build();
    public static final FoodProperties BANANA = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.3F).build();
    public static final FoodProperties ELEPHANT = (new FoodProperties.Builder()).nutrition(4).saturationMod(0.6F).meat().build();
    public static final FoodProperties COOKED_ELEPHANT = (new FoodProperties.Builder()).nutrition(7).saturationMod(0.9F).meat().build();
    public static final FoodProperties LION = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).meat().build();
    public static final FoodProperties COOKED_LION = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).meat().build();
    public static final FoodProperties GIRAFFE = (new FoodProperties.Builder()).nutrition(3).saturationMod(0.5F).meat().build();
    public static final FoodProperties COOKED_GIRAFFE = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.8F).meat().build();

}
