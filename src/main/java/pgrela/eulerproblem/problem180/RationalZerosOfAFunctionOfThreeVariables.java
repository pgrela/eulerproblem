package pgrela.eulerproblem.problem180;

import java.util.Arrays;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Pair;
import pgrela.eulerproblem.common.Streams;
import pgrela.eulerproblem.common.Tuple;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RationalZerosOfAFunctionOfThreeVariables implements EulerSolver {

    private static final int ORDER = 35;
    private static final int[] EXPONENTS = { -2, -1, 1, 2 };

    public static void main(String[] args) {
        printSolution(RationalZerosOfAFunctionOfThreeVariables.class);
    }

    @Override
    public long solve() {
        LongFraction[] fractions = Pair.pairs(1, 1, ORDER, ORDER)
                .filter(p -> p.a < p.b)
                .map(p -> new LongFraction(p.a, p.b))
                .distinct()
                .toArray(LongFraction[]::new);
        return Streams.cartessian(fractions, fractions)
                .flatMap(ab -> Arrays.stream(EXPONENTS)
                        .mapToObj(exponent -> Tuple.tuple(exponent, ab.a.pow(exponent).add(ab.b.pow(exponent))))
                        .filter(t -> t.b.isPow(t.a))
                        .map(t -> t.b.root(t.a))
                        .filter(c -> isOfOrder(c, ORDER))
                        .map(f -> f.add(ab.a.add(ab.b))))
                .distinct()
                .reduce(LongFraction::add)
                .map(f -> f.getNominator() + f.getDenominator())
                .get();
    }

    private boolean isOfOrder(LongFraction fraction, int order) {
        return 0 < fraction.getNominator() && fraction.getNominator() <= order
                && 0 < fraction.getDenominator() && fraction.getDenominator() <= order
                && fraction.getNominator() < fraction.getDenominator();
    }

}

