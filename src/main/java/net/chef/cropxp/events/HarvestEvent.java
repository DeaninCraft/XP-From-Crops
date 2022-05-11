package net.chef.cropxp.events;

import net.chef.cropxp.XpFromCrops;
import net.chef.cropxp.config.ConfigRegister;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.property.Property;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.Arrays;
import java.util.Objects;

public class HarvestEvent {
    public HarvestEvent() {
        PlayerBlockBreakEvents.AFTER.register((world, player, pos, state, blockEntity) -> {
            if (world.isClient) {
                return;
            }
            try {
                ServerWorld serverWorld = Objects.requireNonNull(player.getServer()).getWorld(world.getRegistryKey());
                handleHarvest(serverWorld, state, pos);
            }
            catch (Exception e) {
                XpFromCrops.LOGGER.error(e.getMessage());
            }
        });
    }

    public void handleHarvest(ServerWorld world, BlockState state, BlockPos pos) {
        Vec3d vec = new Vec3d(pos.getX(), pos.getY(), pos.getZ());
        boolean isAllowed = Arrays.toString(ConfigRegister.CONFIG.cropAllowList).contains(state.getBlock().toString());
        boolean isDenied = Arrays.toString(ConfigRegister.CONFIG.cropDenyList).contains(state.getBlock().toString());
        boolean giveXp = (state.isIn(BlockTags.CROPS) || isAllowed) && !isDenied && (getCropAge(state) == getMaxCropAge(state));
        if (giveXp && world.random.nextInt(100) + 1 <= ConfigRegister.CONFIG.chance) {
            ExperienceOrbEntity.spawn(world, vec, ConfigRegister.CONFIG.amount);
        }
    }
    public static int getCropAge(BlockState state) {
        Property<?> AGE = state.getProperties().stream().filter(property -> property.getName().equals("age")).findFirst().orElseThrow();
        return Integer.parseInt(state.get(AGE).toString());
    }

    public static int getMaxCropAge(BlockState state) {
        for (Property property:state.getProperties()) {
            if(property.getName().equals("age")) {
                Object[] ages = property.getValues().toArray();
                double maxValue = -1;
                int indexOfMaxValue = -1;
                for(int i = 0; i < ages.length; i++) {
                    if(Integer.parseInt(ages[i].toString()) > maxValue) {
                        indexOfMaxValue = i;
                    }
                }
                return Integer.parseInt(ages[indexOfMaxValue].toString());
            }
        }
        return 0;
    }
}
