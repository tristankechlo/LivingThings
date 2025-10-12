package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.renderer.AgeableMobRenderer;
import com.tristankechlo.livingthings.client.renderer.state.SnailRenderState;
import com.tristankechlo.livingthings.entity.SnailEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class SnailShellPatternLayer extends RenderLayer<SnailRenderState, EntityModel<SnailRenderState>> {

    private final SnailEntity.PatternType patternType;
    private final AgeableMobRenderer<SnailEntity, SnailRenderState, EntityModel<SnailRenderState>> parent;

    public SnailShellPatternLayer(AgeableMobRenderer<SnailEntity, SnailRenderState, EntityModel<SnailRenderState>> parent, EntityModelSet entityModelSet, SnailEntity.PatternType type) {
        super(parent);
        this.patternType = type;
        this.parent = parent;
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector collector, int packedLight, SnailRenderState state, float yRot, float xRot) {
        int colors = state.getShellColor(this.patternType);
        ResourceLocation texture = state.getShellPatternTexture(this.patternType);
        EntityModel<SnailRenderState> model = state.isBaby ? this.parent.babyModel : this.parent.adultModel;

        coloredCutoutModelCopyLayerRender(model, texture, poseStack, collector, packedLight, state, colors, 1);
    }

}
