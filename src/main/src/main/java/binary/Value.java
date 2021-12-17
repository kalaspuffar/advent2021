package binary;

import java.math.BigInteger;

public class Value extends ValueInt {
    private BigInteger value;

    public Value(int version, int type, String data, int size) {
        this.version = version;
        this.type = type;
        this.data = data;
        this.size = size;
    }

    public void parse() {
        String val = "";
        boolean notLast = true;
        while (!data.isBlank() && notLast) {
            val += data.substring(1, 5);
            if ("0".equals(data.substring(0, 1))) {
                notLast = false;
            }
            data = data.substring(5);
            size += 5;
        }
        value = new BigInteger(val, 2);
    }

    @Override
    public int getVersionSum() {
        return version;
    }

    @Override
    public BigInteger getValue() {
        return value;
    }
}
