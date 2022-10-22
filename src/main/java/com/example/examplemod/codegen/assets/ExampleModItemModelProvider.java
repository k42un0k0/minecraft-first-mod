package com.example.examplemod.codegen.assets;

import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.Objects;

public class ExampleModItemModelProvider extends ItemModelProvider {
    public ExampleModItemModelProvider(DataGenerator gen, String modId, ExistingFileHelper existingFileHelper) {
        super(gen, modId, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        simpleItem(ExampleItems.TITANIUM_INGOT.get());
        simpleItem(ExampleItems.AMETHYST.get());
        simpleItem(ExampleItems.FIRESTONE.get());
        simpleItem(ExampleItems.AMETHYST_DOOR.get());
        simpleItem(ExampleItems.AMETHYST_SWORD.get());
        simpleItem(ExampleItems.AMETHYST_AXE.get());
        simpleItem(ExampleItems.AMETHYST_PICKAXE.get());
        simpleItem(ExampleItems.AMETHYST_HOE.get());
        simpleItem(ExampleItems.AMETHYST_SHOVEL.get());
        simpleItem(ExampleItems.AMETHYST_HELMET.get());
        simpleItem(ExampleItems.AMETHYST_CHESTPLATE.get());
        simpleItem(ExampleItems.AMETHYST_LEGGINGS.get());
        simpleItem(ExampleItems.AMETHYST_BOOTS.get());
        simpleItem(ExampleItems.OATS.get());
        simpleItem(ExampleItems.AMETHYST_HORSE_ARMOR.get());
        simpleItem(ExampleItems.OIL_BUCKET.get());
        pane(ExampleItems.AMETHYST_PANE.get(), ExampleBlocks.AMETHYST_BLOCK.get());
    }

    private void pane(Item item, Block block){
        ResourceLocation name = Objects.requireNonNull(item.getRegistryName());
        singleTexture(name.getPath(), mcLoc(folder + "/generated"), "layer0", new ResourceLocation(block.getRegistryName().getNamespace(),  "block/" + block.getRegistryName().getPath()));

    }
    private void simpleItem(Item item) {
        ResourceLocation name = Objects.requireNonNull(item.getRegistryName());
        singleTexture(name.getPath(), mcLoc(folder + "/generated"), "layer0", new ResourceLocation(name.getNamespace(), folder + "/" + name.getPath()));
    }

}
