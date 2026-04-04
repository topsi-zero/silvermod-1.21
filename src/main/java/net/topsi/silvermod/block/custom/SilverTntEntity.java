package net.topsi.silvermod.block.custom;

import java.util.Optional;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.Entity.MoveEffect;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.fluid.FluidState;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtHelper;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.TeleportTarget;
import net.minecraft.world.World;
import net.minecraft.world.World.ExplosionSourceType;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import net.topsi.silvermod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

public class SilverTntEntity extends Entity implements Ownable {
    private static final TrackedData<Integer> FUSE;
    private static final TrackedData<BlockState> BLOCK_STATE;
    private static final ExplosionBehavior TELEPORTED_EXPLOSION_BEHAVIOR;

    @Nullable
    private LivingEntity causingEntity;
    private boolean teleported;

    public SilverTntEntity(EntityType<? extends net.minecraft.entity.TntEntity> entityType, World world) {
        super(entityType, world);
        this.intersectionChecked = true;
    }

    public SilverTntEntity(World world, double x, double y, double z, @Nullable LivingEntity igniter) {
        this(EntityType.TNT, world);
        this.setPosition(x, y, z);
        double d = world.random.nextDouble() * (double)((float)Math.PI * 2F);
        this.setVelocity(-Math.sin(d) * 0.02, (double)1F, -Math.cos(d) * 0.02);
        this.setFuse(120);
        this.prevX = x;
        this.prevY = y;
        this.prevZ = z;
        this.causingEntity = igniter;
    }

    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(FUSE, 120);
        builder.add(BLOCK_STATE, Blocks.TNT.getDefaultState());
    }

    protected Entity.MoveEffect getMoveEffect() {
        return MoveEffect.NONE;
    }

    public boolean canHit() {
        return !this.isRemoved();
    }

    protected double getGravity() {
        return 0.04;
    }

    public void tick() {
        this.tickPortalTeleportation();
        this.applyGravity();
        this.move(MovementType.SELF, this.getVelocity());
        this.setVelocity(this.getVelocity().multiply(0.98));
        if (this.isOnGround()) {
            this.setVelocity(this.getVelocity().multiply(0.7, (double)-0.5F, 0.7));
        }

        int i = this.getFuse() - 1;
        this.setFuse(i);
        if (i <= 0) {
            this.discard();
            if (!this.getWorld().isClient) {
                this.explode();
            }
        } else {
            this.updateWaterState();
            if (this.getWorld().isClient) {
                this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY() + (double)0.5F, this.getZ(), (double)0.0F, (double)0.0F, (double)0.0F);
            }
        }

    }

    private void explode() {
        float f = 4.0F;
        ((ServerWorld)this.getWorld()).spawnParticles(
                ParticleTypes.SMOKE,
                this.getX(),
                this.getBodyY(0.5),
                this.getZ(),
                80000,
                9,
                9,
                9,
                1);
        ((ServerWorld)this.getWorld()).spawnParticles(
                ParticleTypes.CAMPFIRE_COSY_SMOKE,
                this.getX(),
                this.getBodyY(0.5),
                this.getZ(),
                50,
                9,
                9,
                9,
                0.02);

        this.getWorld().createExplosion(this, Explosion.createDamageSource(this.getWorld(), this), this.teleported ? TELEPORTED_EXPLOSION_BEHAVIOR : null, this.getX(), this.getBodyY((double)0.0625F), this.getZ(), 15.0F, true, ExplosionSourceType.TNT);
        this.getWorld().spawnEntity(
                new SilverTntEntity(
                        this.getWorld(),
                        this.getX() + 0.5,
                        this.getY(),
                        this.getZ() + 0.5,
                        null
                )
        );
    }

    protected void writeCustomDataToNbt(NbtCompound nbt) {
        nbt.putShort("fuse", (short)this.getFuse());
        nbt.put("block_state", NbtHelper.fromBlockState(this.getBlockState()));
    }

    protected void readCustomDataFromNbt(NbtCompound nbt) {
        this.setFuse(nbt.getShort("fuse"));
        if (nbt.contains("block_state", 10)) {
            this.setBlockState(NbtHelper.toBlockState(this.getWorld().createCommandRegistryWrapper(RegistryKeys.BLOCK), nbt.getCompound("block_state")));
        }

    }

    @Nullable
    public LivingEntity getOwner() {
        return this.causingEntity;
    }

    public void copyFrom(Entity original) {
        super.copyFrom(original);
        if (original instanceof net.minecraft.entity.TntEntity tntEntity) {
            this.causingEntity = tntEntity.getOwner();
        }

    }

    public void setFuse(int fuse) {
        this.dataTracker.set(FUSE, fuse);
    }

    public int getFuse() {
        return (Integer)this.dataTracker.get(FUSE);
    }

    public void setBlockState(BlockState state) {
        this.dataTracker.set(BLOCK_STATE, state);
    }

    public BlockState getBlockState() {
        return (BlockState)this.dataTracker.get(BLOCK_STATE);
    }

    private void setTeleported(boolean teleported) {
        this.teleported = teleported;
    }

    static {
        FUSE = DataTracker.registerData(net.topsi.silvermod.block.custom.SilverTntEntity.class, TrackedDataHandlerRegistry.INTEGER);
        BLOCK_STATE = DataTracker.registerData(net.topsi.silvermod.block.custom.SilverTntEntity.class, TrackedDataHandlerRegistry.BLOCK_STATE);
        TELEPORTED_EXPLOSION_BEHAVIOR = new ExplosionBehavior() {
            public boolean canDestroyBlock(Explosion explosion, BlockView world, BlockPos pos, BlockState state, float power) {
                return state.isOf(Blocks.NETHER_PORTAL) ? false : super.canDestroyBlock(explosion, world, pos, state, power);
            }

            public Optional<Float> getBlastResistance(Explosion explosion, BlockView world, BlockPos pos, BlockState blockState, FluidState fluidState) {
                return blockState.isOf(Blocks.NETHER_PORTAL) ? Optional.empty() : super.getBlastResistance(explosion, world, pos, blockState, fluidState);
            }
        };
    }
}
