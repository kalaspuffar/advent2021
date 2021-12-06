import bingo.BingoBoard;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Day4 {
    public static void main(String[] args) {
        String data = """
                7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1

                22 13 17 11  0
                 8  2 23  4 24
                21  9 14 16  7
                 6 10  3 18  5
                 1 12 20 15 19

                 3 15  0  2 22
                 9 18 13 17  5
                19  8  7 25 23
                20 11 10 24  4
                14 21 16 12  6

                14 21 17 24  4
                10 16 15  9 19
                18  8 23 26 20
                22 11 13  6  5
                 2  0 12  3  7                
                                """;

        try {
            BufferedReader br = new BufferedReader(new FileReader("inputs/input-day4a.txt"));

            List<BingoBoard> boards = new ArrayList<>();

            int lineNum = 0;
            String numbers = "";
            BingoBoard currentBoard = null;
            String line;
            while ((line = br.readLine()) != null) {
            //for (String line : data.split("\n")) {
                if (lineNum == 0) {
                    numbers = line;
                    lineNum++;
                    continue;
                }
                if (line.isBlank()) {
                    if (currentBoard != null) {
                        boards.add(currentBoard);
                    }
                    currentBoard = new BingoBoard();
                    continue;
                }

                currentBoard.addLine(line);

                lineNum++;
            }

            if (currentBoard != null) {
               boards.add(currentBoard);
            }

            BingoBoard winningBoard = null;
            int winningNum = 0;
            int numOfWinningBoards = 0;

            outerloop:
            for (String num : numbers.split(",")) {
                int numInt = Integer.parseInt(num);
                for (BingoBoard b : boards) {
                    if(b.addNum(numInt)) {
                        winningBoard = b;
                        winningNum = numInt;
                        numOfWinningBoards++;
                        if (numOfWinningBoards == boards.size()) {
                            break outerloop;
                        }
                        //System.out.println(b.calcWinningBoard(numInt));
                        //System.exit(0);
                    }
                }
            }

            if (winningBoard != null) {
                System.out.println(winningBoard.calcWinningBoard(winningNum));
            }

            System.exit(-1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
