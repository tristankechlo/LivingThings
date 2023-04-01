package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.MantarayModel;
import com.tristankechlo.livingthings.entity.MantarayEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MantarayRenderer extends MobRenderer<MantarayEntity, MantarayModel<MantarayEntity>> {

    protected static final ResourceLocation TEXTURE_BLUE = ModelLayer.getEntityTexture("mantaray/mantaray_blue.png");
    protected static final ResourceLocation TEXTURE_BROWN = ModelLayer.getEntityTexture("mantaray/mantaray_brown.png");

    public MantarayRenderer(Context context) {
        super(context, new MantarayModel<>(context.bakeLayer(ModelLayer.MANTARAY)), 0.35F);
    }

    @Override
    public ResourceLocation getTextureLocation(MantarayEntity entity) {
        if (entity.getVariant() == 1) {
            return TEXTURE_BROWN;
        }
        return TEXTURE_BLUE;
    }

    @Override
    protected void scale(MantarayEntity mantaray, PoseStack matrixStackIn, float partialTickTime) {
        float scale = 1.0F + (mantaray.getScaling() * 0.1F);
        matrixStackIn.scale(scale, scale, scale);
    }

}
