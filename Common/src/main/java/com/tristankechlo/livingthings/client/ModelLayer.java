package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public final class ModelLayer {

    public static final ModelLayerLocation ELEPHANT = createLayerLocation("elephant");
    public static final ModelLayerLocation GIRAFFE = createLayerLocation("giraffe");
    public static final ModelLayerLocation LION = createLayerLocation("lion");
    public static final ModelLayerLocation SHARK = createLayerLocation("shark");
    public static final ModelLayerLocation PENGUIN = createLayerLocation("penguin");

    private static ModelLayerLocation createLayerLocation(final String name) {
        return new ModelLayerLocation(new ResourceLocation(LivingThings.MOD_ID, name), "main");
    }

    public static ResourceLocation getEntityTexture(String texture) {
        return new ResourceLocation(LivingThings.MOD_ID, "textures/entity/" + texture);
    }

}
