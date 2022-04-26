package net.chef.cropxp.init;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import net.chef.cropxp.config.CropXpConfig;

public class ConfigInit {
    public static CropXpConfig CONFIG = new CropXpConfig();

    public static void init() {
        AutoConfig.register(CropXpConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(CropXpConfig.class).getConfig();
    }
}
