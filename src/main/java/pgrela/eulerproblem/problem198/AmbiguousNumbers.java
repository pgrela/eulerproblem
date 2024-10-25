package pgrela.eulerproblem.problem198;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.function.Function;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class AmbiguousNumbers implements EulerSolver {

    public static final long MAX_DENOMINATOR_BOUND = 100_000_000L;
    public static final int MIN_DENOMINATOR = 100;

    public static final double EPSILON = 0.000_000_000_000_000_000_001;

    public static final LongFraction TOP_BOUND_TWICE = new LongFraction(1, MIN_DENOMINATOR / 2);
    public static final LongFraction TOP_BOUND = new LongFraction(1, MIN_DENOMINATOR);
    public static final double TOP_BOUND_DOUBLE = TOP_BOUND.asDouble();

    public static void main(String[] args) {
        printSolution(AmbiguousNumbers.class);
    }

    @Override
    public long solve() {
        TreeSet<LongFraction> alreadyFollowedFractions = new TreeSet<>();
        List<LongFraction> newFractionsToFollow = new ArrayList<>();
        boolean foundNewAmbigousFraction = true;
        long ambigousFractionsCount = 0;
        long denominator = MIN_DENOMINATOR /2;
        while (foundNewAmbigousFraction) {
            newFractionsToFollow.clear();
            foundNewAmbigousFraction = false;
            for (long nominator = 0; nominator <= denominator; nominator++) {
                LongFraction fraction = new LongFraction(nominator, denominator);
                if (!TOP_BOUND_TWICE.isLessThan(fraction)) {
                    if (!alreadyFollowedFractions.contains(fraction)) {
                        newFractionsToFollow.add(fraction);
                    }
                } else {
                    break;
                }
                alreadyFollowedFractions.add(fraction);
            }
            for (LongFraction fraction : newFractionsToFollow) {
                {
                    Tuple<Boolean, Long> result = followFraction(
                            fraction,
                            denominator,
                            d -> ((fraction.getNominator() * d - 1) / fraction.getDenominator()) / ((double) d),
                            alreadyFollowedFractions.lower(fraction));
                    ambigousFractionsCount += result.b;
                    foundNewAmbigousFraction |= result.a;
                }

                {
                    Tuple<Boolean, Long> result = followFraction(
                            fraction,
                            denominator,
                            d -> (fraction.getNominator() * d / fraction.getDenominator() + 1) / ((double) d),
                            alreadyFollowedFractions.higher(fraction));
                    ambigousFractionsCount += result.b;
                    foundNewAmbigousFraction |= result.a;
                }
            }
            ++denominator;
        }
        return ambigousFractionsCount;
    }

    private static Tuple<Boolean, Long> followFraction(LongFraction fraction,
                                                       long denominator,
                                                       Function<Long, Double> closestFractionWithDenominator,
                                                       LongFraction closestFraction) {
        long ambigousFractionsCountNew = 0;
        boolean ambigousFractionsFound = false;

        if (closestFraction != null) {
            double doubleFraction = fraction.asDouble();
            double currentGap = Math.abs(closestFraction.asDouble() - doubleFraction);
            ++denominator;
            while (fraction.getDenominator() * denominator * 2 <= MAX_DENOMINATOR_BOUND) {
                double maybeGap = closestFractionWithDenominator.apply(denominator) - doubleFraction;
                double absMaybeGap = Math.abs(maybeGap);
                if (absMaybeGap < currentGap - EPSILON) {
                    currentGap = absMaybeGap;
                    ambigousFractionsFound = true;
                    if (doubleFraction + maybeGap / 2 < TOP_BOUND_DOUBLE - EPSILON) {
                        ++ambigousFractionsCountNew;
                    }
                }
                ++denominator;
            }
        }
        return Tuple.tuple(ambigousFractionsFound, ambigousFractionsCountNew);
    }

}

