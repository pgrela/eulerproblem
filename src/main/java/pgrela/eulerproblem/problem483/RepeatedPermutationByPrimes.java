package pgrela.eulerproblem.problem483;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

import static java.util.stream.IntStream.of;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RepeatedPermutationByPrimes implements EulerSolver {
    public static final int MAX_ALLOWED_LENGTH = 351;
    public static final int DEFAULT_LENGTH = 3;
    private int[] primes;

    public static void main(String[] args) {
        printSolution(RepeatedPermutationByPrimes.class);
    }

    public String solveToString() {
        primes = Primes.primes(MAX_ALLOWED_LENGTH).toArray();
        int[] identityCharacteristic = new int[primes.length];
        double v = valuesOfExtendedFrom(identityCharacteristic, length, 0);
        String format = DECIMAL_FORMATTER.format(v / factorials[length]);
        return format;
    }

    private double valuesOfExtendedFrom(int[] characteristic, int length, int currentPrimeIndex) {
        assert length >= 0;
        if (length <= 1 || currentPrimeIndex == primes.length || primes[currentPrimeIndex] > length) {
            return valueForGivenCharacteristic(characteristic, length);
        }
        double d = 0;
        int quantity = 0;
        while (primes[currentPrimeIndex] * quantity <= length) {
            //generateCyclesLength
            d += valuesOfExtendedFrom(characteristic, length - primes[currentPrimeIndex] * quantity, currentPrimeIndex + 1);
            characteristic[currentPrimeIndex]++;
        }
        characteristic[currentPrimeIndex] = 0;
        return d;
        //for each characteristic
        //  generate cycles length
        //  for each prime p
        //      foreach exponent e
        //          if(wasn't already generated)
        //          multiplyby
        // calculate characteristic quantity
        // add to solution
        // divide by factorial
    }

    private double valueForGivenCharacteristic(int[] characteristic, int length) {
        double d;
        int[] cyclesLengths = new int[of(characteristic).sum()];
        d = extendSpecificCycleLengths(characteristic, cyclesLengths, length, 0);
        return d;
    }

    protected double extendSpecificCycleLengths(int[] characteristic, int[] cyclesLengths, int remainingLength, int currentPrime) {
        if (currentPrime == characteristic.length || remainingLength < primes[currentPrime]) {
            return evalateCyclesLenth(cyclesLengths, length);
        }
        double d = 0;
        Set<Integer> alreadyProcessedValues = new TreeSet<>();
        if (characteristic[currentPrime] > 0 && remainingLength >= primes[currentPrime]) {
            alreadyProcessedValues.clear();
            --characteristic[currentPrime];
            for (int i = 0; i < cyclesLengths.length; i++) {
                if (cyclesLengths[i] == 0) break;
                if (!alreadyProcessedValues.contains(cyclesLengths[i])) {
                    //if fits in length
                    int newRamainingLength = remainingLength - cyclesLengths[i] * (primes[currentPrime] - 1);
                    if (newRamainingLength >= 0) {
                        cyclesLengths[i] *= primes[currentPrime];
                        d += extendSpecificCycleLengths(characteristic, cyclesLengths, remainingLength, currentPrime);
                        cyclesLengths[i] /= primes[currentPrime];
                    }
                    alreadyProcessedValues.add(cyclesLengths[i]);
                }
            }
            int i = 0;
            while (cyclesLengths[i] != 0) ++i;
            cyclesLengths[i] = primes[currentPrime];
            d += extendSpecificCycleLengths(characteristic, cyclesLengths, remainingLength, currentPrime);
            cyclesLengths[i] = 0;

            ++characteristic[currentPrime];
            //clear
        } else {
            return extendSpecificCycleLengths(characteristic, cyclesLengths, remainingLength, currentPrime + 1);
        }
        return d;
    }

    int[] evaluator = new int[MAX_ALLOWED_LENGTH];

    private double evalateCyclesLenth(int[] cyclesLengths, int totalLength) {
        if (cyclesLengths.length == 0) return 1;
        Arrays.fill(evaluator, 0);
        int i = 0;
        while (cyclesLengths.length > i && cyclesLengths[i] != 0) {
            evaluator[cyclesLengths[i]]++;
            ++i;
        }
        double d = 1;
        for (int j = 0; j < evaluator.length; j++) {
            if (evaluator[j] != 0) {
                d *= factorials[j - 1] / (factorials[totalLength] / factorials[totalLength - evaluator[j] * j]) / factorials[evaluator[j]] / Math.pow(factorials[j], evaluator[j]);
                totalLength -= evaluator[j] * j;
            }
        }
        return d;
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

    public RepeatedPermutationByPrimes() {
        this(DEFAULT_LENGTH);
    }

    public RepeatedPermutationByPrimes(int length) {
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

