package utils;

import lombok.Getter;

public class Map {
    @Getter
    private int xSize;

    @Getter
    private int ySize;
    private int[][] map;

    private final int WALL_HEIGHT = 9999999;

    public Map(int xSize, int ySize) {
        this.xSize = xSize;
        this.ySize = ySize;
        map = new int[ySize][xSize];
    }

    public void clear() {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                map[y][x] = 0;
            }
        }
    }

    public int getFieldValue(Pair pair) {
        return map[pair.getY()][pair.getX()];
    }

    public void setFieldValue(Pair pair, int value) {
        map[pair.getY()][pair.getX()] = value;
    }

    public void setWall(Pair pair) {
        map[pair.getY()][pair.getX()] = WALL_HEIGHT;
    }

    @Override
    public String toString() {
        String concatString = ", ";
        StringBuilder stringBuilder = new StringBuilder();
        for (int y = 0; y < ySize - 1; y++) {
            for (int x = 0; x < xSize - 1; x++) {
                stringBuilder.append(map[y][x]);
                stringBuilder.append(concatString);
            }
            stringBuilder.append(map[y][xSize - 1]);
            stringBuilder.append("\n");
        }
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder.toString();
    }
}
