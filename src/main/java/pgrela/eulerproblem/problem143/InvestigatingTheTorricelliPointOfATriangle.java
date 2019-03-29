package pgrela.eulerproblem.problem143;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingTheTorricelliPointOfATriangle implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(InvestigatingTheTorricelliPointOfATriangle.class);
    }

    public long solve() {
        long max = 40;
        long s = 0;
        for (int a = 1; a < max; a++) {
            for (int b = 1; b <= a; b++) {
                for (int c = 1; c <= b && a + b + c <= max; c++) {
                    if (Maths.isSquare(a * a + b * b + a * b) && Maths.isSquare(a * a + c * c + a * c) && Maths.isSquare(c * c + b * b + c * b)) {
                        System.out.println(String.format("%d %d %d = [%d %d %d]", a, b, c, Maths.intSqrt(a * a + b * b + a * b), Maths.intSqrt(a * a + c * c + a * c), Maths.intSqrt(c * c + b * b + c * b)));
                        s += a + b + c;
                        System.out.println(a + b + c);
                    }
                }
            }
        }
        int mx = Maths.intSqrt(120_0000);
        Map<Long, Set<Long>> byLonger = new HashMap<>();
        Map<Long, Set<Long>> byShorter = new HashMap<>();
        for (long m = 1; m < mx; m++) {
            for (long n = 1; n < m; n++) {
                if (((m ^ n) & 1) == 1) continue;
                long a = (m * m - 3 * n * n - 2 * m * n) / 4;
                long b = m * n;
                long c = (m * m + 3 * n * n) / 4;
                if (a <= 0 || b <= 0 || c <= 0) continue;
                long l = 120_000 / Math.max(a, b);
                for (int i = 1; i <= l; i++) {
                    long shorter = Math.min(a, b) * i;
                    long longer = Math.max(a, b) * i;
                    byLonger.computeIfAbsent(longer, x -> new HashSet<>()).add(shorter);
                    byShorter.computeIfAbsent(shorter, x -> new HashSet<>()).add(longer);
                }
            }
        }
        return byLonger.entrySet().stream().map(entry ->
                entry.getValue().stream().map(shorter -> entry.getValue()
                        .stream()
                        .filter(longer -> byShorter.get(shorter).contains(longer))
                        .map(third -> shorter + entry.getKey() + third)
                )
                        .flatMap(i -> i)
        )
                .flatMap(i -> i)
                .mapToLong(i -> i)
                .filter(i -> i <= 120_000)
                .sorted()
                .distinct()
                .sum();
    }


}

