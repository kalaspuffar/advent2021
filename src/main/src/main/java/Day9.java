import bingo.BingoBoard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Day9 {
    public static final int HEIGHT = 100;
    public static final int WIDTH = 100;


    public static void main(String[] args) {
        String data = """
2199943210
3987894921
9856789892
8767896789
9899965678                
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day9a.txt"));

            int[] map = new int[HEIGHT * WIDTH];

            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                for(String num : line.trim().split("")) {
                    map[i] = Integer.parseInt(num);
                    i++;
                }
            }

            List<Integer> sizes = new ArrayList<>();

            int count = 0;
            for (int y = 0; y < HEIGHT; y++) {
                for (int x = 0; x < WIDTH; x++) {
                    int val = map[y * WIDTH + x];
                    int north = 10;
                    int south = 10;
                    int west = 10;
                    int east = 10;
                    if (y > 0) {
                        north = map[(y - 1) * WIDTH + x];
                    }
                    if (y < HEIGHT - 1) {
                        south = map[(y + 1) * WIDTH + x];
                    }
                    if (x > 0) {
                        west = map[y * WIDTH + x - 1];
                    }
                    if (x < WIDTH - 1) {
                        east = map[y * WIDTH + x + 1];
                    }

                    if (
                        val < north && val < south &&
                        val < west && val < east
                    ) {
                        boolean[] visited = new boolean[WIDTH * HEIGHT];
                        Arrays.fill(visited, false);
                        sizes.add(getSize(map, visited, x, y));
                    }
                }
            }

            Collections.sort(sizes, (a, b) -> b - a);

            System.out.println(sizes.get(0) * sizes.get(1) * sizes.get(2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int getSize(int[] map, boolean[] visited, int x, int y) {
        int count = map[y * WIDTH + x];
        visited[y * WIDTH + x] = true;
        if (count == 9) return 0;
        count = 1;

        if (y > 0 && !visited[(y - 1) * WIDTH + x]) {
            count += getSize(map, visited, x, y - 1);
        }
        if (y < HEIGHT - 1 && !visited[(y + 1) * WIDTH + x]) {
            count += getSize(map, visited, x, y + 1);
        }
        if (x > 0 && !visited[y * WIDTH + x - 1]) {
            count += getSize(map, visited, x - 1, y);
        }
        if (x < WIDTH - 1 && !visited[y * WIDTH + x + 1]) {
            count += getSize(map, visited, x + 1, y);
        }
        return count;
    }
}
