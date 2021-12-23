import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

public class Day21b {

    public static BigInteger countUniverse(int num, int p1pos, int p2pos, int p1points, int p2points, boolean myturn, boolean first) {
        BigInteger sum = BigInteger.ZERO;

        if (!first) {
            if (myturn) {
                p1pos += num;
                p1pos = p1pos % 10;
                p1points += p1pos + 1;
                if (p1points >= 21) {
                    return BigInteger.ONE;
                }
            } else {
                p2pos += num;
                p2pos = p2pos % 10;
                p2points += p2pos + 1;
                if (p2points >= 21) {
                    return BigInteger.ZERO;
                }
            }
            myturn = !myturn;
        } else {
            p1pos--;
            p2pos--;
        }

        sum = sum.add(countUniverse(3, p1pos, p2pos, p1points, p2points, myturn, false).multiply(new BigInteger("1")));
        sum = sum.add(countUniverse(4, p1pos, p2pos, p1points, p2points, myturn, false).multiply(new BigInteger("3")));
        sum = sum.add(countUniverse(5, p1pos, p2pos, p1points, p2points, myturn, false).multiply(new BigInteger("6")));
        sum = sum.add(countUniverse(6, p1pos, p2pos, p1points, p2points, myturn, false).multiply(new BigInteger("7")));
        sum = sum.add(countUniverse(7, p1pos, p2pos, p1points, p2points, myturn, false).multiply(new BigInteger("6")));
        sum = sum.add(countUniverse(8, p1pos, p2pos, p1points, p2points, myturn, false).multiply(new BigInteger("3")));
        sum = sum.add(countUniverse(9, p1pos, p2pos, p1points, p2points, myturn, false).multiply(new BigInteger("1")));

        return sum;
    }


    public static void main(String[] args) {

        System.out.println("Total P1: " + countUniverse(0, 4, 8, 0, 0, true, true));
        System.out.println("Total P2: " + countUniverse(0, 8, 4, 0, 0, false, true));

        System.out.println("Total P1: " + countUniverse(0, 8, 9, 0, 0, true, true));
        System.out.println("Total P2: " + countUniverse(0, 9, 8, 0, 0, false, true));
    }
}

// 444356092776315
// 341960390180808
/*
Total P1: 430229563871565
Total P2: 370143448743170
Total P1: 157595953724471
Total P2: 121908465540796

 */