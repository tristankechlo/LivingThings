package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

public final class ModArmorMaterial {

    public static void init() {}

    private static final RegistrationProvider<ArmorMaterial> MATERIALS = RegistrationProvider.get(Registries.ARMOR_MATERIAL, LivingThings.MOD_ID);

    public static final RegistryObject<ArmorMaterial> ANCIENT = MATERIALS.register("ancient", ModArmorMaterial::makeMaterial);

    private static ArmorMaterial makeMaterial() {
        EnumMap<ArmorItem.Type, Integer> types = new EnumMap<>(ArmorItem.Type.class);
        types.put(ArmorItem.Type.BOOTS, 3);
        types.put(ArmorItem.Type.LEGGINGS, 6);
        types.put(ArmorItem.Type.CHESTPLATE, 8);
        types.put(ArmorItem.Type.HELMET, 3);
        types.put(ArmorItem.Type.BODY, 5);

        Ingredient repairMaterial = Ingredient.of(Items.NETHERITE_INGOT);
        List<ArmorMaterial.Layer> layers = new ArrayList<>();

        return new ArmorMaterial(types, 20, ModSounds.ANCIENT_ARMOR_EQUIP.asHolder(), () -> repairMaterial, layers, 2f, 0f);
    }

}
