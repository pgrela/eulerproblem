package pgrela.eulerproblem.problem78;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Arrays;

import pgrela.eulerproblem.common.EulerSolver;

public class CoinPartitions implements EulerSolver {

    public static final int MAX_ALLOWED_LENGTH = 100000;
    private int[] previous = new int[MAX_ALLOWED_LENGTH + 1];
    private int[] next = new int[MAX_ALLOWED_LENGTH + 1];
    private int MODULO = 1000000;

    public static void main(String[] args) {
        printSolution(CoinPartitions.class);
    }

    public long solve() {
        Arrays.fill(previous,0);
        previous[0]=1;
        next[0]=1;
        for (int max = 1; max <= MAX_ALLOWED_LENGTH; max++) {
            for (int length = 1; length <= MAX_ALLOWED_LENGTH; length++) {
                int sum=0;
                int remainingLength = length;
                while(remainingLength>=0){
                    sum+=previous[remainingLength];
                    remainingLength-=max;
                }
                next[length]=sum%MODULO;
            }
            if(next[max]%MODULO==0){
                return max;
            }
            int[] tmp=next;
            next=previous;
            previous=tmp;
        }
        return -1;
    }
}
