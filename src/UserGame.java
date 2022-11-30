import Enums.Direction;
import Enums.Emoji;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class UserGame {
    Scanner sc = new Scanner(System.in);
    Map<Integer, State> availableStates;

    UserGame(State startState) {
        play(startState);
    }

    State terminalInput(State state) {
        System.out.print("Enter SHOW_DIRECTIONS/SHD | SHOW_NEXT_STATES/SHS | ENTER_NEXT_STATE/ENS: ");
        String input = sc.next();
        switch (input) {
            case "SHD":
            case "SHOW_DIRECTIONS":
                for (Map.Entry<Integer, State> me : availableStates.entrySet()) {
                    System.out.println(Direction.getDirectionByValue(me.getKey()).get().getDirection() + "( " + me.getKey() + " )    ");
                }
                return state;
            case "SHS":
            case "SHOW_NEXT_STATES":
                for (Map.Entry<Integer, State> me : availableStates.entrySet()) {
                    System.out.println(Direction.getDirectionByValue(me.getKey()).get().getDirection() + "( " + me.getKey() + " )    ");
                    me.getValue().printBoard();
                }
                return state;
            case "ENS":
            case "ENTER_NEXT_STEP":
                System.out.print("Enter next step: ");
                int next = sc.nextInt();
                State newState = move(next, state);
                newState.printBoard();
                availableStates.clear();
                return newState;
            default:
                return state;
        }

    }

    State move(int to, State state) {
        if (availableStates.containsKey(to)) {
            for (int cell = 0; cell < availableStates.get(to).currentState.size(); cell++) {
                if (cell == state.finalPosition && !availableStates.get(to).currentState.get(cell).equals(Emoji.PERSON.getEmoji())) {
                    availableStates.get(to).currentState.set(cell, Emoji.FINAL.getEmoji());
                }
                if (state.vipCells.contains(cell) && state.currentState.get(cell) == Emoji.PERSON.getEmoji()) {
                    availableStates.get(to).currentState.set(cell, Emoji.VIP.getEmoji());
                }
            }
            State newState = new State(state.level);
            newState.currentState = new ArrayList<>(availableStates.get(to).currentState);
            newState.parent = state;
            return newState;
        } else {
            System.out.println("not valid direction.. please enter correct one:");
            int next = sc.nextInt();
            move(next, availableStates.get(next));
            return state;
        }
    }

    void play(State state) {
        state.printBoard();
        while (!state.isWin()) {
            availableStates = state.checkMovies();
            if (availableStates.size() > 0) {
                state = terminalInput(state);
            } else break;
        }
        if (state.isWin()) {
            System.out.println("you won 🥳🥳🥳 ");
        } else {
            System.out.println("you lost 😢 ");
        }
        System.out.print("Show Play Path? (y/n): ");
        String sh = sc.next();
        if (sh.equals("y") || sh.equals("Y")) {
            state.printBoard();
            state.printPath();
        }
    }
}
