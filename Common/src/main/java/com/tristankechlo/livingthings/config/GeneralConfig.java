package com.tristankechlo.livingthings.config;

import com.tristankechlo.livingthings.config.util.EntityConfig;
import com.tristankechlo.livingthings.config.values.BooleanValue;
import com.tristankechlo.livingthings.config.values.NumberValue;

public final class GeneralConfig extends EntityConfig {

    private static final GeneralConfig INSTANCE = new GeneralConfig();

    public final BooleanValue ambientMode = new BooleanValue("ambientMode", false);
    public final BooleanValue doBananaDrops = new BooleanValue("doBananaDrops", true);
    public final NumberValue.DoubleValue bananaDropChance = new NumberValue.DoubleValue("bananaDropChance", 45.0D, 0.0D, 100.0D);

    private GeneralConfig() {
        super("general");
        this.registerConfigValues(this.ambientMode, this.doBananaDrops, this.bananaDropChance);
    }

    public static GeneralConfig get() {
        return INSTANCE;
    }

}
