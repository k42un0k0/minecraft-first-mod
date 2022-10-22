package com.example.examplemod.tileentity;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.data.recipes.ExampleRecipeTypes;
import com.example.examplemod.data.recipes.LightningChannelerRecipe;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Optional;

public class LightningChannelerTile extends TileEntity implements ITickableTileEntity {
    private final ItemStackHandler itemHandler = createHandler();

    @Override
    public void load(BlockState blockState, CompoundNBT nbt) {
        itemHandler.deserializeNBT(nbt.getCompound("inv"));
        super.load(blockState, nbt);
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.put("inv", itemHandler.serializeNBT());
        return super.save(compound);
    }

    private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

    public LightningChannelerTile(TileEntityType<?> tileEntityType) {
        super(tileEntityType);
    }

    public LightningChannelerTile() {
        this(ExampleTileEntities.LIGHTNING_CHANNELER_TILE.get());
    }

    private ItemStackHandler createHandler() {
        return new ItemStackHandler(2) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }

            @Override
            public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                return true;
            }

            @Override
            public int getSlotLimit(int slot) {
                return 1;
            }

            @Nonnull
            @Override
            public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                if (!isItemValid(slot, stack)) {
                    return stack;
                }
                return super.insertItem(slot, stack, simulate);
            }
        };
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return handler.cast();
        }
        return super.getCapability(cap, side);
    }

    private void strikeLightning() {
        if (!this.level.isClientSide()) {
            EntityType.LIGHTNING_BOLT.spawn((ServerWorld) level, null, null, getBlockPos(), SpawnReason.TRIGGERED,
                    true, true);
        }
    }

    public void craft() {
        Inventory inv = new Inventory(itemHandler.getSlots());
        for (int i = 0; i < itemHandler.getSlots(); i++) {
            inv.setItem(i, itemHandler.getStackInSlot(i));
        }

        Optional<LightningChannelerRecipe> recipe = level.getRecipeManager()
                .getRecipeFor(ExampleRecipeTypes.LIGHTNING_RECIPE, inv, level);
        recipe.ifPresent(iRecipe -> {
            ItemStack output = iRecipe.getResultItem();

            if (iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.CLEAR) &&
                    !level.isRaining()) {
                craftTheItem(output);
            }

            if (iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.RAIN) &&
                    level.isRaining()) {
                craftTheItem(output);
            }

            if (iRecipe.getWeather().equals(LightningChannelerRecipe.Weather.THUNDERING) &&
                    level.isThundering()) {
                strikeLightning();
                craftTheItem(output);
            }

            setChanged();
        });
    }

    private void craftTheItem(ItemStack output) {
        itemHandler.extractItem(0, 1, false);
        itemHandler.extractItem(1, 1, false);
        itemHandler.insertItem(1, output, false);
    }

    @Override
    public void tick() {
        if (level.isClientSide())
            return;

        craft();
    }
}
