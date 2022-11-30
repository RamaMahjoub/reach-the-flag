package Enums;

public enum Emoji {
    PERSON("PERSON", "🧍"),
    VIP("VIP", "🔁"),
    FINAL("FINAL", "🚩"),
    INIT("INIT", "➖"),
    RESTRICT("RESTRICT", "❌"),
    PASSED("PASSED", "✅");
    private final String emoji;
    private final String shortcut;

    Emoji(String shortcut, String emoji) {
        this.emoji = emoji;
        this.shortcut = shortcut;
    }

    public String getEmoji() {
        return emoji;
    }
}
