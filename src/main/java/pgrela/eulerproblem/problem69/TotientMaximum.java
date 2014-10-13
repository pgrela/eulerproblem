package pgrela.eulerproblem.problem69;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;

import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Double.compare;
import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.Maths.totient;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TotientMaximum implements EulerSolver {

    public static void main(String[] args) {
        printSolution(TotientMaximum.class);
    }

    public long solve() {
        return rangeClosed(2, Integers.ONE_MILLION)
                .boxed()
                .collect(Collectors.<Integer, Integer, Double>toMap(Function.<Integer>identity(), n -> n / (double) totient(n)))
                .entrySet()
                .stream()
                .max((a, b) -> compare(a.getValue(), b.getValue()))
                .get()
                .getKey();
    }
}
