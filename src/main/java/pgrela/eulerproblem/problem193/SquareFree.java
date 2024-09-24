package pgrela.eulerproblem.problem193;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SquareFree implements EulerSolver {

    public static final long LIMIT = 1L << 50;
    public static final long LIMIT_HALF = 1L << 25;
    private long[] primeSquares;

    public static void main(String[] args) {
        printSolution(SquareFree.class);
    }

    @Override
    public long solve() {
        primeSquares = Primes.primes(1 << 25).mapToLong(n -> n).toArray();
        return LIMIT - times(1, 0, -1);
    }

    public long times(long pollutedNumber, int depth, int k) {
        if (pollutedNumber > LIMIT_HALF) return 0;

        long squares = 0;

        for (int i = k + 1; i < primeSquares.length; i++) {
            long morePollutedNumber = pollutedNumber*primeSquares[i];
            if(morePollutedNumber>LIMIT_HALF)break;
            long morePollutedSquare = morePollutedNumber*morePollutedNumber;
            squares += LIMIT / morePollutedSquare;
            squares -= times(morePollutedNumber, depth + 1, i);
        }
        return squares;
    }

}

