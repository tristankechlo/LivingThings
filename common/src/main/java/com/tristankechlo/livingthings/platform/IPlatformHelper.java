package com.tristankechlo.livingthings.platform;

import com.tristankechlo.livingthings.entity.SeahorseEntity;
import com.tristankechlo.livingthings.init.ModSounds;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.MobBucketItem;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraft.world.level.material.Fluid;

import java.nio.file.Path;
import java.util.function.Supplier;

public interface IPlatformHelper {

    IPlatformHelper INSTANCE = Services.load(IPlatformHelper.class);

    boolean isModLoaded(String modId);

    Path getConfigDirectory();

    void openBookEntry(ResourceLocation bookId, ResourceLocation entryId, int page);

    void openBookGui(ServerPlayer player, ResourceLocation bookId);

    MobBucketItem createMobBucketItem(RegistryObject<EntityType<SeahorseEntity>> type, Fluid fluid, SoundEvent sound, Item.Properties props);

    SpawnEggItem createSpawnEgg(Supplier<EntityType<? extends Mob>> type, int primaryColor, int secondaryColor, Item.Properties props);

    CreativeModeTab.Builder getCreativeModeTab();

}
