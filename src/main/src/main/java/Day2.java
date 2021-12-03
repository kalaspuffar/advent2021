import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Day2 {
    public static void main(String[] args) {
        String data = """
                forward 5
                down 5
                forward 8
                up 3
                down 8
                forward 2
                """;

        try {

            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day2a.txt"));

            int depth = 0;
            int horizontal = 0;
            int aim = 0;

            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                String[] arg = line.split(" ");
                String cmd = arg[0];
                int amount = Integer.parseInt(arg[1]);
                switch (cmd) {
                    case "forward":
                        horizontal += amount;
                        depth += aim * amount;
                        break;
                    case "down":
                        aim += amount;
                        break;
                    case "up":
                        aim -= amount;
                        break;
                }
            }

            System.out.println(depth * horizontal);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
