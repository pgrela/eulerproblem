package pgrela.eulerproblem.common;

import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.Longs.safalyMultiplyModulo;

public class Maths {

    public static long newton(int n, int k) {
        return factorial(k + 1, n) / factorial(k);
    }

    private static long factorial(int k, int n) {
        return LongStream.rangeClosed(k, n).reduce(1, Longs::multiply);
    }

    public static long factorial(long n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    public static int factorial(int n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    public static int pow(int number, int exponent) {
        if (exponent == 0)
            return 1;
        int numberSquared = pow(number * number, exponent / 2);
        return exponent % 2 == 0 ? numberSquared : numberSquared * number;
    }

    public static long pow(long number, long exponent) {
        if (exponent == 0)
            return 1;
        long numberSquared = pow(number * number, exponent / 2);
        return exponent % 2 == 0 ? numberSquared : numberSquared * number;
    }

    public static boolean isPalindrome(int number) {
        return isPalindrome(number, 10);
    }

    public static boolean isPalindrome(int number, int base) {
        if (number % base == 0) {
            return false;
        }
        int multiplier = 1;
        int low = 0;
        int high = 0;
        while (low + high <= number) {
            if (low + high == number) {
                return true;
            }
            int nextDigit = number / multiplier % base;
            low += nextDigit * multiplier;
            high *= base;
            if (low + high == number) {
                return true;
            }
            multiplier *= base;
            high = high * base + nextDigit * multiplier;
        }
        return false;
    }

    public static int intSqrt(int n) {
        return (int) Math.sqrt(n);
    }

    public static boolean isTriangleNumber(int n) {
        int k = (int) Math.sqrt(n * 2);
        return n == k * (k + 1) / 2;
    }

    public static boolean isSquare(int n) {
        return pow((int) Math.sqrt(n), 2) == n;
    }

    public static long powMod(long number, long exponent, long modulo) {
        if (exponent == 0)
            return 1L;
        long numberSquared = powMod(safalyMultiplyModulo(number, number, modulo), exponent / 2, modulo);
        return exponent % 2L == 0L ? numberSquared : safalyMultiplyModulo(numberSquared, number, modulo);
    }
}
