package com.tristankechlo.livingthings.platform;

import com.tristankechlo.livingthings.entity.SeahorseEntity;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.level.material.Fluid;

import java.nio.file.Path;

public interface IPlatformHelper {

    IPlatformHelper INSTANCE = Services.load(IPlatformHelper.class);

    boolean isModLoaded(String modId);

    Path getConfigDirectory();

    void openBookEntry(Identifier bookId, Identifier entryId, int page);

    void openBookGui(ServerPlayer player, Identifier bookId);

    MobBucketItem createMobBucketItem(RegistryObject<EntityType<SeahorseEntity>> type, Fluid fluid, SoundEvent sound, Item.Properties props);

    CreativeModeTab.Builder getCreativeModeTab();

}
