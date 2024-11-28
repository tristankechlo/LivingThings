package com.tristankechlo.livingthings.client.renderer.layer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.AncientBlazeModel;
import com.tristankechlo.livingthings.client.renderer.state.AncientBlazeRenderState;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EnergySwirlLayer;
import net.minecraft.resources.ResourceLocation;

public class AncientBlazeChargedLayer extends EnergySwirlLayer<AncientBlazeRenderState, AncientBlazeModel<AncientBlazeRenderState>> {

    private static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("ancient_blaze/ancient_blaze_charge.png");
    private final AncientBlazeModel<AncientBlazeRenderState> model;

    public AncientBlazeChargedLayer(RenderLayerParent<AncientBlazeRenderState, AncientBlazeModel<AncientBlazeRenderState>> entityRenderer, EntityModelSet entityModelSet) {
        super(entityRenderer);
        this.model = new AncientBlazeModel<>(entityModelSet.bakeLayer(ModelLayer.ANCIENT_BLAZE));
    }

    @Override
    protected boolean isPowered(AncientBlazeRenderState ancientBlazeRenderState) {
        return ancientBlazeRenderState.isPowered;
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
    protected AncientBlazeModel<AncientBlazeRenderState> model() {
        return this.model;
    }

}
