package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.CrabModel;
import com.tristankechlo.livingthings.entity.CrabEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends MobRenderer<CrabEntity, CrabModel<CrabEntity>> {

    protected static final ResourceLocation TEXTURE_WHITE = ModelLayer.getEntityTexture("crab/crab_white.png");
    protected static final ResourceLocation TEXTURE_RED = ModelLayer.getEntityTexture("crab/crab_red.png");
    protected static final ResourceLocation TEXTURE_BLUE = ModelLayer.getEntityTexture("crab/crab_blue.png");

    public CrabRenderer(Context context) {
        super(context, new CrabModel<>(context.bakeLayer(ModelLayer.CRAB)), 0.4F);
    }

    @Override
    public ResourceLocation getTextureLocation(CrabEntity entity) {
        byte variant = entity.getVariant();
        if (variant == 2) {
            return TEXTURE_BLUE;
        } else if (variant == 1) {
            return TEXTURE_WHITE;
        }
        return TEXTURE_RED;
    }

    @Override
    protected void scale(CrabEntity crab, PoseStack matrixStackIn, float partialTickTime) {
        if (!crab.isBaby()) {
            float scale = 1.0F + (crab.getScaling() * 0.1F);
            matrixStackIn.scale(scale, scale, scale);
        }
        matrixStackIn.translate(0.0D, 0.01D, 0.0D);
    }

}
