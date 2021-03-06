package com.irtimaled.bbor.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;

abstract class AbstractSlider extends GuiButton implements IRenderableControl {
    double progress;

    AbstractSlider(int id, int x, int y, int width) {
        super(id, x, y, width, 20, "");
    }

    @Override
    public void render(int mouseX, int mouseY) {
        super.render(mouseX, mouseY, 0f);
    }

    @Override
    protected void renderBg(Minecraft minecraft, int mouseX, int mouseY) {
        minecraft.getTextureManager().bindTexture(BUTTON_TEXTURES);
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.drawTexturedModalRect(this.x + (int) (this.progress * (double) (this.width - 8)), this.y, 0, 66, 4, 20);
        this.drawTexturedModalRect(this.x + (int) (this.progress * (double) (this.width - 8)) + 4, this.y, 196, 66, 4, 20);
    }

    boolean setProgress(double progress) {
        progress = MathHelper.clamp(progress, 0d, 1d);
        if (this.progress == progress) return false;

        this.progress = progress;
        return true;
    }

    private void changeProgress(double mouseX) {
        double progress = (mouseX - (double) (this.x + 4)) / (double) (this.width - 8);
        if (setProgress(progress)) {
            onProgressChanged();
        }
        updateText();
    }

    @Override
    protected int getHoverState(boolean hovered) {
        return 0;
    }

    @Override
    protected void onDrag(double mouseX, double mouseY, double deltaX, double deltaY) {
        changeProgress(mouseX);
        super.onDrag(mouseX, mouseY, deltaX, deltaY);
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        changeProgress(mouseX);
        super.onClick(mouseX, mouseY);
    }

    protected abstract void updateText();

    protected abstract void onProgressChanged();
}
