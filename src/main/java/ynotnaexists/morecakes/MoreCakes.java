package ynotnaexists.morecakes;

import net.fabricmc.api.ModInitializer;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Items;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ynotnaexists.morecakes.block.ModBlocks;

import java.util.Objects;

public class MoreCakes implements ModInitializer {

	public static final String MOD_ID = "morecakes";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();
	}
}