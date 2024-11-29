package com.tristankechlo.livingthings.client.renderer;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.PenguinModel;
import com.tristankechlo.livingthings.entity.PenguinEntity;
import net.minecraft.client.model.BabyModelTransform;
import net.minecraft.client.model.geom.builders.MeshTransformer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.resources.ResourceLocation;

import java.util.Set;

public class PenguinRenderer extends AgeableMobRenderer<PenguinEntity, LivingEntityRenderState, PenguinModel> {

    protected static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("penguin/penguin.png");
    protected static final ResourceLocation TEXTURE_CHILD = LivingThings.getEntityTexture("penguin/penguin_baby.png");
    public static final MeshTransformer BABY_TRANSFORMER = new BabyModelTransform(true, 9f, 0f, 1.9f, 1.5f, 12f, Set.of("Head"));

    public PenguinRenderer(EntityRendererProvider.Context context) {
        super(context, PenguinModel::new, ModelLayer.PENGUIN, ModelLayer.PENGUIN_BABY, 0.45F);
    }

    @Override
    public LivingEntityRenderState createRenderState() {
        return new LivingEntityRenderState();
    }

    @Override
    public void extractRenderState(PenguinEntity entity, LivingEntityRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
    }

    @Override
    public ResourceLocation getTextureLocation(LivingEntityRenderState state) {
        return state.isBaby ? TEXTURE_CHILD : TEXTURE;
    }

}
