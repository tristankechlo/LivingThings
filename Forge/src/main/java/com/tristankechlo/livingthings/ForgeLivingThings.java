package com.tristankechlo.livingthings;

import com.mojang.serialization.Codec;
import com.tristankechlo.livingthings.commands.LivingThingsCommand;
import com.tristankechlo.livingthings.config.ConfigManager;
import com.tristankechlo.livingthings.events.BlockEvents;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.util.LivingThingsBiomeModifier;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod(LivingThings.MOD_ID)
public final class ForgeLivingThings {

    private static final DeferredRegister<Codec<? extends BiomeModifier>> BIOME_MODIFIER = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, LivingThings.MOD_ID);
    public static final RegistryObject<Codec<LivingThingsBiomeModifier>> BIOME_MODIFIER_CODEC = BIOME_MODIFIER.register("add_entity_spawns", () -> LivingThingsBiomeModifier.CODEC);

    public ForgeLivingThings() {
        LivingThings.init();
        BIOME_MODIFIER.register(FMLJavaModLoadingContext.get().getModEventBus()); // needs to be registered before config is loaded
        ConfigManager.loadAndVerifyConfig();

        IEventBus modbus = FMLJavaModLoadingContext.get().getModEventBus();
        modbus.addListener(this::commonSetup);
        modbus.addListener(this::onAttributeRegister);
        modbus.addListener(this::onSpawnPlacementsRegister);
        modbus.addListener(this::registerCreativeTabs);

        MinecraftForge.EVENT_BUS.addListener(this::registerCommands);
        MinecraftForge.EVENT_BUS.addListener(this::onBlockBreak);
        MinecraftForge.EVENT_BUS.addListener(this::onBlockPlace);
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(LivingThings::registerDispenserBehavior);
    }

    private void registerCommands(final RegisterCommandsEvent event) {
        LivingThingsCommand.register(event.getDispatcher());
    }

    private void onAttributeRegister(final EntityAttributeCreationEvent event) {
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

    private void registerCreativeTabs(CreativeModeTabEvent.Register event) {
        event.registerCreativeModeTab(new ResourceLocation(LivingThings.MOD_ID, "general"), (builder) -> {
            builder.title(Component.translatable("itemGroup.livingthings.general"));
            builder.icon(() -> ModItems.SHARK_TOOTH.get().getDefaultInstance());
            builder.displayItems((parameters, output) -> {
                ModItems.ALL_ITEMS.forEach(item -> output.accept(item.get().getDefaultInstance()));
                ModItems.SPAWN_EGGS.forEach(spawnEgg -> output.accept(spawnEgg.get().getDefaultInstance()));
            });
        });
    }

}
