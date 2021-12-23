public class Player {
    public int pos;
    public int score = 0;

    public Player(int pos) {
        this.pos = pos - 1;
    }

    public void takeTurn(Die die) {
        pos += die.roll();
        pos = pos % 10;
        score += pos + 1;
    }

    public boolean hasWon() {
        return score >= 21;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return "Player{" +
                "pos=" + pos +
                ", score=" + score +
                '}';
    }
}
