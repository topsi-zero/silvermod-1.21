package net.topsi.silvermod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.MaceItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.topsi.silvermod.Silvermod;
import net.topsi.silvermod.item.custom.*;

import static net.minecraft.item.Items.register;

public class ModItems {

    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Settings()));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new Item.Settings()));
    public static final Item SILVER_TNT_ARROW = registerItem("silver_tnt_arrow", new SilverTntArrowItem(new Item.Settings()));
    public static final Item SILVER_BOW = registerItem("silver_bow", new SilverBow(new Item.Settings()));
    public static final Item THROWABLE_SILVER_TNT = registerItem("throwable_silver_tnt", new ThrowableSilverTntItem(new Item.Settings()));

    public static final Item SILVER_BLAZE_MACE = registerItem(
            "silver_blaze_mace",
            new SilverBlazeMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, SilverBlazeMaceItem.createToolComponent())
                            .attributeModifiers(SilverBlazeMaceItem.createAttributeModifiers())
            )
    );
    public static final Item SILVER_TEST_MACE = registerItem(
            "silver_test_mace",
            new SilverTestMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, SilverTestMaceItem.createToolComponent())
                            .attributeModifiers(SilverTestMaceItem.createAttributeModifiers())
            )
    );

    public static final Item SILVER_MACE = registerItem(
            "silver_mace",
            new SilverMaceItem(
                    new Item.Settings()
                            .rarity(Rarity.EPIC)
                            .maxDamage(500)
                            .component(DataComponentTypes.TOOL, SilverMaceItem.createToolComponent())
                            .attributeModifiers(SilverMaceItem.createAttributeModifiers())
            )
    );


    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Silvermod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Silvermod.LOGGER.info("Registering Mod Items for " + Silvermod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
           entries.add(SILVER_INGOT);
           entries.add(SILVER_NUGGET);
           entries.add(SILVER_BOW);
           entries.add(THROWABLE_SILVER_TNT);

        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
            entries.add(SILVER_MACE);
            entries.add(SILVER_BLAZE_MACE);
            entries.add(SILVER_TEST_MACE);

        });
    }
}
