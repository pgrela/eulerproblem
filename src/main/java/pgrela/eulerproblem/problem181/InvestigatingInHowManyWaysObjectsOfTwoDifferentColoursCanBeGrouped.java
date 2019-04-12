package pgrela.eulerproblem.problem181;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Pair;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class InvestigatingInHowManyWaysObjectsOfTwoDifferentColoursCanBeGrouped implements EulerSolver {

    private static final int BLACK = 60;
    private static final int WHITE = 40;

    private long[][][] cache;

    public static void main(String[] args) {
        printSolution(InvestigatingInHowManyWaysObjectsOfTwoDifferentColoursCanBeGrouped.class);
    }

    @Override
    public long solve() {
        Pair[] groups = Pair.pairs(0, 0, BLACK, WHITE)
                .filter(p -> p.a + p.b > 0).toArray(Pair[]::new);
        cache = new long[BLACK + 1][WHITE + 1][groups.length];
        for (int i = 0; i <= BLACK; i++) {
            for (int j = 0; j <= WHITE; j++) {
                for (int k = 0; k < groups.length; k++) {
                    cache[i][j][k] = -1;
                }
            }
        }
        return fit(BLACK, WHITE, 0, groups);
    }

    private long fit(int black, int white, int i, Pair[] groups) {
        if (i == groups.length)
            return black == 0 && white == 0 ? 1 : 0;
        if (cache[black][white][i] != -1)
            return cache[black][white][i];
        long r = 0;
        int k = 0;
        while (black - groups[i].a * k >= 0 && white - groups[i].b * k >= 0) {
            r += fit(black - groups[i].a * k, white - groups[i].b * k, i + 1, groups);
            ++k;
        }

        return cache[black][white][i] = r;
    }
}

