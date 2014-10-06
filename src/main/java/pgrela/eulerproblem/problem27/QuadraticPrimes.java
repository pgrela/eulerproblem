package pgrela.eulerproblem.problem27;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

import java.util.Set;
import java.util.stream.Collectors;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class QuadraticPrimes implements EulerSolver {

    public static void main(String[] args) {
        printSolution(QuadraticPrimes.class);
    }

    Set<Integer> primes = Primes.primes(1000000).boxed().collect(Collectors.toSet());

    @Override
    public long solve() {
        return Coordinates.square(-999,-999,999,999).reduce((a, b) -> primesSequenceLength(a.x,a.y) > primesSequenceLength(b.x,b.y) ? a : b).map(p->p.x*p.y).get();
    }

    private int primesSequenceLength(int a, int b) {
        int n=0;
        int value;
        do{
            value = n*n+a*n+b;
            ++n;
        }while(primes.contains(value));
        return n-1;
    }
}
