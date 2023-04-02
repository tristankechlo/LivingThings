package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.RaccoonModel;
import com.tristankechlo.livingthings.entity.RaccoonEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RaccoonRenderer extends MobRenderer<RaccoonEntity, RaccoonModel<RaccoonEntity>> {

    protected static final ResourceLocation TEXTURE = ModelLayer.getEntityTexture("raccoon/raccoon.png");

    public RaccoonRenderer(Context context) {
        super(context, new RaccoonModel<>(context.bakeLayer(ModelLayer.RACCOON)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(RaccoonEntity entity) {
        return TEXTURE;
    }

}
