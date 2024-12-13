package com.tristankechlo.livingthings.platform;

import com.google.auto.service.AutoService;
import com.tristankechlo.livingthings.entity.SeahorseEntity;
import com.tristankechlo.livingthings.init.ModItems;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLPaths;

import java.nio.file.Path;

@AutoService(IPlatformHelper.class)
public final class ForgePlatformHelper implements IPlatformHelper {

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
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
