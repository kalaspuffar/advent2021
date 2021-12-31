import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day24 {
    public static void main(String[] args) {
        String data = """
inp w
add z w
mod z 2
div w 2
add y w
mod y 2
div w 2
add x w
mod x 2
div w 2
mod w 2
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day24a.txt"));

            List<String> program = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                program.add(line);
            }

            time = Instant.now();

            ALUComputer alu = new ALUComputer(program);
            alu.compile();
            reqRun(0, alu);

        } catch (Exception e) {
            e.printStackTrace();;
        }

    }

    private static Instant time;
    private static int[] input = new int[] {9,9,9,9,2,2,0,9,9,7,9,9,9,9};

    private static final BigInteger val4 = BigInteger.valueOf(4);
    private static final BigInteger val5 = BigInteger.valueOf(5);
    private static final BigInteger val6 = BigInteger.valueOf(6);
    private static final BigInteger val7 = BigInteger.valueOf(7);
    private static final BigInteger val8 = BigInteger.valueOf(8);
    private static final BigInteger val9 = BigInteger.valueOf(9);
    private static final BigInteger val10 = BigInteger.valueOf(10);
    private static final BigInteger val11 = BigInteger.valueOf(11);
    private static final BigInteger val12 = BigInteger.valueOf(12);
    private static final BigInteger val13 = BigInteger.valueOf(13);
    private static final BigInteger val14 = BigInteger.valueOf(14);

    private static final BigInteger val26 = BigInteger.valueOf(26);

    public static BigInteger compute(int[] input) {
        BigInteger res = BigInteger.valueOf(input[0]);
        res = res.add(BigInteger.TWO);
        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[1]));
        res = res.add(val4);
        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[2]));
        res = res.add(val8);

        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[3]));
        res = res.add(val7);

        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[4]));
        res = res.add(val12);

        res = res.divide(val26);
        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[5]));
        res = res.add(val7);

        res = res.divide(val26);
        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[6]));
        res = res.add(val10);

        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[7]));
        res = res.add(val14);

        res = res.divide(val26);
        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[8]));
        res = res.add(BigInteger.TWO);

        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[9]));
        res = res.add(val6);

        res = res.divide(val26);
        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[10]));
        res = res.add(val8);

        res = res.divide(val26);
        res = res.multiply(val26);
        res = res.add(BigInteger.valueOf(input[11]));
        res = res.add(val11);

        res = res.divide(val26);
        if (input[11] != input[12]) {
            res = res.multiply(val26);
            res = res.add(BigInteger.valueOf(input[12]));
            res = res.add(val5);
        }

        BigInteger x = res.mod(val26);
        res = res.divide(val26);
        x = x.subtract(BigInteger.TWO);
        BigInteger w = BigInteger.valueOf(input[13]);
        if (!x.equals(w)) {
            res = res.multiply(val26);
            res = res.add(w);
            res = res.add(val11);
        }

        return res;
    }

    private static void reqRun(int i, ALUComputer comp) {
/*
        if (i == 7) {
            System.out.println(Arrays.toString(input) + " in " + Duration.between(time, Instant.now()));
        }
 */
        if (i >= input.length) {
            BigInteger result = comp.compute(input);
            if (result.equals(BigInteger.ZERO)) {
                System.out.println(Arrays.toString(input));
                System.exit(0);
            }
            return;
        }

        if (i == 6) {
            for (int a = 8; a < 10; a++) {
                input[i] = a;
                reqRun(i + 1, comp);
            }
        } else if (i == 7 || i == 8) {
            for (int a = 1; a < 10; a++) {
                input[i] = a;
                reqRun(i + 1, comp);
            }
        } else if (i > 9) {
            for (int a = 1; a < 10; a++) {
                input[i] = a;
                reqRun(i + 1, comp);
            }
        } else {
            reqRun(i + 1, comp);
        }

        /*
        for (int a = 1; a < 10; a++) {
            input[i] = a;
            reqRun(i + 1, comp);
        }
         */
/*
        for (int a = 9; a > 0; a--) {
            input[i] = a;
            reqRun(i + 1, comp);
        }
 */
    }
}
