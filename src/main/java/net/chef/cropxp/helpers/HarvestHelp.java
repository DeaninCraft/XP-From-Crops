package net.chef.cropxp.helpers;

import net.minecraft.block.BlockState;
import net.minecraft.state.property.Property;

public class HarvestHelp {
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
