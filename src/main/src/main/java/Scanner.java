import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scanner {
    private int id;
    private int x, y ,z;
    private Set<Beacon> beacons = new HashSet<>();

    public Scanner(int id) {
        this.id = id;
    }

    public Scanner(int id, String line) {
        this.id = id;
        Pattern xPattern = Pattern.compile("x=((-)*\\d+)");
        Matcher xMatcher = xPattern.matcher(line);
        if (xMatcher.find()) {
            x = Integer.parseInt(xMatcher.group(1));
        }

        Pattern yPattern = Pattern.compile("y=((-)*\\d+)");
        Matcher yMatcher = yPattern.matcher(line);
        if (yMatcher.find()) {
            y = Integer.parseInt(yMatcher.group(1));
        }

        Pattern zPattern = Pattern.compile("z=((-)*\\d+)");
        Matcher zMatcher = zPattern.matcher(line);
        if (zMatcher.find()) {
            z = Integer.parseInt(zMatcher.group(1));
        }
    }

    @Override
    public String toString() {
        return "Scanner{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public void addBeacon(int x, int y, int z) {
        Beacon beacon = new Beacon(x, y, z);
        beacons.add(beacon);
    }

    public boolean findScannerPos(Scanner scanner) {
        Set<Beacon> possiblePositions = new HashSet<>();
        for (Beacon b1 : beacons) {
            for (Beacon b2 : scanner.beacons) {
                possiblePositions.addAll(b1.getTestBeacons(b2, true));
            }
        }

        Instant before = Instant.now();
        for (Beacon pos : possiblePositions) {
            List<List<Beacon>> testBeacons = new ArrayList<>();
            for (Beacon b : beacons) {
                testBeacons.add(b.getTestBeacons(pos, false));
            }

            for (int i = 0; i < 15 * 6 * 6; i++) {
                int num = scanner.getNumberOverlapp(testBeacons, i);
                if (num > 11) {
                    changePos(pos.getX(), pos.getY(), pos.getZ(), testBeacons, i);
                    System.out.println("Found " + id + " = " + pos);
                    System.out.println(Duration.between(before, Instant.now()));
                    return true;
                }
            }
        }
        System.out.println(Duration.between(before, Instant.now()));
        return false;
    }

    private int getNumberOverlapp(List<List<Beacon>> testBeacons, int i) {
        int count = 0;
        for(List<Beacon> b : testBeacons) {
            if (beacons.contains(b.get(i))) count++;
        }
        return count;
    }

    public void changePos(int x, int y, int z, List<List<Beacon>> testBeacons, int i) {
        this.x = x;
        this.y = y;
        this.z = z;

        Set<Beacon> newBeacons = new HashSet<>();
        for (List<Beacon> b : testBeacons) {
            newBeacons.add(b.get(i));
        }
        beacons = newBeacons;
    }

    public Set<Beacon> getBeacons() {
        return beacons;
    }

    public long getManhattanDistance(Scanner s) {
        return Math.abs(s.x - this.x) + Math.abs(s.y - this.y) + Math.abs(s.z - this.z);
    }
}
