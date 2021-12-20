import java.util.*;

public class Beacon {
    private int x, y ,z;

    public Beacon(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Beacon beacon = (Beacon) o;
        return x == beacon.x && y == beacon.y && z == beacon.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }

    @Override
    public String toString() {
        return "Beacon{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                '}';
    }

    public Beacon getNewBeacon(int x, int y, int z) {
        return new Beacon(x - this.x, this.y + y, z - this.z);
    }

    public List<Beacon> getTestBeacons(int cx, int cy, int cz, int x, int y, int z) {
        List<Beacon> list = new ArrayList<>();
        list.add(new Beacon(cx + x, cy + y, cz + z));
        list.add(new Beacon(cx - x, cy + y, cz + z));
        list.add(new Beacon(cx + x, cy - y, cz + z));
        list.add(new Beacon(cx + x, cy + y, cz - z));

        list.add(new Beacon(cx + x, cy - y, cz - z));
        list.add(new Beacon(cx - x, cy + y, cz - z));
        list.add(new Beacon(cx - x, cy - y, cz + z));
        list.add(new Beacon(cx - x, cy - y, cz - z));

        list.add(new Beacon(x - cx, cy + y, cz + z));
        list.add(new Beacon(cx + x, y - cy, cz + z));
        list.add(new Beacon(cx + x, cy + y, z - cz));

        list.add(new Beacon(cx + x, y - cy, z - cz));
        list.add(new Beacon(x - cx, cy + y, z - cz));
        list.add(new Beacon(x - cx, y - cy, cz + z));

        list.add(new Beacon(x - cx, y - cy, z - cz));
        return list;
    }

    public List<Beacon> getTestBeacons(Beacon b, boolean prepare) {
        if(prepare) {
            List<Beacon> list = new ArrayList<>();
            list.addAll(getTestBeacons(this.x, this.y, this.z, b.x, b.y, b.z));
            list.addAll(getTestBeacons(this.x, this.z, this.y, b.x, b.y, b.z));
            list.addAll(getTestBeacons(this.y, this.x, this.z, b.x, b.y, b.z));
            list.addAll(getTestBeacons(this.y, this.z, this.x, b.x, b.y, b.z));
            list.addAll(getTestBeacons(this.z, this.x, this.y, b.x, b.y, b.z));
            list.addAll(getTestBeacons(this.z, this.y, this.x, b.x, b.y, b.z));
            return list;
        }
        return getTestBeacons(b.x, b.y, b.z);
    }

    public List<Beacon> getTestBeacons(int x, int y, int z) {
        List<Beacon> list = new ArrayList<>();
        list.addAll(getTestBeacons(this.x, this.y, this.z, x, y, z));
        list.addAll(getTestBeacons(this.x, this.z, this.y, x, y, z));
        list.addAll(getTestBeacons(this.y, this.x, this.z, x, y, z));
        list.addAll(getTestBeacons(this.y, this.z, this.x, x, y, z));
        list.addAll(getTestBeacons(this.z, this.x, this.y, x, y, z));
        list.addAll(getTestBeacons(this.z, this.y, this.x, x, y, z));

        list.addAll(getTestBeacons(this.x, this.y, this.z, x, z, y));
        list.addAll(getTestBeacons(this.x, this.z, this.y, x, z, y));
        list.addAll(getTestBeacons(this.y, this.x, this.z, x, z, y));
        list.addAll(getTestBeacons(this.y, this.z, this.x, x, z, y));
        list.addAll(getTestBeacons(this.z, this.x, this.y, x, z, y));
        list.addAll(getTestBeacons(this.z, this.y, this.x, x, z, y));

        list.addAll(getTestBeacons(this.x, this.y, this.z, y, x, z));
        list.addAll(getTestBeacons(this.x, this.z, this.y, y, x, z));
        list.addAll(getTestBeacons(this.y, this.x, this.z, y, x, z));
        list.addAll(getTestBeacons(this.y, this.z, this.x, y, x, z));
        list.addAll(getTestBeacons(this.z, this.x, this.y, y, x, z));
        list.addAll(getTestBeacons(this.z, this.y, this.x, y, x, z));

        list.addAll(getTestBeacons(this.x, this.y, this.z, y, z, x));
        list.addAll(getTestBeacons(this.x, this.z, this.y, y, z, x));
        list.addAll(getTestBeacons(this.y, this.x, this.z, y, z, x));
        list.addAll(getTestBeacons(this.y, this.z, this.x, y, z, x));
        list.addAll(getTestBeacons(this.z, this.x, this.y, y, z, x));
        list.addAll(getTestBeacons(this.z, this.y, this.x, y, z, x));

        list.addAll(getTestBeacons(this.x, this.y, this.z, z, x, y));
        list.addAll(getTestBeacons(this.x, this.z, this.y, z, x, y));
        list.addAll(getTestBeacons(this.y, this.x, this.z, z, x, y));
        list.addAll(getTestBeacons(this.y, this.z, this.x, z, x, y));
        list.addAll(getTestBeacons(this.z, this.x, this.y, z, x, y));
        list.addAll(getTestBeacons(this.z, this.y, this.x, z, x, y));

        list.addAll(getTestBeacons(this.x, this.y, this.z, z, y, x));
        list.addAll(getTestBeacons(this.x, this.z, this.y, z, y, x));
        list.addAll(getTestBeacons(this.y, this.x, this.z, z, y, x));
        list.addAll(getTestBeacons(this.y, this.z, this.x, z, y, x));
        list.addAll(getTestBeacons(this.z, this.x, this.y, z, y, x));
        list.addAll(getTestBeacons(this.z, this.y, this.x, z, y, x));

        return list;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }
}
