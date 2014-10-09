package pgrela.eulerproblem.problem483;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.pow;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RepeatedPermutation implements EulerSolver {
    public static final int MAX_ALLOWED_LENGTH = 400;
    public static final int DEFAULT_LENGTH = 20;

    public static void main(String[] args) {
        printSolution(RepeatedPermutation.class);
    }

    public String solveToString() {
        c = 0;
        double v = calculateWithMaximalCycle(length, length, 1);
        String format = DECIMAL_FORMATTER.format(
                v / factorials[length]);
        System.out.println(v);
        System.out.println(c);
        double f = Double.MIN_VALUE;
        return format;
    }

    int c = 0;

    private double calculateWithMaximalCycle(int length, int maximalCycleLength, long lcm) {
        ++c;
        if (length == 0) {
            return pow(lcm, 2);
        }
        if (maximalCycleLength < 2) {
            return pow(lcm, 2);
        }
        long queryLCM = maximalLCM[maximalCycleLength].gcdWith(lcm).toLong();
        assert queryLCM > 0;
        double remainingToSquare = lcm / queryLCM;
        assert remainingToSquare > 0;
        if (cache.get(length).get(maximalCycleLength).containsKey(queryLCM)) {
            return cache.get(length).get(maximalCycleLength).get(queryLCM) * remainingToSquare * remainingToSquare;
        }
        if (queryLCM != lcm) {
            return calculateWithMaximalCycle(length, maximalCycleLength, queryLCM) * remainingToSquare * remainingToSquare;
        }

        int howManyMaximalCyclesCanFitIn = length / maximalCycleLength;
        double sum = calculateWithMaximalCycle(length, maximalCycleLength - 1, lcm);
        double waysOfChoosingCycleOrder = factorials[maximalCycleLength - 1];
        long lcmWithMCL = Maths.lcm(lcm, maximalCycleLength);
        for (int i = 1; i <= howManyMaximalCyclesCanFitIn; i++) {
            double waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal = factorials[length] / factorials[length - i * maximalCycleLength] / pow(
                    factorials[maximalCycleLength], i) / factorials[i];
            double possibilitiesInMaximalCycles = waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal * pow(
                    waysOfChoosingCycleOrder, i);
            sum += possibilitiesInMaximalCycles * calculateWithMaximalCycle(length - i * maximalCycleLength, maximalCycleLength - 1, lcmWithMCL);
        }
        cache.get(length).get(maximalCycleLength).put(lcm, sum);
        return sum;
    }

    public static DecimalFormat DECIMAL_FORMATTER;
    private ArrayList<ArrayList<Map<Long, Double>>> cache;
    private LowestCommonMultiple[] maximalLCM = new LowestCommonMultiple[MAX_ALLOWED_LENGTH + 1];
    private final double[] factorials;

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

    private double[] precomputeFactorials(int length) {
        double[] factorials = new double[length];
        factorials[0] = 1.0;
        for (int i = 1; i < factorials.length; i++) {
            factorials[i] = factorials[i - 1] * i;
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

