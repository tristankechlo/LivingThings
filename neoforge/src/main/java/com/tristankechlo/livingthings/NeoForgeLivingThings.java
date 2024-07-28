package com.tristankechlo.livingthings;

import com.mojang.serialization.MapCodec;
import com.tristankechlo.livingthings.util.LivingThingsBiomeModifier;
import com.mojang.serialization.Codec;
import com.tristankechlo.livingthings.commands.LivingThingsCommand;
import com.tristankechlo.livingthings.config.ConfigManager;
import com.tristankechlo.livingthings.events.BlockEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.world.BiomeModifier;
import net.neoforged.neoforge.event.RegisterCommandsEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.event.entity.SpawnPlacementRegisterEvent;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

@Mod(LivingThings.MOD_ID)
public final class NeoForgeLivingThings {

    private static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER = DeferredRegister.create(NeoForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, LivingThings.MOD_ID);
    public static final Supplier<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_CODEC = BIOME_MODIFIER.register("add_entity_spawns", () -> LivingThingsBiomeModifier.CODEC);

    public NeoForgeLivingThings(IEventBus modbus) {
        LivingThings.init();
        BIOME_MODIFIER.register(modbus); // needs to be registered before config is loaded

        modbus.addListener(this::commonSetup);
        modbus.addListener(this::onAttributeRegister);
        modbus.addListener(this::onSpawnPlacementsRegister);

        NeoForge.EVENT_BUS.addListener(this::registerCommands);
        NeoForge.EVENT_BUS.addListener(this::onBlockBreak);
        NeoForge.EVENT_BUS.addListener(this::onBlockPlace);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(LivingThings::registerDispenserBehavior);
    }

    private void registerCommands(final RegisterCommandsEvent event) {
        LivingThingsCommand.register(event.getDispatcher());
    }

    private void onAttributeRegister(final EntityAttributeCreationEvent event) {
        ConfigManager.loadAndVerifyConfig();
        LivingThings.registerMobAttributes((entityType, builder) -> event.put(entityType, builder.build()));
    }

    private void onSpawnPlacementsRegister(final SpawnPlacementRegisterEvent event) {
        LivingThings.registerSpawnPlacements();
    }

    private void onBlockBreak(final BlockEvent.BreakEvent event) {
        BlockEvents.onBlockBreak(event.getLevel(), event.getPlayer(), event.getPos(), event.getState());
    }

    private void onBlockPlace(final BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            BlockEvents.onBlockPlace(event.getLevel(), (Player) entity, event.getPos(), event.getPlacedBlock());
        }
    }

}
