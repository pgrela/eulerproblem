package pgrela.eulerproblem.problem189;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Arrays;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TriColouringATriangularGrid implements EulerSolver {

    public static final int TRIANGLE_LEVELS = 8;
    public static final int MAX_EDGE_SIGNATURE = 3 << (TRIANGLE_LEVELS * 2 - 2);

    public static void main(String[] args) {
        printSolution(TriColouringATriangularGrid.class);
    }

    @Override
    public long solve() {
        long[] upper = new long[MAX_EDGE_SIGNATURE];
        long[] lower = new long[MAX_EDGE_SIGNATURE];
        lower[0] = 1;
        lower[1] = 1;
        lower[2] = 1;
        int level = 1;
        while (level < TRIANGLE_LEVELS) {
            long[] tmp = upper;
            upper = lower;
            lower = tmp;
            Arrays.fill(lower, 0);
            for (int ui = 0; ui < (3 << (level * 2)); ui++) {
                if (upper[ui] > 0) {
                    walk(ui, level * 2 + 1, 0, 0, 0, lower, upper[ui]);
                }
            }
            ++level;
        }
        return Arrays.stream(lower).sum();
    }

    void walk(int upper, int length, int previousBottom, int previous, int current, long[] lower, long strength) {
        if (current == length) {
            lower[previousBottom] += strength;
            return;
        }
        if (current == 0) {
            for (int i = 0; i < 3; i++) {
                walk(upper, length, i, i, current + 1, lower, strength);
            }
            return;
        }
        if (current % 2 == 0) {
            for (int i = 1; i <= 2; i++) {
                int next = (previous + i) % 3;
                walk(upper, length, next | (previousBottom << 2), next, current + 1, lower, strength);
            }
            return;
        }
        int above = (upper >> ((length / 2 - 1 - current / 2) * 2)) & 3;
        if (previous == above) {
            walk(upper, length, previousBottom, ((previous + 1) % 3), current + 1, lower, strength);
            walk(upper, length, previousBottom, ((previous + 2) % 3), current + 1, lower, strength);
        } else {
            walk(upper, length, previousBottom, ((3 - previous - above)), current + 1, lower, strength);
        }
    }

}

