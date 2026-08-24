package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.commands.LivingThingsCommand;
import com.tristankechlo.livingthings.config.ConfigManager;
import com.tristankechlo.livingthings.config.GeneralConfig;
import com.tristankechlo.livingthings.events.BlockEvents;
import com.tristankechlo.livingthings.util.StructureAddon;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BuiltinStructures;
import net.minecraft.world.level.levelgen.structure.Structure;

public final class FabricLivingThings implements ModInitializer {

    @Override
    public void onInitialize() {
        LivingThings.init(); // register all items, blocks, ...
        ConfigManager.loadAndVerifyConfig();
        LivingThings.registerMobAttributes(FabricDefaultAttributeRegistry::register);
        LivingThings.registerSpawnPlacements();
        LivingThings.registerDispenserBehavior();

        // register commands
        CommandRegistrationCallback.EVENT.register((dispatcher, dedicated, environment) -> {
            LivingThingsCommand.register(dispatcher);
        });

        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            BlockEvents.onBlockBreak(world, player, pos, state);
        });

        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            Item item = player.getMainHandItem().getItem();
            if (item instanceof BlockItem) {
                BlockState placedBlock = ((BlockItem) item).getBlock().defaultBlockState();
                BlockPos pos = hitResult.getBlockPos().relative(hitResult.getDirection());
                return BlockEvents.onBlockPlace(world, player, pos, placedBlock);
            }
            return InteractionResult.PASS;
        });

        // register biomemodifiers that mobs can spawn
        GeneralConfig.getSpawnData().forEach((biomeLocation, spawnDataList) -> {
            spawnDataList.forEach((spawnerData) -> {
                EntityType<?> entityType = spawnerData.type;
                MobCategory category = entityType.getCategory();
                BiomeModifications.addSpawn((selectionContext) -> {
                    return selectionContext.getBiomeKey().location().equals(biomeLocation);
                }, category, entityType, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount);
            });
        });

        ServerLifecycleEvents.SERVER_STARTING.register(server -> {
            Registry<Structure> reg = server.registryAccess().lookupOrThrow(Registries.STRUCTURE);
            Structure s = reg.getValueOrThrow(BuiltinStructures.FORTRESS);
            ((StructureAddon) s).livingthings$setupSpawnOverrides();
        });
    }

}
