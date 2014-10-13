package pgrela.eulerproblem.problem71;

import pgrela.eulerproblem.common.EulerSolver;

import static java.lang.Long.compare;
import static java.util.stream.IntStream.range;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Maths.gcd;
import static pgrela.eulerproblem.common.Pair.pair;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class OrderedFractions implements EulerSolver {

    public static void main(String[] args) {
        printSolution(OrderedFractions.class);
    }

    public long solve() {
        return range(2, ONE_MILLION)
                .mapToObj(d -> pair(3 * d / 7, d))
                .filter(p -> 7 * p.a < 3 * p.b)
                .map(p -> pair(p.a / gcd(p.a, p.b), p.b / gcd(p.a, p.b)))
                .max((a, b) -> compare(a.a * (long) b.b, a.b * (long) b.a))
                .get().a;
    }
}
