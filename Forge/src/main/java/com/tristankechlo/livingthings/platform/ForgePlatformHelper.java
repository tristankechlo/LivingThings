package com.tristankechlo.livingthings.platform;

import com.tristankechlo.livingthings.init.ForgeItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

public final class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public TagKey<Item> getBananaTag() {
        return TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation("forge", "fruits/banana"));
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
    public CreativeModeTab getCreativeModeTab() {
        return ForgeItemGroup.GENERAL;
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

}
