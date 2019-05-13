package greedy;

import utils.Pair;
import utils.PointState;

public class Point implements Comparable<Point> {
    private Pair pair;
    private Point parent;
    private PointState state;
    private int valueToThisPoint;
    private int valueToEndPoint;

    public Point(Pair pair) {
        this.pair = pair;
        this.state = PointState.FREE;
    }

    public Point getParent() {
        return parent;
    }

    public void setParent(Point parent) {
        this.parent = parent;
    }

    public PointState getState() {
        return state;
    }

    public void setState(PointState state) {
        this.state = state;
    }

    public Pair getPair() {
        return pair;
    }

    public int getValueToThisPoint() {
        return valueToThisPoint;
    }

    public int getValueToEndPoint() {
        return valueToEndPoint;
    }

    public void setValueToThisPoint(int valueToThisPoint) {
        this.valueToThisPoint = valueToThisPoint;
    }

    public void setValueToEndPoint(int valueToEndPoint) {
        this.valueToEndPoint = valueToEndPoint;
    }

    public void increaseValueToThisPoint(int value) {
        valueToThisPoint += value;
    }

    @Override
    public int compareTo(Point point) {
        if (this.getValueToEndPoint() > point.getValueToEndPoint()) {
            return 1;
        } else if (this.getValueToEndPoint() < point.getValueToEndPoint()) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        if (parent != null) {
            return "Point{" +
                    "pair=" + pair +
                    ", parent=" + parent.getPair() +
                    ", state=" + state +
                    ", valueToThisPoint=" + valueToThisPoint +
                    ", valueToEndPoint=" + valueToEndPoint +
                    '}';
        } else {
            return "Point{" +
                    "pair=" + pair +
                    ", state=" + state +
                    ", valueToThisPoint=" + valueToThisPoint +
                    ", valueToEndPoint=" + valueToEndPoint +
                    '}';
        }
    }
}
