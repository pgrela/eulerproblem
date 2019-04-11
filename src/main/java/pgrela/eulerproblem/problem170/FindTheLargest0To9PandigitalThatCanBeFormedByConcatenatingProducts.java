package pgrela.eulerproblem.problem170;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.text.ParseException;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class FindTheLargest0To9PandigitalThatCanBeFormedByConcatenatingProducts implements EulerSolver {

    public static final long[] TEN_TO = LongStream.rangeClosed(0, 10).map(e -> Maths.pow(10L, e)).toArray();

    public static void main(String[] args) throws ParseException {
        printSolution(FindTheLargest0To9PandigitalThatCanBeFormedByConcatenatingProducts.class);
    }

    public long solve() {
        int[] digits = IntStream.rangeClosed(0, 9).map(i -> 9 - i).toArray();
        return LongStream.range(0, Maths.factorial(10L) - 1)
                .map(i -> toLong(nextPermutation(digits)))
                .filter(this::isPandigitalProduct)
                .findFirst().orElse(-1);

    }

    private boolean isPandigitalProduct(long n) {
        int remainingLength = 10;
        for (int parts = 2; parts < remainingLength; parts++) {
            int[] lengths = new int[parts];
            if (parts == 2) {
                for (int i = 1; i < remainingLength; i++) {
                    lengths[0] = i;
                    lengths[1] = remainingLength - i;
                    if (arePandigitalProducts(n, lengths)) return true;
                }
            }
            if (parts == 3) {
                for (int i = 1; i < remainingLength; i++) {
                    lengths[0] = i;
                    for (int j = 1; j < remainingLength - i; j++) {
                        lengths[1] = j;
                        lengths[2] = remainingLength - i - j;
                        if (arePandigitalProducts(n, lengths)) return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean arePandigitalProducts(long n, int[] lengths) {

        long[] partValues = new long[lengths.length];
        for (int i = 0; i < lengths.length; i++) {
            partValues[i] = n % TEN_TO[lengths[i]];
            n /= TEN_TO[lengths[i]];
        }
        long gcd = Arrays.stream(partValues).reduce(partValues[0], Maths::gcd);
        return Primes.allDivisors(gcd).filter(d->d!=1).mapToObj(
                factor ->{
                    long[] original = new long[lengths.length + 1];
                    for (int i = 0; i < partValues.length; i++) {
                        original[i]=partValues[i]/factor;
                    }
                    original[lengths.length]=factor;
                    return original;
                }
        ).anyMatch(this::arePandigital);
    }

    private boolean arePandigital(long... n) {
        int[] digits = new int[10];
        for (long k : n) {
            if(k==0)++digits[0];
            while (k > 0) {
                ++digits[(int) (k % 10)];
                k /= 10;
            }
        }
        for (int digit : digits) {
            if (digit != 1) return false;
        }
        return true;
    }

    private long toLong(int[] digits) {
        long r = 0;
        for (int digit : digits) {
            r = r * 10 + digit;
        }
        return r;
    }

    private static int[] nextPermutation(final int[] elements) {
        int left = elements.length - 2;
        while (left >= 0) {
            if (elements[left] > (elements[left + 1]))
                break;
            --left;
        }
        int leftValue = elements[left];
        int right = elements.length - 1;
        while (leftValue < (elements[right]))
            --right;
        swap(elements, left++, right);
        right = elements.length - 1;
        while (left < right)
            swap(elements, left++, right--);
        return elements;
    }

    private static void swap(final int[] c, final int i, final int j) {
        final int tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
}

