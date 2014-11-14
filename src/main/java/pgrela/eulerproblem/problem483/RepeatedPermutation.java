package pgrela.eulerproblem.problem483;

import pgrela.eulerproblem.common.EulerSolver;

public class RepeatedPermutation implements EulerSolver {
    public static final int MAX_ALLOWED_LENGTH = 350;/*

    public static void main(String[] args) {
        printSolution(RepeatedPermutation.class);
    }

    public String solveToString() {
        return solveToString(MAX_ALLOWED_LENGTH);
    }

    public String solveToString(int length) {
        return DECIMAL_FORMATTER.format(calculateWithMaximalCycle(length, length, 1).divide(factorials[length]));
    }

    public BigDouble calculateWithMaximalCycle(int remainingPermutationLength, int maximalAllowedCycleLength, long inheritedLCM) {
        if (remainingPermutationLength == 0 || maximalAllowedCycleLength < 2) {
            return valueOf(inheritedLCM).pow(2);
        }
        long queryLCM = MAXIMAL_LCM[maximalAllowedCycleLength].gcdWith(inheritedLCM).toLong();
        if (isCached(remainingPermutationLength, maximalAllowedCycleLength, queryLCM)) {
            BigDouble value = getFromCache(remainingPermutationLength, maximalAllowedCycleLength, queryLCM);
            return applyRemainingFactors(value, inheritedLCM, queryLCM);
        }
        if (shouldBeCached(inheritedLCM, queryLCM)) {

            BigDouble value = calculateWithMaximalCycle(remainingPermutationLength, maximalAllowedCycleLength, queryLCM);
            return applyRemainingFactors(value, inheritedLCM, queryLCM);
        }

        int howManyMaximalCyclesCanFitIn = remainingPermutationLength / maximalAllowedCycleLength;
        BigDouble sum = calculateWithMaximalCycle(remainingPermutationLength, maximalAllowedCycleLength - 1, inheritedLCM);
        BigDouble waysOfChoosingCycleOrder = factorials[maximalAllowedCycleLength - 1];
        long lcmWithMCL = Maths.lcm(inheritedLCM, maximalAllowedCycleLength);
        for (int i = 1; i <= howManyMaximalCyclesCanFitIn; i++) {
            BigDouble waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal = factorials[remainingPermutationLength]
                    .divide(factorials[remainingPermutationLength - i * maximalAllowedCycleLength])
                    .divide(factorials[maximalAllowedCycleLength].pow(i))
                    .divide(factorials[i]);
            BigDouble possibilitiesInMaximalCycles = waysOfChoosingNGroupsOfMaximalCycleLengthFromLengthTotal.multiply(waysOfChoosingCycleOrder.pow(i));
            sum=sum.add(possibilitiesInMaximalCycles.multiply(calculateWithMaximalCycle(remainingPermutationLength - i * maximalAllowedCycleLength, maximalAllowedCycleLength - 1, lcmWithMCL)));
        }
        cache.get(remainingPermutationLength).get(maximalAllowedCycleLength).put(inheritedLCM, sum);
        return sum;
    }

    private BigDouble applyRemainingFactors(BigDouble value, long inheritedLCM, long queryLCM) {
        return value.multiply(valueOf(inheritedLCM / queryLCM).pow(2));
    }

    private BigDouble getFromCache(int remainingPermutationLength, int maximalAllowedCycleLength, long queryLCM) {
        return cache.get(remainingPermutationLength).get(maximalAllowedCycleLength).get(queryLCM);
    }

    private boolean isCached(int remainingPermutationLength, int maximalAllowedCycleLength, long lcm) {
        return cache.get(remainingPermutationLength).get(maximalAllowedCycleLength).containsKey(lcm);
    }

    private boolean shouldBeCached(long inheritedLCM, long queryLCM) {
        return queryLCM != inheritedLCM;
    }

    public static DecimalFormat DECIMAL_FORMATTER;
    private static final List<List<Map<Long, BigDouble>>> cache;
    private static final LowestCommonMultiple[] MAXIMAL_LCM;;
    private static final BigDouble[] factorials;

    static {
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator('.');
        decimalFormatSymbols.setExponentSeparator("e");
        DECIMAL_FORMATTER = new DecimalFormat("#.#########E0", decimalFormatSymbols);
        MAXIMAL_LCM = precomputedMaximalLCMs(MAX_ALLOWED_LENGTH);
        cache = createCacheStructure(MAX_ALLOWED_LENGTH);
        factorials = precomputedFactorials(MAX_ALLOWED_LENGTH + 1);
    }

    public RepeatedPermutation() {
    }

    private static BigDouble[] precomputedFactorials(int length) {
        BigDouble[] factorials = new BigDouble[length];
        factorials[0] = ONE;
        for (int i = 1; i < factorials.length; i++) {
            factorials[i] = factorials[i - 1].multiply(valueOf(i));
        }
        return factorials;
    }

    private static LowestCommonMultiple[] precomputedMaximalLCMs(int length) {
        LowestCommonMultiple[] maximalLCMs = new LowestCommonMultiple[MAX_ALLOWED_LENGTH + 1];
        maximalLCMs[0] = LowestCommonMultiple.toLCM(1);
        for (int i = 1; i <= length; i++) {
            maximalLCMs[i] = maximalLCMs[i - 1].lcmWith(i);
        }
        return  maximalLCMs;
    }


    private static List<List<Map<Long, BigDouble>>> createCacheStructure(int length) {
        List<List<Map<Long, BigDouble>>> cache = new ArrayList<>(length + 1);
        for (int i = 0; i <= length; i++) {
            cache.add(i, new ArrayList<>(length + 1));
            for (int j = 0; j <= length; j++) {
                cache.get(i).add(j, new HashMap<>());
            }
        }
        return cache;
    }*/
}

