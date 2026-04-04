package net.topsi.silvermod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.TagKey;
import net.topsi.silvermod.item.ModItems;
import net.topsi.silvermod.util.ModTags;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends FabricTagProvider.ItemTagProvider {


    public ModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> completableFuture) {
        super(output, completableFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.SILVER_INGOT)
                .add(ModItems.SILVER_NUGGET)
                .add(ModItems.SILVER_TNT_ARROW);

        getOrCreateTagBuilder(TagKey.of(RegistryKeys.ITEM, net.minecraft.util.Identifier.of("minecraft", "arrows")))
                .add(ModItems.SILVER_TNT_ARROW);
    }
}
