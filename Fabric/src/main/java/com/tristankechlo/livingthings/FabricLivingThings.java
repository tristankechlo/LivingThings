package com.tristankechlo.livingthings;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.world.entity.SpawnPlacements;

public final class FabricLivingThings implements ModInitializer {

    @Override
    public void onInitialize() {
        LivingThings.init();
        LivingThings.registerMobAttributes(FabricDefaultAttributeRegistry::register);
        LivingThings.registerSpawnPlacements(SpawnPlacements::register);
    }

}
