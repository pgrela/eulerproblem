package pgrela.eulerproblem.problem121;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Longs;

import static java.util.stream.LongStream.range;
import static pgrela.eulerproblem.common.Maths.factorial;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class DiscGamePrizeFund implements EulerSolver {

    public static final int TURNS = 15;

    public static void main(String[] args) {
        printSolution(DiscGamePrizeFund.class);
    }

    public long solve() {
        return factorial(TURNS + 1L) / range(1, 1 << TURNS)
                .filter(n -> Longs.positiveBits(n) > TURNS / 2)
                .map((n) ->
                        range(0, TURNS)
                                .map(i -> ((n >> (i - 1)) & 1) == 0 ? i + 1 : 1)
                                .reduce(1, Math::multiplyExact)
                ).sum();
    }
}
