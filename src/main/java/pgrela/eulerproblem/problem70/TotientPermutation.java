package pgrela.eulerproblem.problem70;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;

import java.util.function.Function;
import java.util.stream.Collectors;

import static java.lang.Double.compare;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Maths.totient;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TotientPermutation implements EulerSolver {

    public static void main(String[] args) {
        printSolution(TotientPermutation.class);
    }

    public long solve() {
        return range(2, 10 * ONE_MILLION)
                .filter(n -> Integers.arePermutations(n,totient(n)))
                .boxed()
                .collect(Collectors.<Integer, Integer, Double>toMap(Function.<Integer>identity(), n -> n / (double) totient(n)))
                .entrySet()
                .stream()
                .min((a, b) -> compare(a.getValue(), b.getValue()))
                .get()
                .getKey();
    }
}
