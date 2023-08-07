package com.tristankechlo.livingthings.init;

import com.tristankechlo.livingthings.LivingThings;
import com.tristankechlo.livingthings.platform.IPlatformHelper;
import com.tristankechlo.livingthings.platform.RegistrationProvider;
import com.tristankechlo.livingthings.platform.RegistryObject;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.CreativeModeTab;

public class ModCreativeTabs {

    public static void init() {}

    private static final RegistrationProvider<CreativeModeTab> TABS = RegistrationProvider.get(BuiltInRegistries.CREATIVE_MODE_TAB, LivingThings.MOD_ID);

    public static final RegistryObject<CreativeModeTab> GENERAL = TABS.register("general", () -> IPlatformHelper.INSTANCE.getCreativeModeTab().build());

}
