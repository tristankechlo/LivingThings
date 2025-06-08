package com.tristankechlo.livingthings.util;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class MinecraftTags {

    // backport of item tags that are introduced in later versions
    public static final TagKey<Item> MEAT = registerItemTag("meat");

    @SuppressWarnings("removal") // ResourceLocation constructor is removed in 1.21+ => stop forge from complaining here
    private static TagKey<Item> registerItemTag(String name) {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("minecraft", name));
    }

}
