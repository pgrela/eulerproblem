package pgrela.eulerproblem.problem158;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ExploringStringsForWhichOnlyOneCharacterComesLexicographicallyAfterItsNeighbourToTheLeft implements EulerSolver {

    private static final int CHARS = 26;

    public static void main(String[] args) throws ParseException {
        printSolution(ExploringStringsForWhichOnlyOneCharacterComesLexicographicallyAfterItsNeighbourToTheLeft.class);
    }

    public long solve() {
        return IntStream.rangeClosed(2, CHARS)
                .mapToLong(n ->
                        Maths.newton(CHARS, n)
                                * (IntStream.rangeClosed(1, n - 1)
                                .mapToLong(i -> Maths.newton(n, i))
                                .sum()
                                - (n - 1)))
                .max().getAsLong();
    }
}

