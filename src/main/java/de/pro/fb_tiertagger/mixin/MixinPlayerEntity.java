package de.pro.fb_tiertagger.mixin;

import de.pro.fb_tiertagger.PlayerTierManager;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;

@Mixin(PlayerEntity.class)
public class MixinPlayerEntity {
    @ModifyReturnValue(method = "getDisplayName", at = @At("RETURN"))
    private Text fb_tiertagger$addTierPrefix(Text original) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        return PlayerTierManager.appendTier(player, original);
    }
}