package de.pro.fb_tiertagger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PlayerTierManager {
    private static final char ICON_CHAR = '\ua000';
    private static final Text SPACE = Text.literal(" ");
    private static final Text LINE = Text.literal("|").setStyle(Style.EMPTY.withColor(Formatting.GRAY));

    private enum Tier {
        HT1(TextColor.fromFormatting(Formatting.YELLOW)),
        LT1(TextColor.fromFormatting(Formatting.GOLD)),
        HT2(TextColor.fromRgb(0xC0C0C0)),
        LT2(TextColor.fromRgb(0xA9A9A9)),
        HT3(TextColor.fromRgb(0xf0b80b)),
        LT3(TextColor.fromRgb(0xf0d482)),
        HT4(TextColor.fromFormatting(Formatting.DARK_GRAY)),
        LT4(TextColor.fromFormatting(Formatting.DARK_GRAY)),
        HT5(TextColor.fromFormatting(Formatting.DARK_GRAY)),
        LT5(TextColor.fromFormatting(Formatting.DARK_GRAY));

        final Text prefix;

        Tier(TextColor color) {
            Text icon = Text.literal(String.valueOf(ICON_CHAR)).setStyle(Style.EMPTY.withColor(color));
            Text tierText = Text.literal(name()).setStyle(Style.EMPTY.withColor(color));
            this.prefix = Text.empty()
                    .append(icon).append(SPACE)
                    .append(tierText).append(SPACE)
                    .append(LINE).append(SPACE);
        }
    }

    // Lowercase player name → Tier enum
    private static final Map<String, Tier> TIER_MAP = Map.ofEntries(
            // HT1
            Map.entry("turnk", Tier.HT1),
            Map.entry("abyssalmc", Tier.HT1),
            Map.entry("moraes13", Tier.HT1),

            // LT1
            Map.entry("slord", Tier.LT1),
            Map.entry("pogulin", Tier.LT1),
            Map.entry("5bps", Tier.LT1),
            Map.entry("imperfexion", Tier.LT1),
            Map.entry("reddi_guy", Tier.LT1),
            Map.entry("triedyapper", Tier.LT1),
            Map.entry("pro6603", Tier.LT1),
            Map.entry("sajgonek27", Tier.LT1),

            // LT2
            Map.entry("_talyn_", Tier.LT2),
            Map.entry("crytist", Tier.LT2),
            Map.entry("mcffin", Tier.LT2),
            Map.entry("wreet", Tier.LT2),

            // HT3
            Map.entry("fruitnhas", Tier.HT3),
            Map.entry("mizuts", Tier.HT3),
            Map.entry("paragon_pvp", Tier.HT3),
            Map.entry("jaca_", Tier.HT3),
            Map.entry("doorboat", Tier.HT3),
            Map.entry("sxarhawk", Tier.HT3),
            Map.entry("boiboi2mc", Tier.HT3),

            // LT3
            Map.entry("itsmeluke2020", Tier.LT3),
            Map.entry("swifttmc", Tier.LT3),
            Map.entry("louie924", Tier.LT3),
            Map.entry("thejury89yt", Tier.LT3),
            Map.entry("popp_e", Tier.LT3),
            Map.entry("spefex", Tier.LT3),
            Map.entry("gelblich", Tier.LT3),
            Map.entry("tenpura518", Tier.LT3),

            // HT4
            Map.entry("citr0n_vert", Tier.HT4),
            Map.entry("thewildboon", Tier.HT4),
            Map.entry("blizzy09", Tier.HT4),
            Map.entry("justmosslol", Tier.HT4),
            Map.entry("laseryt", Tier.HT4),

            // LT4
            Map.entry("lifeknifemc", Tier.LT4),
            Map.entry("ozi_wafle", Tier.LT4),
            Map.entry("italovidor1512", Tier.LT4),
            Map.entry("pqanda", Tier.LT4),
            Map.entry("s1baken", Tier.LT4),
            Map.entry("justinisgaming", Tier.LT4),
            Map.entry("_cuguli_", Tier.LT4),

            // HT5
            Map.entry("acy_", Tier.HT5),
            Map.entry("itsjustpickles", Tier.HT5),
            Map.entry("fox_aspects", Tier.HT5),
            Map.entry("reverth23", Tier.HT5),
            Map.entry("sculkandtroll", Tier.HT5),
            Map.entry("wetsake", Tier.HT5)
    );

    // Cache for UUID → Tier
    private static final Map<UUID, Tier> tierCache = new ConcurrentHashMap<>();

    public static Tier getTier(PlayerEntity player) {
        if (player == null || player.getGameProfile() == null) return null;
        return tierCache.computeIfAbsent(player.getUuid(),
                id -> TIER_MAP.get(player.getGameProfile().getName().toLowerCase(Locale.ROOT)));
    }

    public static Text appendTier(PlayerEntity player, Text original) {
        Tier tier = getTier(player);
        if (tier == null) return original;
        return Text.empty().append(tier.prefix).append(original);
    }

    public static void clearCache() {
        tierCache.clear();
    }
}
