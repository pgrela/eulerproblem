package pgrela.eulerproblem.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class FactorizedNumber {
    public static final FactorizedNumber ONE = factorizedNumber(1);
    private static final FactorizedNumber ZERO = new FactorizedNumber(Map.of(0L, 1L));
    private final Map<Long, Long> factors;

    private FactorizedNumber(Map<Long, Long> factors) {
        this.factors = factors;
        while (factors.values().remove(0L)) ;
    }

    public Map<Long, Long> factors() {
        return factors;
    }

    public static FactorizedNumber factorizedNumber(long n) {
        if (n == 0) throw new IllegalArgumentException("No zeros, please!");
        return new FactorizedNumber(Primes.getPrimeFactorsHistogram(n).entrySet().stream().collect(Collectors.toMap(e -> (long) e.getKey(), e -> (long) e.getValue())));
    }

    public FactorizedNumber factorial() {
        if (toLong() == 0) return ONE;
        return LongStream.rangeClosed(1, toLong()).mapToObj(FactorizedNumber::factorizedNumber).reduce(ONE, FactorizedNumber::multiply);
    }

    public long toLong() {
        return factors.entrySet().stream().map(e -> Maths.pow(e.getKey(), e.getValue())).reduce(1L, (a, b) -> a * b);
    }

    public FactorizedNumber multiply(FactorizedNumber other) {
        HashMap<Long, Long> factorsSum = new HashMap<>(factors);
        other.factors.forEach((key, value) -> factorsSum.compute(key, (k, v) -> value + (v == null ? 0 : v)));
        return new FactorizedNumber(factorsSum);
    }

    public FactorizedNumber divide(FactorizedNumber divider) {
        HashMap<Long, Long> factorsSum = new HashMap<>(factors);
        divider.factors.forEach((key, value) -> factorsSum.compute(key, (k, v) -> -value + (v == null ? 0 : v)));
        return new FactorizedNumber(factorsSum);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FactorizedNumber that = (FactorizedNumber) o;
        return Objects.equals(factors, that.factors);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(factors);
    }

    @Override
    public String toString() {
        return "FactorizedNumber{" + "factors=" + factors + "=" + toLong() + '}';
    }

    public FactorizedNumber gcd(long b) {
        return gcd(factorizedNumber(b));
    }

    private FactorizedNumber gcd(FactorizedNumber other) {
        Map<Long, Long> newFactors = factors.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> Math.max(e.getValue(), other.factors.getOrDefault(e.getValue(), 0L))));
        return new FactorizedNumber(newFactors);
    }
}
