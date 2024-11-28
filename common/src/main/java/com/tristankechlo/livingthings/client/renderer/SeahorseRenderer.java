package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SeahorseModel;
import com.tristankechlo.livingthings.client.renderer.state.SeahorseRenderState;
import com.tristankechlo.livingthings.entity.SeahorseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SeahorseRenderer extends MobRenderer<SeahorseEntity, SeahorseRenderState, SeahorseModel<SeahorseRenderState>> {

    private static final ResourceLocation GREEN = LivingThings.getEntityTexture("seahorse/seahorse_green.png");
    private static final ResourceLocation BLUE = LivingThings.getEntityTexture("seahorse/seahorse_blue.png");
    private static final ResourceLocation PURPLE = LivingThings.getEntityTexture("seahorse/seahorse_purple.png");
    private static final ResourceLocation RED = LivingThings.getEntityTexture("seahorse/seahorse_red.png");
    private static final ResourceLocation YELLOW = LivingThings.getEntityTexture("seahorse/seahorse_yellow.png");

    public SeahorseRenderer(Context context) {
        super(context, new SeahorseModel<>(context.bakeLayer(ModelLayer.SEAHORSE)), 0.2F);
    }

    @Override
    public SeahorseRenderState createRenderState() {
        return new SeahorseRenderState();
    }

    @Override
    public void extractRenderState(SeahorseEntity entity, SeahorseRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(SeahorseRenderState state) {
        final byte variant = state.variant;
        if (variant == 1) {
            return GREEN;
        } else if (variant == 2) {
            return PURPLE;
        } else if (variant == 3) {
            return YELLOW;
        } else if (variant == 4) {
            return RED;
        } else {
            return BLUE;
        }
    }

}
