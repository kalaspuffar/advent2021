public class Day21 {
    public static void main(String[] args) {
        Die die = new Die();
        Player p1 = new Player(4);
        Player p2 = new Player(8);
        //Player p1 = new Player(8);
        //Player p2 = new Player(9);

        boolean playerOnesTurn = true;
        while(!p1.hasWon() && !p2.hasWon()) {
            if (playerOnesTurn) {
                p1.takeTurn(die);
            } else {
                p2.takeTurn(die);
            }
            playerOnesTurn = !playerOnesTurn;
        }

        System.out.println(die.getRolls());

        if(p1.hasWon()) {
            System.out.println(p2.getScore() * die.getRolls());
        } else {
            System.out.println(p1.getScore() * die.getRolls());
        }
    }
}
