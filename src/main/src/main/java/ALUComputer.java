import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ALUComputer {

    class Variable {
        BigInteger val;

        public Variable(BigInteger val) {
            this.val = val;
        }

        public Variable(int val) {
            this.val = BigInteger.valueOf(val);
        }

        public Variable(String val) {
            this.val = new BigInteger(val);
        }
    }

    class Operator {
        public static final int INP = 0;
        public static final int ADD = 1;
        public static final int MUL = 2;
        public static final int DIV = 3;
        public static final int MOD = 4;
        public static final int EQL = 5;
        public static final int SET = 6;
        public static final int NEQ = 7;

        int type;
        Variable a;
        Variable b;

        public Operator(int type, Variable a, Variable b) {
            this.type = type;
            this.a = a;
            this.b = b;
        }
    }

    private List<String> programList;
    private List<Operator> program;
    private int[] input;
    private int inputIdx = 0;

    private Map<String, Variable> varList = new HashMap<>();

    public ALUComputer(List<String> program) {
        this.programList = program;
        this.program = new ArrayList<>();
    }

    public void compile() {
        varList.put("w", new Variable(0));
        varList.put("x", new Variable(0));
        varList.put("y", new Variable(0));
        varList.put("z", new Variable(0));

        for (String line : programList) {
            compileLine(line);
        }
    }

    public void printVariables() {
        for (Map.Entry<String, Variable> v : varList.entrySet()) {
            System.out.println(v.getKey() + " = " + v.getValue().val);
        }
    }

    private boolean isNumeric(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    private void compileLine(String line) {
        String[] lineArr = line.split(" ");
        switch (lineArr[0]) {
            case "inp":
                program.add(new Operator(Operator.INP, varList.get(lineArr[1]), null));
                break;
            case "add":
                if (isNumeric(lineArr[2])) {
                    program.add(new Operator(Operator.ADD, varList.get(lineArr[1]), new Variable(lineArr[2])));
                } else {
                    program.add(new Operator(Operator.ADD, varList.get(lineArr[1]), varList.get(lineArr[2])));
                }
                break;
            case "mul":
                if (isNumeric(lineArr[2])) {
                    program.add(new Operator(Operator.MUL, varList.get(lineArr[1]), new Variable(lineArr[2])));
                } else {
                    program.add(new Operator(Operator.MUL, varList.get(lineArr[1]), varList.get(lineArr[2])));
                }
                break;
            case "div":
                if (isNumeric(lineArr[2])) {
                    program.add(new Operator(Operator.DIV, varList.get(lineArr[1]), new Variable(lineArr[2])));
                } else {
                    program.add(new Operator(Operator.DIV, varList.get(lineArr[1]), varList.get(lineArr[2])));
                }
                break;
            case "mod":
                if (isNumeric(lineArr[2])) {
                    program.add(new Operator(Operator.MOD, varList.get(lineArr[1]), new Variable(lineArr[2])));
                } else {
                    program.add(new Operator(Operator.MOD, varList.get(lineArr[1]), varList.get(lineArr[2])));
                }
                break;
            case "eql":
                if (isNumeric(lineArr[2])) {
                    program.add(new Operator(Operator.EQL, varList.get(lineArr[1]), new Variable(lineArr[2])));
                } else {
                    program.add(new Operator(Operator.EQL, varList.get(lineArr[1]), varList.get(lineArr[2])));
                }
                break;
            case "neq":
                if (isNumeric(lineArr[2])) {
                    program.add(new Operator(Operator.NEQ, varList.get(lineArr[1]), new Variable(lineArr[2])));
                } else {
                    program.add(new Operator(Operator.NEQ, varList.get(lineArr[1]), varList.get(lineArr[2])));
                }
                break;
            case "set":
                if (isNumeric(lineArr[2])) {
                    program.add(new Operator(Operator.SET, varList.get(lineArr[1]), new Variable(lineArr[2])));
                } else {
                    program.add(new Operator(Operator.SET, varList.get(lineArr[1]), varList.get(lineArr[2])));
                }
                break;
        }
    }

    public BigInteger compute(int[] input) {
        this.input = input;
        this.inputIdx = 0;
        varList.get("w").val = BigInteger.ZERO;
        varList.get("x").val = BigInteger.ZERO;
        varList.get("y").val = BigInteger.ZERO;
        varList.get("z").val = BigInteger.ZERO;

        for (Operator line : program) {
            computeLine(line);
        }

        return varList.get("z").val;
    }

    private void computeLine(Operator line) {
        switch (line.type) {
            case Operator.INP:
                line.a.val = BigInteger.valueOf(input[inputIdx]);
                inputIdx++;
                break;
            case Operator.ADD:
                line.a.val = line.a.val.add(line.b.val);
                break;
            case Operator.MUL:
                line.a.val = line.a.val.multiply(line.b.val);
                break;
            case Operator.DIV:
                line.a.val = line.a.val.divide(line.b.val);
                break;
            case Operator.MOD:
                line.a.val = line.a.val.mod(line.b.val);
                break;
            case Operator.EQL:
                line.a.val = line.a.val.equals(line.b.val) ? BigInteger.ONE : BigInteger.ZERO;
                break;
            case Operator.NEQ:
                line.a.val = line.a.val.equals(line.b.val) ? BigInteger.ZERO : BigInteger.ONE;
                break;
            case Operator.SET:
                line.a.val = line.b.val;
                break;
        }
    }
}
