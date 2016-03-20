package pgrela.eulerproblem.problem112;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Pair;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class BouncyNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(BouncyNumbers.class);
    }

    public long solve() {
        Pair pair = Pair.pair(0, 0);
        return IntStream.iterate(1, i -> i + 1).sequential()
                .peek(n -> {
                    if (isBouncy(n)) {
                        ++pair.a;
                    } else {
                        ++pair.b;
                    }
                })
                .filter(i -> pair.a == pair.b * 99)
                .findFirst()
                .getAsInt();
    }

    private boolean isBouncy(int n) {
        return !isIncreasing(n) && !isDecreasing(n);
    }

    private boolean isDecreasing(int n) {
        int lastDigit = 9;
        while (n > 0) {
            if (n % 10 > lastDigit) {
                return false;
            }
            lastDigit = n % 10;
            n /= 10;
        }
        return true;
    }

    private boolean isIncreasing(int n) {
        int lastDigit = 0;
        while (n > 0) {
            if (n % 10 < lastDigit) {
                return false;
            }
            lastDigit = n % 10;
            n /= 10;
        }
        return true;
    }

}
