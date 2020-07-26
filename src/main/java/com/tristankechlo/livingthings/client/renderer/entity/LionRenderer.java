package com.tristankechlo.livingthings.client.renderer.entity;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.entity.LionModel;
import com.tristankechlo.livingthings.entities.LionEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LionRenderer extends MobRenderer<LionEntity, LionModel<LionEntity>> {
	
	protected static final ResourceLocation MALE = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/lion/lion_entity_male.png");
	protected static final ResourceLocation FEMALE = new ResourceLocation(LivingThings.MOD_ID,	"textures/entity/lion/lion_entity_female.png");

	public LionRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new LionModel<>(), 1F);
	}

	public ResourceLocation getEntityTexture(LionEntity entity) {
		if(entity.isMale()) {
			return MALE;
		}
		return FEMALE;		
	}	
	
}
