package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.CrabModel;
import com.tristankechlo.livingthings.client.renderer.state.CrabRenderState;
import com.tristankechlo.livingthings.entity.CrabEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class CrabRenderer extends AgeableMobRenderer<CrabEntity, CrabRenderState, CrabModel<CrabRenderState>> {

    protected static final ResourceLocation TEXTURE_WHITE = LivingThings.getEntityTexture("crab/crab_white.png");
    protected static final ResourceLocation TEXTURE_RED = LivingThings.getEntityTexture("crab/crab_red.png");
    protected static final ResourceLocation TEXTURE_BLUE = LivingThings.getEntityTexture("crab/crab_blue.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.6F);

    public CrabRenderer(Context context) {
        super(context, CrabModel::new, ModelLayer.CRAB, ModelLayer.CRAB_BABY, 0.4F);
    }

    @Override
    public CrabRenderState createRenderState() {
        return new CrabRenderState();
    }

    @Override
    public void extractRenderState(CrabEntity crab, CrabRenderState state, float $$2) {
        super.extractRenderState(crab, state, $$2);
        state.fromEntity(crab);
    }

    @Override
    public ResourceLocation getTextureLocation(CrabRenderState entity) {
        byte variant = entity.variant;
        if (variant == 2) {
            return TEXTURE_BLUE;
        } else if (variant == 1) {
            return TEXTURE_WHITE;
        }
        return TEXTURE_RED;
    }

    @Override
    protected void scale(CrabRenderState state, PoseStack poseStack) {
        if (!state.isBaby) { // only apply variant based scaling for baby
            float scale = 1.0F + (state.scale * 0.1F);
            poseStack.scale(scale, scale, scale);
        }
        poseStack.translate(0.0D, 0.01D, 0.0D);
        super.scale(state, poseStack);
    }

}
