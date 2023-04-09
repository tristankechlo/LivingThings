package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.ShroomieModel;
import com.tristankechlo.livingthings.entity.ShroomieEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShroomieRenderer extends MobRenderer<ShroomieEntity, EntityModel<ShroomieEntity>> {

    private static final ResourceLocation RED = LivingThingsClient.getEntityTexture("shroomie/shroomie_red.png");
    private static final ResourceLocation BROWN = LivingThingsClient.getEntityTexture("shroomie/shroomie_brown.png");

    public ShroomieRenderer(Context context) {
        super(context, new ShroomieModel<>(context.bakeLayer(ModelLayer.SHROOMIE)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(ShroomieEntity shroomie) {
        if (shroomie.getVariant() == 1) {
            return RED;
        }
        return BROWN;
    }

}