import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day24Test {
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

        int[] input = new int[] {15};

        try {
            List<String> program = new ArrayList<>();
            for (String line : data.split("\n")) {
                program.add(line);
            }

            ALUComputer alu = new ALUComputer(program);
            alu.compile();
            alu.compute(input);
            alu.printVariables();

        } catch (Exception e) {
            e.printStackTrace();;
        }

    }
}
