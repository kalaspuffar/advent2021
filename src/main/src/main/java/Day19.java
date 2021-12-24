import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day19 {

    public static List<Scanner> readScanners() throws Exception {
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

        br.close();

        scanners.add(scanner);
        return scanners;
    }

    public static void findScannerPositions(List<Scanner> scanners, int initialScanner) {
        List<Scanner> knownScanners = new ArrayList<>();
        List<Scanner> testScanners = new ArrayList<>();

        testScanners.add(scanners.get(initialScanner));
        scanners.remove(initialScanner);

        Instant before = Instant.now();
        while (!scanners.isEmpty()) {
            for (Scanner s : scanners) {
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
        System.out.println(Duration.between(before, Instant.now()));

        Set<Beacon> allBeacons = new HashSet<>();
        for (Scanner s : knownScanners) {
            allBeacons.addAll(s.getBeacons());
        }
        for (Scanner s : testScanners) {
            allBeacons.addAll(s.getBeacons());
        }

        System.out.println("Number of beacons: " + allBeacons.size());

        scanners.clear();
        scanners.addAll(knownScanners);
        scanners.addAll(testScanners);
    }

    public static long getManhattanDistance(List<Scanner> scanners) {
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
        return largestDistance;
    }

    public static void main(String[] args) {
        try {
            for (int initialScanner = 2; initialScanner < 25; initialScanner++) {
                List<Scanner> scanners = readScanners();
                findScannerPositions(scanners, initialScanner);


                System.out.println("Initial scanner: " + initialScanner);
                System.out.println("Largest manhattan distance: " + getManhattanDistance(scanners));
            }
            /*
            Initial scanner: 0
            Largest manhattan distance: 12247 - Too high
            Initial scanner: 1
            Largest manhattan distance: 13005
            Initial scanner: 2
            Largest manhattan distance: 12124
            Initial scanner: 3
            Largest manhattan distance: 15849
            Initial scanner: 4
            Largest manhattan distance: 12124
            Initial scanner: 5
            Largest manhattan distance: 15296
             */


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
