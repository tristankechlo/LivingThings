package com.tristankechlo.livingthings.platform;

import com.tristankechlo.livingthings.entity.SeahorseEntity;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.platform.IPlatformHelper;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluid;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.FMLPaths;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;

import java.nio.file.Path;
import java.util.function.Supplier;

public final class NeoForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "NeoForge";
    }

    @Override
    public TagKey<Item> getBananaTag() {
        return TagKey.create(Registries.ITEM, new ResourceLocation("c", "fruits/bananas"));
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevelopmentEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public Path getConfigDirectory() {
        return FMLPaths.CONFIGDIR.get();
    }

    @Override
    public Component getPatchouliSubtitle(ResourceLocation bookId) {
        return vazkii.patchouli.api.PatchouliAPI.get().getSubtitle(bookId);
    }

    @Override
    public void openBookEntry(ResourceLocation bookId, ResourceLocation entryId, int page) {
        vazkii.patchouli.api.PatchouliAPI.get().openBookEntry(bookId, entryId, page);
    }

    @Override
    public void openBookGui(ServerPlayer player, ResourceLocation bookId) {
        vazkii.patchouli.api.PatchouliAPI.get().openBookGUI(player, bookId);
    }

    @Override
    public MobBucketItem createMobBucketItem(RegistryObject<EntityType<SeahorseEntity>> type, Fluid fluid, SoundEvent sound, Item.Properties props) {
        return new MobBucketItem(type, () -> fluid, () -> sound, props);
    }

    @Override
    public SpawnEggItem createSpawnEgg(Supplier<EntityType<? extends Mob>> type, int primaryColor, int secondaryColor, Item.Properties props) {
        return new DeferredSpawnEggItem(type, primaryColor, secondaryColor, props);
    }

    @Override
    public CreativeModeTab.Builder getCreativeModeTab() {
        return CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.livingthings.general"))
                .icon(() -> ModItems.SHARK_TOOTH.get().getDefaultInstance())
                .displayItems((parameters, output) -> {
                    ModItems.ALL_ITEMS.forEach(item -> output.accept(item.get().getDefaultInstance()));
                    ModItems.SPAWN_EGGS.forEach(spawnEgg -> output.accept(spawnEgg.get().getDefaultInstance()));
                });
    }

}
