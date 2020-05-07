package com.irtimaled.bbor.client.gui;

import com.irtimaled.bbor.common.BoundingBoxType;
import net.minecraft.client.util.math.MatrixStack;

import java.awt.*;

public class BoundingBoxTypeButton extends BoolSettingButton {
    private final Color color;

    BoundingBoxTypeButton(int x, int y, int width, String label, BoundingBoxType type) {
        super(x, y, width, label, type.shouldRenderSetting);
        color = type.getColor();
    }

    public BoundingBoxTypeButton(int x, int y, int width, String label, BoundingBoxType type, boolean enabled) {
        this(x, y, width, label, type);
        this.active = enabled;
    }

    @Override
    protected void renderBackground(MatrixStack matrices) {
        if (!active) return;

        int left = x + 1;
        int top = y + 1;
        int right = left + width - 2;
        int bottom = top + height - 2;

        // top & left
        drawRectangle(matrices, left, top, right, top + 1, color);
        drawRectangle(matrices, left, top, left + 1, bottom, color);

        Color darker = color.darker();
        // bottom left & top right
        drawRectangle(matrices, left, bottom - 2, left + 1, bottom, darker);
        drawRectangle(matrices, right - 1, top, right, top + 1, darker);

        Color darkest = darker.darker();
        // bottom & right
        drawRectangle(matrices, left + 1, bottom - 2, right, bottom, darkest);
        drawRectangle(matrices, right - 1, top + 1, right, bottom, darkest);
    }

    private void drawRectangle(MatrixStack matrices, int left, int top, int right, int bottom, Color color) {
        fill(matrices, left, top, right, bottom, color.getRGB());
    }
}
