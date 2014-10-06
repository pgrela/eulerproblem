package pgrela.eulerproblem.problem40;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Maths;

import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ChampernownesConstant implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ChampernownesConstant.class);
    }

    @Override
    public long solve() {
        StringBuilder theNumber = new StringBuilder();
        int n = 0;
        while (theNumber.length() < Integers.ONE_MILLION) {
            theNumber.append(++n);
        }
        return IntStream.rangeClosed(0, 6).map(i -> Maths.pow(10, i)).map(i -> theNumber.charAt(i - 1) - '0').peek(System.out::println).reduce(1, Integers::multiply);
    }
}
