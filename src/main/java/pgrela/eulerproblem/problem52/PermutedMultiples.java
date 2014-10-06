package pgrela.eulerproblem.problem52;

import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Arrays;

import pgrela.eulerproblem.common.EulerSolver;

public class PermutedMultiples implements EulerSolver {

    public static void main(String[] args) {
        printSolution(PermutedMultiples.class);
    }

    public long solve() {
        int[][] a=new int[100][102];
        a[0][0]=1;
        a[0][1]=1;
        for (int i = 1; i < a.length; i++) {
            a[i][0]=1;
            for (int j = 1; j < a[i].length; j++) {
                int value = a[i - 1][j - 1] + a[i - 1][j];
                a[i][j] = value > ONE_MILLION ? ONE_MILLION + 1 : value;
            }
        }
        return Arrays.stream(a).flatMapToInt(Arrays::stream).filter(i->i>ONE_MILLION).count();
    }
}
