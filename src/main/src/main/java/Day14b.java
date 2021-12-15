import java.io.*;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class Day14b {
    public static Map<Integer, List<Integer>> getCombinations(Map<BytePair, byte[]> template) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(BytePair b : template.keySet()) {
            if(!map.containsKey((int)b.getFirst())) {
                map.put((int)b.getFirst(), new ArrayList<>());
            }
            map.get((int)b.getFirst()).add((int)b.getSecond());
        }
        return map;
    }


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

            Map<BytePair, byte[]> template = new HashMap<>();

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

                BytePair bp = new BytePair(templateArr[0]);

                template.put(
                        bp,
                        bp.getResult(templateArr[1])
                );
            }

            Map<Integer, List<Integer>> combo = getCombinations(template);
            for(Integer i : combo.keySet()) {
                for (Integer j : combo.get(i)) {
                    for (Integer k : combo.get(j)) {
                        for (Integer l : combo.get(k)) {
                            for (Integer m : combo.get(l)) {
                                for (Integer n : combo.get(m)) {
                                    for (Integer o : combo.get(n)) {
                                        ByteMulti bq = new ByteMulti(i, j, k, l, m, n, o);
                                        template.put(
                                            bq,
                                            bq.getResult(template)
                                        );
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.print(".");
            }

            System.out.println(template.keySet().size());

            File tempFolder = new File("chars");
            tempFolder.mkdirs();

            File startFile = new File(tempFolder, "0");
            FileOutputStream startStream = new FileOutputStream(startFile);
            startStream.write(workLine.getBytes(StandardCharsets.UTF_8));
            startStream.flush();
            startStream.close();

            byte[] dtl = new byte[32768];

            int MAX = 40;

            for (int j=0; j<MAX; j++) {
                Instant begin = Instant.now();
                FileInputStream ios = new FileInputStream(new File(tempFolder, "" + j));
                int read = 0;
                File outputFile = new File(tempFolder, "" + (j+1));
                FileOutputStream outputStream = new FileOutputStream(outputFile);

                int numMisses = 0;

                BytePair term;
                while ((read = ios.read(dtl)) != -1) {
                    int k = 1;
                    term = new ByteMulti(dtl[0]);
                    outputStream.write(dtl[0]);
                    for (; k + 5 < read; k += 6) {
                        ((ByteMulti)term).newBytes(dtl[k], dtl[k+1], dtl[k+2], dtl[k+3], dtl[k+4], dtl[k+5]);
                        outputStream.write(template.get(term));
                    }

                    term = new BytePair();
                    term.newByte(dtl[k-1]);
                    for (; k < read; k++) {
                        term.newByte(dtl[k]);
                        outputStream.write(template.get(term));
                    }
                }
                outputStream.close();
                ios.close();

                Instant end = Instant.now();
                System.out.println("Step " + j + " = " + Duration.between(begin, end));
//                System.out.println("Num miss: " + numMisses);

                Map<Integer, BigInteger> map = new HashMap<>();

                FileInputStream ios2 = new FileInputStream(new File(tempFolder, "" + (j+1)));
                int read2 = 0;
                BigInteger total = BigInteger.ZERO;
                while ((read2 = ios2.read(dtl)) != -1) {
                    for (int k = 0; k < read2; k++) {
                        int find = dtl[k];
                        if (!map.containsKey(find)) {
                            map.put(find, BigInteger.ZERO);
                        }
                        map.put(find, map.get(find).add(BigInteger.ONE));
                        total = total.add(BigInteger.ONE);
                    }
                }

                List<BigInteger> vals = new ArrayList(map.values());
                Collections.sort(vals);

                for (BigInteger b : vals) System.out.print(b + ",");
                System.out.println(
                    vals.get(vals.size()-1) + " - " +
                    vals.get(0) + " = " +
                    vals.get(vals.size()-1).subtract(vals.get(0))
                );

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
