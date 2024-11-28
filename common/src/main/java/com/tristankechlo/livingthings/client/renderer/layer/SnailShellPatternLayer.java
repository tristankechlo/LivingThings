package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SnailModel;
import com.tristankechlo.livingthings.client.renderer.state.SnailRenderState;
import com.tristankechlo.livingthings.entity.SnailEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class SnailShellPatternLayer extends RenderLayer<SnailRenderState, EntityModel<SnailRenderState>> {

    private final SnailModel<SnailRenderState> model;
    private final SnailEntity.PatternType patternType;

    public SnailShellPatternLayer(RenderLayerParent<SnailRenderState, EntityModel<SnailRenderState>> entityRendererIn, EntityModelSet entityModelSet, SnailEntity.PatternType type) {
        super(entityRendererIn);
        this.patternType = type;
        this.model = new SnailModel<>(entityModelSet.bakeLayer(ModelLayer.SNAIL));
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, SnailRenderState snail, float f1, float f2) {

        int colors = snail.getShellColor(this.patternType);
        ResourceLocation texture = snail.getShellPatternTexture(this.patternType);

        coloredCutoutModelCopyLayerRender(this.model, texture, matrixStack, buffer, packedLight, snail, colors);
    }

}
