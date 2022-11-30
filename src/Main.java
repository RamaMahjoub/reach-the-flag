import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Enter level: ");
        Scanner sc = new Scanner(System.in);
        int level = sc.nextInt();
        State startState = new State(level);
        System.out.println("Choose the game algorithm => ( User: 1, DFS: 2, BFS: 3, UCS: 4, A*: 5 ) ");
        int algorithm = sc.nextInt();
        switch (algorithm) {
            case 1:
                UserGame us = new UserGame(startState);
                break;
            case 2:
                DfsGame dfs = new DfsGame(startState);
                break;
            case 3:
                BfsGame bfs = new BfsGame(startState);
                break;
            case 4:
                UcsGame ucs = new UcsGame(startState);
                break;
            case 5:
                AStarGame astar = new AStarGame(startState);
                break;
        }
    }
}

