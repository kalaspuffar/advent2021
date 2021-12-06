public class LineSegment {
    public static final int MAPSIZE = 1000;
    int x1, x2, y1, y2;

    public LineSegment(String line) {
        String[] coorinates = line.split(" -> ");
        String[] positions = coorinates[0].split(",");
        x1 = Integer.parseInt(positions[0]);
        y1 = Integer.parseInt(positions[1]);

        positions = coorinates[1].split(",");
        x2 = Integer.parseInt(positions[0]);
        y2 = Integer.parseInt(positions[1]);
    }

    public void map(byte[] map) {
        if (x1 == x2) {
            for (int i = Math.min(y1, y2); i <= Math.max(y1, y2); i++) {
                map[MAPSIZE * i + x1]++;
            }
        } else if (y1 == y2) {
            for (int i = Math.min(x1, x2); i <= Math.max(x1, x2); i++) {
                map[MAPSIZE * y1 + i]++;
            }
        } else {
            if (y1 < y2 && x1 < x2) {
                for (int i = 0; i <= y2 - y1; i++) {
                    map[MAPSIZE * (y1 + i) + (x1 + i)]++;
                }
            } else if (y1 > y2 && x1 > x2) {
                for (int i = 0; i <= y1 - y2; i++) {
                    map[MAPSIZE * (y1 - i) + (x1 - i)]++;
                }
            } else if (y1 < y2 && x1 > x2) {
                for (int i = 0; i <= y2 - y1; i++) {
                    map[MAPSIZE * (y1 + i) + (x1 - i)]++;
                }
            } else if (y1 > y2 && x1 < x2) {
                for (int i = 0; i <= x2 - x1; i++) {
                    map[MAPSIZE * (y1 - i) + (x1 + i)]++;
                }
            }
        }
    }
}