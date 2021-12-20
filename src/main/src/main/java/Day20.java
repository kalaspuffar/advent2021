import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Day20 {
    public static void main(String[] args) {

        String data = """
..#.#..#####.#.#.#.###.##.....###.##.#..###.####..#####..#....#..#..##..###..######.###...####..#..#####..##..#.#####...##.#.#..#.##..#.#......#.###.######.###.####...#.##.##..#..#..#####.....#.#....###..#.##......#.....#..#..#..##..#...##.######.####.####.#.#...#.......#..#.#.#...####.##.#......#..#...##.#.##..#...##.#.##..###.#......#.#.......#.#.#.####.###.##...#.....####.#..#..#.##.#....##..#.####....##...##..#...#......#.#.......#.......##..####..#...#.#.#...##..#.#..###..#####........#..####......#..#

#..#.
#....
##..#
..#..
..###
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day20a.txt"));

            String enhancement = null;

            Set<LitPixel> pixels = new HashSet<>();


            int startX = 200;
            int x = 200;
            int y = 200;

            int MAXX = 500;
            int MAXY = 500;

            String line;
            while ((line = br.readLine()) != null) {
            //for(String line : data.split("\n")) {
                if (line.isBlank()) continue;
                if(enhancement == null) {
                    enhancement = line;
                    continue;
                }

                x = startX;
                for(String s : line.split("")) {
                    if("#".equals(s)) {
                        pixels.add(new LitPixel(x, y));
                    }
                    x++;
                }
                y++;
            }

            //printMap(MAXX, MAXY, pixels);

            for (int i = 0; i < 50; i++) {
                Set<LitPixel> newPixels = new HashSet<>();
                for (int cx = 0; cx < MAXX; cx++) {
                    for (int cy = 0; cy < MAXY; cy++) {
                        if (LitPixel.isLitPixel(cx, cy, pixels, enhancement)) {
                            newPixels.add(new LitPixel(cx, cy));
                        }
                    }
                }
                pixels = newPixels;
                System.out.print(".");
            }
            System.out.println();

            //printMap(MAXX, MAXY, pixels);

            int count = 0;
            for (int cx = 70; cx < 430; cx++) {
                for (int cy = 70; cy < 430; cy++) {
                    if (pixels.contains(new LitPixel(cx, cy))) {
                        count++;
                    }
                }
            }

            printMap(MAXX, MAXY, pixels);

            //5717 to high
            //6395 to high

            // 24371 to high
            // 16734 to low
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void printMap(int x, int y, Set<LitPixel> pixels) {
        System.out.println("======================================");
        for (int cy = 0; cy < y; cy++) {
            for (int cx = 0; cx < x; cx++) {
                System.out.print(pixels.contains(new LitPixel(cx, cy)) ? "#" : ".");
            }
            System.out.println();
        }
        System.out.println("======================================");
    }
}
