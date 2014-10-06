package pgrela.eulerproblem.problem1;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class MultipliesOf3And5 implements EulerSolver{
    public static void main(String[] args) {
        printSolution(MultipliesOf3And5.class);
    }
    public long solve(){
        return IntStream
                .iterate(1, n -> n + 1)
                .limit(1001)
                .filter(n -> n % 3 == 0 || n % 5 == 0)
                .sum();
    }
}
