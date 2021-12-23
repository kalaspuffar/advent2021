import java.util.HashSet;
import java.util.Set;

public class Day21b {
    public static void main(String[] args) {
        QuantumDie die = new QuantumDie();

        int p1Win = 0;
        int p2Win = 0;

        for(int i = 0; i < 10000; i++) {
            //Player p1 = new Player(4);
            //Player p2 = new Player(8);
            Player p1 = new Player(8);
            Player p2 = new Player(9);

            boolean playerOnesTurn = true;
            while (!p1.hasWon() && !p2.hasWon()) {
                if (playerOnesTurn) {
                    p1.takeTurn(die);
                } else {
                    p2.takeTurn(die);
                }
                playerOnesTurn = !playerOnesTurn;
            }
            if(p1.hasWon()) {
                p1Win++;
            } else {
                p2Win++;
            }
        }

        System.out.println("P1 Wins: " + p1Win);
        System.out.println("P2 Wins: " + p2Win);
    }
}
