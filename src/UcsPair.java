public class UcsPair implements Comparable<UcsPair>{

    Integer distinace;

    State state;

    public int compareTo(UcsPair pi) {
        return (distinace < pi.distinace) ? -1 : ((pi.distinace == distinace) ? 0 : 1);
    }

    UcsPair(Integer distinace, State state) {
        this.state = state;
        this.distinace = distinace;
    }
}
