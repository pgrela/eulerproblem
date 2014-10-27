package pgrela.eulerproblem.problem84.monopoly;

import java.util.Random;

public class DiceRoll {
    private static Random random = new Random();
    int firstDice;
    int secondDice;

    public DiceRoll(int sides) {
        firstDice = random.nextInt(sides) + 1;
        secondDice = random.nextInt(sides) + 1;
    }

    public boolean isDouble() {
        return firstDice == secondDice;
    }

    public int getSum() {
        return firstDice + secondDice;
    }
}
