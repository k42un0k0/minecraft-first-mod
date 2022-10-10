package com.example.examplemod.item.custom;

import com.example.examplemod.ExampleMod;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class Firestone extends Item {
    public Firestone(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onItemUseFirst(ItemStack stack, ItemUseContext context) {
        World world = context.getLevel();
        if (!world.isClientSide()) {

            PlayerEntity player = context.getPlayer();
            BlockState clickedBlock = world.getBlockState((context.getClickedPos()));
            rightClickOnCertainBlockState(clickedBlock, context, player);
            context.getItemInHand().hurtAndBreak(1, player, (player1) -> {
                player1.broadcastBreakEvent(context.getHand());
            });
        }
        return super.onItemUseFirst(stack, context);
    }

    private void rightClickOnCertainBlockState(BlockState clickedBlock, ItemUseContext context, PlayerEntity player) {
        if (random.nextFloat() > 0.5f) {
            lightEntityOnFire(player, 6);
        } else if (!player.isOnFire() && blockIsValidForResistance(clickedBlock)) {
            gainFireResistanceAndDestroyBlock(player, context.getLevel(), context.getClickedPos());
        } else {
            lightGroundOnFire(context);
        }
    }

    private boolean blockIsValidForResistance(BlockState clickedBlock) {
        return clickedBlock.getBlock() == Blocks.OBSIDIAN;
    }

    public static void lightEntityOnFire(Entity entity, int second) {
        entity.setSecondsOnFire(second);
    }

    private void gainFireResistanceAndDestroyBlock(PlayerEntity player, World world, BlockPos pos) {
        gainFireResistance(player);
        world.destroyBlock(pos, false);
    }

    public static void gainFireResistance(PlayerEntity player) {
        player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
    }

    public static void lightGroundOnFire(ItemUseContext context) {
        PlayerEntity playerentity = context.getPlayer();
        World world = context.getLevel();
        BlockPos blockpos = context.getClickedPos().relative(context.getClickedFace());
        if (AbstractFireBlock.canBePlacedAt(world, blockpos, context.getHorizontalDirection())) {
            world.playSound(playerentity, context.getClickedPos(), SoundEvents.FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            BlockState blockstate = AbstractFireBlock.getState(world, blockpos);
            world.setBlock(blockpos, blockstate, 11);
        }
    }
}
