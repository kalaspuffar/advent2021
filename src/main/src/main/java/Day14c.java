import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Day14c {

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

            Map<String, List<String>> template = new HashMap<>();

            String workLine = "";

            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                if (line.isBlank()) continue;
                if (!line.contains(" -> ")) {
                    workLine = line;
                    continue;
                }
                String[] templateArr = line.split(" -> ");

                String[] tempCharArr = templateArr[0].split("");
                String result1 = tempCharArr[0] + templateArr[1];
                String result2 = templateArr[1] + tempCharArr[1];
                template.put(templateArr[0], List.of(result1, result2));
            }

            Map<String, BigInteger> occurences = new HashMap<>();

            for (int i = 0; i < workLine.length() - 1; i++) {
                String term = workLine.substring(i, i + 2);
                if (!occurences.containsKey(term)) {
                    occurences.put(term, BigInteger.ZERO);
                }
                occurences.put(term, occurences.get(term).add(BigInteger.ONE));
            }

            for (int j=0; j<40; j++) {
                Instant begin = Instant.now();

                Map<String, BigInteger> newOccurences = new HashMap<>();

                for (Map.Entry<String, BigInteger> term : occurences.entrySet()) {
                    List<String> work = template.get(term.getKey());
                    for (String s : work) {
                        if (!newOccurences.containsKey(s)) {
                            newOccurences.put(s, BigInteger.ZERO);
                        }
                        newOccurences.put(s, newOccurences.get(s).add(term.getValue()));
                    }
                }

                occurences = newOccurences;

                Instant end = Instant.now();
                System.out.println("Step " + j + " = " + Duration.between(begin, end));
            }

            Map<String, BigInteger> result = new HashMap<>();
            for (Map.Entry<String, BigInteger> entry : occurences.entrySet()) {
                String s = entry.getKey().split("")[0];
                if (!result.containsKey(s)) {
                    result.put(s, BigInteger.ZERO);
                }
                result.put(s, result.get(s).add(entry.getValue()));
            }


            List<BigInteger> vals = new ArrayList(result.values());
            Collections.sort(vals);

            System.out.println(vals.get(vals.size()-1).subtract(vals.get(0)).add(BigInteger.ONE));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}