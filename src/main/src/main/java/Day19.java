import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day19a.txt"));
            //BufferedReader br = new BufferedReader(new FileReader("inputs/input-day19a-test.txt"));

            List<Scanner> scanners = new ArrayList<>();

            Scanner scanner = null;
            String line;
            int scanNum = 0;
            while ((line = br.readLine()) != null) {
                if(line.isBlank()) continue;
                if(line.startsWith("---")) {
                    if(scanner != null) {
                        scanners.add(scanner);
                    }
                    scanner = new Scanner(scanNum);
                    scanNum++;
                    continue;
                }

                String[] coord = line.split(",");
                scanner.addBeacon(
                    Integer.parseInt(coord[0]),
                    Integer.parseInt(coord[1]),
                    Integer.parseInt(coord[2])
                );
            }
            scanners.add(scanner);

            List<Scanner> knownScanners = new ArrayList<>();
            List<Scanner> testScanners = new ArrayList<>();

            testScanners.add(scanners.get(0));
            scanners.remove(0);

            while(!scanners.isEmpty()) {
                for(Scanner s : scanners) {
                    if (s.findScannerPos(testScanners.get(0))) {
                        testScanners.add(s);
                    }
                }
                knownScanners.add(testScanners.get(0));
                testScanners.remove(0);
                for (Scanner s : testScanners) {
                    scanners.remove(s);
                }
            }

            Set<Beacon> allBeacons = new HashSet<>();
            for(Scanner s : knownScanners) {
                allBeacons.addAll(s.getBeacons());
            }
            for(Scanner s : testScanners) {
                allBeacons.addAll(s.getBeacons());
            }

            System.out.println("Number of beacons: " + allBeacons.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
