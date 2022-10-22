package com.example.examplemod.block.custom;

import com.example.examplemod.tileentity.ExampleSignTileEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.StandingSignBlock;
import net.minecraft.block.WallSignBlock;
import net.minecraft.block.WoodType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;

import javax.annotation.Nullable;

public class ExampleStandingSignBlock extends StandingSignBlock {
    public ExampleStandingSignBlock(Properties p_i225766_1_, WoodType p_i225766_2_) {
        super(p_i225766_1_, p_i225766_2_);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new ExampleSignTileEntity();
    }
}
