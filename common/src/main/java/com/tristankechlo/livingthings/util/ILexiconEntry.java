package com.tristankechlo.livingthings.util;

import com.tristankechlo.livingthings.init.ModItems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public interface ILexiconEntry {

    ResourceLocation getLexiconEntry();

    public static boolean isLexicon(Item item) {
        return item.equals(ModItems.LEXICON.get());
    }

    public static boolean isLexicon(ItemStack stack) {
        return isLexicon(stack.getItem());
    }

}
