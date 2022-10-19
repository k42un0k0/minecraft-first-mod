package com.example.examplemod.tileentity;

import com.example.examplemod.item.ExampleItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class LightningChannelerTile extends TileEntity {
    private final ItemStackHandler itemHandler = createHandler();

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(blockState, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv",itemHandler.serializeNBT());
        return super.save(compound);
    }

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(()->itemHandler);

    public LightningChannelerTile(TileEntityType<?> tileEntityType){
        super(tileEntityType);
    }

    public LightningChannelerTile(){
        super(ExampleTileEntities.LIGHTNING_CHANNELER_TILE.get());
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2){
            @Override
            protected void onContentsChanged(int slot) {

            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                switch (slot){
                    case 0: return stack.getItem() == Items.GLASS_PANE;
                    case 1: return stack.getItem()== ExampleItems.AMETHYST.get()||stack.getItem() == ExampleItems.FIRESTONE.get();
                    default:
                        return false;
                }
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if(!isItemValid(slot,stack)){
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    public void lightningHasStruck(){
        boolean hasFocusInFirstSlot = this.itemHandler.getStackInSlot(0).getCount() >0&& this.itemHandler.getStackInSlot(0).getItem()==Items.GLASS_PANE;
        boolean hasAmethystInSecondSlot = this.itemHandler.getStackInSlot(1).getCount() >0&& this.itemHandler.getStackInSlot(1).getItem()==ExampleItems.AMETHYST.get();

        if(hasFocusInFirstSlot && hasAmethystInSecondSlot) {
            this.itemHandler.getStackInSlot(0).shrink(1);
            this.itemHandler.getStackInSlot(1).shrink(1);

            this.itemHandler.insertItem(1, new ItemStack(ExampleItems.FIRESTONE.get()), false);
        }
    }
}
