package binary;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Operator extends ValueInt  {
    List<ValueInt> operatorList = new ArrayList<>();

    public Operator(int version, int type, String data, int size, boolean subPackages) {
        this.version = version;
        this.type = type;
        this.data = data;
        this.size = size;
        this.subPackages = subPackages;
    }

    public void parse() {
        if (subPackages) {
            int num = new BigInteger(this.data.substring(0, 11), 2).intValue();
            String workData = this.data.substring(11);
            this.size += 11;
            for (int i = 0; i < num; i++) {
                ValueInt vi = ValueInt.parseHeader(workData);
                vi.parse();
                operatorList.add(vi);
                this.size += vi.size;
                workData = workData.substring(vi.size);
            }
        } else {
            int len = new BigInteger(this.data.substring(0, 15), 2).intValue();
            String workData = this.data.substring(15, 15 + len);
            this.size += 15;
            while(!workData.isBlank()) {
                ValueInt vi = ValueInt.parseHeader(workData);
                vi.parse();
                operatorList.add(vi);
                this.size += vi.size;
                workData = workData.substring(vi.size);
            }
        }
    }

    @Override
    public int getVersionSum() {
        int sum = version;
        for (ValueInt vi : operatorList) {
            sum += vi.getVersionSum();
        }
        return sum;
    }

    @Override
    public BigInteger getValue() {

        BigInteger sum = BigInteger.ZERO;
        switch (type) {
            case 0:
                for(ValueInt v : operatorList) sum = sum.add(v.getValue());
                break;
            case 1:
                sum = BigInteger.ONE;
                for(ValueInt v : operatorList) sum = sum.multiply(v.getValue());
                break;
            case 2:
                sum = operatorList.get(0).getValue();
                for(ValueInt v : operatorList) sum = sum.min(v.getValue());
                break;
            case 3:
                for(ValueInt v : operatorList) sum = sum.max(v.getValue());
                break;
            case 5:
                sum = operatorList.get(0).getValue().compareTo(operatorList.get(1).getValue()) == 1 ?
                        BigInteger.ONE : BigInteger.ZERO;
                break;
            case 6:
                sum = operatorList.get(0).getValue().compareTo(operatorList.get(1).getValue()) == -1 ?
                        BigInteger.ONE : BigInteger.ZERO;
                break;
            case 7:
                sum = operatorList.get(0).getValue().equals(operatorList.get(1).getValue()) ?
                        BigInteger.ONE : BigInteger.ZERO;
                break;
        }
        return sum;
    }
}
