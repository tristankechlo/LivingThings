package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.commands.LivingThingsCommand;
import com.tristankechlo.livingthings.config.ConfigManager;
import com.tristankechlo.livingthings.config.GeneralConfig;
import com.tristankechlo.livingthings.events.BlockEvents;
import com.tristankechlo.livingthings.util.StructureAddon;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.BlockEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import java.util.List;
import java.util.Map;

@Mod(LivingThings.MOD_ID)
public final class ForgeLivingThings {

    private static Map<ResourceLocation, List<MobSpawnSettings.SpawnerData>> spawnData = null;

    public ForgeLivingThings() {
        LivingThings.init();
        ConfigManager.loadAndVerifyConfig();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(this::commonSetup);
        modbus.addListener(this::onAttributeRegister);

        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
        MinecraftForge.EVENT_BUS.addListener(this::onBlockPlace);
        MinecraftForge.EVENT_BUS.addListener(EventPriority.HIGH, this::onBiomeLoad);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(LivingThings::registerDispenserBehavior);
        event.enqueueWork(() -> ((StructureAddon) StructureFeatures.FORTRESS.value()).livingthings$setupSpawnOverrides());
    }

    private void registerCommands(final RegisterCommandsEvent event) {
        LivingThingsCommand.register(event.getDispatcher());
    }

    private void onAttributeRegister(final EntityAttributeCreationEvent event) {
        LivingThings.registerMobAttributes((entityType, builder) -> event.put(entityType, builder.build()));
    }

    private void onBlockBreak(final BlockEvent.BreakEvent event) {
        BlockEvents.onBlockBreak(event.getWorld(), event.getPlayer(), event.getPos(), event.getState());
    }

    private void onBlockPlace(final BlockEvent.EntityPlaceEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            BlockEvents.onBlockPlace(event.getWorld(), (Player) entity, event.getPos(), event.getPlacedBlock());
        }
    }

    private void onBiomeLoad(final BiomeLoadingEvent event) {
        if (spawnData == null) {
            spawnData = GeneralConfig.getSpawnData();
        }
        if (spawnData.containsKey(event.getName())) {
            List<MobSpawnSettings.SpawnerData> spawners = spawnData.get(event.getName());
            for (MobSpawnSettings.SpawnerData spawner : spawners) {
                event.getSpawns().addSpawn(spawner.type.getCategory(), spawner);
            }
        }
    }

    @Mod.EventBusSubscriber(modid = LivingThings.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class EventHelper {

        @SubscribeEvent
        public static void onSpawnPlacementsRegister(final RegistryEvent.Register<EntityType<?>> event) {
            LivingThings.registerSpawnPlacements();
        }

    }

}
