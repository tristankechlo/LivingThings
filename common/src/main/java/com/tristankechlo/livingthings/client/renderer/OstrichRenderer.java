package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.OstrichModel;
import com.tristankechlo.livingthings.client.renderer.state.OstrichRenderState;
import com.tristankechlo.livingthings.entity.OstrichEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class OstrichRenderer extends AgeableMobRenderer<OstrichEntity, OstrichRenderState, OstrichModel<OstrichRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("ostrich/ostrich.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.6F);

    public OstrichRenderer(EntityRendererProvider.Context context) {
        super(context, OstrichModel::new, ModelLayer.OSTRICH, ModelLayer.OSTRICH_BABY, 0.45F);
    }

    @Override
    public OstrichRenderState createRenderState() {
        return new OstrichRenderState();
    }

    @Override
    public void extractRenderState(OstrichEntity entity, OstrichRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(OstrichRenderState entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(OstrichRenderState state, PoseStack poseStack) {
        if (state.isLayingEgg) {
            poseStack.translate(0, 0.65, 0);
        }
        super.scale(state, poseStack);
    }

}
