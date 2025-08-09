package de.pro.fb_tiertagger;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.text.Style;
import net.minecraft.text.TextColor;
import net.minecraft.util.Formatting;

import java.util.*;

public class PlayerTierManager {
    private static final char ICON_CHAR = '\ua000'; // special unicode icon
    private static final Text SPACE = Text.literal(" ");
    private static final Text LINE = Text.literal("|").setStyle(Style.EMPTY.withColor(Formatting.GRAY));

    // Lowercased, immutable map for O(1) lookups without String case ops
    private static final Map<String, String> TIER_MAP;
    // Prebuilt icon+tier parts for fast concatenation
    private static final Map<String, Text[]> PREBUILT_TIER_TEXTS = new HashMap<>();
    // Cache to avoid repeated lookups
    private static final Map<UUID, String> tierCache = new HashMap<>();

    static {
        Map<String, String> map = new HashMap<>();

        // === HT1 ===
        map.put("turnk", "HT1");
        map.put("abyssalmc", "HT1");
        map.put("moraes13", "HT1");

        // === LT1 ===
        map.put("slord", "LT1");
        map.put("pogulin", "LT1");
        map.put("5bps", "LT1");
        map.put("imperfexion", "LT1");
        map.put("reddi_guy", "LT1");
        map.put("triedyapper", "LT1");
        map.put("pro6603", "LT1");
        map.put("sajgonek27", "LT1");

        // === LT2 ===
        map.put("_talyn_", "LT2");
        map.put("crytist", "LT2");
        map.put("mcffin", "LT2");
        map.put("wreet", "LT2");

        // === HT3 ===
        map.put("fruitnhas", "HT3");
        map.put("mizuts", "HT3");
        map.put("paragon_pvp", "HT3");
        map.put("jaca_", "HT3");
        map.put("doorboat", "HT3");
        map.put("sxarhawk", "HT3");
        map.put("boiboi2mc", "HT3");

        // === LT3 ===
        map.put("itsmeluke2020", "LT3");
        map.put("swifttmc", "LT3");
        map.put("louie924", "LT3");
        map.put("thejury89yt", "LT3");
        map.put("popp_e", "LT3");
        map.put("spefex", "LT3");
        map.put("gelblich", "LT3");
        map.put("tenpura518", "LT3");

        // === HT4 ===
        map.put("citr0n_vert", "HT4");
        map.put("thewildboon", "HT4");
        map.put("blizzy09", "HT4");
        map.put("justmosslol", "HT4");
        map.put("laseryt", "HT4");

        // === LT4 ===
        map.put("lifeknifemc", "LT4");
        map.put("ozi_wafle", "LT4");
        map.put("italovidor1512", "LT4");
        map.put("pqanda", "LT4");
        map.put("s1baken", "LT4");
        map.put("justinisgaming", "LT4");
        map.put("_cuguli_", "LT4");

        // === HT5 ===
        map.put("acy_", "HT5");
        map.put("itsjustpickles", "HT5");
        map.put("fox_aspects", "HT5");
        map.put("reverth23", "HT5");
        map.put("sculkandtroll", "HT5");
        map.put("wetsake", "HT5");

        TIER_MAP = Collections.unmodifiableMap(map);

        // Prebuild Text parts for all tiers
        for (String tier : new HashSet<>(map.values())) {
            TextColor color = getFormatting(tier);
            Text icon = Text.literal(String.valueOf(ICON_CHAR)).setStyle(Style.EMPTY.withColor(color));
            Text tierText = Text.literal(tier).setStyle(Style.EMPTY.withColor(color));
            PREBUILT_TIER_TEXTS.put(tier, new Text[]{icon, tierText});
        }
    }

    public static String getTier(PlayerEntity player) {
        if (player == null || player.getGameProfile() == null) return null;

        UUID id = player.getUuid();
        String cached = tierCache.get(id);
        if (cached != null) return cached;

        String tier = TIER_MAP.get(player.getGameProfile().getName().toLowerCase(Locale.ROOT));
        if (tier != null) tierCache.put(id, tier);
        return tier;
    }

    public static Text appendTier(PlayerEntity player, Text original) {
        String tier = getTier(player);
        if (tier == null) return original;

        Text[] parts = PREBUILT_TIER_TEXTS.get(tier);
        return Text.empty()
                .append(parts[0]).append(SPACE)
                .append(parts[1]).append(SPACE)
                .append(LINE).append(SPACE)
                .append(original);
    }

    private static TextColor getFormatting(String tier) {
        return switch (tier) {
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

    public static void clearCache() {
        tierCache.clear();
    }
}
