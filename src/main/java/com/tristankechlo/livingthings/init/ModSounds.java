package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModSounds {

	public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, LivingThings.MOD_ID);
	
	
	public static final RegistryObject<SoundEvent> LION_AMBIENT = SOUNDS.register("lion_ambient", () -> new SoundEvent(getSound("lion.ambient")));
	public static final RegistryObject<SoundEvent> LION_HURT = SOUNDS.register("lion_hurt", () -> new SoundEvent(getSound("lion.hurt")));
	public static final RegistryObject<SoundEvent> LION_DEATH = SOUNDS.register("lion_death", () -> new SoundEvent(getSound("lion.death")));
	
	public static final RegistryObject<SoundEvent> PENGUIN_AMBIENT = SOUNDS.register("penguin_ambient", () -> new SoundEvent(getSound("penguin.ambient")));
	public static final RegistryObject<SoundEvent> PENGUIN_HURT = SOUNDS.register("penguin_hurt", () -> new SoundEvent(getSound("penguin.hurt")));
	public static final RegistryObject<SoundEvent> PENGUIN_DEATH = SOUNDS.register("penguin_death", () -> new SoundEvent(getSound("penguin.death")));

	public static final RegistryObject<SoundEvent> ELEPHANT_AMBIENT = SOUNDS.register("elephant_ambient", () -> new SoundEvent(getSound("elephant.ambient")));
	public static final RegistryObject<SoundEvent> ELEPHANT_HURT = SOUNDS.register("elephant_hurt", () -> new SoundEvent(getSound("elephant.hurt")));
	public static final RegistryObject<SoundEvent> ELEPHANT_DEATH = SOUNDS.register("elephant_death", () -> new SoundEvent(getSound("elephant.death")));
	public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_SADDLE = SOUNDS.register("elephant_chest", () -> new SoundEvent(getSound("elephant.equip.saddle")));
	public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_CHEST = SOUNDS.register("elephant_saddle", () -> new SoundEvent(getSound("elephant.equip.chest")));
	
	public static final RegistryObject<SoundEvent> OSTRICH_EGG_CRACKS = SOUNDS.register("ostrich_egg_cracks", () -> new SoundEvent(getSound("ostrich.egg.cracks")));
	public static final RegistryObject<SoundEvent> OSTRICH_EGG_LAYING = SOUNDS.register("ostrich_egg_laying", () -> new SoundEvent(getSound("ostrich.egg.laying")));
	public static final RegistryObject<SoundEvent> OSTRICH_AMBIENT = SOUNDS.register("ostrich_ambient", () -> new SoundEvent(getSound("ostrich.ambient")));

	public static final RegistryObject<SoundEvent> RACCOON_AMBIENT = SOUNDS.register("raccoon_ambient", () -> new SoundEvent(getSound("raccoon.ambient")));
	
	
	private static ResourceLocation getSound(String name) {
		return new ResourceLocation(LivingThings.MOD_ID, name);
	}
}
