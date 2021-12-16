import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.sql.Array;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Day15 {
/*
    public static int getShortestPath(int[] visited, int visitedCount, Point[] map, int x, int y, int maxX, int maxY, int dirx, int diry) {
        if (x < 0) return maxY * maxX;
        if (y < 0) return maxY * maxX;
        if (x >= maxX) return maxY * maxX;
        if (y >= maxY) return maxY * maxX;

        if (map[y * maxX + x].hasValue()) {
            return map[y * maxX + x].getLowestValue();
        }

        int id = map[y * maxX + x].getId();
        for (int i = 0; i < visitedCount; i++) {
            if (id == visited[i]) {
                return maxY * maxX;
            }
        }
        visited[visitedCount] = id;

        if (visitedCount > 6000) {
            return maxY * maxX;
        }

        int val = map[y * maxX + x].getPointValue();
        if (x == maxX - 1 && y == maxY - 1) {
            map[y * maxX + x].setLowestValue(val);
            return val;
        } else {
            int val1_1 = getShortestPath(visited, visitedCount+1, map, x, y + 1, maxX, maxY, 0, 1);
            int val1_2 = getShortestPath(visited, visitedCount+1, map, x, y - 1, maxX, maxY, 0, -1);
            int val1 = Math.min(val1_1, val1_2);

            int val2_1 = getShortestPath(visited, visitedCount+1, map, x + 1, y, maxX, maxY, 1, 0);
            int val2_2 = getShortestPath(visited, visitedCount+1, map, x - 1, y, maxX, maxY, -1, 0);
            int val2 = Math.min(val2_1, val2_2);

            val += Math.min(val1, val2);
        }

        if (val < 7000) {
            map[y * maxX + x].setLowestValue(val);
            return map[y * maxX + x].getLowestValue();
        } else {
            return val;
        }
    }
*/
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

            Point[] map = new Point[MAXX * MAXY * 25];
            Arrays.fill(map, new Point(0,0, 0));

            int x = 0;
            int y = 0;
            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                if (line.isBlank()) continue;
                for (String s : line.trim().split("")) {
                    if (x == MAXX) {
                        y++;
                        x = 0;
                    }
                    writeToMap(map, y, x, MAXX, MAXY, Integer.parseInt(s));
                    x++;
                }
            }
            int i = 0;
            for (Point b : map) {
                if(i % (MAXX * 5) == 0) System.out.println();
                System.out.print(b.getPointValue());
                i++;
            }
            System.out.println();

            int maxX = MAXX * 5;
            int maxY = MAXY * 5;

            map[0].setLowestValue(0);

            List<Point> myList = new ArrayList<>();
            map[1].setLowestValue(map[1].getPointValue());
            map[maxX].setLowestValue(map[maxX].getPointValue());
            myList.add(map[1]);
            myList.add(map[maxX]);
            while(!myList.isEmpty()) {
                List<Point> newList = new ArrayList<>();
                for(Point p : myList) {
                    x = p.getX();
                    y = p.getY();
                    int val = p.getLowestValue();
                    int north = (y - 1) * maxX + x;
                    int south = (y + 1) * maxX + x;
                    int west = y * maxX + x - 1;
                    int east = y * maxX + x + 1;

                    if (y > 0 && map[north].getLowestValue() > val + map[north].getPointValue()) {
                        map[north].setLowestValue(val + map[north].getPointValue());
                        newList.add(map[north]);
                    }
                    if (y < maxY - 1 && map[south].getLowestValue() > val + map[south].getPointValue()) {
                        map[south].setLowestValue(val + map[south].getPointValue());
                        newList.add(map[south]);
                    }
                    if (x > 0 && map[west].getLowestValue() > val + map[west].getPointValue()) {
                        map[west].setLowestValue(val + map[west].getPointValue());
                        newList.add(map[west]);
                    }
                    if (x < maxX - 1 && map[east].getLowestValue() > val + map[east].getPointValue()) {
                        map[east].setLowestValue(val + map[east].getPointValue());
                        newList.add(map[east]);
                    }

                }
                myList = newList;
            }

            System.out.println(map[map.length - 1].getLowestValue());

/*
            int[] visited = new int[MAXX * MAXY * 25];
            Arrays.fill(visited, 0);
            int val1 = getShortestPath(visited, 1, map, 0,1, MAXX * 5, MAXY * 5, 0, 1);
            int val2 = getShortestPath(visited, 1, map, 1,0, MAXX * 5, MAXY * 5, 1, 0);
            System.out.println(Math.min(val1, val2));
 */
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void writeToMap(Point[] map, int y, int x, int MAXX, int MAXY, int val) {

        for (int yy = 0; yy < 5; yy++) {
            for (int xx = 0; xx < 5; xx++) {
                int newVal = (val + xx + yy);
                while(newVal > 9) {
                    newVal -= 9;
                }
                int id = (y + yy * MAXY) * (MAXX * 5) + (x + xx * MAXX);
                map[id] = new Point((x + xx * MAXX), (y + yy * MAXY), newVal);
            }
        }
    }
}
