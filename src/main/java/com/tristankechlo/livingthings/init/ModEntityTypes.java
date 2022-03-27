package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.entities.AncientBlazeEntity;
import com.tristankechlo.livingthings.entities.BabyEnderDragonEntity;
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
import com.tristankechlo.livingthings.misc.Names;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityType.EntityFactory;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntityTypes {

	public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, LivingThings.MOD_ID);

	// register entity types
	public static final RegistryObject<EntityType<ElephantEntity>> ELEPHANT = ENTITY_TYPES.register(Names.ELEPHANT,
			() -> create(Names.ELEPHANT, ElephantEntity::new, MobCategory.CREATURE, 1.85F, 2.7F));
	public static final RegistryObject<EntityType<GiraffeEntity>> GIRAFFE = ENTITY_TYPES.register(Names.GIRAFFE,
			() -> create(Names.GIRAFFE, GiraffeEntity::new, MobCategory.CREATURE, 1.5F, 3.2F));
	public static final RegistryObject<EntityType<LionEntity>> LION = ENTITY_TYPES.register(Names.LION,
			() -> create(Names.LION, LionEntity::new, MobCategory.CREATURE, 1.25F, 1.5F));
	public static final RegistryObject<EntityType<SharkEntity>> SHARK = ENTITY_TYPES.register(Names.SHARK,
			() -> create(Names.SHARK, SharkEntity::new, MobCategory.WATER_CREATURE, 1.4F, 1.1F));
	public static final RegistryObject<EntityType<PenguinEntity>> PENGUIN = ENTITY_TYPES.register(Names.PENGUIN,
			() -> create(Names.PENGUIN, PenguinEntity::new, MobCategory.CREATURE, 0.8F, 1.45F));
	public static final RegistryObject<EntityType<OstrichEntity>> OSTRICH = ENTITY_TYPES.register(Names.OSTRICH,
			() -> create(Names.OSTRICH, OstrichEntity::new, MobCategory.CREATURE, 0.6F, 1.8F));
	public static final RegistryObject<EntityType<FlamingoEntity>> FLAMINGO = ENTITY_TYPES.register(Names.FLAMINGO,
			() -> create(Names.FLAMINGO, FlamingoEntity::new, MobCategory.CREATURE, 0.6F, 1.25F));
	public static final RegistryObject<EntityType<CrabEntity>> CRAB = ENTITY_TYPES.register(Names.CRAB,
			() -> create(Names.CRAB, CrabEntity::new, MobCategory.CREATURE, 0.4F, 0.4F));
	public static final RegistryObject<EntityType<MantarayEntity>> MANTARAY = ENTITY_TYPES.register(Names.MANTARAY,
			() -> create(Names.MANTARAY, MantarayEntity::new, MobCategory.WATER_AMBIENT, 0.75F, 0.45F));
	public static final RegistryObject<EntityType<RaccoonEntity>> RACCOON = ENTITY_TYPES.register(Names.RACCOON,
			() -> create(Names.RACCOON, RaccoonEntity::new, MobCategory.CREATURE, 0.5F, 0.75F));
	public static final RegistryObject<EntityType<OwlEntity>> OWL = ENTITY_TYPES.register(Names.OWL,
			() -> create(Names.OWL, OwlEntity::new, MobCategory.CREATURE, 0.5F, 0.99F));
	public static final RegistryObject<EntityType<AncientBlazeEntity>> ANCIENT_BLAZE = ENTITY_TYPES.register(Names.ANCIENT_BLAZE, 
			() -> EntityType.Builder.of(AncientBlazeEntity::new, MobCategory.MONSTER).sized(0.7F, 2.99F).fireImmune().build(LivingThings.MOD_ID + ":" + Names.ANCIENT_BLAZE));
	public static final RegistryObject<EntityType<KoalaEntity>> KOALA = ENTITY_TYPES.register(Names.KOALA,
			() -> create(Names.KOALA, KoalaEntity::new, MobCategory.CREATURE, 0.6F, 0.75F));
	public static final RegistryObject<EntityType<SnailEntity>> SNAIL = ENTITY_TYPES.register(Names.SNAIL,
			() -> create(Names.SNAIL, SnailEntity::new, MobCategory.CREATURE, 0.6F, 0.7F));
	public static final RegistryObject<EntityType<MonkeyEntity>> MONKEY = ENTITY_TYPES.register(Names.MONKEY,
			() -> create(Names.MONKEY, MonkeyEntity::new, MobCategory.CREATURE, 0.6F, 0.7F));
	public static final RegistryObject<EntityType<NetherKnightEntity>> NETHER_KNIGHT = ENTITY_TYPES.register(Names.NETHER_KNIGHT, 
			() -> EntityType.Builder.of(NetherKnightEntity::new, MobCategory.MONSTER).sized(0.7F, 2.3F).fireImmune().build(LivingThings.MOD_ID + ":" + Names.NETHER_KNIGHT));
	public static final RegistryObject<EntityType<ShroomieEntity>> SHROOMIE = ENTITY_TYPES.register(Names.SHROOMIE,
			() -> create(Names.SHROOMIE, ShroomieEntity::new, MobCategory.CREATURE, 0.5F, 0.99F));
	public static final RegistryObject<EntityType<SeahorseEntity>> SEAHORSE = ENTITY_TYPES.register(Names.SEAHORSE,
			() -> create(Names.SEAHORSE, SeahorseEntity::new, MobCategory.WATER_AMBIENT, 0.2F, 0.7F));
	public static final RegistryObject<EntityType<BabyEnderDragonEntity>> BABY_ENDER_DRAGON = ENTITY_TYPES.register(Names.BABY_ENDER_DRAGON,
			() -> create(Names.BABY_ENDER_DRAGON, BabyEnderDragonEntity::new, MobCategory.CREATURE, 0.75F, 0.75F));

	// create standard entity type
	private static <T extends Entity> EntityType<T> create(String entity_name, EntityFactory<T> factory, MobCategory classification, float width, float height) {
		return EntityType.Builder.of(factory, classification).sized(width, height).build(LivingThings.MOD_ID + ":" + entity_name);
	}

}
