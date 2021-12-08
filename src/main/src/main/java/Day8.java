import bingo.BingoBoard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day8 {

    public static void main(String[] args) {
        String data = """
                be cfbegad cbdgef fgaecd cgeb fdcge agebfd fecdb fabcd edb | fdgacbe cefdb cefbgd gcbe
                edbfga begcd cbg gc gcadebf fbgde acbgfd abcde gfcbed gfec | fcgedb cgb dgebacf gc
                fgaebd cg bdaec gdafb agbcfd gdcbef bgcad gfac gcb cdgabef | cg cg fdcagb cbg
                fbegcd cbd adcefb dageb afcb bc aefdc ecdab fgdeca fcdbega | efabcd cedba gadfec cb
                aecbfdg fbg gf bafeg dbefa fcge gcbea fcaegb dgceab fcbdga | gecf egdcabf bgf bfgea
                fgeab ca afcebg bdacfeg cfaedg gcfdb baec bfadeg bafgc acf | gebdcfa ecba ca fadegcb
                dbcfg fgd bdegcaf fgec aegbdf ecdfab fbedc dacgb gdcebf gf | cefg dcbef fcge gbcadfe
                bdfegc cbegaf gecbf dfcage bdacg ed bedf ced adcbefg gebcd | ed bcgafe cdgba cbgef
                egadfb cdbfeg cegd fecab cgb gbdefca cg fgcdab egfdb bfceg | gbdfcae bgc cg cgb
                gcafb gcf dcaebfg ecagb gf abcdeg gaef cafbge fdbac fegbdc | fgae cfgab fg bagce
                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day8a.txt"));

            int count = 0;

            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank()) continue;
            //for (String line : data.split("\n")) {
                String[] lineArr = line.split("\\|");
                String input = lineArr[0];
                String output = lineArr[1];

                String[] outputArr = output.split("[ ]+");
                for (String s : outputArr) {
                    count += s.length() == 2 ? 1 : 0;
                    count += s.length() == 3 ? 1 : 0;
                    count += s.length() == 4 ? 1 : 0;
                    count += s.length() == 7 ? 1 : 0;
                }
            }

            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
