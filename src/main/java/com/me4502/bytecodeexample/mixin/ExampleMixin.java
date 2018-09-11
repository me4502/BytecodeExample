package com.me4502.bytecodeexample.mixin;

import com.me4502.bytecodeexample.LoaderHook;
import net.minecraft.server.dedicated.DedicatedServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(DedicatedServer.class)
public class ExampleMixin {

    @Inject(method = "init", at = @At(value = "HEAD"))
    public void injectToInit(CallbackInfoReturnable<Boolean> callbackInfo) {
        LoaderHook.runMeMixin();
    }
}
