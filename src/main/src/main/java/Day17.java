public class Day17 {
    public static void main(String[] args) {
        int highestY = 0;
        int count = 0;
        for (int x = -1000; x < 1000; x++) {
            for (int y = -1000; y < 1000; y++) {
                //Probe p = new Probe(x, y, 20, -5, 30, -10);
                Probe p = new Probe(x, y, 88, -103, 125, -157);
                while (p.aboveBottomTarget()) {
                    p.step();
                    if (p.withinTarget()) {
                        count++;
                        /*
                        if (highestY < p.getHighestY()) {
                            highestY = p.getHighestY();
                        }
                         */
                        break;
                    }
                }
            }
        }
        //System.out.println(highestY);
        System.out.println(count);
    }
}
