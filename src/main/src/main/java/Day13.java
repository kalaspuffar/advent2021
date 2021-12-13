
import cave.Path;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;
import java.util.regex.Matcher;

public class Day13 {


    public static void main(String[] args) {
        String test = """
6,10
0,14
9,10
0,3
10,4
4,11
6,0
6,12
4,1
0,13
10,12
3,4
3,0
8,4
1,10
2,14
8,10
9,0

fold along y=7
fold along x=5                
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day13a.txt"));

            List<String> foldingInstructions = new ArrayList<>();

            int maxX = 0;
            int maxY = 0;

            List<String> initialArr = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : test.split("\n")) {
                if (line.isBlank()) continue;
                if (line.startsWith("fold along")) {
                    foldingInstructions.add(line.substring("fold along".length()).trim());
                    continue;
                }
                String[] lineArr = line.trim().split(",");
                int x = Integer.parseInt(lineArr[0]);
                int y = Integer.parseInt(lineArr[1]);

                maxX = Math.max(x, maxX);
                maxY = Math.max(y, maxY);

                initialArr.add(line);
            }

            maxX++;
            maxY++;

            boolean[] initialData = new boolean[maxX * maxY];
            Arrays.fill(initialData, false);

            for (String line2 : initialArr) {
                if (line2.isBlank()) continue;
                if (line2.startsWith("fold along")) continue;
                String[] lineArr = line2.trim().split(",");
                int x = Integer.parseInt(lineArr[0]);
                int y = Integer.parseInt(lineArr[1]);

                initialData[maxX * y + x] = true;
            }

            //printMap(initialData, maxX);

            for (String fold : foldingInstructions) {
                if (fold.startsWith("y=")) {
                    int foldYPos = Integer.parseInt(fold.substring(2));
                    initialData = foldY(initialData, maxX, maxY, foldYPos);
                    maxY = foldYPos;
                } else {
                    int foldXPos = Integer.parseInt(fold.substring(2));
                    initialData = foldX(initialData, maxX, maxY, foldXPos);
                    maxX = foldXPos;
                }
            }


            printMap(initialData, maxX);

            int count = 0;
            for (boolean dot : initialData) {
                count += dot ? 1 : 0;
            }

            System.out.println(count);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean[] foldX(boolean[] initialData, int maxX, int maxY, int foldXPos) {
        boolean[] newData = new boolean[maxY * foldXPos];

        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < foldXPos; x++) {
                newData[y * foldXPos + x] = initialData[y * maxX + x];
            }
        }

        for (int y = 0; y < maxY; y++) {
            for(int x = 0; x < maxX - foldXPos - 1; x++) {
                newData[y * foldXPos + foldXPos - 1 - x] =
                        initialData[y * maxX + foldXPos + 1 + x] || newData[y * foldXPos + foldXPos - 1 - x];
            }
        }
        return newData;
    }

    private static boolean[] foldY(boolean[] initialData, int maxX, int maxY, int foldYPos) {
        boolean[] newData = Arrays.copyOf(initialData, maxX * foldYPos);

        for (int y = 0; y < maxY - foldYPos - 1; y++) {
            for(int x = 0; x < maxX; x++) {
                newData[(foldYPos - 1 - y) * maxX + x] =
                        initialData[(foldYPos + 1 + y) * maxX + x] || newData[(foldYPos - 1 - y) * maxX + x];
            }
        }
        return newData;
    }

    public static void printMap(boolean[] map, int maxX) {
        int i = 0;
        System.out.print("========================================");
        for (boolean v : map) {
            if (i % maxX == 0) System.out.println();
            System.out.print(v ? "#" : ".");
            i++;
        }
        System.out.println();
        System.out.println("========================================");
    }
}


/*

.#...
...#.
.....
.###.
.....
.....
.....
 */