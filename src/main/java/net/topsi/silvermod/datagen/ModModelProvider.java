package net.topsi.silvermod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.topsi.silvermod.block.ModBlocks;
import net.topsi.silvermod.item.ModItems;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_ORE);
        BlockStateModelGenerator.BlockTexturePool silverPool = blockStateModelGenerator.registerCubeAllModelTexturePool(ModBlocks.BLOCK_OF_SILVER);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SILVER_TNT);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SILVER_INGOT, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_NUGGET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SILVER_TNT_ARROW, Models.GENERATED);

    }
}
