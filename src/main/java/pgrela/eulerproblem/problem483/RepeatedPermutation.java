package pgrela.eulerproblem.problem483;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RepeatedPermutation implements EulerSolver {

    public static final int DEFAULT_LENGTH = 20;
    private LowestCommonMultiple[] lcms;

    public static void main(String[] args) {
        printSolution(RepeatedPermutation.class);
    }

    public String solveToString() {
        return DECIMAL_FORMATTER.format(calculateWithMaximalCycle(length, length, new LowestCommonMultiple(1)) / factorials[length]);
    }

    private double calculateWithMaximalCycle(int length, int maximalCycleLength, LowestCommonMultiple lcm) {
        if (length == 0) {
            return Math.pow(lcm.toDouble(), 2);
        }
        if (maximalCycleLength < 2) {
            return Math.pow(lcm.toDouble(), 2);
        }
        if (lcms[maximalCycleLength]!=null && lcms[maximalCycleLength].isDominatedBy(lcm)) {
            if(cache[length][maximalCycleLength]>-0.5)
            {}//return cache[length][maximalCycleLength]*Math.pow(lcm.toDouble()/lcms[maximalCycleLength].toDouble(),2);
        }

        int howManyMaximalCyclesCanFitIn = length / maximalCycleLength;
        double sum = calculateWithMaximalCycle(length, maximalCycleLength - 1, lcm);
        double waysOfChoosingCycleOrder = factorials[maximalCycleLength - 1];
        LowestCommonMultiple lcmWithMCL = lcm.getCommonWith(maximalCycleLength);
        for (int i = 1; i <= howManyMaximalCyclesCanFitIn; i++) {
            double waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal = factorials[length] / factorials[length - i * maximalCycleLength] / Math.pow(factorials[maximalCycleLength], i) / factorials[i];
            double possibilitiesInMaximalCycles = waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal * Math.pow(waysOfChoosingCycleOrder, i);
            sum += possibilitiesInMaximalCycles * calculateWithMaximalCycle(length - i * maximalCycleLength, maximalCycleLength - 1, lcmWithMCL);
        }
        return sum;
    }

    private class LowestCommonMultiple {

        public int[] FACTORS_INDICES;

        public int SIZE_PRIME_INDEX;

        public int[] PRIMES;


        {
            FACTORS_INDICES = new int[length + 1];
            PRIMES = Primes.primes(FACTORS_INDICES.length).toArray();
            for (int i = 0; i < PRIMES.length; i++) {
                FACTORS_INDICES[PRIMES[i]] = i;
                SIZE_PRIME_INDEX = i + 1;
            }
        }

        final int[] factors;

        public LowestCommonMultiple(int i) {
            factors = new int[SIZE_PRIME_INDEX];
            for (int k : Primes.factorize(i)) {
                factors[FACTORS_INDICES[k]]++;
            }
        }

        public LowestCommonMultiple getCommonWith(int n) {
            LowestCommonMultiple lcm = new LowestCommonMultiple(n);
            for (int i = 0; i < SIZE_PRIME_INDEX; i++) {
                lcm.factors[i] = Math.max(factors[i], lcm.factors[i]);
            }
            return lcm;
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

    }

    public static DecimalFormat DECIMAL_FORMATTER;

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setExponentSeparator("e");
        DECIMAL_FORMATTER = new DecimalFormat("#.#########E0", decimalFormatSymbols);
    }

    public final int length;

    private final double[] factorials;
    double[][] cache;

    public RepeatedPermutation() {
        this(DEFAULT_LENGTH);
    }

    public RepeatedPermutation(int length) {
        this.length = length;

        factorials = precomputeFactorials(length + 1);
        createCacheStructure(length);
    }

    private double[] precomputeFactorials(int length) {
        double[] factorials = new double[length];
        factorials[0] = 1;
        for (int i = 1; i < factorials.length; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        return factorials;
    }

    private void createCacheStructure(int length) {
        cache = new double[length+1][length+1];
        for (double[] row : cache)
            Arrays.fill(row, -.5);
        LowestCommonMultiple lcm = new LowestCommonMultiple(1);
        lcms = new LowestCommonMultiple[length+1];
        lcms[0]=new LowestCommonMultiple(1);
        for (int i = 1; i <= length; i++) {
            lcms[i] = lcms[i-1].getCommonWith(i);
            for (int j = 1; j <= i; j++) {
                cache[i][j] = calculateWithMaximalCycle(i, j, lcms[i]);
            }
        }
    }
}

