package com.example.examplemod.item.custom;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.util.ExampleTags;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypePreset;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

public class Firestone extends Item implements ICurioItem {
    public Firestone(Properties properties) {
        super(properties);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        PlayerEntity player = (PlayerEntity) livingEntity;
        if (!player.level.isClientSide()) {
            boolean hasPlayerFireResistance = !Objects.equals(player.getEffect(Effects.FIRE_RESISTANCE), null);
            if (!hasPlayerFireResistance) {
                player.addEffect(new EffectInstance(Effects.FIRE_RESISTANCE, 200));
                if (random.nextFloat() > 0.6f) {
                    stack.hurtAndBreak(1, player,
                            p -> CuriosApi.getCuriosHelper().onBrokenCurio(SlotTypePreset.CHARM.getIdentifier(),
                                    index, p));
                }
            }
        }
        ICurioItem.super.curioTick(identifier, index, livingEntity, stack);
    }

    @Override
    public ActionResultType useOn(ItemUseContext context) {
        World world = context.getLevel();
        if (!world.isClientSide()) {
            ExampleMod.LOGGER.info("USE ON");
            PlayerEntity player = context.getPlayer();
            BlockState clickedBlock = world.getBlockState((context.getClickedPos()));
            rightClickOnCertainBlockState(clickedBlock, context, player);
            context.getItemInHand().hurtAndBreak(1, player, (player1) -> {
                player1.broadcastBreakEvent(context.getHand());
            });
        }
        return super.useOn(context);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip,
                                ITooltipFlag flag) {
        if (Screen.hasShiftDown()) {
            tooltip.add(new TranslationTextComponent("tooltip.examplemod.firestone_shift"));
        } else {
            tooltip.add(new TranslationTextComponent("tooltip.examplemod.firestone"));
        }
        super.appendHoverText(stack, world, tooltip, flag);
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
        return clickedBlock.is(ExampleTags.Blocks.FIRESTONE_CLICKABLE_BLOCKS);
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
            world.playSound(playerentity, context.getClickedPos(), SoundEvents.FLINTANDSTEEL_USE,
                    SoundCategory.BLOCKS, 1.0F, random.nextFloat() * 0.4F + 0.8F);
            BlockState blockstate = AbstractFireBlock.getState(world, blockpos);
            world.setBlock(blockpos, blockstate, 11);
        }
    }

    @Override
    public ItemStack getContainerItem(ItemStack itemStack) {
        ItemStack container = itemStack.copy();
        if (container.hurt(1, random, null)) {
            return ItemStack.EMPTY;
        } else {
            return container;
        }
    }

    @Override
    public boolean hasContainerItem(ItemStack stack) {
        return true;
    }
}
