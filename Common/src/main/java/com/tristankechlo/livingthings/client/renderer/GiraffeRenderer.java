package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.GiraffeModel;
import com.tristankechlo.livingthings.entity.GiraffeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GiraffeRenderer extends MobRenderer<GiraffeEntity, GiraffeModel<GiraffeEntity>> {

    protected static final ResourceLocation TEXTURE_1 = LivingThingsClient.getEntityTexture("giraffe/giraffe_1.png");
    protected static final ResourceLocation TEXTURE_2 = LivingThingsClient.getEntityTexture("giraffe/giraffe_2.png");
    protected static final ResourceLocation TEXTURE_WHITE = LivingThingsClient.getEntityTexture("giraffe/giraffe_white.png");

    public GiraffeRenderer(Context context) {
        super(context, new GiraffeModel<>(context.bakeLayer(ModelLayer.GIRAFFE)), 0.8F);
    }

    @Override
    public ResourceLocation getTextureLocation(GiraffeEntity entity) {
        byte variant = entity.getVariant();
        if (variant == 2) {
            return TEXTURE_WHITE;
        } else if (variant == 1) {
            return TEXTURE_2;
        }
        return TEXTURE_1;
    }

}

