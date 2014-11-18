package pgrela.eulerproblem.problem98;

import static pgrela.eulerproblem.common.Files.getFileAsString;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Maths;

public class AnagramicSquares implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem98/words.txt";

    public static void main(String[] args) {
        printSolution(AnagramicSquares.class);
    }

    public long solve() {
        String[] words = getFileAsString(RESOURCE_FILE).replace("\"", "").split(",");
        int maxLength = Stream.of(words).parallel().mapToInt(String::length).max().getAsInt();
        int maxNumber = Maths.intSqrt(Maths.pow(10,maxLength+1));
        IntStream.rangeClosed(1, maxNumber).boxed().collect(Collectors.toMap(this::lastAnagram, i -> i));
        return 0;
    }
    public int signature(Integer number){
        int[] digits = Integers.toDigitArray(number);
        Arrays.sort(digits);
        pgrela.eulerproblem.common.Arrays.reverse(digits);
        return Integers.fromDigitArray(digits);
    }
    public int lastAnagram(Integer number){
        int[] digits = Integers.toDigitArray(number);
        Arrays.sort(digits);
        pgrela.eulerproblem.common.Arrays.reverse(digits);
        return Integers.fromDigitArray(digits);
    }

}
