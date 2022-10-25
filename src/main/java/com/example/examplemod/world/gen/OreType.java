package com.example.examplemod.world.gen;

import com.example.examplemod.block.ExampleBlocks;
import net.minecraft.block.Block;
import net.minecraftforge.common.util.Lazy;

public enum OreType {

    AMETHYST(Lazy.of(ExampleBlocks.AMETHYST_ORE), 8, 25, 50, 3),
    FIRESTONE(Lazy.of(ExampleBlocks.FIRESTONE_BLOCK), 3, 10, 80, 5);

    private final Lazy<Block> block;
    private final int maxVeinSize;
    private final int minHeight;
    private final int maxHeight;
    private final int veinsPerChunk;

    OreType(Lazy<Block> block, int maxVeinSize, int minHeight, int maxHeight, int veinsPerChunk) {
        this.block = block;
        this.maxVeinSize = maxVeinSize;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.veinsPerChunk = veinsPerChunk;
    }

    public int getVeinsPerChunk() {
        return veinsPerChunk;
    }

    public Lazy<Block> getBlock() {
        return block;
    }

    public int getMaxVeinSize() {
        return maxVeinSize;
    }

    public int getMinHeight() {
        return minHeight;
    }

    public int getMaxHeight() {
        return maxHeight;
    }

    public static OreType get(Block block) {
        for (OreType ore : values()) {
            if(block == ore.block) {
                return ore;
            }
        }
        return null;
    }
}
