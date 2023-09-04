package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {

    public static void init() {}

    private static final RegistrationProvider<SoundEvent> SOUNDS = RegistrationProvider.get(Registry.SOUND_EVENT, LivingThings.MOD_ID);

    public static final RegistryObject<SoundEvent> LION_AMBIENT = registerSound("lion.ambient");
    public static final RegistryObject<SoundEvent> LION_HURT = registerSound("lion.hurt");
    public static final RegistryObject<SoundEvent> LION_DEATH = registerSound("lion.death");

    public static final RegistryObject<SoundEvent> PENGUIN_AMBIENT = registerSound("penguin.ambient");
    public static final RegistryObject<SoundEvent> PENGUIN_HURT = registerSound("penguin.hurt");
    public static final RegistryObject<SoundEvent> PENGUIN_DEATH = registerSound("penguin.death");

    public static final RegistryObject<SoundEvent> ELEPHANT_AMBIENT = registerSound("elephant.ambient");
    public static final RegistryObject<SoundEvent> ELEPHANT_HURT = registerSound("elephant.hurt");
    public static final RegistryObject<SoundEvent> ELEPHANT_DEATH = registerSound("elephant.death");
    public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_SADDLE = registerSound("elephant.equip.saddle");
    public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_CHEST = registerSound("elephant.equip.chest");

    public static final RegistryObject<SoundEvent> OSTRICH_EGG_CRACKS = registerSound("ostrich.egg.cracks");
    public static final RegistryObject<SoundEvent> OSTRICH_EGG_LAYING = registerSound("ostrich.egg.laying");
    public static final RegistryObject<SoundEvent> OSTRICH_EGG_REMOVED = registerSound("ostrich_nest.remove_egg");
    public static final RegistryObject<SoundEvent> OSTRICH_AMBIENT = registerSound("ostrich.ambient");

    public static final RegistryObject<SoundEvent> RACCOON_AMBIENT = registerSound("raccoon.ambient");
    public static final RegistryObject<SoundEvent> RACCOON_HURT = registerSound("raccoon.hurt");
    public static final RegistryObject<SoundEvent> RACCOON_DEATH = registerSound("raccoon.death");

    public static final RegistryObject<SoundEvent> OWL_AMBIENT = registerSound("owl.ambient");
    public static final RegistryObject<SoundEvent> OWL_HURT = registerSound("owl.hurt");
    public static final RegistryObject<SoundEvent> OWL_DEATH = registerSound("owl.death");
    public static final RegistryObject<SoundEvent> OWL_FLY = registerSound("owl.fly");

    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_AMBIENT = registerSound("ancient_blaze.ambient");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_HURT = registerSound("ancient_blaze.hurt");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_DEATH = registerSound("ancient_blaze.death");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_BURN = registerSound("ancient_blaze.burn");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_SHOOT = registerSound("ancient_blaze.shoot");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_SPAWN = registerSound("ancient_blaze.spawn");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_CHARGE_UP = registerSound("ancient_blaze.charge_up");
    public static final RegistryObject<SoundEvent> ANCIENT_ARMOR_EQUIP = registerSound("ancient_armor.equip");

    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_AMBIENT = registerSound("nether_knight.ambient");
    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_HURT = registerSound("nether_knight.hurt");
    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_DEATH = registerSound("nether_knight.death");
    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_STEP = registerSound("nether_knight.step");

    public static final RegistryObject<SoundEvent> SEAHORSE_FLOP = registerSound("seahorse.flop");

    public static final RegistryObject<SoundEvent> MANTARAY_FLOP = registerSound("mantaray.flop");

    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_AMBIENT = registerSound("baby_ender_dragon.ambient");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_HURT = registerSound("baby_ender_dragon.hurt");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_DEATH = registerSound("baby_ender_dragon.death");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_FLAP = registerSound("baby_ender_dragon.flap");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_SHOOT = registerSound("baby_ender_dragon.shoot");

    public static final RegistryObject<SoundEvent> PEACOCK_AMBIENT = registerSound("peacock.ambient");
    public static final RegistryObject<SoundEvent> PEACOCK_HURT = registerSound("peacock.hurt");
    public static final RegistryObject<SoundEvent> PEACOCK_DEATH = registerSound("peacock.death");

    private static RegistryObject<SoundEvent> registerSound(String soundName) {
        return SOUNDS.register(soundName, () -> new SoundEvent(new ResourceLocation(LivingThings.MOD_ID, soundName)));
    }

}
