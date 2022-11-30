package Enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public enum Level {
    FIRST(1, 6, 1, 0, 5, new int[]{}, new ArrayList() {
    },
            new ArrayList<>() {{
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("🧍");
            }}),
    SECOND(2, 7, 2, 7, 13, new int[]{0, 1, 2, 3, 4}, new ArrayList<>() {
    },
            new ArrayList<>() {{
                add("❌");
                add("❌");
                add("❌");
                add("❌");
                add("❌");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("🧍");
            }}),
    THIRD(3, 5, 2, 5, 9, new int[]{0, 1, 2, 4}, new ArrayList<>() {{
        add(8);
    }},
            new ArrayList<>() {{
                add("❌");
                add("❌");
                add("❌");
                add("✅");
                add("❌");
                add("✅");
                add("✅");
                add("✅");
                add("🔁");
                add("🧍");
            }}),
    FOURTH(4, 6, 5, 4, 19, new int[]{0, 1, 2, 3, 5, 6, 7, 8, 9, 11, 12, 13, 14, 15, 17, 24, 26, 27, 29}, new ArrayList<>() {{
        add(22);
    }},
            new ArrayList<>() {{
                add("❌");
                add("❌");
                add("❌");
                add("❌");
                add("✅");
                add("❌");
                add("❌");
                add("❌");
                add("❌");
                add("❌");
                add("✅");
                add("❌");
                add("❌");
                add("❌");
                add("❌");
                add("❌");
                add("✅");
                add("❌");
                add("✅");
                add("🧍");
                add("✅");
                add("✅");
                add("🔁");
                add("✅");
                add("❌");
                add("✅");
                add("❌");
                add("❌");
                add("✅");
                add("❌");
            }}),
    FIFTH(5, 6, 6, 33, 4, new int[]{5, 20, 30, 34, 35}, new ArrayList() {{
        add(10);
        add(25);
    }},
            new ArrayList<>() {{
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("🧍");
                add("❌");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("🔁");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("❌");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("🔁");
                add("✅");
                add("✅");
                add("✅");
                add("✅");
                add("❌");
                add("✅");
                add("✅");
                add("✅");
                add("❌");
                add("❌");
            }});

    private final Integer level;
    private final Integer width;
    private final Integer height;
    private final Integer personPosition;
    private final Integer finalPosition;
    private final int[] blockedCells;
    private final List<Integer> vipCells;
    private final List<String> finalState;

    Level(Integer level, Integer width, Integer height, Integer personPosition, Integer finalPosition, int[] blockedCells, List vipCells, List finalState) {
        this.level = level;
        this.width = width;
        this.height = height;
        this.personPosition = personPosition;
        this.finalPosition = finalPosition;
        this.blockedCells = blockedCells;
        this.vipCells = vipCells;
        this.finalState = finalState;
    }

    public static Optional<Level> getByValue(Integer value) {
        return Arrays.stream(Level.values())
                .filter(levels -> levels.level.equals(value))
                .findFirst();
    }

    public Integer getWidth() {
        return width;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getPersonPosition() {
        return personPosition;
    }

    public Integer getFinalPosition() {
        return finalPosition;
    }

    public int[] getBlockedCells() {
        return blockedCells;
    }

    public List getVipCells() {
        return vipCells;
    }

    public List getFinalState() {
        return finalState;
    }
}
