package bingo;

public class BingoBoard {
    int[] board = new int[25];
    int currentLine = 0;
    boolean hasWon = false;

    public void addLine(String line) {
        String[] lineArr = line.trim().split("[ ]+");
        int x = 0;
        for (String num : lineArr) {
            board[currentLine * 5 + x] = Integer.parseInt(num);
            x++;
        }
        currentLine++;
    }

    public boolean addNum(int num) {
        if (hasWon) return false;
        int pos = 0;
        for (int b : board) {
            if (b == num) {
                if (num > 0) {
                    board[pos] = -num;
                } else {
                    board[pos] = Integer.MIN_VALUE;
                }
                break;
            }
            pos++;
        }

        if (pos > 24) return false;

        int column = pos % 5;
        int row = Math.floorDiv(pos, 5);

        int count = 0;
        for (int i = 0; i < 5; i++) {
            count += board[row * 5 + i] < 0 ? 1 : 0;
        }

        if (count == 5) {
            hasWon = true;
            return true;
        }

        count = 0;
        for (int i = 0; i < 5; i++) {
            count += board[i * 5 + column] < 0 ? 1 : 0;
        }

        if (count == 5) {
            hasWon = true;
            return true;
        }

        return false;
    }

    public int calcWinningBoard(int lastNum) {
        int count = 0;
        for (int i = 0; i < 25; i++) {
            if (board[i] > 0) {
                count += board[i];
            }
        }

        return count * lastNum;
    }
}
