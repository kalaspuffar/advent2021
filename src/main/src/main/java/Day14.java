
import cave.Path;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Day14 {

    public static void main(String[] args) {
        String data = """
NNCB

CH -> B
HH -> N
CB -> H
NH -> C
HB -> C
HC -> B
HN -> C
NN -> C
BH -> H
NC -> B
NB -> B
BN -> B
BB -> N
BC -> B
CC -> N
CN -> C                
                """;

        try {

            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day14a.txt"));

            Map<String, String> template = new HashMap<>();

            String workLine = "";

            //String line;
            //while ((line = br.readLine()) != null) {
            for (String line : data.split("\n")) {
                if (line.isBlank()) continue;
                if (!line.contains(" -> ")) {
                    workLine = line;
                    continue;
                }
                String[] templateArr = line.split(" -> ");

                template.put(templateArr[0], templateArr[1]);
            }

            for (int j=0; j<40; j++) {
                Instant begin = Instant.now();
                String newString = "";
                for (int i = 0; i < workLine.length() - 1; i++) {
                    String term = workLine.substring(i, i + 2);
                    String work = template.get(term);
                    if (work == null) {
                        newString += workLine.substring(i, i + 1);
                    } else {
                        newString += workLine.substring(i, i + 1);
                        newString += work;
                    }
                }
                newString += workLine.substring(workLine.length() - 1);
                workLine = newString;
                Instant end = Instant.now();
                System.out.println("Step " + j + " = " + Duration.between(begin, end));
            }


            Map<String, BigInteger> map = new HashMap<>();
            for (int i = 0; i < workLine.length(); i++) {
                String find = workLine.substring(i, i + 1);
                if (!map.containsKey(find)) {
                    map.put(find, BigInteger.ZERO);
                }
                map.put(find, map.get(find).add(BigInteger.ONE));
            }

            List<BigInteger> vals = new ArrayList(map.values());
            Collections.sort(vals);

            System.out.println(vals.get(vals.size()-1).subtract(vals.get(0)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
