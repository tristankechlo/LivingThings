package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.client.LivingThingsClient;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.listener.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, value = Dist.CLIENT)
public final class ForgeLivingThingsClient {

    @SubscribeEvent
    public static void onRegisterRenderers(EntityRenderersEvent.RegisterRenderers event) {
        LivingThingsClient.registerRenderers(event::registerEntityRenderer);
    }

    @SubscribeEvent
    public static void onRegisterLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        LivingThingsClient.registerLayerDefinition(event::registerLayerDefinition);
    }

}
