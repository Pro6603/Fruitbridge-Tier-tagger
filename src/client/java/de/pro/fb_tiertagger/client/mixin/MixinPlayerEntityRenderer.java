package de.pro.fb_tiertagger.client.mixin;

import de.pro.fb_tiertagger.PlayerTierManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.MatrixStack.Entry;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Pair;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

//@Mixin(PlayerEntityRenderer.class)
public class MixinPlayerEntityRenderer {
/*
    @Inject(method = "renderLabelIfPresent", at = @At("RETURN"))
    private void renderTierIconNextToNametag(
            AbstractClientPlayerEntity player,
            Text text,
            MatrixStack matrices,
            VertexConsumerProvider vertexConsumers,
            int light,
            float tickDelta,
            CallbackInfo ci
    ) {
        String tier = PlayerTierManager.getTier(player);
        if (tier == null) return;

        Pair<Character, TextColor> iconAndColor = getIconAndColorForTier(tier);
        if (iconAndColor == null) return;

        MinecraftClient mc = MinecraftClient.getInstance();

        char iconChar = iconAndColor.getLeft();
        TextColor color = iconAndColor.getRight();

        matrices.push();

        // Position icon slightly above the nametag, aligned left of the text
        double yOffset = player.isSneaking() ? 0.9 : 1.3;
        matrices.translate(0.0, yOffset, 0.0);

        // Face the camera
        matrices.multiply(mc.getEntityRenderDispatcher().getRotation());

        // Match nametag scale
        float scale = 0.025f;
        matrices.scale(-scale, -scale, scale);

        // Calculate x offset to left of nametag text
        int nameWidth = mc.textRenderer.getWidth(text.getString());
        float xOffset = -(nameWidth / 2f) - 12f;  // 12 pixels left of nametag
        float yOffsetPixels = -mc.textRenderer.fontHeight / 2f;

        Text iconText = Text.literal(String.valueOf(iconChar))
                .setStyle(Style.EMPTY.withColor(color).withFont(Identifier.of("minecraft", "default")));

        OrderedText orderedIcon = iconText.asOrderedText();

        int packedColor = 0xFF000000 | color.getRgb();

        Entry matrixEntry = matrices.peek();

        mc.textRenderer.draw(
                orderedIcon,
                xOffset,
                yOffsetPixels,
                packedColor,
                false,
                matrixEntry.getPositionMatrix(),
                vertexConsumers,
                net.minecraft.client.font.TextRenderer.TextLayerType.NORMAL,
                0,
                light
        );

        matrices.pop();
    }

    private Pair<Character, TextColor> getIconAndColorForTier(String tier) {
        String t = tier.trim().toUpperCase();
        TextColor color = switch (t) {
            case "LT1", "HT1" -> TextColor.fromFormatting(Formatting.GOLD);
            case "LT2", "HT2" -> TextColor.fromFormatting(Formatting.YELLOW);
            case "LT3", "HT3" -> TextColor.fromFormatting(Formatting.GOLD);
            case "LT4", "HT4" -> TextColor.fromFormatting(Formatting.LIGHT_PURPLE);
            case "LT5", "HT5" -> TextColor.fromFormatting(Formatting.DARK_PURPLE);
            default -> TextColor.fromFormatting(Formatting.WHITE);
        };

        return new Pair<>('\ua001', color);
    }

 */
}
