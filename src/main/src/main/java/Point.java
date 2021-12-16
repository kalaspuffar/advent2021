import java.util.Objects;

public class Point {
    private final int x;
    private final int y;
    private final int pointValue;
    private int lowestValue = 250000;

    public Point(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.pointValue = value;
    }

    public int getLowestValue() {
        return lowestValue;
    }

    public void setLowestValue(int lowestValue) {
        this.lowestValue = lowestValue;
    }

    public int getPointValue() {
        return pointValue;
    }
/*
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return id == point.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
 */

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
