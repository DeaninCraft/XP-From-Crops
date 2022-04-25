package net.chef.cropxp.config;
import com.mojang.datafixers.util.Pair;
import net.chef.cropxp.XpFromCrops;

public class ModConfigs {
    public static SimpleConfig CONFIG;
    private static ModConfigProvider configs;

    public static int CHANCE;
    public static int AMOUNT;
    public static String[] CROPS;

    public static String[] crops = {
            "minecraft:potatoes[age=7]",
            "minecraft:carrots[age=7]",
            "minecraft:wheat[age=7]",
            "minecraft:beetroots[age=3]",
            "minecraft:nether_wart[age=3]"
    };

    public static void registerConfigs() {
        configs = new ModConfigProvider();
        createConfigs();

        CONFIG = SimpleConfig.of(XpFromCrops.MOD_ID + "config").provider(configs).request();

        assignConfigs();
    }

    private static void createConfigs() {
        configs.addKeyValuePair(new Pair<>("harvest.xp.chance", 100), "Chance for xp tp drop from harvest : Range(1-100)");
        configs.addKeyValuePair(new Pair<>("harvest.xp.amount", 1), "Amount of xp dropped from harvest : Minimum 1");
        configs.addKeyValuePairList(new Pair<>("harvest.xp.crops", crops), "List of crops to allow xp drops");
    }

    private static void assignConfigs() {
        CHANCE = CONFIG.getOrDefault("harvest.xp.chance", 100);
        AMOUNT = CONFIG.getOrDefault("harvest.xp.amount", 1);
        CROPS = CONFIG.getOrDefault("harvest.xp.crops", crops);

        System.out.println("All " + configs.getConfigsList().size() + " have been set properly");
    }
}