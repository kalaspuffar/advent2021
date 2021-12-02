import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Day1 {
    public static void main(String[] args) {
        String data = """
        199
        200
        208
        210
        200
        207
        240
        269
        260
        263
        """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day1a.txt"));

            int countIncrease = 0;
            int[] lastNumbers = new int[3];
            int[] currentNumbers = new int[3];
            int startCount = 0;
            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                if (line.isBlank()) continue;
                int i = Integer.parseInt(line);
                if (startCount < 3) {
                    if (startCount > 0) {
                        currentNumbers[startCount - 1] = i;
                    }
                    lastNumbers[startCount] = i;
                    startCount++;
                    continue;
                }

                currentNumbers[2] = i;

                int curr = 0;
                int last = 0;
                for (int j = 0; j < 3; j++) {
                    last += lastNumbers[j];
                    curr += currentNumbers[j];
                }

                if (curr > last) {
                    countIncrease++;
                }

                lastNumbers = Arrays.copyOf(currentNumbers, 3);

                for (int j = 0; j < 2; j++) {
                    currentNumbers[j] = currentNumbers[j + 1];
                }
            }
            System.out.println(countIncrease);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
