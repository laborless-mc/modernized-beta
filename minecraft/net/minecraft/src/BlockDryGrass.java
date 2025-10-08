package net.minecraft.src;

import java.util.Random;

public class BlockDryGrass extends BlockFlower {
    protected BlockDryGrass(int i1, int i2) {
        super(i1, i2);
        float f3 = 0.4F;
        this.setBlockBounds(0.5F - f3, 0.0F, 0.5F - f3, 0.5F + f3, 0.8F, 0.5F + f3);
    }

    public boolean canPlaceBlockAt(World world1, int i2, int i3, int i4) {
        if(world1.getBlockId(i2, i3 - 1, i4) == Block.sand.blockID) { return true; }
        return false;
    }

    public boolean canBlockStay(World world1, int i2, int i3, int i4) {
        if(world1.getBlockId(i2, i3 - 1, i4) == Block.sand.blockID) { return true; }
        return false;
    }

    public int quantityDropped(Random random1) {
        return 0;
    }
}
