package com.example.examplemod.codegen;

import com.example.examplemod.item.ExampleItems;
import com.example.examplemod.util.ExampleTags;
import net.minecraft.block.Blocks;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;


public class ExampleModItemTagsProvider extends ItemTagsProvider {


    public ExampleModItemTagsProvider(DataGenerator p_i232552_1_, BlockTagsProvider p_i232552_2_, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_i232552_1_, p_i232552_2_, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
        this.tag(ExampleTags.Items.AMETHYST)
                .add(ExampleItems.AMETHYST.get());
    }
}
