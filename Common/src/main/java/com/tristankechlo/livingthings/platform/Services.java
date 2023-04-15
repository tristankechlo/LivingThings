package com.tristankechlo.livingthings.platform;

import com.tristankechlo.livingthings.LivingThings;

import java.util.ServiceLoader;

public final class Services {

    public static <T> T load(Class<T> clazz) {
        final T loadedService = ServiceLoader.load(clazz)
                .findFirst()
                .orElseThrow(() -> new NullPointerException("Failed to load service for " + clazz.getName()));
        LivingThings.LOGGER.info("Loaded {} for service {}", loadedService.getClass().getSimpleName(), clazz.getSimpleName());
        return loadedService;
    }

}
