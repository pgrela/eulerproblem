package pgrela.eulerproblem.problem31;

import pgrela.eulerproblem.common.EulerSolver;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CoinSums implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CoinSums.class);
    }

    @Override
    public long solve() {
        int[] ways = new int[201];
        ways[0] = 1;
        for (int coin : new int[]{1, 2, 5, 10, 20, 50, 100, 200}) {
            for (int i = ways.length - 1; i >= 0; i--) {
                int previous = i - coin;
                while (previous >= 0) {
                    ways[i] += ways[previous];
                    previous -= coin;
                }
            }
        }
        return ways[200];
    }
}
