import java.util.Arrays;

public class QuantumDie extends Die {
    private int[] results = new int[17];
    private int rolls = 0;

    public QuantumDie() {
        Arrays.fill(results, 1);
        results[0] = 0;
    }

    public boolean add() {
        int it = 0;
        while(results[it] == 9) {
            results[it] = 3;
            it++;
            if(it > results.length - 1) return false;
        }
        results[it]++;
        rolls = 0;
        return true;
    }

    public int roll() {
        int val1 = (int)(Math.random() * 3) + 1;
        int val2 = (int)(Math.random() * 3) + 1;
        int val3 = (int)(Math.random() * 3) + 1;

        return val1 + val2 + val3;
    }

    public int getRolls() {
        return rolls;
    }

    public String getString() {
        String s = "";
        for(int i = 0; i < rolls; i++) s += results[i];
        return s;
    }

    @Override
    public String toString() {
        return "QuantumDie{" +
                "results=" + Arrays.toString(results) +
                '}';
    }
}
