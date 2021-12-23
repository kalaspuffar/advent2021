public class Die {
    private int nextNumber = 1;
    private int rolls = 0;

    private int next() {
        rolls++;
        if(nextNumber > 1000) nextNumber = 1;
        return nextNumber++;
    }

    public int roll() {
        return next() + next() + next();
    }

    public int getRolls() {
        return rolls;
    }
}
