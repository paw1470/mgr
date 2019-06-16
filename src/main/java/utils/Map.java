package utils;

import lombok.Getter;

public class Map {
    @Getter
    private int xSize;

    @Getter
    private int ySize;
    private int[][] map;

    private final int WALL_HEIGHT = 999999;

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

    public void setMapFrom09(int[][] map, int wallHeight, int multiplier) {
        for (int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                if (map[y][x] == wallHeight) {
                    this.map[y][x] = WALL_HEIGHT;
                } else {
                    this.map[y][x] = map[y][x] * multiplier;
                }
            }
        }
    }

    public int getFieldValue(Coordinate coordinate) {
        if (isInMap(coordinate)) {
            return map[coordinate.getY()][coordinate.getX()];
        } else {
            throw new IllegalArgumentException("Wrong coordinates");
        }
    }

    public void setFieldValue(Coordinate coordinate, int value) {
        if (isInMap(coordinate)) {
            map[coordinate.getY()][coordinate.getX()] = value;
        } else {
            throw new IllegalArgumentException("Wrong coordinates or 0 value");
        }
    }


    public void setWall(Coordinate coordinate) {
        map[coordinate.getY()][coordinate.getX()] = WALL_HEIGHT;
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

    private boolean isInMap(Coordinate coordinate) {
        return (coordinate.getX() < xSize && coordinate.getY() < ySize);
    }

    public int getWALL_HEIGHT() {
        return WALL_HEIGHT;
    }
}
