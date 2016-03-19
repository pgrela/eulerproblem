package pgrela.eulerproblem.common;

import java.util.stream.IntStream;

public class Integers {
    public static final int ONE_MILLION = 1000000;
    public static final int ONE_BILLION = 1000000000;

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int length(int n) {
        int length = 1;
        while ((n /= 10) > 0) ++length;
        return length;
    }

    public static boolean arePermutations(int a, int b) {
        int[] digits = new int[10];
        while (a > 0) {
            digits[a % 10]++;
            a /= 10;
        }
        while (b > 0) {
            digits[b % 10]--;
            b /= 10;
        }
        return IntStream.of(digits).allMatch(i -> i == 0);
    }

    public static int fromDigitArray(int[] digits) {
        int r = 0;
        for (int i = 0; i < digits.length; i++) {
            r = r * 10 + digits[i];
        }
        return r;
    }

    public static int[] toDigitArray(int number) {
        return toDigitArray(number, length(number));
    }

    public static int[] toDigitArray(int number, int length) {
        int[] aDigits = new int[length];
        for (int i = 0; i < length; i++) {
            aDigits[i] = number % 10;
            number /= 10;
        }
        return aDigits;
    }
}
