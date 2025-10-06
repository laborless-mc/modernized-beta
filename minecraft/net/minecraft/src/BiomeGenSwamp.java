package net.minecraft.src;

import java.util.Random;

public class BiomeGenSwamp extends BiomeGenBase {
    public WorldGenerator getRandomWorldGenForTrees(Random random1) {
        if(random1.nextInt(3) == 0) { return new WorldGenBigMushroom(-1); }
        return new WorldGenSwamp();
    }
}
