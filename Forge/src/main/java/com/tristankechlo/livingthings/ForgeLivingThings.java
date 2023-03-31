package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.commands.LivingThingsCommand;
import com.tristankechlo.livingthings.config.util.ConfigManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
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

        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void registerCommands(final RegisterCommandsEvent event) {
        LivingThingsCommand.register(event.getDispatcher());
    }

    public void onAttributeRegister(final EntityAttributeCreationEvent event) {
        LivingThings.registerMobAttributes((entityType, builder) -> event.put(entityType, builder.build()));
    }

    public void onSpawnPlacementsRegister(final SpawnPlacementRegisterEvent event) {
        LivingThings.registerSpawnPlacements();
    }

}
