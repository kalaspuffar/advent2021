import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day25 {
    public static void main(String[] args) {
        String data = """
v...>>.vv>
.vv>>.vv..
>>.>v>...v
>>v>>.>.v.
v>v.vv.v..
>.>>..v...
.vv..>.>v.
v.v..>>v.v
....v..v.>                
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day25a.txt"));

            int MAXX = 139;
            int MAXY = 137;

            byte[] map = new byte[MAXX * MAXY];
            Arrays.fill(map, (byte)0);

            int i = 0;
            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                String[] lineArr = line.split("");
                for (String s : lineArr) {
                    if(s.equals(">")) {
                        map[i] = 1;
                    }
                    if(s.equals("v")) {
                        map[i] = 2;
                    }
                    i++;
                }
            }

            //printMap(map, MAXX, MAXY);
            long[] changes = new long[1];
            changes[0] = 1;
            int countMoves = 0;
            while (changes[0] != 0) {
                changes[0] = 0;
                map = move(map, MAXX, MAXY, changes);
                countMoves++;
            }

            System.out.println(countMoves);
            //printMap(map, MAXX, MAXY);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static byte[] move(byte[] map, int maxx, int maxy, long[] changes) {
        byte[] newMap = Arrays.copyOf(map, maxx * maxy);
        for (int y = 0; y < maxy; y++) {
            for (int x = 0; x < maxx; x++) {
                if(map[y * maxx + x] == 1) {
                    if(x + 1 >= maxx && map[y * maxx] == 0) {
                        newMap[y * maxx + x] = 0;
                        newMap[y * maxx] = 1;
                        changes[0]++;
                    } else if (x + 1 < maxx && map[y * maxx + x + 1] == 0){
                        newMap[y * maxx + x] = 0;
                        newMap[y * maxx + x + 1] = 1;
                        changes[0]++;
                    }
                }
            }
        }

        map = newMap;
        newMap = Arrays.copyOf(map, maxx * maxy);

        for (int y = 0; y < maxy; y++) {
            for (int x = 0; x < maxx; x++) {
                if(map[y * maxx + x] == 2) {
                    if(y + 1 >= maxy && map[x] == 0) {
                        newMap[y * maxx + x] = 0;
                        newMap[x] = 2;
                        changes[0]++;
                    } else if (y + 1 < maxy && map[(y + 1) * maxx + x] == 0){
                        newMap[y * maxx + x] = 0;
                        newMap[(y + 1) * maxx + x] = 2;
                        changes[0]++;
                    }
                }
            }
        }
        return newMap;
    }

    public static void printMap(byte[] map, int maxx, int maxy) {
        System.out.print("============================");
        for (int y = 0; y < maxy; y++) {
            System.out.println();
            for (int x = 0; x < maxx; x++) {
                if(map[y * maxx + x] == 1) {
                    System.out.print(">");
                } else {
                    System.out.print(map[y * maxx + x] == 2 ? "v" : ".");
                }
            }
        }
        System.out.println();
        System.out.println("============================");
    }
}
