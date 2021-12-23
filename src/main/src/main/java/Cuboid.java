import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Cuboid {
    private int x1, y1, z1, x2, y2, z2;
    private Boolean on = null;
    private int id;
    private boolean splitted = false;

    public Cuboid(int id, String s) {
        this.id = id;
        if (s.trim().startsWith("on")) {
            on = Boolean.TRUE;
        } else {
            on = Boolean.FALSE;
        }

        String[] coord = s.trim().substring(3).trim().split(",");
        for (String c : coord) {
            if(c.startsWith("x=")) {
                String[] xc = c.substring(2).split("\\.\\.");
                int first = Integer.parseInt(xc[0]);
                int second = Integer.parseInt(xc[1]);

                x1 = Math.min(first, second);
                x2 = Math.max(first, second);
            } else if(c.startsWith("y=")) {
                String[] yc = c.substring(2).split("\\.\\.");
                int first = Integer.parseInt(yc[0]);
                int second = Integer.parseInt(yc[1]);

                y1 = Math.min(first, second);
                y2 = Math.max(first, second);
            } else if(c.startsWith("z=")) {
                String[] zc = c.substring(2).split("\\.\\.");
                int first = Integer.parseInt(zc[0]);
                int second = Integer.parseInt(zc[1]);

                z1 = Math.min(first, second);
                z2 = Math.max(first, second);
            }
        }
    }

    public Cuboid(int id,  int x1, int y1, int z1, int x2, int y2, int z2) {
        this.id = id;
        this.x1 = x1;
        this.y1 = y1;
        this.z1 = z1;
        this.x2 = x2;
        this.y2 = y2;
        this.z2 = z2;
        this.on = Boolean.TRUE;
    }

    public Boolean on(int x, int y, int z) {
        if (x < this.x1) return null;
        if (x > this.x2) return null;
        if (y < this.y1) return null;
        if (y > this.y2) return null;
        if (z < this.z1) return null;
        if (z > this.z2) return null;
        return on;
    }

    public boolean intersect(Cuboid c) {
        return Math.max(x1, c.x1) <= Math.min(x2, c.x2) &&
                Math.max(y1, c.y1) <= Math.min(y2, c.y2) &&
                Math.max(z1, c.z1) <= Math.min(z2, c.z2);
    }

    public List<Cuboid> split(List<Cuboid> list) throws Exception {
        List<Cuboid> cuboids = new ArrayList<>();
        for (Cuboid c : list) {
            cuboids.addAll(split(c));
        }
        return cuboids;
    }

    public List<Cuboid> split(Cuboid c) {
        Cuboid first = this;
        Cuboid second = c;
        if (this.id > c.id) {
            first = c;
            second = this;
        }

        if(first.on && second.on) {
            List<Cuboid> cuboids = new ArrayList<>();
            if (this.size().compareTo(c.size()) < 0) {
                first = c;
                second = this;
            } else {
                first = this;
                second = c;
            }
            cuboids.addAll(merge(first, second));
            cuboids.add(first);
            return cuboids;
        }
        if(!first.on && !second.on) {
            return new ArrayList<>();
        }
        if(!first.on && second.on) {
            return List.of(second);
        }
        if(first.on && !second.on) {
            return cut(first, second);
        }
        return new ArrayList<>();
    }

    private List<Cuboid> cut(Cuboid first, Cuboid second) {
        List<Cuboid> cuboids = new ArrayList<>();

        if (first.x1 < second.x1) {
            cuboids.add(new Cuboid(first.id + 1, first.x1, first.y1, first.z1, second.x1 - 1, first.y2, first.z2));
        }
        if (first.x2 > second.x2) {
            cuboids.add(new Cuboid(first.id + 1, second.x2 + 1, first.y1, first.z1, first.x2, first.y2, first.z2));
        }

        if (first.y1 < second.y1) {
            cuboids.add(new Cuboid(first.id + 1, first.x1, first.y1, first.z1, first.x2, second.y1 - 1, first.z2));
        }
        if (first.y2 > second.y2) {
            cuboids.add(new Cuboid(first.id + 1, first.x1, second.y2 + 1, first.z1, first.x2, first.y2, first.z2));
        }

        if (first.z1 < second.z1) {
            cuboids.add(new Cuboid(first.id + 1, first.x1, first.y1, first.z1, first.x2, first.y2, second.z1 - 1));
        }
        if (first.z2 > second.z2) {
            cuboids.add(new Cuboid(first.id + 1, first.x1, first.y1, second.z2 + 1, first.x2, first.y2, first.z2));
        }

        second.splitted();
        return cuboids;
    }

    private List<Cuboid> merge(Cuboid first, Cuboid second) {
        List<Cuboid> cuboids = new ArrayList<>();
        if (
            first.x1 <= second.x1 && first.x2 >= second.x2 &&
            first.y1 <= second.y1 && first.y2 >= second.y2 &&
            first.z1 <= second.z1 && first.z2 >= second.z2
        ) {
            second.splitted();
            return cuboids;
        }

        if (first.x1 > second.x1) {
            cuboids.add(new Cuboid(first.id + 1, second.x1, second.y1, second.z1, first.x1 - 1, second.y2, second.z2));
        }
        if (first.x2 < second.x2) {
            cuboids.add(new Cuboid(first.id + 1, first.x2 + 1, second.y1, second.z1, second.x2, second.y2, second.z2));
        }

        if (first.y1 > second.y1) {
            cuboids.add(new Cuboid(first.id + 1, second.x1, second.y1, second.z1, second.x2, first.y1 - 1, second.z2));
        }
        if (first.y2 < second.y2) {
            cuboids.add(new Cuboid(first.id + 1, second.x1, first.y2 + 1, second.z1, second.x2, second.y2, second.z2));
        }

        if (first.z1 > second.z1) {
            cuboids.add(new Cuboid(first.id + 1, second.x1, second.y1, second.z1, second.x2, second.y2, first.z1 - 1));
        }
        if (first.z2 < second.z2) {
            cuboids.add(new Cuboid(first.id + 1, second.x1, second.y1, first.z2 + 1, second.x2, second.y2, second.z2));
        }
        second.splitted();
        return cuboids;
    }

    private void splitted() {
        this.splitted = true;
    }

    @Override
    public String toString() {
        return "Cuboid{" +
                "x=" + x1 + ".." + x2 +
                ",y=" + y1 + ".." + y2 +
                ",z=" + z1 + ".." + z2 +
                ", on=" + on +
                ", sz=" + size() +
                '}';
    }

    public BigInteger size() {
        int xSize = Math.abs(x2 - x1) + 1;
        int ySize = Math.abs(y2 - y1) + 1;
        int zSize = Math.abs(z2 - z1) + 1;
        BigInteger xBi = new BigInteger("" + xSize);
        BigInteger yBi = new BigInteger("" + ySize);
        BigInteger zBi = new BigInteger("" + zSize);
        return xBi.multiply(yBi).multiply(zBi);
    }

    public boolean isOn() {
        return on;
    }

    public boolean isSplitted() {
        return this.splitted;
    }

    public void setId(int id) {
        this.id = id;
    }
}
