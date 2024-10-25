package pgrela.eulerproblem.problem219;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SkewCostCoding implements EulerSolver {

    public static final long BILLION = 1_000_000_000L;

    public static void main(String[] args) {
        printSolution(SkewCostCoding.class);
    }

    @Override
    public long solve() {
        long[] leaves = new long[70];
        leaves[0] = 1;
        long nodes = 1;
        int cheapestLeaf = 0;
        long cost = 0;
        while (nodes != BILLION) {
            long expand = Math.min(leaves[cheapestLeaf], BILLION - nodes);
            leaves[cheapestLeaf + 1] += expand;
            leaves[cheapestLeaf + 4] += expand;
            nodes += expand;
            cost += expand * (5 + cheapestLeaf);
            cheapestLeaf++;
        }
        return cost;
    }
}

