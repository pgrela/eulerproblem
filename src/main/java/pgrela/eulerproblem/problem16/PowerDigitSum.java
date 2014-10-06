package pgrela.eulerproblem.problem16;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class PowerDigitSum implements EulerSolver {
    public static void main(String[] args) {
        printSolution(PowerDigitSum.class);
    }

    public long solve() {
        int[] t=new int[500];
        t[0]=1;
        for (int i = 0; i < 1000; i++) {
            int r=0;
            for (int j = 1; j < t.length; j++) {
                int s=t[j-1]*2+r;
                t[j-1]=s%10;
                r=s/10;
            }
        }
        return IntStream.of(t).sum();
    }

}
