package com.tristankechlo.livingthings.client.renderer.layer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.AncientBlazeModel;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class AncientBlazeChargedLayer
		extends EnergySwirlLayer<AncientBlazeEntity, AncientBlazeModel<AncientBlazeEntity>> {

	private static final ResourceLocation TEXTURE = new ResourceLocation(LivingThings.MOD_ID,
			"textures/entity/ancient_blaze/ancient_blaze_charge.png");
	private final AncientBlazeModel<AncientBlazeEntity> model;

	public AncientBlazeChargedLayer(
			RenderLayerParent<AncientBlazeEntity, AncientBlazeModel<AncientBlazeEntity>> entityRenderer,
			EntityModelSet entityModelSet) {
		super(entityRenderer);
		this.model = new AncientBlazeModel<>(entityModelSet.bakeLayer(ModelLayer.ANCIENT_BLAZE));
	}

	@Override
	protected float xOffset(float speed) {
		return speed * 0.005F;
	}

	@Override
	protected ResourceLocation getTextureLocation() {
		return TEXTURE;
	}

	@Override
	protected EntityModel<AncientBlazeEntity> model() {
		return this.model;
	}

}
