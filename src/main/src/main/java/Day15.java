import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Day15 {

    public static int getShortestPath(byte[] map, int x, int y, int maxX, int maxY) {
        int val = map[y * maxX + x];
        if (x + 1 >= maxX && y + 1 >= maxY) {
            return val;
        } else if (x + 1 >= maxX) {
            val += getShortestPath(map, x, y + 1, maxX, maxY);
        } else if (y + 1 >= maxY) {
            val += getShortestPath(map, x + 1, y, maxX, maxY);
        } else {
            int val1 = getShortestPath(map, x, y + 1, maxX, maxY);
            int val2 = getShortestPath(map, x + 1, y, maxX, maxY);
            return val + Math.min(val1, val2);
        }
        return val;
    }

    public static void main(String[] args) {
        String data = """
1163751742
1381373672
2136511328
3694931569
7463417111
1319128137
1359912421
3125421639
1293138521
2311944581
                """;

        try {

            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day15a.txt"));

            int MAXX = 100;
            int MAXY = 100;

            byte[] map = new byte[MAXX * MAXY];

            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                for (String s : line.split("")) {
                    map[i] = (byte) Integer.parseInt(s);
                    i++;
                }
            }

            int val1 = getShortestPath(map, 0,1, MAXX, MAXY);
            int val2 = getShortestPath(map, 1,0, MAXX, MAXY);
            System.out.println(Math.min(val1, val2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
