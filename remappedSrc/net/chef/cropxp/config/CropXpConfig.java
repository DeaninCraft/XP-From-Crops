package net.chef.cropxp.config;

import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;
import net.chef.cropxp.XpFromCrops;

@Config(name = XpFromCrops.MOD_ID)
public class CropXpConfig implements ConfigData {

    @ConfigEntry.Category("xp_settings")
    @Comment("% Chance for xp tp drop from harvest : Range(1-100)")
    public int chance = 100;

    @ConfigEntry.Category("xp_settings")
    @Comment("Amount of xp dropped from harvest : Minimum 1")
    public int amount = 1;

}
