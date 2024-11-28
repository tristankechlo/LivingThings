package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.OwlModel;
import com.tristankechlo.livingthings.client.renderer.state.OwlRenderState;
import com.tristankechlo.livingthings.entity.OwlEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.resources.ResourceLocation;

public class OwlRenderer extends AgeableMobRenderer<OwlEntity, OwlRenderState, OwlModel<OwlRenderState>> {

    protected static final ResourceLocation TEXTURE_BROWN = LivingThings.getEntityTexture("owl/owl_brown.png");
    protected static final ResourceLocation TEXTURE_WHITE = LivingThings.getEntityTexture("owl/owl_white.png");
    protected static final ResourceLocation TEXTURE_BLACK = LivingThings.getEntityTexture("owl/owl_black.png");
    public static final MeshTransformer NORMAL_TRANSFORMER = MeshTransformer.scaling(0.94F);
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.5F);

    public OwlRenderer(Context context) {
        super(context, OwlModel::new, ModelLayer.OWL, ModelLayer.OWL_BABY, 0.29F);
    }

    @Override
    public OwlRenderState createRenderState() {
        return new OwlRenderState();
    }

    @Override
    public void extractRenderState(OwlEntity entity, OwlRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
        state.flapAngle(entity, $$2);
    }

    @Override
    public ResourceLocation getTextureLocation(OwlRenderState state) {
        byte variant = state.variant;
        if (variant == 1) {
            return TEXTURE_WHITE;
        } else if (variant == 2) {
            return TEXTURE_BLACK;
        }
        return TEXTURE_BROWN;
    }

    @Override
    protected void setupRotations(OwlRenderState state, PoseStack poseStack, float f1, float f2) {
        super.setupRotations(state, poseStack, f1, f2);
        this.model.setLivingAnimations(state);
    }

}
