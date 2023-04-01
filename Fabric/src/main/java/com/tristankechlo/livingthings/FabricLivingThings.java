package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.commands.LivingThingsCommand;
import com.tristankechlo.livingthings.config.ConfigManager;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;

public final class FabricLivingThings implements ModInitializer {

    @Override
    public void onInitialize() {
        LivingThings.init(); // register all items, blocks, ...
        ConfigManager.loadAndVerifyConfig();
        LivingThings.registerMobAttributes(FabricDefaultAttributeRegistry::register);
        LivingThings.registerSpawnPlacements();

        // register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            LivingThingsCommand.register(dispatcher);
        });
    }

}
