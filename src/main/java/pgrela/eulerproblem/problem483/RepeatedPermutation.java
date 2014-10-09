package pgrela.eulerproblem.problem483;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RepeatedPermutation implements EulerSolver {
    public static final int MAX_ALLOWED_LENGTH = 400;
    public static final int DEFAULT_LENGTH = 200;

    public static void main(String[] args) {
        printSolution(RepeatedPermutation.class);
    }

    public String solveToString() {
        c = 0;
        BigInteger bi = calculateWithMaximalCycle(length, length, 1);
        //double v = bi.doubleValue()/(factorials[length]).doubleValue();
        BigInteger v=bi.divide(factorials[length]);
        String format = DECIMAL_FORMATTER.format(
                v);
        System.out.println(bi);
        System.out.println(c);
        double f = Double.MIN_VALUE;
        return format;
    }

    int c = 0;

    private BigInteger calculateWithMaximalCycle(int length, int maximalCycleLength, long lcm) {
        ++c;
        if (length == 0) {
            return BigInteger.valueOf(lcm).pow(2);
        }
        if (maximalCycleLength < 2) {
            return BigInteger.valueOf(lcm).pow(2);
        }
        long queryLCM = maximalLCM[maximalCycleLength].gcdWith(lcm).toLong();
        assert queryLCM > 0;
        long remainingToSquare = lcm / queryLCM;
        assert remainingToSquare > 0;
        if (cache.get(length).get(maximalCycleLength).containsKey(queryLCM)) {
            BigInteger remaining = BigInteger.valueOf(remainingToSquare);
            return cache.get(length).get(maximalCycleLength).get(queryLCM).multiply(remaining).multiply(remaining);
        }
        if (queryLCM != lcm) {
            BigInteger remaining = BigInteger.valueOf(remainingToSquare);
            return calculateWithMaximalCycle(length, maximalCycleLength, queryLCM).multiply(remaining).multiply(remaining);
        }

        int howManyMaximalCyclesCanFitIn = length / maximalCycleLength;
        BigInteger sum = calculateWithMaximalCycle(length, maximalCycleLength - 1, lcm);
        BigInteger waysOfChoosingCycleOrder = factorials[maximalCycleLength - 1];
        long lcmWithMCL = Maths.lcm(lcm, maximalCycleLength);
        for (int i = 1; i <= howManyMaximalCyclesCanFitIn; i++) {
            BigInteger waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal = factorials[length]
                    .divide(factorials[length - i * maximalCycleLength])
                    .divide(factorials[maximalCycleLength].pow(i)).divide(factorials[i]);
            BigInteger possibilitiesInMaximalCycles = waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal.multiply(waysOfChoosingCycleOrder.pow(i));
            sum=sum.add(possibilitiesInMaximalCycles.multiply(calculateWithMaximalCycle(length - i * maximalCycleLength, maximalCycleLength - 1, lcmWithMCL)));
        }
        cache.get(length).get(maximalCycleLength).put(lcm, sum);
        return sum;
    }

    public static DecimalFormat DECIMAL_FORMATTER;
    private ArrayList<ArrayList<Map<Long, BigInteger>>> cache;
    private LowestCommonMultiple[] maximalLCM = new LowestCommonMultiple[MAX_ALLOWED_LENGTH + 1];
    private final BigInteger[] factorials;

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setExponentSeparator("e");
        DECIMAL_FORMATTER = new DecimalFormat("#.#########E0", decimalFormatSymbols);
    }

    public final int length;

    public RepeatedPermutation() {
        this(DEFAULT_LENGTH);
    }

    public RepeatedPermutation(int length) {
        this.length = length;

        factorials = precomputeFactorials(length + 1);
        createCacheStructure(length);
        precomputeMaximalLCMs(length);
    }

    private BigInteger[] precomputeFactorials(int length) {
        BigInteger[] factorials = new BigInteger[length];
        factorials[0] = BigInteger.ONE;
        for (int i = 1; i < factorials.length; i++) {
            factorials[i] = factorials[i - 1].multiply(BigInteger.valueOf(i));
        }
        return factorials;
    }

    private void createCacheStructure(int length) {
        cache = new ArrayList<>(length + 1);
        for (int i = 0; i <= length; i++) {
            cache.add(i, new ArrayList<>(length + 1));
            for (int j = 0; j <= length; j++) {
                cache.get(i).add(j, new HashMap<>());
            }
        }
    }


    private void precomputeMaximalLCMs(int length) {
        maximalLCM[0] = LowestCommonMultiple.toLCM(1);
        for (int i = 1; i <= length; i++) {
            maximalLCM[i] = maximalLCM[i - 1].lcmWith(i);
        }
    }
}

