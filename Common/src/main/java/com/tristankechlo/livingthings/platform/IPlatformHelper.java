package com.tristankechlo.livingthings.platform;

import net.minecraft.world.item.CreativeModeTab;

import java.nio.file.Path;

public interface IPlatformHelper {

    public static final IPlatformHelper INSTANCE = Services.load(IPlatformHelper.class);

    String getPlatformName();

    boolean isModLoaded(String modId);

    boolean isDevelopmentEnvironment();

    default String getEnvironmentName() {
        return isDevelopmentEnvironment() ? "development" : "production";
    }

    Path getConfigDirectory();

    CreativeModeTab getCreativeModeTab();

    static CreativeModeTab getCreativeTab() {
        return IPlatformHelper.INSTANCE.getCreativeModeTab();
    }

}
