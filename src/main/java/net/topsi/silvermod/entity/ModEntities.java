package net.topsi.silvermod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.topsi.silvermod.Silvermod;
import net.topsi.silvermod.entity.custom.SilverTntArrowEntity;

public class ModEntities {

    public static final EntityType<SilverTntArrowEntity> SILVER_TNT_ARROW =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of("silvermod", "silver_tnt_arrow"),
                    EntityType.Builder
                            .<SilverTntArrowEntity>create(SilverTntArrowEntity::new, SpawnGroup.MISC)
                            .dimensions(1F, 1F)
                            .build()
            );

    public static void registerModEntities() {
        // optional log
        System.out.println("Registering Mod Entities for " + Silvermod.MOD_ID);
    }
}