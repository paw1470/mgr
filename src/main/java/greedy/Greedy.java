package greedy;


import utils.Map;
import utils.Pair;
import utils.PointState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Greedy {
    private int mapSizeX;
    private int mapSizeY;
    private Point start;
    private Point end;
    private boolean diagonals;
    private Map map;
    private Point[][] points;
    private PriorityQueue<Point> pointPriorityQueue;
    private int lengthMultipler = 100;

    public Greedy(Map map, Pair start, Pair end, boolean diagonals) {
        this.map = map;
        this.mapSizeX = map.getXSize();
        this.mapSizeY = map.getYSize();
        init();
        setParameters(start, end, diagonals);
    }

    private void init() {
        points = new Point[mapSizeY][mapSizeY];
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                points[y][x] = new Point(new Pair(x, y));
            }
        }
        pointPriorityQueue = new PriorityQueue<Point>();
    }

    public void setParameters(Pair start, Pair end, boolean diagonals) {
        if (start.equals(end)) {
            throw new IllegalArgumentException("Start and End cannot be the same point");
        }
        points[start.getY()][start.getX()].setState(PointState.START);
        points[start.getY()][start.getX()].setValueToThisPoint(0);
        points[end.getY()][end.getX()].setState(PointState.END);

        this.start = points[start.getY()][start.getX()];
        this.end = points[end.getY()][end.getX()];
        this.diagonals = diagonals;
    }

    public void setLengthMultipler(int lengthMultipler) {
        this.lengthMultipler = lengthMultipler;
    }

    public void findWalls(int value) {
        for (int y = 0; y < mapSizeY; y++) {
            for (int x = 0; x < mapSizeX; x++) {
                if (map.getFieldValue(new Pair(x, y)) >= value) {
                    points[y][x].setState(PointState.WALL);
                }
            }
        }
    }

    private int calculateValue3D(Point p1, Point p2) {
        return (int) Math.sqrt(Math.pow((p2.getPair().getX() - p1.getPair().getX()) * lengthMultipler, 2) +
                Math.pow((p2.getPair().getY() - p1.getPair().getY()) * lengthMultipler, 2) +
                Math.pow(map.getFieldValue(p2.getPair()) - map.getFieldValue(p1.getPair()), 2));
    }

    private int calculateValue(Point p1, Point p2) {
        return (int) Math.sqrt(Math.pow((p2.getPair().getX() - p1.getPair().getX()) * lengthMultipler, 2) +
                Math.pow((p2.getPair().getY() - p1.getPair().getY()) * lengthMultipler, 2));
    }

    public boolean search() {
        pointPriorityQueue.add(start);
        int counter = mapSizeX * mapSizeY;
        int valueToThis = 0;
        while ((!pointPriorityQueue.isEmpty()) && (counter > 0)) {
            Point now = pointPriorityQueue.remove();
            pointPriorityQueue.clear();                                             //greedy nie interesuje sie przeszloscia wiec nie musi pamietac
            System.out.println(now);
            if (now.getState() == PointState.END) {
                return true;
            } else if (now.getState() == PointState.START && valueToThis > 0) {    //greedy jezeli wroci na start to znaczy ze juz nigdy nie trafi
                return false;
            } else if (now.getState() == PointState.VISITED) {
                return false;
            }
            if (now.getState() == PointState.ADDED) {
                now.setState(PointState.VISITED);
            }
            checkNeighbors(now);
            counter--;
        }
        return false;
    }

    public List<Point> getPath() {
        List<Point> pathList = new ArrayList<>();
        Point point = end;
        while (point.getParent() != null) {
            pathList.add(point);
            point = point.getParent();
        }
        if (!pathList.isEmpty()) {
            pathList.add(start);
        }
        Collections.reverse(pathList);
        return pathList;
    }

    private void checkNeighbors(Point now) {
        int x = now.getPair().getX();
        int y = now.getPair().getY();
        Point pointNew;
        if (y > 0) {  //top
            pointNew = points[y - 1][x];
            addPointToQueue(pointNew, now);
        }
        if (x < (mapSizeX - 1)) { //right
            pointNew = points[y][x + 1];
            addPointToQueue(pointNew, now);
        }
        if (y < (mapSizeY - 1)) { //bot
            pointNew = points[y + 1][x];
            addPointToQueue(pointNew, now);
        }
        if (x > 0) { //left
            pointNew = points[y][x - 1];
            addPointToQueue(pointNew, now);
        }
        if (diagonals) {
            if (y > 0 &&
                    x < (mapSizeX - 1)) {  //top-right
                pointNew = points[y - 1][x + 1];
                addPointToQueue(pointNew, now);
            }
            if (x < (mapSizeX - 1) &&
                    y < (mapSizeY - 1)) { //right-bot
                pointNew = points[y + 1][x + 1];
                addPointToQueue(pointNew, now);
            }
            if (y < (mapSizeY - 1) &&
                    x > 0) { //bot-left
                pointNew = points[y + 1][x - 1];
                addPointToQueue(pointNew, now);
            }
            if (y > 0 &&
                    x > 0) { //left-top
                pointNew = points[y - 1][x - 1];
                addPointToQueue(pointNew, now);
            }
        }
    }

    private void addPointToQueue(Point now, Point parent) {
        if (now.getState() == PointState.WALL) {

        } else {
            if (now.getState() == PointState.FREE) {
                now.setState(PointState.ADDED);
            }
            now.setParent(parent);
            now.setValueToThisPoint(parent.getValueToThisPoint() + calculateValue3D(now, parent));
            now.setValueToEndPoint(calculateValue3D(now, end));
            pointPriorityQueue.add(now);
        }

    }

}
