package com.tristankechlo.livingthings.platform;

import com.tristankechlo.livingthings.entity.SeahorseEntity;
import com.tristankechlo.livingthings.init.ModItems;
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
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;
import java.util.function.Supplier;

public final class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public TagKey<Item> getBananaTag() {
        return TagKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath("forge", "fruits/banana"));
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
    public void openBookEntry(ResourceLocation bookId, ResourceLocation entryId, int page) {
        // Patchouli no longer available for Forge
    }

    @Override
    public void openBookGui(ServerPlayer player, ResourceLocation bookId) {
        // Patchouli no longer available for Forge
    }

    @Override
    public MobBucketItem createMobBucketItem(RegistryObject<EntityType<SeahorseEntity>> type, Fluid fluid, SoundEvent sound, Item.Properties props) {
        return new MobBucketItem(type, () -> fluid, () -> sound, props);
    }

    @Override
    public SpawnEggItem createSpawnEgg(Supplier<EntityType<? extends Mob>> type, int primaryColor, int secondaryColor, Item.Properties props) {
        // fix invisible spawn eggs (only a problem in forge)
        primaryColor = (255 << 24) | primaryColor;
        secondaryColor = (255 << 24) | secondaryColor;
        return new ForgeSpawnEggItem(() -> (EntityType<? extends Mob>) type.get(), primaryColor, secondaryColor, props);
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
