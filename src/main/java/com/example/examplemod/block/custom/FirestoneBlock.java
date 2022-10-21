package com.example.examplemod.block.custom;

import com.example.examplemod.item.custom.Firestone;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.command.impl.ParticleCommand;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particles.BlockParticleData;
import net.minecraft.particles.ParticleType;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import java.util.Random;

public class FirestoneBlock extends Block {
    public FirestoneBlock(Block.Properties properties) {
        super(properties);
    }

    @SuppressWarnings({"deprecation"})
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand,
                                BlockRayTraceResult hit) {
        if (!world.isClientSide()) {
            if (hand == Hand.MAIN_HAND) {
                System.out.println("I right-clicked a FirestoneBlock. Called for the Main Hand!");
            }
            if (hand == Hand.OFF_HAND) {
                System.out.println("I right-clicked a FirestoneBlock. Called for the Off Hand!");
            }
        }
        return super.use(state, world, pos, player, hand, hit);
    }

    @Override
    public void animateTick(BlockState state, World world, BlockPos pos, Random random) {
        float chance = 0.35f;
        if (chance < random.nextFloat()) {
            world.addParticle(ParticleTypes.FLAME,
                    pos.getX() + random.nextDouble(),
                    pos.getY() + 0.50,
                    pos.getZ() + random.nextDouble(), 0d, 0.05d, 0d);

            world.addParticle(new BlockParticleData(ParticleTypes.BLOCK, state),
                    pos.getX() + random.nextDouble(),
                    pos.getY() + 0.50,
                    pos.getZ() + random.nextDouble(), 0d, 0.05d, 0d);
        }
    }

    @SuppressWarnings("deprecation")
    @Override
    public void attack(BlockState state, World world, BlockPos pos, PlayerEntity player) {
        if (!world.isClientSide()) {
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
