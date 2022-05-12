package net.chef.cropxp.events;

import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.CropBlock;
import net.minecraft.block.NetherWartBlock;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerInteractionManager;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import static net.chef.cropxp.helpers.HarvestHelp.*;

public class RightClickEvent {

    private static final MinecraftClient client = MinecraftClient.getInstance();

    public RightClickEvent() {
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            if (hitResult.getType() == HitResult.Type.BLOCK) {
                BlockPos pos = ((BlockHitResult) hitResult).getBlockPos();
                ItemStack mainStack = player.getMainHandStack();
                ItemStack offhandStack = player.getOffHandStack();

                if (hand == Hand.MAIN_HAND && !mainStack.isEmpty()) {
                    this.replantCrop(mainStack, pos, world);
                } else if (hand == Hand.OFF_HAND && !offhandStack.isEmpty()) {
                    this.replantCrop(offhandStack, pos, world);
                }
            }
            return ActionResult.PASS;
        });
    }

    private void replantCrop(ItemStack stack, BlockPos pos, World world) {
        BlockState state = world.getBlockState(pos);
        ClientPlayerInteractionManager playerInteractionManager = client.interactionManager;
        if (stack.getItem() instanceof BlockItem) {
            if (state.isIn(BlockTags.CROPS)) {
                if(((BlockItem) stack.getItem()).getBlock() instanceof CropBlock || ((BlockItem) stack.getItem()).getBlock() instanceof NetherWartBlock) {
                    if (getCropAge(state) == getMaxCropAge(state)) {
                        playerInteractionManager.attackBlock(pos, Direction.DOWN);
                    }
                }
            }
        }
    }
}
