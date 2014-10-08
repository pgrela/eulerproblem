package pgrela.eulerproblem.problem483;

import static java.lang.Math.pow;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem483.LowestCommonMultiple.toLCM;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;

import pgrela.eulerproblem.common.EulerSolver;

public class RepeatedPermutation implements EulerSolver {
    public static final int MAX_ALLOWED_LENGTH=400;
    public static final int DEFAULT_LENGTH = 100;

    public static void main(String[] args) {
        printSolution(RepeatedPermutation.class);
    }

    public String solveToString() {
        c=0;
        String format = DECIMAL_FORMATTER.format(
                calculateWithMaximalCycle(length, length, toLCM(1)) / factorials[length]);
        System.out.println(c);
        return format;
    }

    int c=0;
    private double calculateWithMaximalCycle(int length, int maximalCycleLength, LowestCommonMultiple lcm) {++c;
        if (length == 0) {
            return pow(lcm.toDouble(), 2);
        }
        if (maximalCycleLength < 2) {
            return pow(lcm.toDouble(), 2);
        }
        if (maxLCMs[length][maximalCycleLength] != null && maxLCMs[length][maximalCycleLength].isDominatedBy(lcm)) {
            if (cache[length][maximalCycleLength] > -0.5) {
                return cache[length][maximalCycleLength] * pow(lcm.toDouble() /
                        maxLCMsValues[length][maximalCycleLength], 2);
            }
        }

        int howManyMaximalCyclesCanFitIn = length / maximalCycleLength;
        double sum = calculateWithMaximalCycle(length, maximalCycleLength - 1, lcm);
        double waysOfChoosingCycleOrder = factorials[maximalCycleLength - 1];
        LowestCommonMultiple lcmWithMCL = lcm.getCommonWith(maximalCycleLength);
        for (int i = 1; i <= howManyMaximalCyclesCanFitIn; i++) {
            double waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal = factorials[length] / factorials[length - i * maximalCycleLength] / pow(
                    factorials[maximalCycleLength], i) / factorials[i];
            double possibilitiesInMaximalCycles = waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal * pow(
                    waysOfChoosingCycleOrder, i);
            sum += possibilitiesInMaximalCycles * calculateWithMaximalCycle(length - i * maximalCycleLength, maximalCycleLength - 1, lcmWithMCL);
        }
        return sum;
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
        for (int cachedLength = 1; cachedLength <= length; cachedLength++) {
            for (int maximalCycle = 1; maximalCycle <= cachedLength; maximalCycle++) {
                cache[cachedLength][maximalCycle] = calculateWithMaximalCycle(cachedLength, maximalCycle,
                        getLCM(cachedLength, maximalCycle));
            }
        }
    }

    private double[][] maxLCMsValues = new double[MAX_ALLOWED_LENGTH + 1][MAX_ALLOWED_LENGTH + 1];
    private LowestCommonMultiple[][] maxLCMs = new LowestCommonMultiple[MAX_ALLOWED_LENGTH + 1][MAX_ALLOWED_LENGTH + 1];

    private LowestCommonMultiple getLCM(int cachedLength, int maximalCycle) {
        LowestCommonMultiple lcm = toLCM(1);
        for (int i = 2; i <= maximalCycle; i++) {
            lcm = lcm.getCommonWith(LowestCommonMultiple.toPoweredLCM(i, cachedLength / i));
        }
        maxLCMsValues[cachedLength][maximalCycle] = lcm.toDouble();
        maxLCMs[cachedLength][maximalCycle] = lcm;
        return lcm;
    }
}

