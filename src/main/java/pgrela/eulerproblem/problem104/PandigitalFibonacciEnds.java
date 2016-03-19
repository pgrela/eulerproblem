package pgrela.eulerproblem.problem104;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;

import java.util.stream.Stream;

import static pgrela.eulerproblem.common.Pair.pair;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.common.Tuple.tuple;
import static pgrela.eulerproblem.problem32.PandigitalProducts.isPandigital;

public class PandigitalFibonacciEnds implements EulerSolver {


    public static final double SQRT_5 = Math.sqrt(5);
    public static final double PHI = (1.0 + SQRT_5) / 2;
    public static final double[] PHI_POWERS = new double[31];

    static {
        PHI_POWERS[0] = 1;
        PHI_POWERS[1] = PHI;
        for (int i = 2; i < PHI_POWERS.length; i++) {
            double phiPower = PHI_POWERS[i - 1];
            PHI_POWERS[i] = phiPower * phiPower;
            if (PHI_POWERS[i] > Integers.ONE_BILLION) {
                PHI_POWERS[i] /= Integers.ONE_BILLION;
            }
        }
    }


    public static void main(String[] args) {
        printSolution(PandigitalFibonacciEnds.class);
    }

    public long solve() {
        return Stream
                .iterate(tuple(0, pair(0, 1)), previous -> tuple(previous.a + 1, pair(previous.b.b, (previous.b.a + previous.b.b) % Integers.ONE_BILLION)))
                .filter(s -> isPandigital(String.valueOf(s.b.a)))
                .filter(s -> isPandigital(String.valueOf(fib(s.a)).replace(".", "").substring(0, 9)))
                .findFirst().get().a;
    }

    double fib(int n) {
        double ret = 1;
        int i = 1;
        while (n > 0) {
            if ((n & 1) == 1) {
                ret *= PHI_POWERS[i];
            }
            ++i;
            n >>= 1;
        }
        return ret / SQRT_5;
    }
}
