package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.OwlModel;
import com.tristankechlo.livingthings.entities.OwlEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class OwlRenderer extends MobRenderer<OwlEntity, OwlModel<OwlEntity>>{
	
	protected static final ResourceLocation TEXTURE1 = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/owl/owl.png");
	protected static final ResourceLocation TEXTURE2 = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/owl/owl_white.png");
	protected static final ResourceLocation TEXTURE3 = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/owl/owl_brown.png");

	public OwlRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new OwlModel<>(), 0.35F);
	}

	@Override
	public ResourceLocation getEntityTexture(OwlEntity entity) {
		return TEXTURE1;
	}

}
