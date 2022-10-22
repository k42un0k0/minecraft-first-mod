package com.example.examplemod.codegen.data.advancements;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ExampleBlocks;
import com.example.examplemod.item.ExampleItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.*;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;

import java.util.function.Consumer;

public class ExampleAdvancements implements Consumer<Consumer<Advancement>> {
    public static String modKey(String... keys) {
        return ExampleMod.MOD_ID + ":" + String.join("/", keys);

    }

    public void accept(Consumer<Advancement> consumer) {
        Advancement advancement = Advancement.Builder.advancement().display(ExampleBlocks.AMETHYST_ORE.get(),
                new StringTextComponent("nice found!!"), new StringTextComponent(
                        "found amethyst ore"), new ResourceLocation("textures/gui/advancements/backgrounds/end.png"),
                FrameType.TASK, true, true, false).addCriterion("found_amethyst_ore",
                InventoryChangeTrigger.Instance.hasItems(ExampleItems.AMETHYST_ORE.get())).save(consumer, modKey(
                "root"));
        Advancement advancement1 = Advancement.Builder.advancement().parent(advancement).display(Blocks.ZOMBIE_HEAD,
                new TranslationTextComponent("r u God?"), new TranslationTextComponent(
                        "killed zombie"), (ResourceLocation) null, FrameType.TASK, true,
                true, false).addCriterion("killed_zombie",
                KilledTrigger.Instance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.ZOMBIE))).save(consumer, modKey("kill_zombie"));
    }
}