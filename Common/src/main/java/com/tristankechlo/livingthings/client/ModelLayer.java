package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class ModelLayer {

    public static final ModelLayerLocation PENGUIN = createLayerLocation("penguin");

    private static ModelLayerLocation createLayerLocation(final String name) {
        return new ModelLayerLocation(new ResourceLocation(LivingThings.MOD_ID, name), "main");
    }

}
