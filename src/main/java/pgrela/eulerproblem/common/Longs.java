package pgrela.eulerproblem.common;

public class Longs {
    public static final long ONE_BILLION = 1000000000L;

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


    public static long fromDigitArray(int[] digits) {
        long r = 0;
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
            aDigits[i] = (int) number % 10;
            number /= 10;
        }
        return aDigits;
    }

    public static int length(long n) {
        int length = 1;
        while ((n /= 10) > 0) ++length;
        return length;
    }

    public static int positiveBits(long n) {
        int positive = 0;
        do {
            positive += n & 1;
        } while ((n >>= 1) > 0);
        return positive;
    }

    public static int binaryLength(long n) {
        int k = 0;
        while (n > 0) {
            ++k;
            n /= 2;
        }
        return k;
    }

    public static long sqrt(long n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        if (n >= (1L << 62)) {
            throw new IllegalArgumentException("Squares of numbers from " + (1 << 31) + " to " + 3037000499L + " are not allowed but you are welcome to add support for them.");
        }
        long digit = 1;
        while ((digit) * (digit) <= n) digit <<= 1;
        digit >>= 1;
        long root = digit;
        digit >>= 1;
        while (digit > 0) {
            if (n >= (root | digit) * (root | digit))
                root |= digit;
            digit >>= 1;
        }
        if(n<(root)*(root) || (root+1)*(root+1)<=n){
            throw new RuntimeException();
        }
        return root;
    }

    public static boolean isPerfectSquare(long n) {
        long sqrt = sqrt(n);
        return sqrt * sqrt == n;
    }
}
