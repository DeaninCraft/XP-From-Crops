package net.chef.cropxp.config;

import blue.endless.jankson.Comment;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.annotation.Config;
import net.chef.cropxp.XpFromCrops;

@Config(name = XpFromCrops.MOD_ID)
public class CropXpConfig implements ConfigData {

    @ConfigEntry.Category("harvest_settings")
    @Comment("Enables right-click auto-replanting with crop in-hand")
    public Boolean autoReplant = false;
    @ConfigEntry.Category("xp_settings")
    @Comment("% Chance for xp tp drop from harvest : Range(1-100)")
    public int chance = 100;

    @ConfigEntry.Category("xp_settings")
    @Comment("Amount of xp dropped from harvest : Minimum 1")
    public int amount = 1;

    @ConfigEntry.Category("xp_settings")
    @Comment("List of crops that will not drop xp")
    public String[] cropDenyList = {
            "Block{minecraft:pumpkin_stem}",
            "Block{minecraft:melon_stem}"
    };

    @ConfigEntry.Category("xp_settings")
    @Comment("List of extra crops that you want to drop xp, if they aren't included in the CROPS tag")
    public String[] cropAllowList = {
            "Block{minecraft:nether_wart}"
    };

}
