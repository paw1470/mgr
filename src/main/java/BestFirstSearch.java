import utils.Map;
import utils.Pair;

import java.util.PriorityQueue;

public class BestFirstSearch {
    private PriorityQueue<Position> priorityQueue;
    private boolean[][] visited;
    private Map map;
    private Pair startPair;
    private Pair endPair;
    private int fieldSize;

    public void BestFirstSearch(Map map, Pair startPair, Pair endPair, int fieldSize) {
        this.map = map;
        init(startPair, endPair, fieldSize);
    }

    private void init(Pair startPair, Pair endPair, int fieldSize) {
        this.startPair = startPair;
        this.endPair = endPair;
        this.fieldSize = fieldSize;
        priorityQueue = new PriorityQueue<Position>();
        visited = new boolean[map.getYSize()][];
        for (int y = 0; y < map.getYSize(); y++) {
            visited[y] = new boolean[map.getXSize()];
            for (int x = 0; x < map.getXSize(); x++) {
                visited[y][x] = false;
            }
        }
    }


    public void search(Map map, Pair startPair, Pair endPair, int fieldSize) {

        addToQueue(startPair, startPair);


        while (!priorityQueue.isEmpty()) {
            Position current = priorityQueue.remove();

        }
    }

    public void addToQueue(Pair pair, Pair parent) {
        Position position = new Position(pair, parent, calculateValue(pair));
        priorityQueue.add(position);
    }

    private int calculateValue(Pair pair) {
        return Math.abs(pair.getX() - endPair.getX()) + Math.abs(pair.getY() - endPair.getY());
    }

}

