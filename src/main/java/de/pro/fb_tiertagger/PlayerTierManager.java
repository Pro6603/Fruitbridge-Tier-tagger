package de.pro.fb_tiertagger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

import java.util.HashMap;
import java.util.Map;

public class PlayerTierManager {
    private static final Map<String, String> TIER_MAP = new HashMap<>();

    static {
        TIER_MAP.put("Turnk", "HT1");
        TIER_MAP.put("AbyssalMC", "HT1");
        TIER_MAP.put("MORAES13", "HT1");
        TIER_MAP.put("Slord", "LT1");
        TIER_MAP.put("Pogulin", "LT1");
        TIER_MAP.put("5bps", "LT1");
        TIER_MAP.put("Imperfexion", "LT1");
        TIER_MAP.put("Reddi_Guy", "LT1");
        TIER_MAP.put("TriedYapper", "LT1");
        TIER_MAP.put("Pro6603", "LT1");
        TIER_MAP.put("sajgonek27", "LT1");

        TIER_MAP.put("_Talyn_", "LT2");
        TIER_MAP.put("Crytist", "LT2");
        TIER_MAP.put("mcffin", "LT2");
        TIER_MAP.put("Wreet", "LT2");

        TIER_MAP.put("fruitnhas", "HT3");
        TIER_MAP.put("mizuts", "HT3");
        TIER_MAP.put("Paragon_PVP", "HT3");
        TIER_MAP.put("Jaca_", "HT3");
        TIER_MAP.put("doorboat", "HT3");
        TIER_MAP.put("Sxarhawk", "HT3");
        TIER_MAP.put("boiboi2mc", "HT3");

        TIER_MAP.put("ItsMeLuke2020", "LT3");
        TIER_MAP.put("SwifttMC", "LT3");
        TIER_MAP.put("louie924", "LT3");
        TIER_MAP.put("TheJury89YT", "LT3");
        TIER_MAP.put("Popp_E", "LT3");
        TIER_MAP.put("Spefex", "LT3");
        TIER_MAP.put("Gelblich", "LT3");
        TIER_MAP.put("tenpura518", "LT3");

        TIER_MAP.put("Citr0n_Vert", "HT4");
        TIER_MAP.put("THEWildBoon", "HT4");
        TIER_MAP.put("Blizzy09", "HT4");
        TIER_MAP.put("JustMossLOL", "HT4");
        TIER_MAP.put("LaserYT", "HT4");

        TIER_MAP.put("LifeKnifeMC", "LT4");
        TIER_MAP.put("ozi_wafle", "LT4");
        TIER_MAP.put("italovidor1512", "LT4");
        TIER_MAP.put("pqanda", "LT4");
        TIER_MAP.put("s1baken", "LT4");
        TIER_MAP.put("JustinIsGaming", "LT4");
        TIER_MAP.put("_Cuguli_", "LT4");

        TIER_MAP.put("Acy_", "HT5");
        TIER_MAP.put("ItsJustPickles", "HT5");
        TIER_MAP.put("Fox_Aspects", "HT5");
        TIER_MAP.put("reverth23", "HT5");
        TIER_MAP.put("SculkAndTroll", "HT5");
        TIER_MAP.put("Wetsake", "HT5");
        // Add more players and tiers as needed
    }

    // Returns the tier code for the player or null if none
    public static String getTier(PlayerEntity player) {
        if (player == null || player.getGameProfile() == null) return null;
        return TIER_MAP.get(player.getGameProfile().getName());
    }

    // Appends the tier icon (unicode char) before the player's name, colored per tier
    public static Text appendTier(PlayerEntity player, Text original) {
        String tier = getTier(player);
        if (tier == null) return original;

        // avaiable: ua000 and ua001, check emerald_1 and emerald_2.png for more epic not more epic stuff yes frfr true bonkers huh ezz LLL
        char iconChar = '\ua000'; // Your special unicode icon char
        TextColor color = getFormatting(tier);

        Text iconText = Text.literal(String.valueOf(iconChar))
                .setStyle(Style.EMPTY.withColor(Formatting.GREEN));

        Text tierText = Text.literal(tier.toUpperCase())
                .setStyle(Style.EMPTY.withColor(color));

        Text lineText = Text.literal("|")
                .setStyle(Style.EMPTY.withColor(Formatting.GRAY));

        Text space = Text.literal(" ");

        // Compose: icon + tier + space + original name
        return Text.empty()
                .append(iconText)
                .append(space)
                .append(tierText)
                .append(space)
                .append(lineText)
                .append(space)
                .append(original);
    }

    private static TextColor getFormatting(String tier) {
        return switch (tier.toUpperCase()) {
            case "HT1" -> TextColor.fromFormatting(Formatting.YELLOW);
            case "LT1" -> TextColor.fromFormatting(Formatting.GOLD);
            case "HT2" -> TextColor.fromRgb(0xC0C0C0);
            case "LT2" -> TextColor.fromRgb(0xA9A9A9);
            case "HT3" -> TextColor.fromRgb(0xf0b80b);
            case "LT3" -> TextColor.fromRgb(0xf0d482);
            case "HT4", "LT4", "HT5", "LT5" -> TextColor.fromFormatting(Formatting.DARK_GRAY);
            default -> TextColor.fromFormatting(Formatting.GRAY);
        };
    }
}
