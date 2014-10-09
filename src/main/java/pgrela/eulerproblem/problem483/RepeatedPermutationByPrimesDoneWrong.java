package pgrela.eulerproblem.problem483;

import static java.util.stream.IntStream.of;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

public class RepeatedPermutationByPrimesDoneWrong implements EulerSolver {
    public static final int MAX_ALLOWED_LENGTH = 351;
    public static final int DEFAULT_LENGTH = 5;
    private int[] primes;

    public static void main(String[] args) {
        printSolution(RepeatedPermutationByPrimesDoneWrong.class);
    }

    public String solveToString() {
        primes = Primes.primes(MAX_ALLOWED_LENGTH).toArray();
        int[] identityCharacteristic = new int[primes.length];
        double v = extendCharacteristic(identityCharacteristic, length, 0, 1);
        System.out.println(v);
        String format = DECIMAL_FORMATTER.format(v / factorials[length]);
        return format;
    }

    private double extendCharacteristic(int[] characteristic, int length, int currentPrimeIndex, int value) {
        assert length >= 0;
        if (length <= 1 || currentPrimeIndex == primes.length || primes[currentPrimeIndex] > length) {
            return valueForGivenCharacteristic(characteristic, this.length, value);
        }
        double d = 0;
        int addedPrimes = 0;
        while (primes[currentPrimeIndex] * characteristic[currentPrimeIndex] <= length) {
            //generateCyclesLength
            d += extendCharacteristic(characteristic, length - primes[currentPrimeIndex] * characteristic[currentPrimeIndex],
                    currentPrimeIndex + 1, value);
            ++addedPrimes;
            characteristic[currentPrimeIndex]++;
            value*=primes[currentPrimeIndex];
        }
        characteristic[currentPrimeIndex] = 0;
        return d;
    }

    private double valueForGivenCharacteristic(int[] characteristic, int totalLength, double value) {
        double d;
        int[] cyclesLengths = new int[of(characteristic).sum()];
        d = value * value * extendSpecificCycleLengths(characteristic, cyclesLengths, totalLength, 0);
        return d;
    }

    protected double extendSpecificCycleLengths(int[] characteristic, int[] cyclesLengths, int remainingLength, int currentPrime) {
        if (currentPrime == characteristic.length) {
            //TODO: skip if only zeroes ahead
            return evalateCyclesLenth(cyclesLengths, length);
        }
        if(characteristic[currentPrime]==0){
            return extendSpecificCycleLengths(characteristic, cyclesLengths, remainingLength, currentPrime+1);
        }
        double d = 0;
        if (remainingLength >= primes[currentPrime]) {
            --characteristic[currentPrime];
            int lastEmptySlot = 0;
            for (int i = 0; i < cyclesLengths.length; i++) {
                if (cyclesLengths[i] == 0) break;
                ++lastEmptySlot;
                    //if fits in length
                    int newRemainingLength = remainingLength - cyclesLengths[i] * (primes[currentPrime] - 1);
                    if (newRemainingLength >= 0) {
                        cyclesLengths[i] *= primes[currentPrime];
                        d += extendSpecificCycleLengths(characteristic, cyclesLengths, remainingLength, currentPrime);
                        cyclesLengths[i] /= primes[currentPrime];
                    }

            }
            cyclesLengths[lastEmptySlot] = primes[currentPrime];
            d += extendSpecificCycleLengths(characteristic, cyclesLengths, remainingLength - primes[currentPrime], currentPrime);
            cyclesLengths[lastEmptySlot] = 0;

            //not sure if needed
            ++characteristic[currentPrime];
            //clear
        } else {
            return extendSpecificCycleLengths(characteristic, cyclesLengths, remainingLength, currentPrime + 1);
        }
        return d;
    }
    public static final int NUMBER_1000_101_11_11_10_10_10_10 = 71658;
    Set<CycleLengths> alreadyProcessed = new HashSet<>();
    private boolean hasBeenProcessed(CycleLengths cycleLengths) {
        if(alreadyProcessed.contains(cycleLengths))
            return false;
        alreadyProcessed.add(cycleLengths);
        return true;
    }

    public static class CycleLengths{
        public final int[] cycleLengths;

        public CycleLengths(int[] cycleLengths) {
            int cycles = 0;
            while(cycles<cycleLengths.length && cycleLengths[cycles]>0)++cycles;
            this.cycleLengths = Arrays.copyOf(cycleLengths,cycles);
            Arrays.sort(this.cycleLengths);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof CycleLengths)) return false;

            CycleLengths that = (CycleLengths) o;

            if (!Arrays.equals(cycleLengths, that.cycleLengths)) return false;

            return true;
        }

        @Override
        public int hashCode() {
            return Arrays.hashCode(cycleLengths);
        }
    }

    private double evalateCyclesLenth(int[] cyclesLengths, int totalLength) {
        CycleLengths cycleLengths = new CycleLengths(cyclesLengths);
        if(!hasBeenProcessed(cycleLengths)) {
            return 0;
        }
        if (cycleLengths.cycleLengths.length == 0) return 1;
        double d = 1;
        int previous = cycleLengths.cycleLengths[0];
        int quantity = 0;
        for (int cycle : cycleLengths.cycleLengths) {
            if (previous == cycle) ++quantity;
            else {
                d *= countCycles(totalLength, previous, quantity);
                totalLength -= quantity * previous;

                previous = cycle;
                quantity = 1;
            }
        }
        d*= countCycles(totalLength, previous, quantity);
        return d;
    }

    protected double countCycles(int permutationLength, int cycleLength, int quantity) {
        return factorials[cycleLength - 1]
                * (factorials[permutationLength] / factorials[permutationLength - quantity * cycleLength])
                / factorials[quantity]
                / Math.pow(factorials[cycleLength], quantity);
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

    public RepeatedPermutationByPrimesDoneWrong() {
        this(DEFAULT_LENGTH);
    }

    public RepeatedPermutationByPrimesDoneWrong(int length) {
        this.length = length;

        factorials = precomputeFactorials();
    }

    private double[] precomputeFactorials() {
        double[] factorials = new double[MAX_ALLOWED_LENGTH];
        factorials[0] = 1;
        for (int i = 1; i < factorials.length; i++) {
            factorials[i] = factorials[i - 1] * i;
        }
        return factorials;
    }
}

