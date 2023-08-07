package com.tristankechlo.livingthings.platform;

import com.tristankechlo.livingthings.entity.SeahorseEntity;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluid;

import java.nio.file.Path;
import java.util.function.Supplier;

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

    Component getPatchouliSubtitle(ResourceLocation bookId);

    void openBookEntry(ResourceLocation bookId, ResourceLocation entryId, int page);

    void openBookGui(ServerPlayer player, ResourceLocation bookId);

    MobBucketItem createMobBucketItem(RegistryObject<EntityType<SeahorseEntity>> type, Fluid fluid, SoundEvent sound, Item.Properties props);

    SpawnEggItem createSpawnEgg(Supplier<EntityType<?>> type, int primaryColor, int secondaryColor, Item.Properties props);

    CreativeModeTab.Builder getCreativeModeTab();

}
