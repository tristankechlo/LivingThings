package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.entities.CrabEntity;
import com.tristankechlo.livingthings.entities.ElephantEntity;
import com.tristankechlo.livingthings.entities.FlamingoEntity;
import com.tristankechlo.livingthings.entities.GiraffeEntity;
import com.tristankechlo.livingthings.entities.KoalaEntity;
import com.tristankechlo.livingthings.entities.LionEntity;
import com.tristankechlo.livingthings.entities.MantarayEntity;
import com.tristankechlo.livingthings.entities.MonkeyEntity;
import com.tristankechlo.livingthings.entities.NetherKnightEntity;
import com.tristankechlo.livingthings.entities.OstrichEntity;
import com.tristankechlo.livingthings.entities.OwlEntity;
import com.tristankechlo.livingthings.entities.PenguinEntity;
import com.tristankechlo.livingthings.entities.RaccoonEntity;
import com.tristankechlo.livingthings.entities.SeahorseEntity;
import com.tristankechlo.livingthings.entities.SharkEntity;
import com.tristankechlo.livingthings.entities.ShroomieEntity;
import com.tristankechlo.livingthings.entities.SnailEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.fmllegacy.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, LivingThings.MOD_ID);

	// create entity types
	private static final EntityType<ElephantEntity> elephant = createStandardEntityType("elephant", ElephantEntity::new, MobCategory.CREATURE, 1.85F, 2.7F);
	private static final EntityType<GiraffeEntity> giraffe = createStandardEntityType("giraffe", GiraffeEntity::new, MobCategory.CREATURE, 1.5F, 3.2F);
	private static final EntityType<LionEntity> lion = createStandardEntityType("lion", LionEntity::new, MobCategory.CREATURE, 1.25F, 1.5F);
	private static final EntityType<SharkEntity> shark = createStandardEntityType("shark", SharkEntity::new, MobCategory.WATER_CREATURE, 1.4F, 1.1F);
	private static final EntityType<PenguinEntity> penguin = createStandardEntityType("penguin", PenguinEntity::new, MobCategory.CREATURE, 0.8F, 1.45F);
	private static final EntityType<OstrichEntity> ostrich = createStandardEntityType("ostrich", OstrichEntity::new, MobCategory.CREATURE, 0.8F, 1.8F);
	private static final EntityType<FlamingoEntity> flamingo = createStandardEntityType("flamingo", FlamingoEntity::new, MobCategory.CREATURE, 0.6F, 1.25F);
	private static final EntityType<CrabEntity> crab = createStandardEntityType("crab", CrabEntity::new, MobCategory.CREATURE, 0.4F, 0.4F);
	private static final EntityType<MantarayEntity> mantaray = createStandardEntityType("mantaray", MantarayEntity::new, MobCategory.WATER_AMBIENT, 0.75F, 0.45F);
	private static final EntityType<RaccoonEntity> raccoon = createStandardEntityType("raccoon", RaccoonEntity::new, MobCategory.CREATURE, 0.5F, 0.75F);
	private static final EntityType<OwlEntity> owl = createStandardEntityType("owl", OwlEntity::new, MobCategory.CREATURE, 0.5F, 0.99F);
	private static final EntityType<AncientBlazeEntity> ancient_blaze = EntityType.Builder.of(AncientBlazeEntity::new, MobCategory.MONSTER).sized(0.7F, 2.99F).fireImmune().build(LivingThings.MOD_ID + ":ancient_blaze");
	private static final EntityType<KoalaEntity> koala = createStandardEntityType("koala", KoalaEntity::new, MobCategory.CREATURE, 0.6F, 0.75F);
	private static final EntityType<SnailEntity> snail = createStandardEntityType("snail", SnailEntity::new, MobCategory.CREATURE, 0.6F, 0.7F);
	private static final EntityType<MonkeyEntity> monkey = createStandardEntityType("monkey", MonkeyEntity::new, MobCategory.CREATURE, 0.6F, 0.7F);
	private static final EntityType<NetherKnightEntity> nether_knight = EntityType.Builder.of(NetherKnightEntity::new, MobCategory.MONSTER).sized(0.7F, 2.3F).fireImmune().build(LivingThings.MOD_ID + ":nether_knight");
	private static final EntityType<ShroomieEntity> shroomie = createStandardEntityType("shroomie", ShroomieEntity::new, MobCategory.CREATURE, 0.5F, 0.99F);
	public static final EntityType<SeahorseEntity> seahorse = createStandardEntityType("seahorse", SeahorseEntity::new, MobCategory.WATER_AMBIENT, 0.2F, 0.7F);

	// register entity types
	public static final RegistryObject<EntityType<ElephantEntity>> ELEPHANT = ENTITY_TYPES.register("elephant", () -> elephant);
	public static final RegistryObject<EntityType<GiraffeEntity>> GIRAFFE = ENTITY_TYPES.register("giraffe", () -> giraffe);
	public static final RegistryObject<EntityType<LionEntity>> LION = ENTITY_TYPES.register("lion", () -> lion);
	public static final RegistryObject<EntityType<SharkEntity>> SHARK = ENTITY_TYPES.register("shark", () -> shark);
	public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN = ENTITY_TYPES.register("penguin", () -> penguin);
	public static final RegistryObject<EntityType<OstrichEntity>> OSTRICH = ENTITY_TYPES.register("ostrich", () -> ostrich);
	public static final RegistryObject<EntityType<FlamingoEntity>> FLAMINGO = ENTITY_TYPES.register("flamingo", () -> flamingo);
	public static final RegistryObject<EntityType<CrabEntity>> CRAB = ENTITY_TYPES.register("crab", () -> crab);
	public static final RegistryObject<EntityType<MantarayEntity>> MANTARAY = ENTITY_TYPES.register("mantaray", () -> mantaray);
	public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON = ENTITY_TYPES.register("raccoon", () -> raccoon);
	public static final RegistryObject<EntityType<OwlEntity>> OWL = ENTITY_TYPES.register("owl", () -> owl);
	public static final RegistryObject<EntityType<AncientBlazeEntity>> ANCIENT_BLAZE = ENTITY_TYPES.register("ancient_blaze", () -> ancient_blaze);
	public static final RegistryObject<EntityType<KoalaEntity>> KOALA = ENTITY_TYPES.register("koala", () -> koala);
	public static final RegistryObject<EntityType<SnailEntity>> SNAIL = ENTITY_TYPES.register("snail", () -> snail);
	public static final RegistryObject<EntityType<MonkeyEntity>> MONKEY = ENTITY_TYPES.register("monkey", () -> monkey);
	public static final RegistryObject<EntityType<NetherKnightEntity>> NETHER_KNIGHT = ENTITY_TYPES.register("nether_knight", () -> nether_knight);
	public static final RegistryObject<EntityType<ShroomieEntity>> SHROOMIE = ENTITY_TYPES.register("shroomie", () -> shroomie);
	public static final RegistryObject<EntityType<SeahorseEntity>> SEAHORSE = ENTITY_TYPES.register("seahorse", () -> seahorse);

	// create standard entity type
	private static <T extends Entity> EntityType<T> createStandardEntityType(String entity_name, EntityType.EntityFactory<T> factory, MobCategory classification, float width, float height) {
		return EntityType.Builder.of(factory, classification).sized(width, height).build(LivingThings.MOD_ID + ":" + entity_name);
	}

}
