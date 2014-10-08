package pgrela.eulerproblem.problem483;

import java.util.Arrays;

import pgrela.eulerproblem.common.Primes;

public class LowestCommonMultiple {

    public static int[] FACTORS_INDICES;

    public static int SIZE_PRIME_INDEX;

    public static int[] PRIMES;

    private static LowestCommonMultiple[] INSTANCES;

    static{
        FACTORS_INDICES = new int[RepeatedPermutation.MAX_ALLOWED_LENGTH];
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

    public static LowestCommonMultiple toLCM(int n){
        if(INSTANCES==null){
            INSTANCES = new LowestCommonMultiple[RepeatedPermutation.MAX_ALLOWED_LENGTH+1];
            for (int j = 0; j <= RepeatedPermutation.MAX_ALLOWED_LENGTH; j++) {
                INSTANCES[j]=new LowestCommonMultiple(j);
            }
        }
        if(n<= RepeatedPermutation.MAX_ALLOWED_LENGTH)
            return INSTANCES[n].clone();
        return new LowestCommonMultiple(n);
    }

    private LowestCommonMultiple(int i) {
        factors = new int[SIZE_PRIME_INDEX];
        for (int k : Primes.factorize(i)) {
            factors[FACTORS_INDICES[k]]++;
        }
    }

    public LowestCommonMultiple getCommonWith(int n) {
        LowestCommonMultiple lcm = toLCM(n);
        return getCommonWith(lcm);
    }

    public LowestCommonMultiple getCommonWith(LowestCommonMultiple lowestCommonMultiple) {
        LowestCommonMultiple lcm=lowestCommonMultiple.clone();
        for (int i = 0; i < SIZE_PRIME_INDEX; i++) {
            lcm.factors[i] = Math.max(factors[i], lcm.factors[i]);
        }
        return lcm;

    }

    public LowestCommonMultiple clone(){
        return new LowestCommonMultiple(Arrays.copyOf(factors,factors.length));
    }

    public boolean isDominatedBy(LowestCommonMultiple other) {
        for (int i = 0; i < factors.length; i++) {
            if (factors[i] > other.factors[i]) return false;
        }
        return true;
    }

    public double toDouble() {
        double value = 1;
        for (int i = 0; i < SIZE_PRIME_INDEX; i++) {
            if (factors[i] == 0) continue;
            value *= Math.pow(PRIMES[i], factors[i]);
        }
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LowestCommonMultiple that = (LowestCommonMultiple) o;

        return Arrays.equals(factors, that.factors);

    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(factors);
    }

    @Override
    public String toString() {
        return "LowestCommonMultiple{" + "factors=" + Arrays.toString(factors) + '}';
    }

    public static LowestCommonMultiple toPoweredLCM(int n, int exponent) {
        LowestCommonMultiple lcm = toLCM(n);
        for (int i = 0; i < lcm.factors.length; i++) {
            lcm.factors[i]*=exponent;
        }
        return lcm;
    }
}
