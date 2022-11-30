import Enums.Emoji;

import java.util.*;

public class UcsGame {

    HashSet<List<String>> visitedStates = new HashSet<>();
    Queue<UcsPair> priorityQueue = new PriorityQueue<>();
    long startTime= System.currentTimeMillis();

    UcsGame(State state) {
        uca(state);
    }

    void uca(State state) {
        priorityQueue.add(new UcsPair(0, state));
        while (!priorityQueue.isEmpty()) {
            UcsPair last = priorityQueue.poll();
            visitedStates.add(last.state.currentState);
            if (last.state.currentState.hashCode() == last.state.finalState.hashCode()) {
                System.out.println("won 🥳🥳🥳 ");
                System.out.println("total time is " + (System.currentTimeMillis() - startTime)+" ms");
                System.out.println("solution depth is: " + last.state.depth);
                System.out.println("visited States Number is: " + visitedStates.size());
                System.out.println("play path: ");
                last.state.printBoard();
                last.state.printPath();
                return;
            }

            if (last.state.distinace > last.distinace) {
                last.state.distinace = last.distinace;
            }

            Map<Integer, State> availableStates = last.state.checkMovies();
            for (Map.Entry<Integer, State> entry : availableStates.entrySet()) {
                for (int cell = 0; cell < entry.getValue().currentState.size(); cell++) {
                    if (cell == state.finalPosition && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.FINAL.getEmoji());
                    }
                    if (state.vipCells.contains(cell) && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.VIP.getEmoji());
                    }
                }
                if (last.state.distinace + 1 < entry.getValue().distinace && !visitedStates.contains(entry.getValue().currentState)) {
                    State newState = new State(state.level);
                    newState.currentState = new ArrayList<>(entry.getValue().currentState);
                    newState.parent = last.state;
                    newState.depth = newState.parent.depth + 1;
                    priorityQueue.add(new UcsPair(last.state.distinace + 1, newState));
                }
            }
        }
    }
}
