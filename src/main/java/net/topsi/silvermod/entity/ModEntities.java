package net.topsi.silvermod.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.topsi.silvermod.Silvermod;
import net.topsi.silvermod.entity.custom.SilverTntArrowEntity;
import net.topsi.silvermod.entity.custom.SilverTntEntity;
import net.topsi.silvermod.entity.custom.ThrowableSilverTntEntity;

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

    public static final EntityType<ThrowableSilverTntEntity> THROWABLE_SILVER_TNT =
            Registry.register(
                    Registries.ENTITY_TYPE,
                    Identifier.of(Silvermod.MOD_ID, "throwable_silver_tnt"),
                    EntityType.Builder.<ThrowableSilverTntEntity>create(ThrowableSilverTntEntity::new, SpawnGroup.MISC)
                            .dimensions(0.25F, 0.25F)
                            .build()
            );


    public static void registerModEntities() {
        // optional log
        System.out.println("Registering Mod Entities for " + Silvermod.MOD_ID);
    }
}