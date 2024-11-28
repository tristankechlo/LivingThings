package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class RegistryHelper {

    public static ResourceKey<Item> itemId(String name) {
        return ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, name));
    }

    public static ResourceKey<Block> blockId(String name) {
        return ResourceKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, name));
    }

}
