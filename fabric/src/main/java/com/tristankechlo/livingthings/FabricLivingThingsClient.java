package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

@Environment(EnvType.CLIENT)
public final class FabricLivingThingsClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        LivingThingsClient.registerRenderers(EntityRendererRegistry::register);
        LivingThingsClient.registerLayerDefinition((a, b) -> EntityModelLayerRegistry.registerModelLayer(a, b::get));
    }

}
