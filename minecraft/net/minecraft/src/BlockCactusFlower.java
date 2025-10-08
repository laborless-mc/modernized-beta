package net.minecraft.src;

public class BlockCactusFlower extends BlockFlower {
    protected BlockCactusFlower(int i1, int i2) {
        super(i1, i2);
    }

    public boolean canPlaceBlockAt(World world1, int i2, int i3, int i4) {
        if(world1.getBlockId(i2, i3 - 1, i4) == Block.cactus.blockID) { return true; }
        return super.canPlaceBlockAt(world1, i2, i3, i4) && this.canThisPlantGrowOnThisBlockID(world1.getBlockId(i2, i3 - 1, i4));
    }

    public boolean canBlockStay(World world1, int i2, int i3, int i4) {
        if(world1.getBlockId(i2, i3 - 1, i4) == Block.cactus.blockID) { return true; }
        return (world1.getFullBlockLightValue(i2, i3, i4) >= 8 || world1.canBlockSeeTheSky(i2, i3, i4)) && this.canThisPlantGrowOnThisBlockID(world1.getBlockId(i2, i3 - 1, i4));
    }
}
