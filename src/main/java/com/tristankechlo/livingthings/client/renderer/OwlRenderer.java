package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.OwlModel;
import com.tristankechlo.livingthings.entities.OwlEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OwlRenderer extends MobRenderer<OwlEntity, OwlModel<OwlEntity>> {

	protected static final ResourceLocation TEXTURE_BROWN = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/owl/owl_brown.png");
	protected static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/owl/owl_white.png");
	protected static final ResourceLocation TEXTURE_BLACK = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/owl/owl_black.png");

	public OwlRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new OwlModel<>(), 0.29F);
	}

	@Override
	public ResourceLocation getEntityTexture(OwlEntity entity) {
		byte variant = entity.getVariant();
		if (variant == 1) {
			return TEXTURE_WHITE;
		} else if (variant == 2) {
			return TEXTURE_BLACK;
		}
		return TEXTURE_BROWN;
	}

	@Override
	protected void preRenderCallback(OwlEntity entitylivingbaseIn, MatrixStack matrixStackIn, float partialTickTime) {
		// scale the owl down a bit, to be smaller than one block
		matrixStackIn.scale(0.94F, 0.94F, 0.94F);
	}

	@Override
	protected float handleRotationFloat(OwlEntity livingBase, float partialTicks) {
		float f = MathHelper.lerp(partialTicks, livingBase.oFlap, livingBase.flap);
		float f1 = MathHelper.lerp(partialTicks, livingBase.oFlapSpeed, livingBase.flapSpeed);
		return (MathHelper.sin(f) + 1.0F) * f1;
	}

}
