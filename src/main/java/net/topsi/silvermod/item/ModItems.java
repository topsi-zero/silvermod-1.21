package net.topsi.silvermod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.topsi.silvermod.Silvermod;
import net.topsi.silvermod.item.custom.SilverTntArrowItem;

public class ModItems {

    public static final Item SILVER_INGOT = registerItem("silver_ingot", new Item(new Item.Settings()));
    public static final Item SILVER_NUGGET = registerItem("silver_nugget", new Item(new Item.Settings()));
    public static final Item SILVER_TNT_ARROW = registerItem("silver_tnt_arrow", new SilverTntArrowItem(new Item.Settings()));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Silvermod.MOD_ID, name), item);
    }

    public static void registerModItems() {
        Silvermod.LOGGER.info("Registering Mod Items for " + Silvermod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
           entries.add(SILVER_INGOT);
           entries.add(SILVER_NUGGET);
        });
    }
}
