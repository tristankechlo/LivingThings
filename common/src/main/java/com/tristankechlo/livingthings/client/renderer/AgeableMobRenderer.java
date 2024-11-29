package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.Mob;

import java.util.function.Function;

public abstract class AgeableMobRenderer<T extends Mob, S extends LivingEntityRenderState, M extends EntityModel<? super S>> extends MobRenderer<T, S, M> {

    public final M adultModel;
    public final M babyModel;

    public AgeableMobRenderer(EntityRendererProvider.Context context, M adult, M baby, float shadowRadius) {
        super(context, adult, shadowRadius);
        this.adultModel = adult;
        this.babyModel = baby;
    }

    public AgeableMobRenderer(EntityRendererProvider.Context context, Function<ModelPart, M> model, ModelLayerLocation adult, ModelLayerLocation baby, float shadowRadius) {
        this(context, model.apply(context.bakeLayer(adult)), model.apply(context.bakeLayer(baby)), shadowRadius);
    }

    public void render(S state, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
        this.model = state.isBaby ? this.babyModel : this.adultModel;
        super.render(state, poseStack, bufferSource, packedLight);
    }

}
