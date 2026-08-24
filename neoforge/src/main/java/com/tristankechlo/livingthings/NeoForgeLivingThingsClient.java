package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@OnlyIn(Dist.CLIENT)
@EventBusSubscriber(modid = LivingThings.MOD_ID, value = Dist.CLIENT)
public final class NeoForgeLivingThingsClient {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        LivingThingsClient.registerRenderers(event::registerEntityRenderer);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LivingThingsClient.registerLayerDefinition(event::registerLayerDefinition);
    }

}
