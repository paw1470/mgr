//package generator;
//
//import lombok.Getter;
//import utils.Coordinate;
//import utils.Map;
//
//public class HillMap {
//    @Getter
//    private int xSize;
//
//    @Getter
//    private int ySize;
//    private double[][] map;
//
//    public HillMap(int xSize, int ySize) {
//        this.xSize = xSize;
//        this.ySize = ySize;
//        map = new double[ySize][xSize];
//    }
//
//    public void clear() {
//        for (int y = 0; y < ySize; y++) {
//            for (int x = 0; x < xSize; x++) {
//                map[y][x] = 0;
//            }
//        }
//    }
//
//    public double getFieldValue(Coordinate coordinate) {
//        if (isInMap(coordinate)) {
//            return map[coordinate.getY()][coordinate.getX()];
//        } else {
//            throw new IllegalArgumentException("Wrong coordinates");
//        }
//    }
//
//    public void setFieldValue(Coordinate coordinate, double value) {
//        if (isInMap(coordinate)) {
//            map[coordinate.getY()][coordinate.getX()] = value;
//        } else {
//            throw new IllegalArgumentException("Wrong coordinates or 0 value");
//        }
//    }
//
//    public Map getMapInt(int height) {
//        Map mapInt = new Map(xSize, ySize);
//        for (int y = 0; y < ySize - 1; y++) {
//            for (int x = 0; x < xSize - 1; x++) {
//                mapInt.setFieldValue(new Coordinate(x, y), (int) (map[y][x] * height));
//            }
//        }
//        return mapInt;
//    }
//
//    @Override
//    public String toString() {
//        String concatString = ", ";
//        StringBuilder stringBuilder = new StringBuilder();
//        for (int y = 0; y < ySize - 1; y++) {
//            for (int x = 0; x < xSize - 1; x++) {
//                stringBuilder.append(map[y][x]);
//                stringBuilder.append(concatString);
//            }
//            stringBuilder.append(map[y][xSize - 1]);
//            stringBuilder.append("\n");
//        }
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        return stringBuilder.toString();
//    }
//
//    private boolean isInMap(Coordinate coordinate) {
//        return (coordinate.getX() < xSize && coordinate.getY() < ySize);
//    }
//}
