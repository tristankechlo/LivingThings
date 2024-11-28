package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.MantarayModel;
import com.tristankechlo.livingthings.client.renderer.state.MantarayRenderState;
import com.tristankechlo.livingthings.entity.MantarayEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MantarayRenderer extends MobRenderer<MantarayEntity, MantarayRenderState, MantarayModel<MantarayRenderState>> {

    protected static final ResourceLocation TEXTURE_BLUE = LivingThings.getEntityTexture("mantaray/mantaray_blue.png");
    protected static final ResourceLocation TEXTURE_BROWN = LivingThings.getEntityTexture("mantaray/mantaray_brown.png");

    public MantarayRenderer(Context context) {
        super(context, new MantarayModel<>(context.bakeLayer(ModelLayer.MANTARAY)), 0.35F);
    }

    @Override
    public MantarayRenderState createRenderState() {
        return new MantarayRenderState();
    }

    @Override
    public void extractRenderState(MantarayEntity entity, MantarayRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(MantarayRenderState entity) {
        if (entity.variant == 1) {
            return TEXTURE_BROWN;
        }
        return TEXTURE_BLUE;
    }

    @Override
    protected void scale(MantarayRenderState mantaray, PoseStack poseStack) {
        float scale = 1.0F + (mantaray.scale * 0.1F);
        poseStack.scale(scale, scale, scale);
    }

}
