package com.tristankechlo.livingthings;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Logger;

import com.tristankechlo.livingthings.init.ModEntities;

import net.minecraftforge.fml.common.Mod;

@Mod(LivingThings.MOD_ID)
public class LivingThings {
	
    public static LivingThings instance;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "livingthings";
    
    public LivingThings() {
		final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		

		ModEntities.ENTITIES.register(modEventBus);
		
		
        LivingThings.instance = this;
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
            
}