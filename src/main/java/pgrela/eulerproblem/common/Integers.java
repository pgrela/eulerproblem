package pgrela.eulerproblem.common;

import java.util.Arrays;

public class Integers {
    public static final int ONE_MILLION = 1000000;

    public static int multiply(int a, int b) {
        return a * b;
    }

    public static int length(int n) {
        int length = 1;
        while ((n /= 10) > 0) ++length;
        return length;
    }

    public static boolean arePermutations(int a, int b) {
        int length = Integers.length(a);
        if (length != Integers.length(b)) {
            return false;
        }
        int[] aDigits = toDigitArray(a, length);
        int[] bDigits = toDigitArray(b, length);
        Arrays.sort(aDigits);
        Arrays.sort(bDigits);
        return Arrays.equals(aDigits, bDigits);
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
