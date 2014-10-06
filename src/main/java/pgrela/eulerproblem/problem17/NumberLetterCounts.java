package pgrela.eulerproblem.problem17;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.Stream;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class NumberLetterCounts implements EulerSolver {
    public static void main(String[] args) {
        printSolution(NumberLetterCounts.class);
    }

    public long solve() {
        return rangeClosed(1, 1000)
                .mapToObj(NumberToWordsConverter::intToString)
                .map(s -> s.replaceAll("[^a-z]", ""))
                .map(String::length)
                .reduce(0, Integer::sum);

    }

    protected static class NumberToWordsConverter {
        private static final String[] DIGITS = new String[]{"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        private static final String[] TEENS = new String[]{"ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
        private static final String[] TENS = new String[]{"zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
        private static final String[] HUNDREDS = Stream.of(DIGITS).map(s -> s + " hundred").toArray(String[]::new);
        private static final String[] THOUSANDS = new String[]{"one thousand"};
        public static final String SPACE = " ";
        public static final String SPACE_AND_SPACE = " and ";

        public static String intToString(int n) {
            if (n < 10) return DIGITS[n];
            if (n < 20) return TEENS[n - 10];
            if (n < 100) {
                if (n % 10 == 0) return TENS[n / 10];
                return TENS[n / 10] + SPACE + DIGITS[n % 10];
            }
            if (n < 1000) {
                if (n % 100 == 0) return HUNDREDS[n / 100];
                return HUNDREDS[n / 100] + SPACE_AND_SPACE + intToString(n % 100);
            }
            if (n == 1000) return THOUSANDS[0];
            throw new IllegalArgumentException("I don't know this number :/");
        }
    }

}
