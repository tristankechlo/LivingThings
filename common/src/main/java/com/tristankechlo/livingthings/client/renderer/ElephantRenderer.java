package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.ElephantModel;
import com.tristankechlo.livingthings.client.renderer.state.ElephantRenderState;
import com.tristankechlo.livingthings.entity.ElephantEntity;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class ElephantRenderer extends AgeableMobRenderer<ElephantEntity, ElephantRenderState, ElephantModel<ElephantRenderState>> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("elephant/elephant.png");
    public static final MeshTransformer BABY_TRANSFORMER = MeshTransformer.scaling(0.6F);

    public ElephantRenderer(EntityRendererProvider.Context context) {
        super(context, ElephantModel::new, ModelLayer.ELEPHANT, ModelLayer.ELEPHANT_BABY, 1.2F);
    }

    @Override
    public ElephantRenderState createRenderState() {
        return new ElephantRenderState();
    }

    @Override
    public void extractRenderState(ElephantEntity entity, ElephantRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.partialTicks = $$2; // TODO check if correct
    }

    @Override
    public ResourceLocation getTextureLocation(ElephantRenderState entity) {
        return TEXTURE;
    }

    @Override
    protected void scale(ElephantRenderState state, PoseStack poseStack) {
        super.scale(state, poseStack);
        if (state.isBaby) {
            poseStack.scale(0.6F, 0.6F, 0.6F);
            poseStack.translate(0, 1, 0);
        }
    }

}
