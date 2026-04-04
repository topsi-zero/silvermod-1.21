package net.topsi.silvermod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ProjectileItem;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Position;
import net.minecraft.world.World;
import net.topsi.silvermod.entity.ModEntities;
import net.topsi.silvermod.entity.custom.SilverTntArrowEntity;

import org.jetbrains.annotations.Nullable;

public class SilverTntArrowItem extends ArrowItem implements ProjectileItem {

    public SilverTntArrowItem(Item.Settings settings) {
        super(settings);
    }

    public PersistentProjectileEntity createArrow(World world, ItemStack stack,
                                                  LivingEntity shooter,
                                                  @Nullable ItemStack shotFrom) {

        SilverTntArrowEntity arrow = new SilverTntArrowEntity(
                ModEntities.SILVER_TNT_ARROW,
                world,
                shooter,
                stack.copyWithCount(1),
                shotFrom
        );

        arrow.setColor(0xFF0000);

        return arrow;
    }

    @Override
    public ProjectileEntity createEntity(World world, Position pos, ItemStack stack, Direction direction) {
        return null;
    }
}