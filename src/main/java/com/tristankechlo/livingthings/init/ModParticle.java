package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.particles.BasicParticleType;
import net.minecraft.particles.ParticleType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModParticle {

	public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, LivingThings.MOD_ID);
	
	public static final RegistryObject<BasicParticleType> ARROW_UP_GREEN = PARTICLES.register("arrow_up_green",  () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> ARROW_UP_RED = PARTICLES.register("arrow_up_red",  () -> new BasicParticleType(false));
	public static final RegistryObject<BasicParticleType> ARROW_UP_BLUE = PARTICLES.register("arrow_up_blue",  () -> new BasicParticleType(false));
	
}
