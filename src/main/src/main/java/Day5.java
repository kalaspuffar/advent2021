import javax.sound.sampled.Line;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day5 {
    public static void main(String[] args) {
        String data = """
0,9 -> 5,9
8,0 -> 0,8
9,4 -> 3,4
2,2 -> 2,1
7,0 -> 7,4
6,4 -> 2,0
0,9 -> 2,9
3,4 -> 1,4
0,0 -> 8,8
5,5 -> 8,2
        """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day5a.txt"));

            byte[] map = new byte[LineSegment.MAPSIZE * LineSegment.MAPSIZE];

            Arrays.fill(map, (byte)0);

            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                var ls = new LineSegment(line);
                ls.map(map);
            }
/*
            for (int i = 0; i < 100; i++) {
                if(i % 10 == 0) System.out.println();
                System.out.print(map[i]);
            }
*/
            int count = 0;
            for (byte b : map) {
                if(b > 1) {
                    count++;
                }
            }

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
