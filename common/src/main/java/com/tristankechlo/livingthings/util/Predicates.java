package com.tristankechlo.livingthings.util;

import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;

public final class Predicates {

    // for all food related item tags, keep an ingredient instance for easier access
    public static final Predicate<ItemStack> BABY_ENDERDRAGON_FOOD = of(LivingThingsTags.BABY_ENDERDRAGON_FOOD);
    public static final Predicate<ItemStack> CRAB_FOOD = of(LivingThingsTags.CRAB_FOOD);
    public static final Predicate<ItemStack> ELEPHANT_FOOD = of(LivingThingsTags.ELEPHANT_FOOD);
    public static final Predicate<ItemStack> ELEPHANT_TAMING_OOD = of(LivingThingsTags.ELEPHANT_TAMING_FOOD);
    public static final Predicate<ItemStack> FLAMINGO_FOOD = of(LivingThingsTags.FLAMINGO_FOOD);
    public static final Predicate<ItemStack> GIRAFFE_FOOD = of(LivingThingsTags.GIRAFFE_FOOD);
    public static final Predicate<ItemStack> KOALA_FOOD = of(LivingThingsTags.KOALA_FOOD);
    public static final Predicate<ItemStack> LION_FOOD = of(LivingThingsTags.LION_FOOD);
    public static final Predicate<ItemStack> MONKEY_FOOD = of(LivingThingsTags.MONKEY_FOOD);
    public static final Predicate<ItemStack> OSTRICH_FOOD = of(LivingThingsTags.OSTRICH_FOOD);
    public static final Predicate<ItemStack> OWL_FOOD = of(LivingThingsTags.OWL_FOOD);
    public static final Predicate<ItemStack> PEACOCK_FOOD = of(LivingThingsTags.PEACOCK_FOOD);
    public static final Predicate<ItemStack> PENGUIN_FOOD = of(LivingThingsTags.PENGUIN_FOOD);
    public static final Predicate<ItemStack> RACCOON_FOOD = of(LivingThingsTags.RACCOON_FOOD);
    public static final Predicate<ItemStack> SHROOMIE_FOOD = of(LivingThingsTags.SHROOMIE_FOOD);
    public static final Predicate<ItemStack> SNAIL_FOOD = of(LivingThingsTags.SNAIL_FOOD);

    private static Predicate<ItemStack> of(TagKey<Item> tag) {
        return (s) -> s.is(tag);
    }

}
