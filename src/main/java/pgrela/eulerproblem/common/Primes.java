package pgrela.eulerproblem.common;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class Primes {
    private volatile static int[] primesUpTo100000 = null;
    private static Set<Integer> primesSetUpTo100000 = null;

    public static IntStream primes(int maxPrime) {
        BitSet set = new BitSet();
        return IntStream
                .range(2, maxPrime)
                .filter(n -> !set.get(n))
                .peek(n -> IntStream.iterate(n, k -> k + n).limit(maxPrime / n).forEach(set::set));

    }

    public static int[] getPrimesToFactorizeUpTo100000() {
        if (primesUpTo100000 == null) {
            synchronized (Primes.class) {
                if (primesSetUpTo100000 == null) {
                    primesUpTo100000 = primes(100000).toArray();
                }
            }
        }
        return primesUpTo100000;
    }

    public static int[] getPrimesToFactorizeInteger() {
        if (primesUpTo100000 == null) {
            primesUpTo100000 = primes((int) Math.sqrt(Integer.MAX_VALUE)).toArray();
        }
        return primesUpTo100000;
    }

    public static boolean isPrime(int n) {
        return isPrime(n, getPrimesToFactorizeInteger());
    }

    public static boolean isPrime(int n, int[] primes) {
        if (n < primes[primes.length - 1])
            return getSetOfPrimesToFactorizeInteger().contains(n);
        for (int prime : primes) {
            if (n % prime == 0) {
                return false;
            }
            if (prime * prime > n) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPrime(long n, long[] primes) {
        for (long prime : primes) {
            if (n % prime == 0) {
                return false;
            }
            if (prime * prime > n) {
                return true;
            }
        }
        return false;
    }

    private static Set<Integer> getSetOfPrimesToFactorizeInteger() {
        if (primesSetUpTo100000 == null) {
            primesSetUpTo100000 = IntStream.of(getPrimesToFactorizeInteger()).boxed().collect(Collectors.toSet());
        }
        return primesSetUpTo100000;
    }

    public static List<Integer> factorize(int n) {
        List<Integer> primeFactors = new ArrayList<>();
        if (n < 1) return primeFactors;
        int[] primes = getPrimesToFactorizeInteger();
        for (int prime : primes) {
            while (n % prime == 0) {
                primeFactors.add(prime);
                n /= prime;
            }
            if (prime * prime > n) {
                if (n != 1) {
                    primeFactors.add(n);
                }
                return primeFactors;
            }
        }
        return primeFactors;

    }

    public static IntStream allDivisors(int number) {
        return allDivisors(number, getPrimesToFactorizeUpTo100000());
    }

    public static int sumDivisors(int number) {
        Map<Integer, Integer> primesCount = getPrimeFactorsHistogram(number, getPrimesToFactorizeUpTo100000());
        int sum = 1;
        for (Map.Entry<Integer, Integer> primeFactor : primesCount.entrySet()) {
            int factor = primeFactor.getKey();
            sum += sum * factor * ((Maths.pow(factor, primeFactor.getValue()) - 1) / (factor - 1));
        }
        return sum - number;
    }

    public static IntStream allDivisors(int number, int[] primes) {
        Map<Integer, Integer> primesCount = getPrimeFactorsHistogram(number, primes);
        List<Integer> factors = new ArrayList<>();
        factors.add(1);
        for (Map.Entry<Integer, Integer> primeFactor : primesCount.entrySet()) {
            int factor = 1;
            int processUpTo = factors.size();
            for (int i = 0; i < primeFactor.getValue(); i++) {
                factor *= primeFactor.getKey();
                for (int j = 0; j < processUpTo; j++) {
                    factors.add(factor * factors.get(j));
                }
            }
        }
        return factors.stream().mapToInt(i -> i);
    }

    public static Map<Integer, Integer> getPrimeFactorsHistogram(long number) {
        return getPrimeFactorsHistogram(number, getPrimesToFactorizeInteger());
    }

    public static Map<Integer, Integer> getPrimeFactorsHistogram(long number, int[] primes) {
        Map<Integer, Integer> primesCount = new HashMap<>(10);
        for (int prime : primes) {
            int times = 0;
            while (number % prime == 0) {
                number /= prime;
                ++times;
            }
            if (times > 0)
                primesCount.put(prime, times);
            if (number == 1) {
                break;
            }
            if (prime * prime > number) {
                primesCount.put((int)number, 1);
                break;
            }
        }
        return primesCount;
    }

    public static LongStream allDivisors(long number) {
        return allDivisors(number, getPrimesToFactorizeUpTo100000());
    }

    public static LongStream allDivisors(long number, int... primes) {
        LongStream divisors = LongStream.of(1);
        for (int prime : primes) {
            int times = 0;
            while (number % prime == 0) {
                number /= prime;
                ++times;
            }
            if (times > 0) {
                final int finalTimes = times;
                divisors = divisors.flatMap(i -> LongStream.rangeClosed(0, finalTimes).map(e -> i * Maths.pow(prime, e)));
            }
            if (number == 1) {
                break;
            }
        }
        return divisors;
    }

    public static List<Integer> factorizeLikeInt(long n) {

        List<Integer> primeFactors = new ArrayList<>();
        if (n < 1) return primeFactors;
        int[] primes = getPrimesToFactorizeInteger();
        for (int prime : primes) {
            while (n % prime == 0) {
                primeFactors.add(prime);
                n /= prime;
            }
            if (prime * prime > n) {
                if (n != 1) {
                    primeFactors.add((int) n);
                }
                return primeFactors;
            }
        }
        return primeFactors;

    }


}
