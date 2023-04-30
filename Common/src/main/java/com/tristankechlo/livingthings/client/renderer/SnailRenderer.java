package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SnailModel;
import com.tristankechlo.livingthings.client.renderer.layer.SnailShellPatternLayer;
import com.tristankechlo.livingthings.entity.SnailEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SnailRenderer extends MobRenderer<SnailEntity, EntityModel<SnailEntity>> {

    public SnailRenderer(Context context) {
        super(context, new SnailModel<>(context.bakeLayer(ModelLayer.SNAIL), 0.0f), 0.35F);
        this.addLayer(new SnailShellPatternLayer(this, context.getModelSet(), SnailEntity.PatternType.BACKGROUND));
        this.addLayer(new SnailShellPatternLayer(this, context.getModelSet(), SnailEntity.PatternType.FOREGROUND));
    }

    @Override
    public ResourceLocation getTextureLocation(SnailEntity snail) {
        return snail.getBodyTexture();
    }

    @Override
    protected void scale(SnailEntity snail, PoseStack matrixStackIn, float partialTickTime) {
        if (snail.isBaby()) {
            matrixStackIn.scale(0.5F, 0.5F, 0.5F);
        }
        super.scale(snail, matrixStackIn, partialTickTime);
    }

}
