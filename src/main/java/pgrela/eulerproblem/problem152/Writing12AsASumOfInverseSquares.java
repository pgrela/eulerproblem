package pgrela.eulerproblem.problem152;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Writing12AsASumOfInverseSquares implements EulerSolver {

    private static final int EIGHTY = 80;

    public static void main(String[] args) throws ParseException {
        printSolution(Writing12AsASumOfInverseSquares.class);
    }

    public long solve() {
        int[] possibleNumbers = getPossibleNumbers();
        long theDenominator = IntStream.of(possibleNumbers).mapToLong(i -> i * i).reduce(Maths::lcm).getAsLong();
        long[] bricks = IntStream.of(possibleNumbers).mapToLong(i -> theDenominator / (i * i)).toArray();
        long target = theDenominator / 4;
        long maxValue = LongStream.of(bricks).sum();
        long processed = 0L;
        int N = 90_000_000;
        long[] steps = new long[N];
        int[] ways = new int[N];
        int[] newWays = new int[N];
        long[] newSteps = new long[N];
        steps[0] = 0;
        ways[0] = 1;
        int totalSteps = 1;
        for (long brick : bricks) {
            int currentStep = 0;
            int currentStepToRewrite = 0;
            int nextNewStep = 0;
            long forgetBelow = target - (maxValue - processed);
            do {
                if (currentStep < totalSteps && currentStepToRewrite < totalSteps && steps[currentStep] + brick == steps[currentStepToRewrite]) {
                    newSteps[nextNewStep] = steps[currentStepToRewrite];
                    newWays[nextNewStep] = ways[currentStepToRewrite] + ways[currentStep];
                    ++currentStepToRewrite;
                    ++currentStep;
                    ++nextNewStep;
                } else {
                    if (currentStep < totalSteps && (currentStepToRewrite == totalSteps || steps[currentStep] + brick > steps[currentStepToRewrite])) {
                        if (steps[currentStep] + brick > target) {
                            ++currentStep;
                            continue;
                        }
                        newSteps[nextNewStep] = steps[currentStep] + brick;
                        newWays[nextNewStep] = ways[currentStep];
                        ++currentStep;
                        ++nextNewStep;
                    } else {
                        if (steps[currentStepToRewrite] < forgetBelow) {
                            ++currentStepToRewrite;
                            continue;
                        }
                        newSteps[nextNewStep] = steps[currentStepToRewrite];
                        newWays[nextNewStep] = ways[currentStepToRewrite];
                        ++currentStepToRewrite;
                        ++nextNewStep;
                    }
                }
            } while (currentStep < totalSteps || currentStepToRewrite < totalSteps);
            long[] tmp;
            tmp = steps;
            steps = newSteps;
            newSteps = tmp;
            int[] tmpi = ways;
            ways = newWays;
            newWays = tmpi;
            processed += brick;
            totalSteps = nextNewStep;
            System.out.println(totalSteps);
        }
        if (steps[0]==target) return ways[0];
        Set<Long> setOfPossibleNumbers = Arrays.stream(possibleNumbers).mapToLong(i -> i).boxed().collect(Collectors.toSet());
        int[] numberToIndex = new int[100];
        for (int i = 0; i < possibleNumbers.length; i++) {
            numberToIndex[possibleNumbers[i]] = i;
        }
        Set<long[]> sets = new HashSet<>();

        long[] possibleSets = LongStream.rangeClosed(4, 80).flatMap((long prime) -> {
            int[] multiplies = IntStream.iterate(1, i -> i + 1).limit(80 / prime).filter(n -> setOfPossibleNumbers.contains(n * prime)).toArray();
            return LongStream.range(1, 1 << multiplies.length)
                    .mapToObj(n ->
                            IntStream.range(0, multiplies.length)
                                    .mapToLong(i -> multiplies[i] * ((n >> i) & 1))
                                    .filter(i -> i > 0)
                                    .toArray()
                    )
                    .filter(array -> {
                        long denominator = Arrays.stream(array).map(i -> i * i).reduce(Maths::lcm).getAsLong();
                        boolean isGood = Arrays.stream(array).map(i -> denominator / i / i).sum() % (prime * prime) == 0;
                        if (isGood) {
                            long nominator = Arrays.stream(array).map(n -> denominator / n / n).sum();
                            if (!Maths.isSquare(denominator)) throw new RuntimeException();
                            long denominatorRoot = Maths.intSqrt(denominator);
                            System.out.println(String.format("%d/%d %b:\t %s", nominator / prime / prime, denominatorRoot, Maths.isSquare(denominator), Arrays.toString(Arrays.stream(array).map(i -> i * prime).toArray())));
                        }
                        return isGood;
                    })
                    .peek((e) -> sets.add(Arrays.stream(e).map(n -> (n * prime)).toArray()))
                    .mapToLong(a -> Arrays.stream(a)
                            .map(i -> i * prime)
                            .map(i -> (1L << numberToIndex[(int) i]))
                            .sum());
        }).toArray();
        System.out.println(Arrays.stream(possibleSets).reduce(0, (a, b) -> a | b));
        System.out.println(Arrays.toString(possibleNumbers));
        System.out.println(possibleSets.length);
        return 0;
    }

    private int[] getPossibleNumbers() {
        long filter = Primes.primes(EIGHTY).skip(2).filter(prime -> {
            int[] multiplies = IntStream.iterate(1, i -> i + 1).limit(EIGHTY / prime).toArray();
            return LongStream.range(1, 1 << multiplies.length)
                    .mapToObj(n ->
                            IntStream.range(0, multiplies.length)
                                    .mapToLong(i -> multiplies[i] * ((n >> i) & 1))
                                    .filter(i -> i > 0)
                                    .toArray()
                    )
                    .filter(array -> {
                        long lcm = Arrays.stream(array).map(i -> i * i).reduce(Maths::lcm).getAsLong();
                        return Arrays.stream(array).map(i -> lcm / i / i).sum() % (prime * prime) == 0;
                    })
                    .map(a -> Arrays.stream(a).map(i -> i * prime).toArray())
                    .findAny().isPresent();
        }).mapToLong(this::getPow).reduce(1, (a, b) -> a * b) * getPow(2) * getPow(3);
        return IntStream.rangeClosed(3, EIGHTY).filter(i -> (filter) % i == 0).toArray();
    }

    private int getPow(int i) {
        return Maths.pow(i, (int) (Math.log(EIGHTY) / Math.log(i)));
    }
}

