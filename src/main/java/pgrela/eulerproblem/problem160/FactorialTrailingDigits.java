package pgrela.eulerproblem.problem160;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Longs;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FactorialTrailingDigits implements EulerSolver {

    private static final int TWELVE = 2;
    private static final long N = Maths.pow(10L, TWELVE);
    private static final int FIVE = 1;
    private static final int TEN_TO_5 = Maths.pow(10, FIVE);

    public static void main(String[] args) throws ParseException {
        printSolution(FactorialTrailingDigits.class);
    }

    public long solve() {
        long twos = 0;
        long fives = 0;
        {
            long p = 2;
            do {
                twos += N / p;
                p *= 2;
            } while (N >= p);
        }
        {
            long p = 5;
            do {
                fives += N / p;
                p *= 5;
            } while (N >= p);
        }
        long s = 1;
        long ones = N / Maths.pow(10L, FIVE - 1) / 9;
        for (int aPrime = 1; aPrime < TEN_TO_5; aPrime++) {
            if (aPrime % 2 != 0 && aPrime % 5 != 0) {
                int seven = TWELVE - FIVE;
                int times = 0;
//                times += FIVE - Integers.length(aPrime);
//                System.out.println(times);
//                for (int i = 0; i <= seven; i++) {
//                    times += Maths.pow(10, i);
//                }
                System.out.println(times);
                long reductor = 1;
                long original = aPrime;
                while (original <= N) {
                    for (int trailingZeros = 0; trailingZeros < FIVE - Longs.length(original); trailingZeros++) {

                        int internalIncrement = FIVE - Longs.length(original);
                        if (internalIncrement > 0)
                            times += internalIncrement;
                        long remainder = original / TEN_TO_5;
                        for (int i = 0; i <= seven; i++) {
                            long increment = (Maths.pow(10, i) - remainder) / reductor;
                            if (increment > 0)
                                times += increment;
                        }
                        original *= 2;
                        reductor *= 2;
                    }
                }
                System.out.println(String.format("%d^%d", aPrime, times));
                s *= pow(aPrime, times);
            }
        }
        System.out.println(rd(s * pow(2, twos - fives)));
        Map<Long, Integer> map = new HashMap<>();
        for (long i = 1; i < N; i++) {
            long rd = rd(s25((int) i));
            map.merge(rd, 1, (a, b) -> a + b);
        }
        long[] longs = map.keySet().stream().mapToLong(i -> pow(i, 11)).toArray();
        s = 1;
        for (int i = 1; i < TEN_TO_5; i++) {
            if (i == rd(i)) {
                //fives+=s5(i);
                //twos+=s2(i);
                int ii = s25(i);
                s = rd(s * pow(ii, ones + FIVE - Integers.length(i)));
            }
        }
        return s * pow(2, twos - fives);
    }

    private long rd(long n) {
        while (n % 10 == 0) n /= 10;
        return n % (TEN_TO_5);
    }

    private int s25(int n) {
        while (n % 2 == 0) n /= 2;
        while (n % 5 == 0) n /= 5;
        return n;
    }

    private long s2(long n) {
        int s = 0;
        while (n % 2 == 0) {
            n /= 2;
            ++s;
        }
        return s;
    }

    private long s5(long n) {
        int s = 0;
        while (n % 5 == 0) {
            n /= 5;
            ++s;
        }
        return s;
    }

    public long pow(long number, long exponent) {
        if (exponent == 0)
            return 1;
        long numberSquared = rd(pow(rd(number * number), exponent / 2));
        return exponent % 2 == 0 ? numberSquared : rd(numberSquared * number);
    }
}

