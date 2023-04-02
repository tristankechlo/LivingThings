package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entity.*;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public final class ModEntityTypes {

    public static void init() {}

    private static final RegistrationProvider<EntityType<?>> ENTITY_TYPES = RegistrationProvider.get(Registry.ENTITY_TYPE, LivingThings.MOD_ID);

    public static final RegistryObject<EntityType<ElephantEntity>> ELEPHANT = ENTITY_TYPES.register("elephant", () -> create("elephant", ElephantEntity::new, MobCategory.CREATURE, 1.85F, 2.7F));
    public static final RegistryObject<EntityType<GiraffeEntity>> GIRAFFE = ENTITY_TYPES.register("giraffe", () -> create("giraffe", GiraffeEntity::new, MobCategory.CREATURE, 1.5F, 3.2F));
    public static final RegistryObject<EntityType<LionEntity>> LION = ENTITY_TYPES.register("lion", () -> create("lion", LionEntity::new, MobCategory.CREATURE, 1.25F, 1.5F));
    public static final RegistryObject<EntityType<SharkEntity>> SHARK = ENTITY_TYPES.register("shark", () -> create("shark", SharkEntity::new, MobCategory.WATER_CREATURE, 1.4F, 1.1F));
    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN = ENTITY_TYPES.register("penguin", () -> create("penguin", PenguinEntity::new, MobCategory.CREATURE, 0.8F, 1.45F));
    public static final RegistryObject<EntityType<OstrichEntity>> OSTRICH = ENTITY_TYPES.register("ostrich", () -> create("ostrich", OstrichEntity::new, MobCategory.CREATURE, 0.6F, 1.8F));
    public static final RegistryObject<EntityType<FlamingoEntity>> FLAMINGO = ENTITY_TYPES.register("flamingo", () -> create("flamingo", FlamingoEntity::new, MobCategory.CREATURE, 0.6F, 1.25F));
    public static final RegistryObject<EntityType<CrabEntity>> CRAB = ENTITY_TYPES.register("crab", () -> create("crab", CrabEntity::new, MobCategory.CREATURE, 0.4F, 0.4F));
    public static final RegistryObject<EntityType<MantarayEntity>> MANTARAY = ENTITY_TYPES.register("mantaray", () -> create("mantaray", MantarayEntity::new, MobCategory.WATER_AMBIENT, 0.75F, 0.45F));
    public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON = ENTITY_TYPES.register("raccoon", () -> create("raccoon", RaccoonEntity::new, MobCategory.CREATURE, 0.5F, 0.75F));
    public static final RegistryObject<EntityType<OwlEntity>> OWL = ENTITY_TYPES.register("owl", () -> create("owl", OwlEntity::new, MobCategory.CREATURE, 0.5F, 0.99F));
    public static final RegistryObject<EntityType<AncientBlazeEntity>> ANCIENT_BLAZE = ENTITY_TYPES.register("ancient_blaze", () -> EntityType.Builder.of(AncientBlazeEntity::new, MobCategory.MONSTER).sized(0.7F, 2.99F).fireImmune().build(LivingThings.MOD_ID + ":ancient_blaze"));
    public static final RegistryObject<EntityType<KoalaEntity>> KOALA = ENTITY_TYPES.register("koala", () -> create("koala", KoalaEntity::new, MobCategory.CREATURE, 0.6F, 0.75F));
    public static final RegistryObject<EntityType<SnailEntity>> SNAIL = ENTITY_TYPES.register("snail", () -> create("snail", SnailEntity::new, MobCategory.CREATURE, 0.6F, 0.7F));
    public static final RegistryObject<EntityType<MonkeyEntity>> MONKEY = ENTITY_TYPES.register("monkey", () -> create("monkey", MonkeyEntity::new, MobCategory.CREATURE, 0.6F, 0.7F));
    public static final RegistryObject<EntityType<NetherKnightEntity>> NETHER_KNIGHT = ENTITY_TYPES.register("nether_knight", () -> EntityType.Builder.of(NetherKnightEntity::new, MobCategory.MONSTER).sized(0.7F, 2.3F).fireImmune().build(LivingThings.MOD_ID + ":nether_knight"));
    public static final RegistryObject<EntityType<ShroomieEntity>> SHROOMIE = ENTITY_TYPES.register("shroomie", () -> create("shroomie", ShroomieEntity::new, MobCategory.CREATURE, 0.5F, 0.99F));

    // create standard entity type
    private static <T extends Entity> EntityType<T> create(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        return EntityType.Builder.of(factory, category).sized(width, height).build(LivingThings.MOD_ID + ":" + name);
    }

}
