package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LivingThings.MOD_ID);

	public static final RegistryObject<SoundEvent> LION_AMBIENT = SOUNDS.register("lion_ambient", () -> getSound("lion.ambient"));
	public static final RegistryObject<SoundEvent> LION_HURT = SOUNDS.register("lion_hurt", () -> getSound("lion.hurt"));
	public static final RegistryObject<SoundEvent> LION_DEATH = SOUNDS.register("lion_death", () -> getSound("lion.death"));

	public static final RegistryObject<SoundEvent> PENGUIN_AMBIENT = SOUNDS.register("penguin_ambient", () -> getSound("penguin.ambient"));
	public static final RegistryObject<SoundEvent> PENGUIN_HURT = SOUNDS.register("penguin_hurt", () -> getSound("penguin.hurt"));
	public static final RegistryObject<SoundEvent> PENGUIN_DEATH = SOUNDS.register("penguin_death", () -> getSound("penguin.death"));

	public static final RegistryObject<SoundEvent> ELEPHANT_AMBIENT = SOUNDS.register("elephant_ambient", () -> getSound("elephant.ambient"));
	public static final RegistryObject<SoundEvent> ELEPHANT_HURT = SOUNDS.register("elephant_hurt", () -> getSound("elephant.hurt"));
	public static final RegistryObject<SoundEvent> ELEPHANT_DEATH = SOUNDS.register("elephant_death", () -> getSound("elephant.death"));
	public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_SADDLE = SOUNDS.register("elephant_chest", () -> getSound("elephant.equip.saddle"));
	public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_CHEST = SOUNDS.register("elephant_saddle", () -> getSound("elephant.equip.chest"));

	public static final RegistryObject<SoundEvent> OSTRICH_EGG_CRACKS = SOUNDS.register("ostrich_egg_cracks", () -> getSound("ostrich.egg.cracks"));
	public static final RegistryObject<SoundEvent> OSTRICH_EGG_LAYING = SOUNDS.register("ostrich_egg_laying", () -> getSound("ostrich.egg.laying"));
	public static final RegistryObject<SoundEvent> OSTRICH_AMBIENT = SOUNDS.register("ostrich_ambient", () -> getSound("ostrich.ambient"));

	public static final RegistryObject<SoundEvent> RACCOON_AMBIENT = SOUNDS.register("raccoon_ambient", () -> getSound("raccoon.ambient"));
	public static final RegistryObject<SoundEvent> RACCOON_HURT = SOUNDS.register("raccoon_hurt", () -> getSound("raccoon.hurt"));
	public static final RegistryObject<SoundEvent> RACCOON_DEATH = SOUNDS.register("raccoon_death", () -> getSound("raccoon.death"));

	public static final RegistryObject<SoundEvent> OWL_AMBIENT = SOUNDS.register("owl_ambient", () -> getSound("owl.ambient"));
	public static final RegistryObject<SoundEvent> OWL_HURT = SOUNDS.register("owl_hurt", () -> getSound("owl.hurt"));
	public static final RegistryObject<SoundEvent> OWL_DEATH = SOUNDS.register("owl_death", () -> getSound("owl.death"));
	public static final RegistryObject<SoundEvent> OWL_FLY = SOUNDS.register("owl_fly", () -> getSound("owl.fly"));

	public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_AMBIENT = SOUNDS.register("ancient_blaze_ambient", () -> getSound("ancient_blaze.ambient"));
	public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_HURT = SOUNDS.register("ancient_blaze_hurt", () -> getSound("ancient_blaze.hurt"));
	public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_DEATH = SOUNDS.register("ancient_blaze_death", () -> getSound("ancient_blaze.death"));
	public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_BURN = SOUNDS.register("ancient_blaze_burn", () -> getSound("ancient_blaze.burn"));
	public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_SHOOT = SOUNDS.register("ancient_blaze_shoot", () -> getSound("ancient_blaze.shoot"));
	public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_SPAWN = SOUNDS.register("ancient_blaze_spawn", () -> getSound("ancient_blaze.spawn"));
	public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_CHARGE_UP = SOUNDS.register("ancient_blaze_charge_up", () -> getSound("ancient_blaze.charge_up"));

	public static final RegistryObject<SoundEvent> NETHER_KNIGHT_AMBIENT = SOUNDS.register("nether_knight_ambient", () -> getSound("nether_knight.ambient"));
	public static final RegistryObject<SoundEvent> NETHER_KNIGHT_HURT = SOUNDS.register("nether_knight_hurt", () -> getSound("nether_knight.hurt"));
	public static final RegistryObject<SoundEvent> NETHER_KNIGHT_DEATH = SOUNDS.register("nether_knight_death", () -> getSound("nether_knight.death"));
	public static final RegistryObject<SoundEvent> NETHER_KNIGHT_STEP = SOUNDS.register("nether_knight_step", () -> getSound("nether_knight.step"));

	private static SoundEvent getSound(String name) {
		return new SoundEvent(new ResourceLocation(LivingThings.MOD_ID, name));
	}
}
