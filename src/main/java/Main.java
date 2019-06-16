import alg.Algorithms;
import alg.Algorithm;
import utils.Coordinate;
import utils.Map;

public class Main {

    public static void main(String[] args) {


        Map map = new Map(10, 10);
        int[][] map1 = {
                {8, 8, 8, 8, 1, 1, 1, 1, 8, 8},
                {8, 8, 8, 8, 8, 8, 8, 1, 8, 8},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {1, 1, 1, 1, 1, 1, 1, 1, 8, 8},
                {1, 8, 8, 8, 8, 8, 8, 8, 8, 8},
                {1, 1, 1, 1, 1, 1, 1, 1, 8, 8},
                {0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {8, 8, 8, 8, 8, 8, 8, 1, 1, 1},
                {8, 8, 8, 8, 8, 8, 8, 8, 8, 1},
                {8, 8, 8, 1, 1, 1, 1, 1, 1, 1}};
        map.setMapFrom09(map1, 0, 100); //aktualnie jest ustawione 100 jako odleglosc miedzy 2 punktami wiec tutaj tez musi byc 100


        Algorithm algorithm1 = new Algorithm(map, new Coordinate(4, 0), new Coordinate(4, 9), true, Algorithms.GREEDY);
        Algorithm algorithm2 = new Algorithm(map, new Coordinate(4, 0), new Coordinate(4, 9), true, Algorithms.GREEDY_WITH_MEMORY);
        Algorithm algorithm3 = new Algorithm(map, new Coordinate(4, 0), new Coordinate(4, 9), true, Algorithms.BEST_FIRST_SEARCH);
        Algorithm algorithm4 = new Algorithm(map, new Coordinate(4, 0), new Coordinate(4, 9), true, Algorithms.ASTAR);

        algorithm1.search();
        algorithm2.search();
        algorithm3.search();
        algorithm4.search();

        algorithm1.infoOAlg();
        algorithm2.infoOAlg();
        algorithm3.infoOAlg();
        algorithm4.infoOAlg();

        System.out.println(algorithm3.getPathToEndString());
        System.out.println(algorithm4.getPathToEndString());

    }


}
