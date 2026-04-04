package net.topsi.silvermod.entity.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.EntityEffectParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.world.World;


import org.jetbrains.annotations.Nullable;

public class SilverTntArrowEntity extends PersistentProjectileEntity {

    private static final TrackedData<Integer> COLOR =
            DataTracker.registerData(SilverTntArrowEntity.class, TrackedDataHandlerRegistry.INTEGER);

    private static final byte PARTICLE_EFFECT_STATUS = 0;

    public SilverTntArrowEntity(EntityType<? extends SilverTntArrowEntity> entityType, World world) {
        super(entityType, world);
    }

    public SilverTntArrowEntity(EntityType<? extends SilverTntArrowEntity> entityType,
                                World world, double x, double y, double z,
                                ItemStack stack, @Nullable ItemStack shotFrom) {
        super(entityType, x, y, z, world, stack, shotFrom);
        this.setColor(0xFF0000); // default red
    }

    public SilverTntArrowEntity(EntityType<? extends SilverTntArrowEntity> entityType,
                                World world, LivingEntity owner,
                                ItemStack stack, @Nullable ItemStack shotFrom) {
        super(entityType, owner, world, stack, shotFrom);
        this.setColor(0xFF0000); // default red
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        builder.add(COLOR, -1);
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            if (this.inGround) {
                if (this.inGroundTime % 5 == 0) {
                    spawnParticles(1);
                }
            } else {
                spawnParticles(2);
            }
        }
    }

    private void spawnParticles(int amount) {
        int color = this.getColor();
        if (color == -1) return;

        double r = (color >> 16 & 255) / 255.0;
        double g = (color >> 8 & 255) / 255.0;
        double b = (color & 255) / 255.0;

        for (int i = 0; i < amount; i++) {
            this.getWorld().addParticle(
                    EntityEffectParticleEffect.create(ParticleTypes.ENTITY_EFFECT, (float) r, (float) g, (float) b),
                    this.getParticleX(0.5),
                    this.getRandomBodyY(),
                    this.getParticleZ(0.5),
                    0, 0, 0
            );
        }
    }

    public int getColor() {
        return this.dataTracker.get(COLOR);
    }

    public void setColor(int color) {
        this.dataTracker.set(COLOR, color);
    }

    @Override
    protected void onHit(LivingEntity target) {
        super.onHit(target);
        explode();
    }

    @Override
    protected void onBlockHit(BlockHitResult blockHitResult) {
        super.onBlockHit(blockHitResult);
        explode();
    }

    private void explode() {
        if (!this.getWorld().isClient) {
            this.getWorld().createExplosion(
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    9.0F, // TNT strength
                    World.ExplosionSourceType.TNT
            );
            this.discard();
        }
    }

    @Override
    protected ItemStack getDefaultItemStack() {
        return new ItemStack(Items.ARROW);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == PARTICLE_EFFECT_STATUS) {
            spawnParticles(20);
        } else {
            super.handleStatus(status);
        }
    }
}