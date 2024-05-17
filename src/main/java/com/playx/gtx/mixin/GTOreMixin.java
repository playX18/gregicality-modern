package com.playx.gtx.mixin;
import com.gregtechceu.gtceu.data.loader.OreDataLoader;
import com.playx.gtx.worldgen.GTXVeinRemoval;
import com.playx.gtx.worldgen.GTXVeins;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(OreDataLoader.class)
public class GTOreMixin {
    @Inject(method = "apply*", at = @At(value = "INVOKE", target = "Lcom/gregtechceu/gtceu/common/data/GTOres;init()V", shift = At.Shift.AFTER), remap = false)
    private void postInit(CallbackInfo ci) {
        GTXVeinRemoval.removeOres();
    }

}