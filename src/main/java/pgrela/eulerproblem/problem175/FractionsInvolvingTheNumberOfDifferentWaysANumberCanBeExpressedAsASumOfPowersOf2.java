package pgrela.eulerproblem.problem175;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Pair;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FractionsInvolvingTheNumberOfDifferentWaysANumberCanBeExpressedAsASumOfPowersOf2 implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(FractionsInvolvingTheNumberOfDifferentWaysANumberCanBeExpressedAsASumOfPowersOf2.class);
    }

    @Override
    public String solveToString() {
        int nominator = 123456789;
        int denominator = 987654321;
        int gcd = Maths.gcd(nominator, denominator);
        nominator /= gcd;
        denominator /= gcd;
        Pair extendedEuclidian = Integers.extendedEuclidian(nominator, denominator - nominator);
        int upper = (denominator + (extendedEuclidian.a - extendedEuclidian.b)) % denominator;
        int bottom = denominator;
        List<Integer> result = new ArrayList<>();
        while (upper > 1 || bottom > 1) {
            if (upper > bottom) {
                result.add(upper / bottom);
                upper %= bottom;
            } else {
                result.add(bottom / upper);
                bottom %= upper;
            }
        }
        return result.stream().map(Object::toString).collect(Collectors.joining(","));
    }
}

