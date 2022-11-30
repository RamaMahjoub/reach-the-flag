import Enums.Emoji;
import Enums.Level;

import java.util.*;

public class State {
    int level, width, height, personPosition, finalPosition, size, blockedCells[], distinace = 100000000, depth = 0;
    List<Integer> vipCells;
    List<String> currentState, finalState;
    State parent = null;

    State(int level) {
        this.level = level;
        width = Level(this.level).getWidth();
        height = Level(this.level).getHeight();
        personPosition = Level(this.level).getPersonPosition();
        finalPosition = Level(this.level).getFinalPosition();
        size = width * height;
        blockedCells = Level(this.level).getBlockedCells();
        vipCells = Level(this.level).getVipCells();
        finalState = Level(this.level).getFinalState();
        currentState = new ArrayList(Collections.nCopies(size, Emoji.INIT.getEmoji()));
        for (int i = 0; i < blockedCells.length; i++) {
            currentState.set(blockedCells[i], Emoji.RESTRICT.getEmoji());
        }
        for (int i = 0; i < vipCells.size(); i++) {
            currentState.set(vipCells.get(i), Emoji.VIP.getEmoji());
        }
        currentState.set(finalPosition, Emoji.FINAL.getEmoji());
        currentState.set(personPosition, Emoji.PERSON.getEmoji());
    }

    Level Level(Integer i) {
        return Level.getByValue(i).get();
    }

    void printPath() {
        if (parent != null) {
            parent.printBoard();
            parent.printPath();
        }
    }

    void printBoard() {
        int boardHeight = 1;
        for (int cell = 0; cell < size; cell++) {
            System.out.print(currentState.get(cell) + "    ");
            if (cell == (width * boardHeight) - 1) {
                System.out.println();
                boardHeight++;
            }
        }
        System.out.println("========================================");
    }

    boolean isWin() {
        return currentState.hashCode() == finalState.hashCode();
    }

    boolean isValidCell(List currentState, int cell) {
        return currentState.get(cell).equals(Emoji.INIT.getEmoji()) || currentState.get(cell).equals(Emoji.VIP.getEmoji()) || currentState.get(cell).equals(Emoji.FINAL.getEmoji());
    }

    void swapCells(List currentState, int currentPersonPosition, int nextPersonPosition) {
        currentState.set(currentPersonPosition, Emoji.PASSED.getEmoji());
        currentState.set(nextPersonPosition, Emoji.PERSON.getEmoji());
    }

    Map<Integer, State> checkMovies() {
        Map<Integer, State> availableStates = new HashMap();
        State top = new State(level), down = new State(level), right = new State(level), left = new State(level);
        for (int cell = 0; cell < currentState.size(); cell++) {
            if (currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                if ((cell - width + 1) > 0) {//top
                    int upperCell = cell - width;
                    if (isValidCell(currentState, upperCell)) {
                        top.parent = this;
                        top.currentState = new ArrayList<>(currentState);
                        swapCells(top.currentState, cell, upperCell);
                        top.personPosition = upperCell;
                        availableStates.put(1, top);
                    }
                }
                if ((cell + 1) % (width) != 0) {//right
                    int rightCell = cell + 1;
                    if (isValidCell(currentState, rightCell)) {
                        right.parent = this;
                        right.currentState = new ArrayList<>(currentState);
                        swapCells(right.currentState, cell, rightCell);
                        right.personPosition = rightCell;
                        availableStates.put(2, right);
                    }
                }
                if (cell + width < (width * height)) {//down
                    int lowerCell = cell + width;
                    if (isValidCell(currentState, lowerCell)) {
                        down.parent = this;
                        down.currentState = new ArrayList<>(currentState);
                        swapCells(down.currentState, cell, lowerCell);
                        down.personPosition = lowerCell;
                        availableStates.put(3, down);
                    }
                }
                if ((cell) % width != 0) {//left
                    int leftCell = cell - 1;
                    if (isValidCell(currentState, leftCell)) {
                        left.parent = this;
                        left.currentState = new ArrayList<>(currentState);
                        swapCells(left.currentState, cell, leftCell);
                        left.personPosition = leftCell;
                        availableStates.put(4, left);
                    }
                }
            }
        }
        return availableStates;
    }

}
