//package generator;
//
//import utils.Map;
//
//public class Hill {
//
//    private HillMap hillMap;
//    private HillGenerator hillGenerator;
//
//    public Hill(int xSize, int ySize, int hillMin, int hillMax, int numHills, int flattening, boolean isIsland) {
//        try {
//            hillGenerator = new HillGenerator(xSize, ySize, hillMin, hillMax, numHills, flattening, isIsland);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public Map getHillsMap(int height) {
//        return hillGenerator.getHillMap().getMapInt(height);
//    }
//
//    public void printHillsMapToFile(String filename, int height) {
//        HillPrinter hillPrinter = new HillPrinter();
//        hillPrinter.printToFileTxt(getHillsMap(height), ",  ", "", filename);
//    }
//}
