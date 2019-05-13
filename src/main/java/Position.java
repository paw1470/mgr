import lombok.AllArgsConstructor;
import lombok.Getter;
import utils.Pair;

@Getter
@AllArgsConstructor
public class Position implements Comparable<Position> {
    Pair pair;
    Pair parent;
    private int value;

    public int compareTo(Position position) {
        if (this.getValue() > position.getValue()) {
            return 1;
        } else if (this.getValue() < position.getValue()) {
            return -1;
        } else {
            return 0;
        }
    }
}
