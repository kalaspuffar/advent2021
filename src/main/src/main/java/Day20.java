import java.io.BufferedReader;
import java.io.FileReader;

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
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day8a.txt"));

            //String line;
            //while ((line = br.readLine()) != null) {
            for(String line : data.split("\n")) {
                if (line.isBlank()) continue;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
