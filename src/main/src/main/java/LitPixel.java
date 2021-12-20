import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class LitPixel {
    private final int x, y;

    public LitPixel(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LitPixel litPixel = (LitPixel) o;
        return x == litPixel.x && y == litPixel.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "LitPixel{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Set<LitPixel> getLitPixels(Set<LitPixel> pixels, String enhancement) {
        Set<LitPixel> newPixels = new HashSet<>();
        for (int cx = x - 5; cx < x + 5; cx++) {
            for (int cy = y - 5; cy < y + 5; cy++) {
                if (isLitPixel(cx, cy, pixels, enhancement)) {
                    newPixels.add(new LitPixel(cx, cy));
                }
            }
        }
        return newPixels;
    }

    public static boolean isLitPixel(int x, int y, Set<LitPixel> pixels, String enhancement) {
        String binary = "";
        binary += pixels.contains(new LitPixel(x - 1, y - 1)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x,       y - 1)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x + 1, y - 1)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x - 1, y)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x,       y)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x + 1, y)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x - 1, y + 1)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x,       y + 1)) ? "1" : "0";
        binary += pixels.contains(new LitPixel(x + 1, y + 1)) ? "1" : "0";
        BigInteger bi = new BigInteger(binary, 2);
        return "#".equals(enhancement.split("")[bi.intValue()]);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
