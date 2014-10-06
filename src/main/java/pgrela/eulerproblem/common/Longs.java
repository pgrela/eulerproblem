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
}
