package com.example.examplemod.screen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.container.LightningChannelerContainer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
public class LightningChannelerScreen extends ContainerScreen<LightningChannelerContainer> {
    private final ResourceLocation GUI = new ResourceLocation(ExampleMod.MOD_ID,
            "textures/gui/lightning_channeler_gui.png");

    public LightningChannelerScreen(LightningChannelerContainer screenContainer, PlayerInventory inv, ITextComponent titleIn) {
        super(screenContainer, inv, titleIn);
    }

    @Override
    public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(matrixStack);
        super.render(matrixStack, mouseX, mouseY, partialTicks);
        this.renderLabels(matrixStack, mouseX, mouseY);
    }

    @Override
    public void renderBackground(MatrixStack p_230446_1_) {
        super.renderBackground(p_230446_1_);
    }

    @Override
    protected void renderBg(MatrixStack matrixStack, float partialTicks, int x, int y) {
        RenderSystem.color4f(1f, 1f, 1f, 1f);
        this.minecraft.getTextureManager().bind(GUI);
        int i = this.getGuiLeft();
        int j = this.getGuiTop();
        this.blit(matrixStack, i, j, 0, 0, this.getXSize(), this.getYSize());

        if(menu.isLightningStorm()) {
            this.blit(matrixStack, i + 82, j + 9, 176, 0, 13, 17);
        }
    }
}
