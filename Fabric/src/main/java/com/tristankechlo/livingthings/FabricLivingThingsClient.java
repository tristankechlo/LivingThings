package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.ModelLayer;
import com.tristankechlo.livingthings.client.model.entity.PenguinModel;
import com.tristankechlo.livingthings.client.renderer.PenguinRenderer;
import com.tristankechlo.livingthings.init.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public final class FabricLivingThingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        registerRenderers();
        registerLayerDefinitions();
    }

    private void registerRenderers() {
        EntityRendererRegistry.register(ModEntities.PENGUIN.get(), PenguinRenderer::new);
    }

    private void registerLayerDefinitions() {
        EntityModelLayerRegistry.registerModelLayer(ModelLayer.PENGUIN, PenguinModel::createBodyLayer);
    }

}
