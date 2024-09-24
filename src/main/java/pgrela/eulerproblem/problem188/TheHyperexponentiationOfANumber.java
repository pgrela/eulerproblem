package pgrela.eulerproblem.problem188;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TheHyperexponentiationOfANumber implements EulerSolver {

    public static void main(String[] args) {
        printSolution(TheHyperexponentiationOfANumber.class);
    }

    @Override
    public long solve() {
        return hyperExponentModulo(1777, 1855, 100_000_000);
    }

    private long hyperExponentModulo(int base, int hyperExponent, int modulo) {
        if (hyperExponent == 1) return base % modulo;
        int cycleLength = 1;
        long k = base;
        while ((base % modulo) != (k = k * base % modulo)) {
            ++cycleLength;
        }
        return Maths.powMod(base, hyperExponentModulo(base, hyperExponent - 1, cycleLength), modulo);
    }
}

