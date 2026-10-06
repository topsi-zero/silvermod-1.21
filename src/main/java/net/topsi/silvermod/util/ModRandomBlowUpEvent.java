package net.topsi.silvermod.util;

import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ModRandomBlowUpEvent implements PlayerBlockBreakEvents.After{


    @Override
    public void afterBlockBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity) {
        player.sendMessage(Text.literal("Hello World"), false);

        ItemStack mainHandStack = player.getMainHandStack();

        if (mainHandStack.getItem() ==  Items.WOODEN_HOE && player instanceof ServerPlayerEntity serverPlayer) {
            player.sendMessage(Text.literal("Why did you do that? Wooden Hoes are useless! You are USELESS"), false);

            ServerWorld serverWorld = (ServerWorld) world;

            serverWorld.setTimeOfDay(13000);
            serverWorld.setWeather(0, 1000, true, true);
            serverWorld.setThunderGradient(1);
            serverWorld.spawnEntity(new net.minecraft.entity.LightningEntity(net.minecraft.entity.EntityType.LIGHTNING_BOLT, serverWorld) {{ setPosition( serverPlayer.getPos()); }});
            serverPlayer.kill();

            serverPlayer.setCustomName(Text.literal("USELESS"));
        }
    }
}
