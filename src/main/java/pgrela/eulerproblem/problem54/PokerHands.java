package pgrela.eulerproblem.problem54;

import static pgrela.eulerproblem.common.Files.getLinesStream;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.problem54.poker.Card;
import pgrela.eulerproblem.problem54.poker.Hand;

public class PokerHands implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem54/poker.txt";

    public static void main(String[] args) {
        printSolution(PokerHands.class);
    }

    public long solve() {
        return getLinesStream(RESOURCE_FILE).filter(hands -> createHand(hands.substring(0,
                15)).compareTo(createHand(hands.substring(15)))>0).count();
    }

    private Hand createHand(String hand) {
        return new Hand(Stream.of(hand.trim().split(" ")).map(Card::fromString).collect(Collectors.toSet()));
    }
}
