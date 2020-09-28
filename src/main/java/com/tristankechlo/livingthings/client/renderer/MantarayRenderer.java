package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.MantarayModel;
import com.tristankechlo.livingthings.entities.MantarayEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class MantarayRenderer extends MobRenderer<MantarayEntity, MantarayModel<MantarayEntity>>{
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/mantaray/mantaray.png");
	protected static final ResourceLocation TEXTURE2 = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/mantaray/mantaray2.png");

	public MantarayRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new MantarayModel<>(), 0.35F);
	}

	@Override
	public ResourceLocation getEntityTexture(MantarayEntity entity) {
		if(entity.getVariant() == 1) {
			return TEXTURE2;
		}
		return TEXTURE;
	}
	
	@Override
	protected void preRenderCallback(MantarayEntity mantaray, MatrixStack matrixStackIn, float partialTickTime) {
		float scale = 1.0F + (mantaray.getScaling() * 0.1F);
		matrixStackIn.scale(scale, scale, scale);
	}

}
