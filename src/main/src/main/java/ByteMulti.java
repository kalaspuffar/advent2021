import java.nio.charset.StandardCharsets;
import java.util.Map;

public class ByteMulti extends BytePair {

    public ByteMulti(int a, int b, int c, int d, int e, int f, int g) {
        bytes = new byte[] {(byte)a, (byte)b, (byte)c, (byte)d, (byte) e, (byte) f, (byte) g};
    }
    public ByteMulti(byte d) {
        bytes = new byte[] {0, 0, 0, 0, 0, 0, d};
    }

    public void newBytes(byte b, byte b2, byte b3, byte b4, byte b5, byte b6) {
        bytes[0] = bytes[6];
        bytes[1] = b;
        bytes[2] = b2;
        bytes[3] = b3;
        bytes[4] = b4;
        bytes[5] = b5;
        bytes[6] = b6;
    }

    public byte[] getResult(Map<BytePair, byte[]> map) {
        byte[] result = new byte[12];
        BytePair bytePair = new BytePair();
        bytePair.newByte(bytes[0]);
        bytePair.newByte(bytes[1]);
        result[0] = map.get(bytePair)[0];
        result[1] = bytes[1];
        bytePair.newByte(bytes[2]);
        result[2] = map.get(bytePair)[0];
        result[3] = bytes[2];
        bytePair.newByte(bytes[3]);
        result[4] = map.get(bytePair)[0];
        result[5] = bytes[3];
        bytePair.newByte(bytes[4]);
        result[6] = map.get(bytePair)[0];
        result[7] = bytes[4];
        bytePair.newByte(bytes[5]);
        result[8] = map.get(bytePair)[0];
        result[9] = bytes[5];

        bytePair.newByte(bytes[6]);
        result[10] = map.get(bytePair)[0];
        result[11] = bytes[6];

        return result;
    }
}
