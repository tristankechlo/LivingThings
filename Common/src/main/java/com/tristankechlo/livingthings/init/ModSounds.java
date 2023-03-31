package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public final class ModSounds {

    public static void init() {}

    public static final RegistrationProvider<SoundEvent> SOUNDS = RegistrationProvider.get(Registry.SOUND_EVENT, LivingThings.MOD_ID);

    public static final RegistryObject<SoundEvent> LION_AMBIENT = registerSound("lion_ambient", "lion.ambient");
    public static final RegistryObject<SoundEvent> LION_HURT = registerSound("lion_hurt", "lion.hurt");
    public static final RegistryObject<SoundEvent> LION_DEATH = registerSound("lion_death", "lion.death");

    public static final RegistryObject<SoundEvent> PENGUIN_AMBIENT = registerSound("penguin_ambient", "penguin.ambient");
    public static final RegistryObject<SoundEvent> PENGUIN_HURT = registerSound("penguin_hurt", "penguin.hurt");
    public static final RegistryObject<SoundEvent> PENGUIN_DEATH = registerSound("penguin_death", "penguin.death");

    public static final RegistryObject<SoundEvent> ELEPHANT_AMBIENT = registerSound("elephant_ambient", "elephant.ambient");
    public static final RegistryObject<SoundEvent> ELEPHANT_HURT = registerSound("elephant_hurt", "elephant.hurt");
    public static final RegistryObject<SoundEvent> ELEPHANT_DEATH = registerSound("elephant_death", "elephant.death");
    public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_SADDLE = registerSound("elephant_chest", "elephant.equip.saddle");
    public static final RegistryObject<SoundEvent> ELEPHANT_EQUIP_CHEST = registerSound("elephant_saddle", "elephant.equip.chest");

    public static final RegistryObject<SoundEvent> OSTRICH_EGG_CRACKS = registerSound("ostrich_egg_cracks", "ostrich.egg.cracks");
    public static final RegistryObject<SoundEvent> OSTRICH_EGG_LAYING = registerSound("ostrich_egg_laying", "ostrich.egg.laying");
    public static final RegistryObject<SoundEvent> OSTRICH_AMBIENT = registerSound("ostrich_ambient", "ostrich.ambient");

    public static final RegistryObject<SoundEvent> RACCOON_AMBIENT = registerSound("raccoon_ambient", "raccoon.ambient");
    public static final RegistryObject<SoundEvent> RACCOON_HURT = registerSound("raccoon_hurt", "raccoon.hurt");
    public static final RegistryObject<SoundEvent> RACCOON_DEATH = registerSound("raccoon_death", "raccoon.death");

    public static final RegistryObject<SoundEvent> OWL_AMBIENT = registerSound("owl_ambient", "owl.ambient");
    public static final RegistryObject<SoundEvent> OWL_HURT = registerSound("owl_hurt", "owl.hurt");
    public static final RegistryObject<SoundEvent> OWL_DEATH = registerSound("owl_death", "owl.death");
    public static final RegistryObject<SoundEvent> OWL_FLY = registerSound("owl_fly", "owl.fly");

    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_AMBIENT = registerSound("ancient_blaze_ambient", "ancient_blaze.ambient");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_HURT = registerSound("ancient_blaze_hurt", "ancient_blaze.hurt");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_DEATH = registerSound("ancient_blaze_death", "ancient_blaze.death");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_BURN = registerSound("ancient_blaze_burn", "ancient_blaze.burn");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_SHOOT = registerSound("ancient_blaze_shoot", "ancient_blaze.shoot");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_SPAWN = registerSound("ancient_blaze_spawn", "ancient_blaze.spawn");
    public static final RegistryObject<SoundEvent> ANCIENT_BLAZE_CHARGE_UP = registerSound("ancient_blaze_charge_up", "ancient_blaze.charge_up");

    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_AMBIENT = registerSound("nether_knight_ambient", "nether_knight.ambient");
    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_HURT = registerSound("nether_knight_hurt", "nether_knight.hurt");
    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_DEATH = registerSound("nether_knight_death", "nether_knight.death");
    public static final RegistryObject<SoundEvent> NETHER_KNIGHT_STEP = registerSound("nether_knight_step", "nether_knight.step");

    public static final RegistryObject<SoundEvent> SEAHORSE_FLOP = registerSound("seahorse_flop", "seahorse.flop");

    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_AMBIENT = registerSound("baby_ender_dragon_ambient", "baby_ender_dragon.ambient");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_HURT = registerSound("baby_ender_dragon_hurt", "baby_ender_dragon.hurt");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_DEATH = registerSound("baby_ender_dragon_death", "baby_ender_dragon.death");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_FLAP = registerSound("baby_ender_dragon_flap", "baby_ender_dragon.flap");
    public static final RegistryObject<SoundEvent> BABY_ENDER_DRAGON_SHOOT = registerSound("baby_ender_dragon_shoot", "baby_ender_dragon.shoot");

    private static RegistryObject<SoundEvent> registerSound(String soundName, String soundLocation) {
        return SOUNDS.register(soundName, () -> new SoundEvent(new ResourceLocation(LivingThings.MOD_ID, soundLocation)));
    }

}
