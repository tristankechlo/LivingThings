package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SnailModel;
import com.tristankechlo.livingthings.entity.SnailEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;

public class SnailShellPatternLayer extends RenderLayer<SnailEntity, EntityModel<SnailEntity>> {

    private final SnailModel<SnailEntity> model;
    private final SnailEntity.PatternType patternType;

    public SnailShellPatternLayer(RenderLayerParent<SnailEntity, EntityModel<SnailEntity>> entityRendererIn, EntityModelSet entityModelSet, SnailEntity.PatternType type) {
        super(entityRendererIn);
        this.patternType = type;
        float scale = (type == SnailEntity.PatternType.FOREGROUND) ? 0.016F : 0.008F;
        this.model = new SnailModel<>(entityModelSet.bakeLayer(ModelLayer.SNAIL), scale);
    }

    @Override
    public void render(PoseStack matrixStack, MultiBufferSource buffer, int packedLight, SnailEntity snail,
                       float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
                       float headPitch) {

        int colors = snail.getShellColor(this.patternType);
        ResourceLocation texture = snail.getShellPatternTexture(this.patternType);

        coloredCutoutModelCopyLayerRender(this.getParentModel(), this.model, texture, matrixStack, buffer, packedLight,
                snail, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, colors);
    }

}
