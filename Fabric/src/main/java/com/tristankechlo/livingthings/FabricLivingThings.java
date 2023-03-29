package com.tristankechlo.livingthings;

import net.fabricmc.api.ModInitializer;

public final class FabricLivingThings implements ModInitializer {

    @Override
    public void onInitialize() {
        LivingThings.LOGGER.info("Hello from FabricLivingThings!");
        LivingThings.init();
    }

}
