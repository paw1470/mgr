import alg.Algorithms;
import alg.Algorithm;
import generator.Hill;
import utils.Coordinate;
import utils.Map;

public class Main {

    public static void main(String[] args) {
//        Map map = new Map(8, 8);
//        map.clear();
//        map.setFieldValue(new Coordinate(0, 5), 9999);
//        map.setFieldValue(new Coordinate(1, 5), 9999);
//        map.setFieldValue(new Coordinate(2, 5), 9999);
//        map.setFieldValue(new Coordinate(3, 5), 9999);
//        map.setFieldValue(new Coordinate(4, 5), 9999);
//
//        Algorithm algorithm1 = new Algorithm(map, new Coordinate(1, 0), new Coordinate(0, 7), true, Algorithms.GREEDY);
//        Algorithm algorithm2 = new Algorithm(map, new Coordinate(1, 0), new Coordinate(0, 7), true, Algorithms.GREEDY_WITH_MEMORY);
//        Algorithm algorithm3 = new Algorithm(map, new Coordinate(1, 0), new Coordinate(0, 7), true, Algorithms.BEST_FIRST_SEARCH);
//        Algorithm algorithm4 = new Algorithm(map, new Coordinate(1, 0), new Coordinate(0, 7), true, Algorithms.ASTAR);
//
//
//        algorithm1.search();
//        algorithm2.search();
//        algorithm3.search();
//        algorithm4.search();


        Hill hill = new Hill(6, 6, 1, 40, 1000, 1, false);
        hill.printHillsMapToFile("test.txt", 1);
        Map map = hill.getHillsMap(1);

        Algorithm algorithm1 = new Algorithm(map, new Coordinate(0, 0), new Coordinate(5, 5), true, Algorithms.GREEDY);
        Algorithm algorithm2 = new Algorithm(map, new Coordinate(0, 0), new Coordinate(5, 5), true, Algorithms.GREEDY_WITH_MEMORY);
        Algorithm algorithm3 = new Algorithm(map, new Coordinate(0, 0), new Coordinate(5, 5), true, Algorithms.BEST_FIRST_SEARCH);
        Algorithm algorithm4 = new Algorithm(map, new Coordinate(0, 0), new Coordinate(5, 5), true, Algorithms.ASTAR);

        algorithm1.search();
        algorithm2.search();
//        algorithm3.search();
//        algorithm4.search();

        System.out.println(algorithm1.getPathToEnd().size());
        System.out.println(algorithm2.getPathToEnd().size());
//        System.out.println(algorithm3.getPathToEnd().size());
//        System.out.println(algorithm4.getPathToEnd().size());



    }
}
