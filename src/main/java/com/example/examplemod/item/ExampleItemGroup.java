package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ExampleItemGroup {
    public static final ItemGroup TAB = new ItemGroup(ExampleMod.MOD_ID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ExampleItems.TITANIUM_INGOT.get());
        }
    };
}
