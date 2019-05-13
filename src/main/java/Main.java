import greedy.Greedy;
import utils.Map;
import utils.Pair;

public class Main {

    public static void main(String[] args) {
        Map map = new Map(8, 8);
        map.clear();
        map.setFieldValue(new Pair(4, 0), 9999);
        map.setFieldValue(new Pair(4, 1), 9999);
        map.setFieldValue(new Pair(4, 2), 9999);
        map.setFieldValue(new Pair(4, 3), 9999);
        map.setFieldValue(new Pair(4, 4), 9999);

        Greedy greedy = new Greedy(map, new Pair(0, 7), new Pair(7, 0), true);
        greedy.search();
    }
}
