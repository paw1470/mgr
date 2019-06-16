//package generator;
//
//import lombok.Getter;
//import utils.Coordinate;
//import utils.Map;
//
//import java.util.Random;
//
//public class HillGenerator {
//    private int xSize;
//    private int ySize;
//    private double hillMin;
//    private double hillMax;
//    private int numHills;
//    private int flattening;
//    private boolean isIsland;
//    private Random random;
//
//    @Getter
//    private HillMap hillMap;
//
//
//    public HillGenerator(int xSize, int ySize, double hillMin, double hillMax, int numHills, int flattening, boolean isIsland) throws IllegalAccessException {
//        if (xSize <= 0 && ySize <= 0)
//            throw new IllegalArgumentException("size");
//        if (hillMin < 0)
//            throw new IllegalArgumentException("hillMin");
//        if (hillMax < 0)
//            throw new IllegalArgumentException("hillMax");
//        if (hillMin > hillMax)
//            throw new IllegalArgumentException("hillDifference");
//        if (numHills < 0)
//            throw new IllegalArgumentException("numHills");
//        if (flattening < 0)
//            throw new IllegalArgumentException("flattening");
//
//        this.xSize = xSize;
//        this.ySize = ySize;
//
//        this.hillMin = hillMin;
//        this.hillMax = hillMax;
//        this.numHills = numHills;
//        this.flattening = flattening;
//        this.isIsland = isIsland;
//
//        hillMap = new HillMap(xSize, ySize);
//        random = new Random();
//
//        generate();
//    }
//
//    private void generate() {
//        hillMap.clear();
//        for (int i = 0; i < numHills; i++) {
//            addHill();
//        }
//        normalize();
////        flatten();
//    }
//
//
//    private void addHill() {
//        double radius = randomRange(hillMin, hillMax);
//        double x, y;
//        //without island code
//        x = randomRange(-radius, xSize + radius);
//        y = randomRange(-radius, ySize + radius);
//
//        double radiusSq = radius * radius;
//        double distSq;
//        double height;
//
//        int xMin = (int) (x - radius - 1);
//        int xMax = (int) (x + radius + 1);
//        if (xMin < 0)
//            xMin = 0;
//        if (xMax >= xSize)
//            xMax = xSize - 1;
//
//        int yMin = (int) (y - radius - 1);
//        int yMax = (int) (y + radius + 1);
//        if (yMin < 0)
//            yMin = 0;
//        if (yMax >= ySize)
//            yMax = ySize - 1;
//
//        for (int h = xMin; h <= xMax; h++) {
//            for (int v = yMin; v <= yMax; v++) {
//                distSq = (x - h) * (x - h) + (y + v) * (y - v);
//                height = radiusSq - distSq;
//
//                if (height > 0) {
//                    offsetCell(h, v, height);
//                }
//            }
//        }
//
//    }
//
//    private void normalize() {
//        double min = getCell(0, 0);
//        double max = getCell(0, 0);
//        double value;
//        for (int x = 0; x < xSize; x++) {
//            for (int y = 0; y < ySize; y++) {
//                double z = getCell(x, y);
//                if (z < min)
//                    min = z;
//                if (z > max)
//                    max = z;
//            }
//        }
//
//        if (max != min) {
//            for (int x = 0; x < xSize; x++) {
//                for (int y = 0; y < ySize; y++) {
//                    value = getCell(x, y);
////                    double v1 = (double)(getCell( x, y ) - min);
////                    double v2 =  (max - min);
////                    double v3 = v1 / v2;
////                    double v = v3 * hillMax;          //todo poprawic
//                    setCell(x, y, ((value - min) / (max - min)));
//                }
//            }
//        } else {
//            hillMap.clear();
//        }
//    }
//
//    private void flatten() {
//        if (flattening > 1) {
//            for (int x = 0; x < xSize; x++) {
//                for (int y = 0; y < ySize; y++) {
//                    double flat = 1.0;
//                    double original = getCell(x, y);
//                    for (int i = 0; i < flattening; i++) {
//                        flat *= original;
//                    }
//                    setCell(x, y, flat);
//                }
//            }
//        }
//    }
//
//    private double randomRange(double min, double max) {
//        return (Math.random() * ((max - min) + 1)) + min;
//    }
//
//    private double getCell(int x, int y) {
//        return hillMap.getFieldValue(new Coordinate(x, y));
//    }
//
//    private void setCell(int x, int y, double v) {
//        hillMap.setFieldValue(new Coordinate(x, y), v);
//    }
//
//    private void offsetCell(int x, int y, double v) {
//        v += getCell(x, y);
//        setCell(x, y, v);
//    }
//
//}
