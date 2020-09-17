package com.tristankechlo.livingthings.client;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.client.particle.ArrowUpParticle;
import com.tristankechlo.livingthings.init.ModParticle;

import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ParticleManager {

    @SuppressWarnings("resource")
	@SubscribeEvent
    public static void onParticleRegister(ParticleFactoryRegisterEvent event) {
    	Minecraft.getInstance().particles.registerFactory(ModParticle.ARROW_UP_GREEN.get(), ArrowUpParticle.Factory::new);
    }
}
