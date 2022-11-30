import Enums.Emoji;

import java.util.*;

public class DfsGame {
    HashSet<List<String>> visitedStates = new HashSet<>();
    Stack<State> stack = new Stack<>();
    long startTime = System.currentTimeMillis();

    DfsGame(State state) {
        dfs(state);
    }

    void dfs(State state) {
        stack.push(state);
        while (!stack.empty()) {
            State topItem = stack.pop();
            visitedStates.add(topItem.currentState);
            if (topItem.currentState.hashCode() == topItem.finalState.hashCode()) {
                System.out.println("won 🥳🥳🥳 ");
                System.out.println("total time is " + (System.currentTimeMillis() - startTime) + " ms");
                System.out.println("solution depth is: " + topItem.depth);
                System.out.println("visited States Number is: " + visitedStates.size());
                System.out.println("play path: ");
                topItem.printBoard();
                topItem.printPath();
                break;
            }
            Map<Integer, State> availableStates = topItem.checkMovies();
            for (Map.Entry<Integer, State> entry : availableStates.entrySet()) {
                for (int cell = 0; cell < entry.getValue().currentState.size(); cell++) {
                    if (cell == topItem.finalPosition && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.FINAL.getEmoji());
                    }
                    if (topItem.vipCells.contains(cell) && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.VIP.getEmoji());
                    }
                }
                if (!visitedStates.contains(entry.getValue().currentState)) {
                    State newState = new State(topItem.level);
                    newState.currentState = new ArrayList<>(entry.getValue().currentState);
                    newState.parent = topItem;
                    newState.depth = newState.parent.depth + 1;
                    stack.push(newState);
                }
            }
        }
    }
}