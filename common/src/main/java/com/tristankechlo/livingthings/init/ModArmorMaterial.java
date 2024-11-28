package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.util.LivingThingsTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;

import java.util.EnumMap;

public final class ModArmorMaterial {

    public static void init() {}

    public static final ArmorMaterial ANCIENT = makeMaterial();

    private static ArmorMaterial makeMaterial() {
        EnumMap<ArmorType, Integer> defense = new EnumMap<>(ArmorType.class);
        defense.put(ArmorType.BOOTS, 3);
        defense.put(ArmorType.LEGGINGS, 6);
        defense.put(ArmorType.CHESTPLATE, 8);
        defense.put(ArmorType.HELMET, 3);
        defense.put(ArmorType.BODY, 5);

        int durability = 20;
        int enchantmentValue = 1;
        float toughness = 2f;
        float knockbackResistance = 0f;
        TagKey<Item> repairIngredient = LivingThingsTags.REPAIRS_ANCIENT_HELMET;
        ResourceLocation modelId = ResourceLocation.fromNamespaceAndPath(LivingThings.MOD_ID, "ancientarmormodel");

        return new ArmorMaterial(durability, defense, enchantmentValue, ModSounds.ANCIENT_ARMOR_EQUIP.asHolder(), toughness, knockbackResistance, repairIngredient, modelId);

    }

}
