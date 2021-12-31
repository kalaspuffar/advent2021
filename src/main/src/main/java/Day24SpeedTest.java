import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day24SpeedTest {
    public static void main(String[] args) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day24a.txt"));

            List<String> program = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
                program.add(line);
            }

            time = Instant.now();
            ALUComputer alu = new ALUComputer(program);
            alu.compile();

            br.close();

            br = new BufferedReader(new FileReader("inputs/input-day24a-reduced.txt"));

            program = new ArrayList<>();

            while ((line = br.readLine()) != null) {
                program.add(line);
            }

            time = Instant.now();
            ALUComputer aluReduced = new ALUComputer(program);
            aluReduced.compile();

            List<int[]> list = new ArrayList<>();
            list.add(new int[] {1,1,1,1,1,1,1,1,1,1,1,1,1,1});
/*
            list.add(new int[] {1,3,5,7,9,2,4,6,8,9,9,9,9,9});
            list.add(new int[] {9,9,9,9,9,9,9,9,9,9,9,9,9,9});
            list.add(new int[] {1,2,3,4,5,6,7,8,9,1,2,3,4,5});
            list.add(new int[] {9,8,7,6,5,4,3,2,1,9,8,7,6,5});
            list.add(new int[] {2,3,4,3,2,4,3,4,5,6,5,4,3,2});
            list.add(new int[] {5,5,5,5,5,5,5,5,5,5,5,5,5,5});
            list.add(new int[] {1,2,4,3,6,3,5,3,1,3,4,5,3,5});
            list.add(new int[] {2,4,8,7,3,7,1,5,2,6,8,2,3,5});
            list.add(new int[] {1,1,1,1,3,3,8,9,5,7,9,6,9,9});
*/

            /*
            w1 = w14
            w2 - 7 = w13
            w3 + 5 = w12
            w4 + 7 = w7
            w5 - 2 = w6
            w8 + 4 = w9
            w10 - 6 = w11

             */

            int[] input = new int[] {
                    1, // w1
                    8, // w2
                    1, // w3
                    1, // w4
                    3, // w5
                    1, // w6
                    8, // w7
                    1, // w8
                    5, // w9
                    7, // w10
                    1, // w11
                    6, // w12
                    1, // w13
                    1, // w14
            };
            //18113181571611
            //99429795993929

            System.out.println(alu.compute(input));

/*            
            for(int[] input : list) {

                for (int i = 1; i < 10; i++) {
                    for (int j = 8; j < 10; j++) {
                        for (int k = 1; k < 10; k++) {
                            for (int l = 1; l < 10; l++) {
                                for (int m = 1; m < 10; m++) {
                                    for (int n = 1; n < 10; n++) {

                                        input[0] = i;
                                        input[1] = k;
                                        input[2] = l;
                                        input[3] = m;
                                        input[4] = 2;
                                        input[5] = 2;
                                        input[6] = j;
                                        input[7] = 9;
                                        input[8] = 9;
                                        input[9] = 7;
                                        input[10] = n;

                                        if (!compute(input).equals(alu.compute(input))) {
                                            int[] newArr = Arrays.copyOfRange(input, 0, 11);
                                            System.out.println("not equal" + Arrays.toString(newArr));
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
*/

        } catch (Exception e) {
            e.printStackTrace();;
        }

    }

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

    private static Instant time;
    /*
    private static int[] input = new int[] {1,3,5,7,9,2,4,6,8,9,9,9,9,9};

    private static void reqRun(int i, ALUComputer comp) {
        if (i == 6) {
            System.out.println(Arrays.toString(input) + " in " + Duration.between(time, Instant.now()));
        }
        if (i >= input.length) {
            BigInteger result = comp.compute(input);
            System.out.println(result);
            if (result.equals(BigInteger.ZERO)) {
                System.out.println(Arrays.toString(input));
                System.exit(0);
            }
            return;
        }
        for (int a = 9; a > 0; a--) {
            input[i] = a;
            reqRun(i + 1, comp);
        }
    }
     */
}
