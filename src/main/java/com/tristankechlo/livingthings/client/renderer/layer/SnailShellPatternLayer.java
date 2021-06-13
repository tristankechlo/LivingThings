package com.tristankechlo.livingthings.client.renderer.layer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tristankechlo.livingthings.client.model.entity.SnailModel;
import com.tristankechlo.livingthings.entities.SnailEntity;
import com.tristankechlo.livingthings.entities.SnailEntity.PatternType;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnailShellPatternLayer extends LayerRenderer<SnailEntity, EntityModel<SnailEntity>> {

	private final SnailModel<SnailEntity> model;
	private PatternType patternType;

	public SnailShellPatternLayer(IEntityRenderer<SnailEntity, EntityModel<SnailEntity>> entityRendererIn,
			PatternType type) {
		super(entityRendererIn);
		this.patternType = type;
		float scale = (type == PatternType.FOREGROUND) ? 0.016F : 0.008F;
		this.model = new SnailModel<>(scale);
	}

	@Override
	public void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int packedLight, SnailEntity snail,
			float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw,
			float headPitch) {

		EntityModel<SnailEntity> snailModel = this.model;
		float[] colors = snail.getShellColorScheme(this.patternType);
		ResourceLocation texture = snail.getShellPatternTexture(this.patternType);

		coloredCutoutModelCopyLayerRender(this.getParentModel(), snailModel, texture, matrixStack, buffer, packedLight,
				snail, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, colors[0],
				colors[1], colors[2]);
	}

}
