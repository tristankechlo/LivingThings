package com.tristankechlo.livingthings;

import net.minecraftforge.fml.common.Mod;

@Mod(LivingThings.MOD_ID)
public final class ForgeLivingThings {

    public ForgeLivingThings() {
        LivingThings.LOGGER.info("Hello from ForgeLivingThings!");
        LivingThings.init();
    }

}
