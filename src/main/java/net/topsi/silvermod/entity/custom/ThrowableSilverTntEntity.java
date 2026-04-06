package net.topsi.silvermod.entity.custom;

import net.minecraft.entity.EntityStatuses;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.topsi.silvermod.entity.ModEntities;
import net.topsi.silvermod.item.ModItems;

public class ThrowableSilverTntEntity extends ThrownItemEntity {
    public ThrowableSilverTntEntity(EntityType<? extends ThrowableSilverTntEntity> entityType, World world) {
        super(entityType, world);
    }

    public ThrowableSilverTntEntity(World world, LivingEntity owner) {
        super(ModEntities.THROWABLE_SILVER_TNT, owner, world);
    }

    public ThrowableSilverTntEntity(World world, double x, double y, double z) {
        super(ModEntities.THROWABLE_SILVER_TNT, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.THROWABLE_SILVER_TNT;
    }



    private ParticleEffect getParticleParameters() {
        ItemStack itemStack = this.getStack();
        return (ParticleEffect)(!itemStack.isEmpty() && !itemStack.isOf(this.getDefaultItem())
                ? new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack)
                : ParticleTypes.ITEM_SNOWBALL);
    }

    @Override
    public void handleStatus(byte status) {
        if (status == EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES) {
            ParticleEffect particleEffect = this.getParticleParameters();

            for (int i = 0; i < 8; i++) {
                this.getWorld().addParticle(particleEffect, this.getX(), this.getY(), this.getZ(), 0.0, 0.0, 0.0);
            }
        }
    }

    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        this.getWorld().createExplosion(
                this.getOwner(),
                this.getX(),
                this.getY(),
                this.getZ(),
                3.0F,
                World.ExplosionSourceType.TNT
        );
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);
        if (!this.getWorld().isClient) {
            this.getWorld().sendEntityStatus(this, EntityStatuses.PLAY_DEATH_SOUND_OR_ADD_PROJECTILE_HIT_PARTICLES);
            this.discard();
            this.getWorld().createExplosion(
                    this.getOwner(),
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    3.0F,
                    World.ExplosionSourceType.TNT);
        }
    }
}

