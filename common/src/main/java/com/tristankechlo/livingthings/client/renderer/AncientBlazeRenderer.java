package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.AncientBlazeModel;
import com.tristankechlo.livingthings.client.renderer.layer.AncientBlazeChargedLayer;
import com.tristankechlo.livingthings.entity.AncientBlazeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class AncientBlazeRenderer extends MobRenderer<AncientBlazeEntity, AncientBlazeModel<AncientBlazeEntity>> {

    private static final ResourceLocation TEXTURE = LivingThingsClient.getEntityTexture("ancient_blaze/ancient_blaze.png");

    public AncientBlazeRenderer(Context context) {
        super(context, new AncientBlazeModel<>(context.bakeLayer(ModelLayer.ANCIENT_BLAZE)), 0.5F);
        this.addLayer(new AncientBlazeChargedLayer(this, context.getModelSet()));
    }

    @Override
    protected int getBlockLightLevel(AncientBlazeEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(AncientBlazeEntity entity) {
        return TEXTURE;
    }

}
