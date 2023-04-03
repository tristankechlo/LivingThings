package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.SeahorseModel;
import com.tristankechlo.livingthings.entity.SeahorseEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class SeahorseRenderer extends MobRenderer<SeahorseEntity, SeahorseModel<SeahorseEntity>> {

    private static final ResourceLocation GREEN = ModelLayer.getEntityTexture("seahorse/seahorse_green.png");
    private static final ResourceLocation BLUE = ModelLayer.getEntityTexture("seahorse/seahorse_blue.png");
    private static final ResourceLocation PURPLE = ModelLayer.getEntityTexture("seahorse/seahorse_purple.png");
    private static final ResourceLocation RED = ModelLayer.getEntityTexture("seahorse/seahorse_red.png");
    private static final ResourceLocation YELLOW = ModelLayer.getEntityTexture("seahorse/seahorse_yellow.png");

    public SeahorseRenderer(Context context) {
        super(context, new SeahorseModel<>(context.bakeLayer(ModelLayer.SEAHORSE)), 0.2F);
    }

    @Override
    public ResourceLocation getTextureLocation(SeahorseEntity entity) {
        final byte variant = entity.getVariant();
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
