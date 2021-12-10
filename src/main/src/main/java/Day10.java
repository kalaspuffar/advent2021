
import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class Day10 {


    public static void main(String[] args) {
        String data = """
[({(<(())[]>[[{[]{<()<>>
[(()[<>])]({[<{<<[]>>(
{([(<{}[<>[]}>{[]{[(<()>
(((({<>}<{<{<>}{[]{[]{}
[[<[([]))<([[{}[[()]]]
[{[{({}]{}}([{[{{{}}([]
{<[[]]>}<{[{[{[]{()[[[]
[<(<(<(<{}))><([]([]()
<{([([[(<>()){}]>(<<{{
<{([{{}}[<[[[<>{}]]]>[]]                
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day10a.txt"));

            Deque<String> chunk = new ArrayDeque<>();

            int count = 0;
            List<BigInteger> validSums = new ArrayList<>();

            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                String[] charArr = line.trim().split("");

                boolean valid = true;

                chunk = new ArrayDeque<>();
                forLoop:
                for(String c : charArr) {
                    switch (c) {
                        case "(": chunk.push(c); break;
                        case "[": chunk.push(c); break;
                        case "{": chunk.push(c); break;
                        case "<": chunk.push(c); break;
                        case ")":
                            if (!chunk.peek().equals("(")) {
                                count += 3;
                                valid = false;
                                break forLoop;
                            }
                            chunk.pop();
                            break;
                        case "]":
                            if (!chunk.peek().equals("[")) {
                                count += 57;
                                valid = false;
                                break forLoop;
                            }
                            chunk.pop();
                            break;
                        case "}":
                            if (!chunk.peek().equals("{")) {
                                count += 1197;
                                valid = false;
                                break forLoop;
                            }
                            chunk.pop();
                            break;
                        case ">":
                            if (!chunk.peek().equals("<")) {
                                count += 25137;
                                valid = false;
                                break forLoop;
                            }
                            chunk.pop();
                            break;
                    }
                }

                if (valid) {
                    BigInteger countValid = BigInteger.ZERO;

                    while(chunk.size() > 0) {
                        String s = chunk.pop();
                        countValid = countValid.multiply(new BigInteger("5"));
                        switch (s) {
                            case "(": countValid = countValid.add(new BigInteger("1")); break;
                            case "[": countValid = countValid.add(new BigInteger("2")); break;
                            case "{": countValid = countValid.add(new BigInteger("3")); break;
                            case "<": countValid = countValid.add(new BigInteger("4")); break;
                        }
                    }
                    validSums.add(countValid);
                }
            }

            Collections.sort(validSums);

            System.out.println(validSums.get(Math.floorDiv(validSums.size(), 2)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
