package pgrela.eulerproblem.problem483;

import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.util.Arrays;

public class LowestCommonMultiple {

    public static int[] FACTORS_INDICES;

    public static int SIZE_PRIME_INDEX;

    public static int[] PRIMES;

    private static LowestCommonMultiple[] CACHED_INSTANCES;

    private static int MAX_ALLOWED_LENGTH = RepeatedPermutation.MAX_ALLOWED_LENGTH+1;

    static {
        FACTORS_INDICES = new int[MAX_ALLOWED_LENGTH+1];
        PRIMES = Primes.primes(FACTORS_INDICES.length).toArray();
        for (int i = 0; i < PRIMES.length; i++) {
            FACTORS_INDICES[PRIMES[i]] = i;
        }
        SIZE_PRIME_INDEX = PRIMES.length;
    }

    final int[] factors;

    private LowestCommonMultiple(int[] factors) {
        this.factors = factors;
    }

    public static LowestCommonMultiple toLCM(long n) {
        if (CACHED_INSTANCES == null) {
            CACHED_INSTANCES = new LowestCommonMultiple[MAX_ALLOWED_LENGTH + 1];
            for (int j = 0; j <= MAX_ALLOWED_LENGTH; j++) {
                CACHED_INSTANCES[j] = new LowestCommonMultiple(j);
            }
        }
        if (n <= MAX_ALLOWED_LENGTH)
            return CACHED_INSTANCES[(int) n].clone();
        return new LowestCommonMultiple(n);
    }

    private LowestCommonMultiple(long n) {
        factors = new int[SIZE_PRIME_INDEX];
        for (int k : Primes.factorizeLikeInt(n)) {
            factors[FACTORS_INDICES[k]]++;
        }
    }

    public LowestCommonMultiple lcmWith(long n) {
        LowestCommonMultiple lcm = toLCM(n);
        return lcmWith(lcm);
    }

    public LowestCommonMultiple gcdWith(long n) {
        LowestCommonMultiple lcm = toLCM(n);
        return gcdWith(lcm);
    }

    private LowestCommonMultiple gcdWith(LowestCommonMultiple lowestCommonMultiple) {
        LowestCommonMultiple lcm = lowestCommonMultiple.clone();
        for (int i = 0; i < SIZE_PRIME_INDEX; i++) {
            lcm.factors[i] = Math.min(factors[i], lcm.factors[i]);
        }
        return lcm;
    }

    public LowestCommonMultiple lcmWith(LowestCommonMultiple lowestCommonMultiple) {
        LowestCommonMultiple lcm = lowestCommonMultiple.clone();
        for (int i = 0; i < SIZE_PRIME_INDEX; i++) {
            lcm.factors[i] = Math.max(factors[i], lcm.factors[i]);
        }
        return lcm;

    }

    public LowestCommonMultiple clone() {
        return new LowestCommonMultiple(Arrays.copyOf(factors, factors.length));
    }

    public long toLong() {
        long value = 1;
        for (int i = 0; i < SIZE_PRIME_INDEX; i++) {
            if (factors[i] == 0) continue;
            value *= Maths.pow(PRIMES[i], factors[i]);
        }
        return value;
    }

    @Override
    public String toString() {
        return "LowestCommonMultiple{" + "factors=" + Arrays.toString(factors) + '}';
    }
}
