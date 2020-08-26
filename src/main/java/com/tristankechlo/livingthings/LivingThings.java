package com.tristankechlo.livingthings;

import org.apache.logging.log4j.LogManager;

import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.Logger;

import com.tristankechlo.livingthings.client.renderer.RenderHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod(LivingThings.MOD_ID)
public class LivingThings {
	
    public static LivingThings INSTANCE;
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "livingthings";
    
    public LivingThings() {
    	INSTANCE = this;
    	
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		
        modEventBus.addListener(this::ClientSetup);
        modEventBus.addListener(this::CommonSetup);
                
        MinecraftForge.EVENT_BUS.register(this);
    }
    
    public void ClientSetup(final FMLClientSetupEvent event) {
    	RenderHandler.registerEntityRenders();
    }
    
    public void CommonSetup(final FMLCommonSetupEvent event) {
		//ModEntityTypes.registerEntitySpawns();
    }
            
}