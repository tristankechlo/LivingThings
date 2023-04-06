package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.commands.LivingThingsCommand;
import com.tristankechlo.livingthings.config.ConfigManager;
import com.tristankechlo.livingthings.config.GeneralConfig;
import com.tristankechlo.livingthings.events.BlockEvents;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.state.BlockState;

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

        GeneralConfig.getSpawnData().forEach((biomeLocation, spawnDataList) -> {
            spawnDataList.forEach((spawnerData) -> {
                EntityType<?> entityType = spawnerData.type;
                MobCategory category = entityType.getCategory();
                BiomeModifications.addSpawn((selectionContext) -> {
                    return selectionContext.getBiomeKey().location().equals(biomeLocation);
                }, category, entityType, spawnerData.getWeight().asInt(), spawnerData.minCount, spawnerData.maxCount);
            });
        });
    }

}
