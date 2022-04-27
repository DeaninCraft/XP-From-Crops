package net.chef.cropxp.config;

import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;

public class ConfigRegister {
    public static CropXpConfig CONFIG = new CropXpConfig();

    public static void register() {
        AutoConfig.register(CropXpConfig.class, JanksonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(CropXpConfig.class).getConfig();
    }
}
