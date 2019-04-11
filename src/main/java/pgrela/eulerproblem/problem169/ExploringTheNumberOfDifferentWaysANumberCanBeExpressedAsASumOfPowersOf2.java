package pgrela.eulerproblem.problem169;

import pgrela.eulerproblem.common.Arrays;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.ParseException;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ExploringTheNumberOfDifferentWaysANumberCanBeExpressedAsASumOfPowersOf2 implements EulerSolver {

    public static final int EXPONENT = 25;

    public static void main(String[] args) throws ParseException {
        printSolution(ExploringTheNumberOfDifferentWaysANumberCanBeExpressedAsASumOfPowersOf2.class);
    }
    
    public long solve() {
        int[] array = (Long.toBinaryString(Maths.pow(5L, EXPONENT)) +
                IntStream.range(0, EXPONENT).map(i -> '0').collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append))
                .chars()
                .map(i -> i - '0')
                .toArray();

        Arrays.reverse(array);
        long overZeros = 0;
        long sum=1;
        for (int bit : array) {
            if(bit==1)sum+=overZeros;
            if(bit==0)overZeros+=sum;
        }
        return sum;
    }
}

