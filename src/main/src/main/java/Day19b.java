import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19b {
    public static void main(String[] args) {
        try {
            //BufferedReader br = new BufferedReader(new FileReader("inputs/input-day19b.txt"));
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day19b-test.txt"));

            List<Scanner> scanners = new ArrayList<>();

            String line;
            int scanNum = 0;
            while ((line = br.readLine()) != null) {
                if(line.isBlank()) continue;
                scanners.add(new Scanner(scanNum, line));
                scanNum++;
            }

            long largestDistance = 0;
            for (Scanner s1 : scanners) {
                for (Scanner s2 : scanners) {
                    if (s1.equals(s2)) continue;
                    long dist = s1.getManhattanDistance(s2);
                    if (dist > largestDistance) {
                        largestDistance = dist;
                    }
                }
            }

            System.out.println("Number of scanners: " + scanners.size());
            System.out.println("Largest manhattan distance: " + largestDistance);


            //Number of scanners: 25
            //Largest manhattan distance: 12247 - Too high

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
