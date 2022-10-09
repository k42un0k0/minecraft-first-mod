package com.example.examplemod.codegen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.data.*;
import net.minecraft.item.Item;

import java.util.Objects;
import java.util.function.Consumer;

public class ExampleModRecipeProvider extends RecipeProvider {
    public ExampleModRecipeProvider(DataGenerator gen) {
        super(gen);
    }
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        addReversibleCraft(consumer,ExampleItems.TITANIUM_BLOCK.get(),ExampleItems.TITANIUM_INGOT.get());
        addReversibleCraft(consumer,ExampleItems.AMETHYST_BLOCK.get(),ExampleItems.AMETHYST.get());

    }

    private void addReversibleCraft(Consumer<IFinishedRecipe> consumer, Item block, Item item){
        ShapedRecipeBuilder.shaped(block)
                .define('#', item)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_"+ Objects.requireNonNull(item.getRegistryName()).getPath(), has(ExampleItems.TITANIUM_INGOT.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(item, 9)
                .requires(block)
                .group(item.getRegistryName().getPath())
                .unlockedBy("has_"+ Objects.requireNonNull(block.getRegistryName()).getPath(), has(ExampleBlocks.TITANIUM_BLOCK.get()))
                .save(consumer, ExampleMod.MOD_ID +":"+ Objects.requireNonNull(item.getRegistryName()).getPath() +"_from_" +Objects.requireNonNull(block.getRegistryName()).getPath() );

    }
}
