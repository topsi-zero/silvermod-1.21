package net.topsi.silvermod;

import net.fabricmc.api.ModInitializer;
import net.topsi.silvermod.block.ModBlocks;
import net.topsi.silvermod.entity.ModEntities;
import net.topsi.silvermod.item.ModItemGroups;
import net.topsi.silvermod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Silvermod implements ModInitializer {
	public static final String MOD_ID = "silvermod";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModEntities.registerModEntities();
		ModBlocks.registerModBlocks();
		ModItemGroups.registerItemGroups();
	}
}