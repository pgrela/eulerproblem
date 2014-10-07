package pgrela.eulerproblem.common;

public class Longs {
    public static long multiply(long a, long b) {
        return a * b;
    }

    public static long safalyMultiplyModulo(long a, long b, long modulo) {
        long k = 1000 * 1000 * 100;
        long aLow = a % k;
        long bLow = b % k;
        long aHigh = a / k;
        long bHigh = b / k;
        return (
                (aHigh * bHigh) % modulo * k % modulo * k % modulo
                        + (aLow * bHigh) % modulo * k % modulo
                        + (aHigh * bLow) % modulo * k % modulo
                        + (aLow * bLow % modulo)
        ) % modulo;
    }

    public static long reverse(long n) {
        long r = 0;
        while (n > 0) {
            r = r * 10 + n % 10;
            n /= 10;
        }
        return r;
    }


    public static long fromDigitArray(int[] digits){
        long r=0;
        for (int digit : digits) {
            r = r * 10 + digit;
        }
        return r;
    }

    public static int[] toDigitArray(long number) {
        return toDigitArray(number, length(number));
    }
    public static int[] toDigitArray(long number, int length) {
        int[] aDigits = new int[length];
        for (int i = 0; i < length; i++) {
            aDigits[i] = (int)number % 10;
            number /= 10;
        }
        return aDigits;
    }

    public static int length(long n) {
        int length = 1;
        while ((n /= 10) > 0) ++length;
        return length;
    }
}
