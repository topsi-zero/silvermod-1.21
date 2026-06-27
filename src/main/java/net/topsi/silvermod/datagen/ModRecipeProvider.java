package net.topsi.silvermod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.topsi.silvermod.block.ModBlocks;
import net.topsi.silvermod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {

        List<ItemConvertible> SILVER_INGOT_SMELTABLES = List.of(
                ModBlocks.SILVER_ORE);

        offerSmelting(recipeExporter, SILVER_INGOT_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT, 0.25f, 200, "silver");
        offerBlasting(recipeExporter, SILVER_INGOT_SMELTABLES, RecipeCategory.MISC, ModItems.SILVER_INGOT, 0.25f, 100, "silver");

        offerReversibleCompactingRecipes(recipeExporter, RecipeCategory.BUILDING_BLOCKS, ModItems.SILVER_INGOT, RecipeCategory.DECORATIONS, ModBlocks.BLOCK_OF_SILVER);

        ShapedRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.SILVER_TNT)
                .pattern("*g*")
                .pattern("gsg")
                .pattern("*g*")
                .input('s', ModItems.SILVER_INGOT)
                .input('g', Items.GUNPOWDER)
                .input('*', Blocks.SAND)
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(ModBlocks.SILVER_TNT))
                .offerTo(recipeExporter);  // shaped recipies

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_TNT_ARROW)
                .pattern(" g ")
                .pattern("gag")
                .pattern(" g ")
                .input('a', Items.ARROW)
                .input('g', Items.GUNPOWDER)
                .criterion(hasItem(Items.GUNPOWDER), conditionsFromItem(ModBlocks.SILVER_TNT))
                .offerTo(recipeExporter);  // shaped recipies

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_MACE)
                .pattern("  i")
                .pattern(" h ")
                .pattern("r  ")
                .input('h', Items.HEAVY_CORE)
                .input('r', Items.BREEZE_ROD)
                .input('i', ModItems.SILVER_INGOT)
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(ModItems.SILVER_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_BLAZE_MACE)
                .pattern("  i")
                .pattern(" h ")
                .pattern("r  ")
                .input('h', Items.HEAVY_CORE)
                .input('r', Items.BLAZE_ROD)
                .input('i', ModItems.SILVER_INGOT)
                .criterion(hasItem(Items.HEAVY_CORE), conditionsFromItem(ModItems.SILVER_BLAZE_MACE))
                .offerTo(recipeExporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.COMBAT, ModItems.SILVER_TEST_MACE)
                .pattern("  c")
                .pattern(" h ")
                .pattern("r  ")
                .input('c', Items.COMMAND_BLOCK)
                .input('r', Items.BREEZE_ROD)
                .input('h', Items.HEAVY_CORE)
                .criterion(hasItem(Items.COMMAND_BLOCK), conditionsFromItem(ModItems.SILVER_BLAZE_MACE))
                .offerTo(recipeExporter);
    }
}
