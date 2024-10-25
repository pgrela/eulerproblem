package pgrela.eulerproblem.problem201;

import pgrela.eulerproblem.common.*;

import java.util.Arrays;
import java.util.BitSet;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SubsetsWithAUniqueSum implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SubsetsWithAUniqueSum.class);
    }

    @Override
    public long solve() {
        List<Integer> set = IntStream.rangeClosed(1, 100).map(i -> i * i).boxed().toList();
        int[][] sums=new int[set.size()/2+1][set.stream().mapToInt(Integer::intValue).sum()];
        sums[0][0]=1;
        for (Integer number : set) {
            for (int totalNumbers = set.size() / 2; totalNumbers > 0; totalNumbers--) {
                for (int possibleSum = sums[totalNumbers].length - 1; possibleSum >= number; possibleSum--) {
                    sums[totalNumbers][possibleSum] += sums[totalNumbers-1][possibleSum-number];
                    if(sums[totalNumbers][possibleSum]>2)sums[totalNumbers][possibleSum]=2;
                }
            }
        }
        return IntStream.range(0, sums[set.size()/2].length).filter(i->sums[set.size()/2][i]==1).sum();
    }
}

