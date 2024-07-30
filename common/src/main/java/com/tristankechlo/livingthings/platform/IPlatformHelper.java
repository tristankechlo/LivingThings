package com.tristankechlo.livingthings.platform;

import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;

import java.nio.file.Path;

public interface IPlatformHelper {

    public static final IPlatformHelper INSTANCE = Services.load(IPlatformHelper.class);

    String getPlatformName();

    TagKey<Item> getBananaTag();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    Path getConfigDirectory();

    CreativeModeTab getCreativeModeTab();

    static CreativeModeTab getCreativeTab() {
        return IPlatformHelper.INSTANCE.getCreativeModeTab();
    }

    Component getPatchouliSubtitle(ResourceLocation bookId);

    void openBookEntry(ResourceLocation bookId, ResourceLocation entryId, int page);

    void openBookGui(ServerPlayer player, ResourceLocation bookId);

}
