package alg;


import utils.Coordinate;
import utils.Map;
import utils.PointState;

import java.util.*;

public class Algorithm {
    private int mapSizeX;
    private int mapSizeY;
    private Point start;
    private Point end;
    private boolean diagonals;
    private Map map;
    private Point[][] points;
    private PriorityQueue<Point> pointPriorityQueue;
    private int lengthMultiplier = 100;
    Algorithms algorithm;
    int visitedCounter;
    int calculateCounter;
    boolean found;

    public Algorithm(Map map, Coordinate start, Coordinate end, boolean diagonals, Algorithms algorithm) {
        this.map = map;
        this.mapSizeX = map.getXSize();
        this.mapSizeY = map.getYSize();
        found = false;
        initializePointMap();
        setParameters(start, end, diagonals, algorithm);
    }

    private void initializePointMap() {
        points = new Point[mapSizeY][mapSizeY];
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                points[y][x] = new Point(new Coordinate(x, y));
            }
        }
        pointPriorityQueue = new PriorityQueue<>();
    }

    public void setParameters(Coordinate start, Coordinate end, boolean diagonals, Algorithms algorithm) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("Start and End cannot be the same point");
        }
        points[start.getY()][start.getX()].setState(PointState.START);
        points[start.getY()][start.getX()].setValueToThisPoint(0);
        points[end.getY()][end.getX()].setState(PointState.END);

        this.start = points[start.getY()][start.getX()];
        this.end = points[end.getY()][end.getX()];
        this.diagonals = diagonals;
        this.algorithm = algorithm;
    }

    public void setLengthMultiplier(int lengthMultiplier) {
        this.lengthMultiplier = lengthMultiplier;
    }

    public void findWalls(int value) {
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (map.getFieldValue(new Coordinate(x, y)) >= value) {
                    points[y][x].setState(PointState.WALL);
                }
            }
        }
    }

    private int calculateValue3D(Point p1, Point p2) {
        calculateCounter++;
        return (int) Math.sqrt(Math.pow((p2.getCoordinate().getX() - p1.getCoordinate().getX()) * lengthMultiplier, 2) +
                Math.pow((p2.getCoordinate().getY() - p1.getCoordinate().getY()) * lengthMultiplier, 2) +
                Math.pow(map.getFieldValue(p2.getCoordinate()) - map.getFieldValue(p1.getCoordinate()), 2));
    }

    private int calculateValue2D(Point p1, Point p2) {
        calculateCounter++;
        return (int) Math.sqrt(Math.pow((p2.getCoordinate().getX() - p1.getCoordinate().getX()) * lengthMultiplier, 2) +
                Math.pow((p2.getCoordinate().getY() - p1.getCoordinate().getY()) * lengthMultiplier, 2));
    }

    public boolean search() {
        System.out.println(algorithm);
        visitedCounter = 0;
        calculateCounter = 0;
        pointPriorityQueue.add(start);
        int valueToThis = 0;
        while (!pointPriorityQueue.isEmpty()) {
            Point bestPoint = pointPriorityQueue.remove();
            visitedCounter++;
            if (isGreedy() || isGreedyWithMemory()) {
                clearQueue();
            }
            System.out.println(bestPoint);
            if (bestPoint.getState() == PointState.END) {
                System.out.println("Znaleziony");
//                System.out.println(getPath());
                found = true;
                return true;
            } else if (bestPoint.getState() == PointState.START && valueToThis > 0) {    //alg jezeli wroci na start to znaczy ze juz nigdy nie trafi
                System.out.println("Zapetlenie do start");
                return false;
            } else if (bestPoint.getState() == PointState.VISITED) {
                System.out.println("Zapetlenie");
                return false;
            }
            if (bestPoint.getState() == PointState.ADDED) {
                bestPoint.setState(PointState.VISITED);
            }
            checkNeighbors(bestPoint);
        }
        System.out.println("Brak przejścia");
        return false;
    }

    private void clearQueue() {
        Point point;
        while (!pointPriorityQueue.isEmpty()) {
            point = pointPriorityQueue.remove();
            if (point.getState() == PointState.ADDED) {
                point.setState(PointState.FREE);
                point.setParent(null);
            }
        }
    }

    public List<Point> getPathToEnd() {
        List<Point> pathList = new LinkedList<>();
        Point point = end;
        if (found) {
            pathList.add(point);
            do {
                point = point.getParent();
                pathList.add(point);
            } while (point.getParent() != null);
            Collections.reverse(pathList);
        }
        return pathList;
    }

    private void checkNeighbors(Point currentPoint) {
        int x = currentPoint.getCoordinate().getX();
        int y = currentPoint.getCoordinate().getY();
        Point pointNew;
        if (y > 0) {  //top
            pointNew = points[y - 1][x];
            addPointToQueue(pointNew, currentPoint);
        }
        if (x < (mapSizeX - 1)) { //right
            pointNew = points[y][x + 1];
            addPointToQueue(pointNew, currentPoint);
        }
        if (y < (mapSizeY - 1)) { //bot
            pointNew = points[y + 1][x];
            addPointToQueue(pointNew, currentPoint);
        }
        if (x > 0) { //left
            pointNew = points[y][x - 1];
            addPointToQueue(pointNew, currentPoint);
        }
        if (diagonals) {
            if (y > 0 &&
                    x < (mapSizeX - 1)) {  //top-right
                pointNew = points[y - 1][x + 1];
                addPointToQueue(pointNew, currentPoint);
            }
            if (x < (mapSizeX - 1) &&
                    y < (mapSizeY - 1)) { //right-bot
                pointNew = points[y + 1][x + 1];
                addPointToQueue(pointNew, currentPoint);
            }
            if (y < (mapSizeY - 1) &&
                    x > 0) { //bot-left
                pointNew = points[y + 1][x - 1];
                addPointToQueue(pointNew, currentPoint);
            }
            if (y > 0 &&
                    x > 0) { //left-top
                pointNew = points[y - 1][x - 1];
                addPointToQueue(pointNew, currentPoint);
            }
        }
    }

    private boolean addPointToQueue(Point newPoint, Point bestPoint) {
        if (isPointWall(newPoint)) {
            return false;
        }
        if (isGreedy()) {
            if (isPointVisited(newPoint)) {
                pointPriorityQueue.add(newPoint);
                return true;
            }
        } else if (isGreedyWithMemory()) {
            if (isPointVisited(newPoint)) {
                return false;
            }
        } else if (isBestFirstSearch()) {
            if (isPointVisited(newPoint) || isPointAdded(newPoint)) {
                return false;
            }
        } else if (isAStar()) {
            if (isPointVisited(newPoint) || isPointAdded(newPoint)) {
                int c = bestPoint.getValueToThisPoint() + calculateValue3D(newPoint, bestPoint);
                if (c < newPoint.getValueToThisPoint()) {
                    newPoint.setValueToThisPoint(c);
                }
                return false;
            }
        }
        if (isPointFree(newPoint)) {
            newPoint.setState(PointState.ADDED);
        }
        newPoint.setParent(bestPoint);
        newPoint.setValueToThisPoint(bestPoint.getValueToThisPoint() + calculateValue3D(newPoint, bestPoint));
        if (isAStar()) {
            newPoint.setValueToEndPoint(calculateValue3D(newPoint, end) + newPoint.getValueToThisPoint());
        } else {
            newPoint.setValueToEndPoint(calculateValue3D(newPoint, end));
        }
        pointPriorityQueue.add(newPoint);
        return true;
    }

    private boolean isGreedy() {
        return algorithm == Algorithms.GREEDY;
    }

    private boolean isGreedyWithMemory() {
        return algorithm == Algorithms.GREEDY_WITH_MEMORY;
    }

    private boolean isBestFirstSearch() {
        return algorithm == Algorithms.BEST_FIRST_SEARCH;
    }

    private boolean isAStar() {
        return algorithm == Algorithms.ASTAR;
    }

    private boolean isPointWall(Point point) {
        return point.getState() == PointState.WALL;
    }

    private boolean isPointFree(Point point) {
        return point.getState() == PointState.FREE;
    }

    private boolean isPointAdded(Point point) {
        return point.getState() == PointState.ADDED;
    }

    private boolean isPointVisited(Point point) {
        return point.getState() == PointState.VISITED;
    }

}



