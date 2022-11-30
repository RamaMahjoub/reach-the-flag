import Enums.Emoji;

import java.util.*;

import static java.lang.Math.abs;

public class AStarGame {
    Queue<AStarPair> openSet = new PriorityQueue<>();
    Set<List<String>> closeSet = new HashSet<>();
    long startTime = System.currentTimeMillis();

    public AStarGame(State state) {
        astar(state);
    }

    int manhattanHeuristic(State state) {
        int currentPosition = state.personPosition;
        int finalPosition = state.finalPosition;
        return abs(finalPosition - currentPosition);
    }

    void astar(State state) {
        openSet.add(new AStarPair((0 + manhattanHeuristic(state)), state));
        state.distinace = 0;
        while (!openSet.isEmpty()) {
            AStarPair current = openSet.poll();
            closeSet.add(current.state.currentState);
//            System.out.println("gggggggg" + current.state.distinace);
//            System.out.println("hhhhhhhh" + manhattanHeuristic(current.state));
//            System.out.println("ffffffffff" + current.f);
//            System.out.println("person" + current.state.personPosition);
            if (current.state.currentState.hashCode() == current.state.finalState.hashCode()) {
                System.out.println("won 🥳🥳🥳 ");
                System.out.println("total time is " + (System.currentTimeMillis() - startTime) + " ms");
                System.out.println("solution depth is: " + current.state.depth);
                System.out.println("visited States Number is: " + closeSet.size());
                System.out.println("play path: ");
                current.state.printBoard();
                current.state.printPath();
                return;
            }

            Map<Integer, State> availableStates = current.state.checkMovies();
            for (Map.Entry<Integer, State> entry : availableStates.entrySet()) {
                for (int cell = 0; cell < entry.getValue().currentState.size(); cell++) {
                    if (cell == state.finalPosition && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.FINAL.getEmoji());
                    }
                    if (state.vipCells.contains(cell) && !entry.getValue().currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                        entry.getValue().currentState.set(cell, Emoji.VIP.getEmoji());
                    }
                }
                int g = entry.getValue().parent.distinace + 1;
                int tempF = g + manhattanHeuristic(entry.getValue());
                if (!closeSet.contains(entry.getValue().currentState)) {
                    State newState = new State(state.level);
                    newState.currentState = new ArrayList<>(entry.getValue().currentState);
                    newState.parent = current.state;
                    newState.distinace = entry.getValue().parent.distinace + 1;
                    newState.depth = newState.parent.depth + 1;
                    newState.personPosition = entry.getValue().personPosition;
                    openSet.add(new AStarPair(tempF, newState));
                }
            }
        }
    }
}
