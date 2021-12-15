import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class BytePair {
    protected byte[] bytes = new byte[2];

    public BytePair() {}

    public BytePair(String s) {
        bytes = s.getBytes(StandardCharsets.UTF_8);
    }

    public void newByte(byte b) {
        bytes[0] = bytes[1];
        bytes[1] = b;
    }

    @Override
    public boolean equals(Object o) {
        BytePair bytePair = (BytePair) o;
        return Arrays.equals(bytes, bytePair.bytes);
    }

    @Override
    public int hashCode() {
        int h = 0;
        for (byte v : bytes) {
            h = 3 * h + (v & 0xff);
        }
        return h;
    }

    @Override
    public String toString() {
        return "BytePair{" +
                "bytes=" + Arrays.toString(bytes) +
                '}';
    }

    public byte getFirst() {
        return bytes[0];
    }

    public byte getSecond() {
        return bytes[1];
    }

    public byte[] getResult(String s) {
        byte[] newByte = new byte[2];
        byte[] strBytes = s.getBytes(StandardCharsets.UTF_8);

        newByte[0] = strBytes[0];
        newByte[1] = bytes[1];
        return newByte;
    }

}
