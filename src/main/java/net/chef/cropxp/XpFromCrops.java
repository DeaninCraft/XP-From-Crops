package net.chef.cropxp;

import net.chef.cropxp.config.ModConfigs;
import net.chef.cropxp.events.HarvestEvent;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class XpFromCrops implements ModInitializer {

	public static final String MOD_ID = "cropxp";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModConfigs.registerConfigs();
		HarvestEvent harvest = new HarvestEvent();
	}
}
