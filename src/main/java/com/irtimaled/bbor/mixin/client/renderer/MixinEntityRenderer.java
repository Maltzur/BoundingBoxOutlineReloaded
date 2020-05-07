package com.irtimaled.bbor.mixin.client.renderer;

import com.irtimaled.bbor.client.interop.ClientInterop;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.Matrix4f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class MixinEntityRenderer {
    @Shadow
    @Final
    private MinecraftClient client;

    @Inject(method = "render", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=weather", shift = At.Shift.BEFORE))
    private void render(MatrixStack matrices, float partialTicks, long ignored_1, boolean ignored_2, Camera ignored_3, GameRenderer ignored_4, LightmapTextureManager ignored_5, Matrix4f ignored_6, CallbackInfo ci) {
        ClientInterop.render(matrices, partialTicks, this.client.player);
    }
}

