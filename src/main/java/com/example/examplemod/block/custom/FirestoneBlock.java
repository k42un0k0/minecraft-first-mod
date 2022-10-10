package com.example.examplemod.block.custom;

import com.example.examplemod.item.custom.Firestone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class FirestoneBlock extends Block {
    public  FirestoneBlock(Block.Properties properties){
        super(properties);
    }

    @SuppressWarnings({"deprecation"})
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockRayTraceResult hit) {
        if(!world.isClientSide()) {
            if(hand == Hand.MAIN_HAND) {
                System.out.println("I right-clicked a FirestoneBlock. Called for the Main Hand!");
            }
            if(hand == Hand.OFF_HAND) {
                System.out.println("I right-clicked a FirestoneBlock. Called for the Off Hand!");
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }



    @SuppressWarnings("deprecation")
    @Override
    public void attack(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if(!world.isClientSide()) {
            System.out.println("I left-clicked a FirestoneBlock");
        }
        super.attack(state, world, pos, player);
    }

    @Override
    public void stepOn(World world, BlockPos pos, Entity entity) {
        Firestone.lightEntityOnFire(entity, 5);
        super.stepOn(world, pos, entity);
    }
}
