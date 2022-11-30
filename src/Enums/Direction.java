package Enums;

import java.util.Arrays;
import java.util.Optional;

public enum Direction {
    TOP(1, "TOP"),
    RIGHT(2, "RIGHT"),
    DOWN(3, "DOWN"),
    LEFT(4, "LEFT");
    private final String direction;
    private final Integer value;

    Direction(Integer value, String direction) {
        this.value = value;
        this.direction = direction;
    }

    public static Optional<Direction> getDirectionByValue(Integer value) {
        return Arrays.stream(Direction.values())
                .filter(directions -> directions.value.equals(value))
                .findFirst();
    }

    public String getDirection() {
        return direction;
    }
}
