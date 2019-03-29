package pgrela.eulerproblem.problem148;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ExploringPascalsTriangle implements EulerSolver {
    long caches[] = new long[20];

    public static void main(String[] args) throws ParseException {
        printSolution(ExploringPascalsTriangle.class);
    }

    public long solve() {
        long lines = 1000_000_000;
        return (lines + 1) * lines / 2 - light((int) (1 + Math.log(lines) / Math.log(7)), lines);
    }

    private long light(int degree, long limit) {
        if (degree <= 1) return 0;
        if (limit <= 0) return 0;
        long lowerBase = Maths.pow(7L, degree - 1);
        if (limit > lowerBase * 7 && caches[degree] != 0) return caches[degree];
        long cache = LongStream.range(0, 7)
                .map(h -> (h + 1) * light(degree - 1, limit - h * lowerBase)
                        + h * dark(lowerBase, limit - h * lowerBase)
                )
                .sum();
        if (limit > lowerBase * 7) caches[degree] = cache;
        return cache;
    }

    private long dark(long base, long limit) {
        if (limit < 1 || base < 2) {
            return 0;
        }
        if (base > limit) return (base - 1 + base - limit) * limit / 2;
        return (base - 1) * (base) / 2;
    }
}

