package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.model.AncientBlazeModel;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientBlazeRenderer extends MobRenderer<AncientBlazeEntity, AncientBlazeModel<AncientBlazeEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID, "textures/entity/ancient_blaze.png");

	public AncientBlazeRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new AncientBlazeModel<>(), 0.5F);
	}
	
	@Override
	protected int getBlockLight(AncientBlazeEntity entityIn, BlockPos partialTicks) {
		return 15;
	}

	@Override
	public ResourceLocation getEntityTexture(AncientBlazeEntity entity) {
		return TEXTURE;
	}

}
