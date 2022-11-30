public class AStarPair implements Comparable<AStarPair> {
    Integer f;
    State state;

    public int compareTo(AStarPair helper) {
        return (f < helper.f) ? -1 : ((helper.f == f) ? 0 : 1);
    }

    AStarPair(Integer f, State state){
        this.f = f;
        this.state =state;
    }
}
