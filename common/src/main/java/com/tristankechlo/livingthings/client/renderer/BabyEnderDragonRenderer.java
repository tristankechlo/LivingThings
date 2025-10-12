package com.tristankechlo.livingthings.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.BabyEnderDragonModel;
import com.tristankechlo.livingthings.client.model.entity.BabyEnderDragonSittingModel;
import com.tristankechlo.livingthings.client.renderer.layer.BabyEnderDragonCollarLayer;
import com.tristankechlo.livingthings.client.renderer.state.BabyEnderDragonRenderState;
import com.tristankechlo.livingthings.entity.BabyEnderDragonEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.resources.ResourceLocation;

public class BabyEnderDragonRenderer extends MobRenderer<BabyEnderDragonEntity, BabyEnderDragonRenderState, EntityModel<BabyEnderDragonRenderState>> {

    private static final ResourceLocation TEXTURE = LivingThings.getEntityTexture("baby_ender_dragon/baby_ender_dragon.png");
    private final BabyEnderDragonModel modelNormal;
    private final BabyEnderDragonSittingModel modelSitting;

    public BabyEnderDragonRenderer(Context context) {
        super(context, new BabyEnderDragonModel(context.bakeLayer(ModelLayer.BABY_ENDER_DRAGON)), 0.5F);
        this.modelSitting = new BabyEnderDragonSittingModel(context.bakeLayer(ModelLayer.BABY_ENDER_DRAGON_SITTING));
        this.modelNormal = new BabyEnderDragonModel(context.bakeLayer(ModelLayer.BABY_ENDER_DRAGON));
        this.addLayer(new BabyEnderDragonCollarLayer(this));
    }

    @Override
    public BabyEnderDragonRenderState createRenderState() {
        return new BabyEnderDragonRenderState();
    }

    @Override
    public void extractRenderState(BabyEnderDragonEntity entity, BabyEnderDragonRenderState state, float $$2) {
        super.extractRenderState(entity, state, $$2);
        state.fromEntity(entity);
    }

    @Override
    public ResourceLocation getTextureLocation(BabyEnderDragonRenderState entity) {
        return TEXTURE;
    }

    @Override
    public void submit(BabyEnderDragonRenderState state, PoseStack poseStack, SubmitNodeCollector collector, CameraRenderState cameraRenderState) {
        if (state.isTame && state.isSitting && !state.flying) {
            this.model = this.modelSitting;
        } else {
            this.model = this.modelNormal;
        }
        super.submit(state, poseStack, collector, cameraRenderState);
    }

}
