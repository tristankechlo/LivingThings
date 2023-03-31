package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.config.util.ConfigManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(LivingThings.MOD_ID)
public final class ForgeLivingThings {

    public ForgeLivingThings() {
        LivingThings.init();
        ConfigManager.loadAndVerifyConfig();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(this::onAttributeRegister);
        modbus.addListener(this::onSpawnPlacementsRegister);

        MinecraftForge.EVENT_BUS.register(this);
    }

    public void onAttributeRegister(final EntityAttributeCreationEvent event) {
        LivingThings.registerMobAttributes((entityType, builder) -> event.put(entityType, builder.build()));
    }

    public void onSpawnPlacementsRegister(final SpawnPlacementRegisterEvent event) {
        LivingThings.registerSpawnPlacements();
    }

}
