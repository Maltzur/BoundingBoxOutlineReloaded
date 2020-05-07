package com.irtimaled.bbor.client.gui;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.widget.AbstractButtonWidget;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.text.LiteralText;

abstract class AbstractButton extends AbstractButtonWidget implements IRenderableControl {
    AbstractButton(int x, int y, int width, String name) {
        super(x, y, width, 20, new LiteralText(name));
    }

    AbstractButton(int x, int y, int width, String name, boolean enabled) {
        this(x, y, width, name);
        this.active = enabled;
    }

    @Override
    public void render(MatrixStack matrices, int mouseX, int mouseY) {
        super.render(matrices, mouseX, mouseY, 0f);
    }

    @Override
    protected void renderBg(MatrixStack matrices, MinecraftClient minecraft, int mouseX, int mouseY) {
        renderBackground(matrices);
    }

    protected void renderBackground(MatrixStack matrices) {}

    @Override
    protected int getYImage(boolean hovered) {
        return getState();
    }

    protected int getState() {
        return this.active ? this.isHovered() ? 2 : 1 : 0;
    }

    @Override
    public void onClick(double mouseX, double mouseY) {
        super.onClick(mouseX, mouseY);
        onPressed();
    }

    protected abstract void onPressed();
}
