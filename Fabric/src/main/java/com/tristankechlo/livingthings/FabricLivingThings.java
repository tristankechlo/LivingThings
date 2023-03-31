package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.config.util.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public final class FabricLivingThings implements ModInitializer {

    @Override
    public void onInitialize() {
        LivingThings.init(); // register all items, blocks, ...
        ConfigManager.loadAndVerifyConfig();
        LivingThings.registerMobAttributes(FabricDefaultAttributeRegistry::register);
        LivingThings.registerSpawnPlacements();
    }

}
