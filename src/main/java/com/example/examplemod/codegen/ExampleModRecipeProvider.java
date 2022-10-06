package com.example.examplemod.codegen;

import com.example.examplemod.ExampleBlocks;
import com.example.examplemod.ExampleItems;
import net.minecraft.data.*;

import java.util.function.Consumer;

public class ExampleModRecipeProvider extends RecipeProvider {
    public ExampleModRecipeProvider(DataGenerator gen) {
        super(gen);
    }
    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(ExampleBlocks.TITANIUM_BLOCK.get())
                .define('#', ExampleItems.TITANIUM_INGOT.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .unlockedBy("has_titanium_ingot", has(ExampleItems.TITANIUM_INGOT.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(ExampleItems.TITANIUM_INGOT.get(), 9)
                .requires(ExampleBlocks.TITANIUM_BLOCK.get())
                .group("titanium_ingot")
                .unlockedBy("has_titanium_block", has(ExampleBlocks.TITANIUM_BLOCK.get()))
                .save(consumer, "examplemod:titanium_ingot_from_titanium_block");
    }
}
