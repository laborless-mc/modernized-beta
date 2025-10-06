package net.minecraft.src;

import java.util.Random;

public class BiomeGenSwamp extends BiomeGenBase {
    public WorldGenerator getRandomWorldGenForTrees(Random random1) {
        return new WorldGenSwamp();
    }
}
