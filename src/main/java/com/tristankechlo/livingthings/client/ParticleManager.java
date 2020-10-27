package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleManager {

    @SubscribeEvent
    public static void onParticleRegister(ParticleFactoryRegisterEvent event) {
    	//Minecraft.getInstance().particles.registerFactory(ModParticle.ARROW_UP_GREEN.get(), ArrowUpParticle.Green::new);
    	//Minecraft.getInstance().particles.registerFactory(ModParticle.ARROW_UP_RED.get(), ArrowUpParticle.Red::new);
    	//Minecraft.getInstance().particles.registerFactory(ModParticle.ARROW_UP_BLUE.get(), ArrowUpParticle.Blue::new);
    }
}
