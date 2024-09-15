package ynotnaexists.morecakes.block;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import ynotnaexists.morecakes.MoreCakes;
import ynotnaexists.morecakes.block.customblocks.FoodCakeBlock;

public class ModBlocks {

    public static final Block APPLE_CAKE = registerBlock("apple_cake", new FoodCakeBlock(Items.APPLE, AbstractBlock.Settings.copy(Blocks.CAKE)));
    public static final Block CARROT_CAKE = registerBlock("carrot_cake", new FoodCakeBlock(Items.CARROT, AbstractBlock.Settings.copy(Blocks.CAKE)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(MoreCakes.MOD_ID, name), block);
    }
    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(MoreCakes.MOD_ID, name),
                new BlockItem(block, new Item.Settings()));
    }
    public static void registerModBlocks() {
        MoreCakes.LOGGER.info("Registering MoreCakes Blocks");
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FOOD_AND_DRINK).register(ModBlocks::addToFoodAndDrinkTab);
    }
    public static void addToFoodAndDrinkTab(FabricItemGroupEntries entries) {
        entries.add(ModBlocks.APPLE_CAKE.asItem());
        entries.add(ModBlocks.CARROT_CAKE.asItem());
    }
}
