package com.example.examplemod

import net.minecraft.data.DataGenerator
import net.minecraft.data.RecipeProvider
import net.minecraft.data.ShapelessRecipeBuilder

import net.minecraft.data.ShapedRecipeBuilder

import net.minecraft.data.IFinishedRecipe
import java.util.function.Consumer


class ExampleModRecipeProvider(gen: DataGenerator) : RecipeProvider(gen) {
    override fun buildShapelessRecipes(consumer: Consumer<IFinishedRecipe>) {
        ShapedRecipeBuilder.shaped(ExampleBlocks.TITANIUM_BLOCK.get())
            .define('#', ExampleItems.TITANIUM_INGOT.get())
            .pattern("###")
            .pattern("###")
            .pattern("###")
            .unlockedBy("has_titanium_ingot", has(ExampleItems.TITANIUM_INGOT.get()))
            .save(consumer)
        ShapelessRecipeBuilder.shapeless(ExampleItems.TITANIUM_INGOT.get(), 9)
            .requires(ExampleBlocks.TITANIUM_BLOCK.get())
            .group("titanium_ingot")
            .unlockedBy("has_titanium_block", has(ExampleBlocks.TITANIUM_BLOCK.get()))
            .save(consumer, "examplemod:titanium_ingot_from_titanium_block")
    }

}
