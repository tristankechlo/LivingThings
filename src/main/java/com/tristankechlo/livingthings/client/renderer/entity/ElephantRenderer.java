package com.tristankechlo.livingthings.client.renderer.entity;

import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.ElephantModel;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ElephantRenderer extends MobRenderer<ElephantEntity, ElephantModel<ElephantEntity>> {
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/elephant/elephant_entity.png");

	public ElephantRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new ElephantModel<>(), 1.2F);
	}

	@Override
	public ResourceLocation getEntityTexture(ElephantEntity entity) {
		return TEXTURE;
	}
}