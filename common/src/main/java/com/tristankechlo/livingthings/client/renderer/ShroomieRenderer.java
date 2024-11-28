package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.ShroomieModel;
import com.tristankechlo.livingthings.client.renderer.state.ShroomieRenderState;
import com.tristankechlo.livingthings.entity.ShroomieEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class ShroomieRenderer extends MobRenderer<ShroomieEntity, ShroomieRenderState, EntityModel<ShroomieRenderState>> {

    private static final ResourceLocation RED = LivingThings.getEntityTexture("shroomie/shroomie_red.png");
    private static final ResourceLocation BROWN = LivingThings.getEntityTexture("shroomie/shroomie_brown.png");

    public ShroomieRenderer(Context context) {
        super(context, new ShroomieModel<>(context.bakeLayer(ModelLayer.SHROOMIE)), 0.4F);
    }

    @Override
    public ShroomieRenderState createRenderState() {
        return new ShroomieRenderState();
    }

    @Override
    public void extractRenderState(ShroomieEntity entity, ShroomieRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(ShroomieRenderState state) {
        if (state.variant == 1) {
            return RED;
        }
        return BROWN;
    }

}