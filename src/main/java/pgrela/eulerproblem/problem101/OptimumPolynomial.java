package pgrela.eulerproblem.problem101;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class OptimumPolynomial implements EulerSolver {

    public static final int SIZE = 21;

    public static void main(String[] args) {
        printSolution(OptimumPolynomial.class);
    }

    public long solve() {
        long r = 1;
        long[][] table = new long[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[0][i] = f2(i + 1);
        }
        for (int diffs = 1; diffs < 10; diffs++) {
            for (int rows = 0; rows < diffs; rows++) {
                for (int cell = 0; cell < diffs - rows; cell++) {
                    table[1 + rows][cell] = table[rows][cell + 1] - table[rows][cell];
                }
            }
            //trace back
            table[diffs + 1][0] = 0;
            for (int i = 0; i < diffs; i++) {
                table[diffs - i][i + 1] = table[diffs - i][i] + table[diffs - i + 1][i];
            }
            long probableBOP = table[0][diffs] + table[1][diffs];
            if (table[0][diffs + 1] != probableBOP) {
                r += probableBOP;
            } else {
                throw new RuntimeException("Not implemented!");
            }
        }
        return r;
    }

    private long f(int n) {
        return new Equation(1, 0, 0, 0).calculate(n);
    }

    private long f2(int n) {
        return new Equation(1, -1, 1, -1, 1, -1, 1, -1, 1, -1, 1).calculate(n);
    }

}
