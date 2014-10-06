package pgrela.eulerproblem.problem50;

import static java.util.stream.Collectors.toSet;
import static pgrela.eulerproblem.common.Integers.ONE_MILLION;
import static pgrela.eulerproblem.common.Primes.primes;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.Set;
import java.util.stream.IntStream;

import pgrela.eulerproblem.common.EulerSolver;

public class ConsecutivePrimeSum implements EulerSolver {

    public static void main(String[] args) {
        printSolution(ConsecutivePrimeSum.class);
    }

    @Override
    public long solve() {
        int[] primes = primes(ONE_MILLION).toArray();
        Set primesSet = IntStream.of(primes).boxed().collect(toSet());
        int maxLength = 0;
        int maxLengthFor = 0;
        int sum;
        int sumTo;
        int nextSum = 0;
        for (int sumFrom = 0; sumFrom < primes.length; sumFrom++) {
            if(sumFrom==0)
                nextSum=0;
            else {
                if(sumFrom+maxLength>primes.length){
                    break;
                }
                nextSum = nextSum - primes[sumFrom - 1] + primes[sumFrom + maxLength - 1];
            }
            sum = nextSum;
            sumTo = sumFrom + maxLength;
            while (sum < ONE_MILLION && sumTo < primes.length) {
                if (primesSet.contains(sum)) {
                    if (sumTo - sumFrom > maxLength) {
                        maxLength = sumTo - sumFrom;
                        maxLengthFor = sum;
                        nextSum = sum;
                    }
                }
                sum += primes[sumTo++];
            }
        }
        return maxLengthFor;
    }
}
