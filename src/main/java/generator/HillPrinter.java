package generator;

import utils.Coordinate;
import utils.Map;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HillPrinter {
    public void printToFileTxt(Map map, String separator, String path, String filename) {
        try {
            FileWriter fileWriter = new FileWriter(path + filename);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(map.getXSize());
            bufferedWriter.newLine();
            bufferedWriter.write(map.getYSize());
            bufferedWriter.newLine();
            for (int y = 0; y < map.getYSize(); y++) {
                for (int x = 0; x < map.getXSize(); x++) {
                    bufferedWriter.write(String.valueOf(map.getFieldValue(new Coordinate(x, y))));
                    bufferedWriter.write(separator);
                }
                bufferedWriter.newLine();
            }
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
