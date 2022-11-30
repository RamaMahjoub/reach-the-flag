import Enums.Emoji;

import java.util.*;

public class BfsGame {
    HashSet<List<String>> visitedStates = new HashSet<>();
    LinkedList<State> queue = new LinkedList();
    long startTime = System.currentTimeMillis();
    BfsGame(State state) {
        bfs(state);
    }

    void bfs(State state) {
        queue.add(state);
        while (!queue.isEmpty()) {
            State last = queue.poll();
            visitedStates.add(last.currentState);
            if (last.currentState.hashCode() == last.finalState.hashCode()) {
                System.out.println("won 🥳🥳🥳 ");
                System.out.println("total time is " + (System.currentTimeMillis() - startTime) + " ms");
                System.out.println("solution depth is: " + last.depth);
                System.out.println("visited States Number is: " + visitedStates.size());
                System.out.println("play path: ");
                last.printBoard();
                last.printPath();
                return;
            }
            Map<Integer, State> availableStates = last.checkMovies();
            for (Map.Entry<Integer, State> entry : availableStates.entrySet()) {
                for (int cell = 0; cell < entry.getValue().currentState.size(); cell++) {
                    if (cell == state.finalPosition && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.FINAL.getEmoji());
                    }
                    if (state.vipCells.contains(cell) && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.VIP.getEmoji());
                    }
                }
                if (!visitedStates.contains(entry.getValue().currentState)) {
                    State newState = new State(last.level);
                    newState.currentState = new ArrayList<>(entry.getValue().currentState);
                    newState.parent = last;
                    newState.depth = newState.parent.depth + 1;
                    queue.add(newState);
                }
            }
        }
    }
}
