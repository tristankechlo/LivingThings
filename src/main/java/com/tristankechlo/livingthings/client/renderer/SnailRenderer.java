package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tristankechlo.livingthings.client.model.entity.SnailModel;
import com.tristankechlo.livingthings.client.renderer.layer.SnailShellPatternLayer;
import com.tristankechlo.livingthings.entities.SnailEntity;
import com.tristankechlo.livingthings.entities.SnailEntity.PatternType;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class SnailRenderer extends MobRenderer<SnailEntity, EntityModel<SnailEntity>> {

	public SnailRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new SnailModel<>(0.0F), 0.35F);
		this.addLayer(new SnailShellPatternLayer(this, PatternType.BACKGROUND));
		this.addLayer(new SnailShellPatternLayer(this, PatternType.FOREGROUND));
	}

	@Override
	public ResourceLocation getTextureLocation(SnailEntity snail) {
		return snail.getBodyTexture();
	}

	@Override
	protected void scale(SnailEntity snail, MatrixStack matrixStackIn, float partialTickTime) {
		if (snail.isBaby()) {
			matrixStackIn.scale(0.5F, 0.5F, 0.5F);
		}
		super.scale(snail, matrixStackIn, partialTickTime);
	}

}
