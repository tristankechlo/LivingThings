package com.tristankechlo.livingthings.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class MinecraftTags {

    // backport of item tags that are introduced in later versions
    public static final TagKey<Item> MEAT = registerItemTag("meat");

    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("minecraft", name));
    }

}
