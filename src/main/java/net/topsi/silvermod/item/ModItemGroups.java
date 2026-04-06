package net.topsi.silvermod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.topsi.silvermod.Silvermod;
import net.topsi.silvermod.block.ModBlocks;

public class ModItemGroups {

    public static final ItemGroup SILVER = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(Silvermod.MOD_ID, "silver_items"),
            FabricItemGroup.builder().icon(() -> new ItemStack(ModItems.SILVER_INGOT))
                    .displayName(Text.translatable("itemgroup.silvermod.silver_items"))
                    .entries((displayContext, entries) -> {
                        entries.add(ModItems.SILVER_INGOT);
                        entries.add(ModItems.SILVER_NUGGET);
                        entries.add(ModBlocks.BLOCK_OF_SILVER);
                        entries.add(ModBlocks.SILVER_ORE);
                        entries.add(ModBlocks.SILVER_TNT);
                        entries.add(ModItems.SILVER_TNT_ARROW);

                    }).build());



    public static void registerItemGroups() {
        Silvermod.LOGGER.info("Registering Item Groups for " + Silvermod.MOD_ID);
    }
}
