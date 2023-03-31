package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Supplier;

public class ForgeItemGroup extends CreativeModeTab {

    public static final ForgeItemGroup GENERAL = new ForgeItemGroup("general", ModItems.SHARK_TOOTH);

    private final Supplier<Item> icon;

    public ForgeItemGroup(String name, Supplier<Item> icon) {
        super(LivingThings.MOD_ID + "." + name);
        this.icon = icon;
    }

    @Override
    public ItemStack makeIcon() {
        return icon.get().getDefaultInstance();
    }

}
