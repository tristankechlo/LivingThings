package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public final class FabricItemGroup {

    public static final CreativeModeTab GENERAL = FabricItemGroupBuilder.create(new ResourceLocation(LivingThings.MOD_ID, "general"))
            .icon(() -> ModItems.SHARK_TOOTH.get().getDefaultInstance())
            .appendItems(stacks -> {
                ModItems.ALL_ITEMS.forEach(item -> stacks.add(item.get().getDefaultInstance()));
                ModItems.SPAWN_EGGS.forEach(item -> stacks.add(item.get().getDefaultInstance()));
            })
            .build();

}
