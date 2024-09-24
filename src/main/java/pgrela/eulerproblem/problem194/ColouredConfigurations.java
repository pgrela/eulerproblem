package pgrela.eulerproblem.problem194;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ColouredConfigurations implements EulerSolver {

    public static final long MODULO = Maths.pow(10L, 8);

    public static void main(String[] args) {
        printSolution(ColouredConfigurations.class);
    }

    public static final int A = 25;
    public static final int B = 75;
    public static final int C = 1984;

    @Override
    public long solve() {
        return combinations(A, B, C);
    }

    protected long combinations(int typeA, int typeB, int colours) {
        long ac3 = possibleAContinuationWithColours(3);
        long ac4 = possibleAContinuationWithColours(4);
        long ac5 = possibleAContinuationWithColours(5);
        long ac6 = possibleAContinuationWithColours(6);
        long bc3 = possibleBContinuationWithColours(3);
        long bc4 = possibleBContinuationWithColours(4);
        long bc5 = possibleBContinuationWithColours(5);
        long bc6 = possibleBContinuationWithColours(6);
        long ac = Maths.newton(colours - 2, 3 - 2) * ac3
                + (colours < 4 ? 0 : Maths.newtonModulo(colours - 2, 4 - 2, MODULO) * ac4)
                + (colours < 5 ? 0 : Maths.newtonModulo(colours - 2, 5 - 2, MODULO) * ac5)
                + (colours < 6 ? 0 : Maths.newtonModulo(colours - 2, 6 - 2, MODULO) * ac6)
                + (colours < 7 ? 0 : Maths.newtonModulo(colours - 2, 7 - 2, MODULO) * Maths.factorial(7 - 2));
        long bc = Maths.newtonModulo(colours - 2, 3 - 2, MODULO) * bc3
                + (colours < 4 ? 0 : Maths.newtonModulo(colours - 2, 4 - 2, MODULO) * bc4)
                + (colours < 5 ? 0 : Maths.newtonModulo(colours - 2, 5 - 2, MODULO) * bc5)
                + (colours < 6 ? 0 : Maths.newtonModulo(colours - 2, 6 - 2, MODULO) * bc6)
                + (colours < 7 ? 0 : Maths.newtonModulo(colours - 2, 7 - 2, MODULO) * Maths.factorial(7 - 2));
        ac %= MODULO;
        bc %= MODULO;
        return colours * (colours - 1) * Maths.powMod(ac, typeA, MODULO) % MODULO * Maths.powMod(bc, typeB, MODULO) % MODULO * Maths.newtonModulo(typeA + typeB, typeA, MODULO) % MODULO;
    }

    protected long possibleAContinuationWithColours(int colours) {
        return seriouslyInternalCalculation(colours, true);
    }

    private long seriouslyInternalCalculation(int colours, boolean isItASegment) {

        int valid = 0;
        int pow = Maths.pow(colours, 5);
        for (int i = 0; i < pow; i++) {
            boolean[] usedColours = new boolean[colours];
            int n = i;
            int b = (n) % colours;
            int c = (n /= colours) % colours;
            int d = (n /= colours) % colours;
            int e = (n /= colours) % colours;
            int g = (n /= colours) % colours;
            int a = 0;
            int f = 1;
            usedColours[a] = true;
            usedColours[b] = true;
            usedColours[c] = true;
            usedColours[d] = true;
            usedColours[e] = true;
            usedColours[f] = true;
            usedColours[g] = true;
            if (a == b || b == c || a == c) continue;
            if (c == d || d == e || e == f || e == g || b == g) continue;
            if (isItASegment && f == g) continue;
            if (IntStream.range(0, usedColours.length).filter(j -> usedColours[j]).count() != colours) {
                continue;
            }
            ;
            ++valid;
        }

        return valid;
    }

    private long possibleBContinuationWithColours(int colours) {
        return seriouslyInternalCalculation(colours, false);
    }

}

