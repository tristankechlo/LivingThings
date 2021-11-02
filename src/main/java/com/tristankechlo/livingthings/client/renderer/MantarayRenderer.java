package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.MantarayModel;
import com.tristankechlo.livingthings.entities.MantarayEntity;

import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MantarayRenderer extends MobRenderer<MantarayEntity, MantarayModel<MantarayEntity>> {

	protected static final ResourceLocation TEXTURE_1 = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/mantaray/mantaray.png");
	protected static final ResourceLocation TEXTURE_2 = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/mantaray/mantaray_2.png");

	public MantarayRenderer(Context context) {
		super(context, new MantarayModel<>(context.bakeLayer(ModelLayer.MANTARAY)), 0.35F);
	}

	@Override
	public ResourceLocation getTextureLocation(MantarayEntity entity) {
		if (entity.getVariant() == 1) {
			return TEXTURE_2;
		}
		return TEXTURE_1;
	}

	@Override
	protected void scale(MantarayEntity mantaray, PoseStack matrixStackIn, float partialTickTime) {
		float scale = 1.0F + (mantaray.getScaling() * 0.1F);
		matrixStackIn.scale(scale, scale, scale);
	}

}
