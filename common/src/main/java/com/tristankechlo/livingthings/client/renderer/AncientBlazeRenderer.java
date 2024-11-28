package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.AncientBlazeModel;
import com.tristankechlo.livingthings.client.renderer.layer.AncientBlazeChargedLayer;
import com.tristankechlo.livingthings.client.renderer.state.AncientBlazeRenderState;
import com.tristankechlo.livingthings.entity.AncientBlazeEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;

public class AncientBlazeRenderer extends MobRenderer<AncientBlazeEntity, AncientBlazeRenderState, AncientBlazeModel<AncientBlazeRenderState>> {

    private static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("ancient_blaze/ancient_blaze.png");

    public AncientBlazeRenderer(Context context) {
        super(context, new AncientBlazeModel<>(context.bakeLayer(ModelLayer.ANCIENT_BLAZE)), 0.5F);
        this.addLayer(new AncientBlazeChargedLayer(this, context.getModelSet()));
    }

    @Override
    public AncientBlazeRenderState createRenderState() {
        return new AncientBlazeRenderState();
    }

    @Override
    protected int getBlockLightLevel(AncientBlazeEntity entityIn, BlockPos partialTicks) {
        return 15;
    }

    @Override
    public ResourceLocation getTextureLocation(AncientBlazeRenderState state) {
        return TEXTURE;
    }

    @Override
    public void extractRenderState(AncientBlazeEntity blaze, AncientBlazeRenderState state, float f) {
        super.extractRenderState(blaze, state, f);
        state.fromEntity(blaze);
    }
}
