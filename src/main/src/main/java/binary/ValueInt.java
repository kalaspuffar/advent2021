package binary;

import java.math.BigInteger;

public abstract class ValueInt {
    protected int version;
    protected int type;
    protected boolean subPackages;
    protected String data;
    protected int size = 0;

    public static ValueInt parseHeader(String s) {
        int version = new BigInteger(s.substring(0, 3), 2).intValue();
        int type = new BigInteger(s.substring(3, 6), 2).intValue();
        if (type == 4) {
            return new Value(version, type, s.substring(6), 6);
        } else {
            return new Operator(version, type, s.substring(7), 7, "1".equals(s.substring(6, 7)));
        }
    }

    public abstract void parse();

    public abstract int getVersionSum();

    public abstract BigInteger getValue();
}
