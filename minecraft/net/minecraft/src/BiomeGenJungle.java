package net.minecraft.src;

import java.util.Random;

public class BiomeGenJungle extends BiomeGenBase {
    public WorldGenerator getRandomWorldGenForTrees(Random random1) {
        return (WorldGenerator)(random1.nextInt(10) == 0 ? new WorldGenBigTree() : (random1.nextInt(2) == 0 ? new WorldGenShrub(3, 0) : (random1.nextInt(3) == 0 ? new WorldGenHugeTrees(false, 10 + random1.nextInt(20), 0, 0) : new WorldGenTrees())));
    }
}
