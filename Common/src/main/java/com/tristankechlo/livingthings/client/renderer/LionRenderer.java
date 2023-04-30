package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.LionModel;
import com.tristankechlo.livingthings.entity.LionEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LionRenderer extends MobRenderer<LionEntity, LionModel<LionEntity>> {

    protected static final ResourceLocation TEXTURE = LivingThingsClient.getEntityTexture("lion/lion.png");
    protected static final ResourceLocation TEXTURE_WHITE = LivingThingsClient.getEntityTexture("lion/lion_white.png");

    public LionRenderer(Context context) {
        super(context, new LionModel<>(context.bakeLayer(ModelLayer.LION)), 1F);
    }

    @Override
    public ResourceLocation getTextureLocation(LionEntity entity) {
        if (entity.getVariant() != 0) {
            return TEXTURE_WHITE;
        }
        return TEXTURE;
    }

}
