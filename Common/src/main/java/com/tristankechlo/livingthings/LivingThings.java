package com.tristankechlo.livingthings;

import com.tristankechlo.livingthings.entity.PenguinEntity;
import com.tristankechlo.livingthings.init.ModBlocks;
import com.tristankechlo.livingthings.init.ModEntities;
import com.tristankechlo.livingthings.init.ModItems;
import com.tristankechlo.livingthings.init.ModSounds;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.BiConsumer;

public final class LivingThings {

    public static final String MOD_ID = "livingthings";
    public static final String MOD_NAME = "Living Things";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

    public static final String GITHUB_URL = "https://github.com/tristankechlo/LivingThings";
    public static final String GITHUB_ISSUE_URL = GITHUB_URL + "/issues";
    public static final String GITHUB_WIKI_URL = GITHUB_URL + "/wiki";
    public static final String DISCORD_URL = "https://discord.gg/bhUaWhq";
    public static final String CURSEFORGE_URL = "https://curseforge.com/minecraft/mc-mods/living-things";
    public static final String MODRINTH_URL = "https://modrinth.com/mod/living-things";

    public static void init() {
        //make sure all classes are loaded
        ModItems.init();
        ModBlocks.init();
        ModSounds.init();
        ModEntities.init();
    }

    public static void registerMobAttributes(BiConsumer<EntityType<? extends LivingEntity>, AttributeSupplier.Builder> consumer) {
        consumer.accept(ModEntities.PENGUIN.get(), PenguinEntity.createAttributes());
    }

    public static <T extends Mob> void registerSpawnPlacements(SpawnPlacementHelper<T> helper) {
        helper.register((EntityType<T>) ModEntities.PENGUIN.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, PenguinEntity::checkPenguinSpawnRules);
    }

    @FunctionalInterface
    public interface SpawnPlacementHelper<T extends Mob> {
        void register(EntityType<T> entityType, SpawnPlacements.Type type, Heightmap.Types heightmap, SpawnPlacements.SpawnPredicate<T> predicate);
    }

}
