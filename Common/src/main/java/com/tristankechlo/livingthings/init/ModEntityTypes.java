package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entity.ElephantEntity;
import com.tristankechlo.livingthings.entity.GiraffeEntity;
import com.tristankechlo.livingthings.entity.LionEntity;
import com.tristankechlo.livingthings.entity.PenguinEntity;
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
    public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN = ENTITY_TYPES.register("penguin", () -> create("penguin", PenguinEntity::new, MobCategory.CREATURE, 0.8F, 1.45F));

    // create standard entity type
    private static <T extends Entity> EntityType<T> create(String name, EntityType.EntityFactory<T> factory, MobCategory category, float width, float height) {
        return EntityType.Builder.of(factory, category).sized(width, height).build(LivingThings.MOD_ID + ":" + name);
    }

}
