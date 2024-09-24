package pgrela.eulerproblem.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.Longs.safalyMultiplyModulo;
import static pgrela.eulerproblem.common.Primes.factorize;
import static pgrela.eulerproblem.common.Primes.factorizeLikeInt;

public class Maths {

    public static long newton(int n, int k) {
        if (k < n - k) return newton(n, n - k);
        return factorial(k + 1, n) / factorial(n - k);
    }

    public static long newtonModulo(int n, int k, long modulo) {
        if (k < n - k) return newtonModulo(n, n - k, modulo);
        int[] nominator = IntStream.rangeClosed(k + 1, n).toArray();
        for (int i = 2; i <= n - k; i++) {
            int j = i;
            int l = 0;
            while (j > 1) {
                int gcd = gcd(nominator[l], j);
                if (gcd > 1) {
                    j /= gcd;
                    nominator[l] /= gcd;
                }
                ++l;
            }
        }
        return Arrays.stream(nominator).mapToLong(i -> i).reduce(1L, (a, b) -> a * b % modulo);
    }

    private static long factorial(int k, int n) {
        return LongStream.rangeClosed(k, n).reduce(1, Longs::multiply);
    }

    public static long factorial(long n) {
        return n == 0 ? 1 : n * factorial(n - 1);
    }

    public static int factorial(int n) {
        return n <= 0 ? 1 : n * factorial(n - 1);
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

    public static boolean isPalindrome(long number, int base) {
        if (number % base == 0) {
            return false;
        }
        long multiplier = 1;
        long low = 0;
        long high = 0;
        while (low + high <= number) {
            if (low + high == number) {
                return true;
            }
            long nextDigit = number / multiplier % base;
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

    public static int intSqrt(long n) {
        return (int) Math.sqrt(n);
    }

    public static long intRoot(long n, int degree) {
        return Math.round(Math.pow(n, 1. / degree));
    }

    public static boolean isPow(long n, int exponent) {
        return pow(intRoot(n, exponent), exponent) == n;
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

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static long lcm(long a, long b) {
        return (a / Maths.gcd(a, b)) * b;
    }

    public static int lcm(int a, int b) {
        return (a / Maths.gcd(a, b)) * b;
    }

    public static boolean isSquare(long n) {
        return pow((long) Math.sqrt(n), 2) == n;
    }

    public static int totient(int n) {
        List<Integer> f = factorize(n);
        return n / f.stream().distinct().reduce(1, (a, b) -> a * b) * f.stream().distinct().sequential().map(i -> i - 1).reduce(1, (a, b) -> a * b);
    }

    public static int totientLikeInt(long n) {
        List<Integer> f = factorizeLikeInt(n);
        return (int) (n / f.stream().distinct().reduce(1, (a, b) -> a * b) * f.stream().distinct().sequential().map(i -> i - 1).reduce(1, (a, b) -> a * b));
    }

    public static long longSqrt(long n) {
        return (long) Math.sqrt(n);
    }

    public static boolean isCube(long n) {
        return pow((long) Math.cbrt(n), 3) == n;
    }

    public static int log(long base, long n) {
        int r = 0;
        while (n >= base) {
            n /= base;
            ++r;
        }
        return r;
    }

    public static long gcd(long a, long b, long c) {
        return gcd(a,gcd(b,c));
    }
}
