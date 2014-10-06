package pgrela.eulerproblem.problem58;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

public class SpiralPrimes implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SpiralPrimes.class);
    }

    public long solve() {
        int primes=3;
        int radius=1;
        int total=9;
        while(10*primes>radius*4+1){
            ++radius;
            primes+= Primes.isPrime(total+radius*2)?1:0;
            primes+= Primes.isPrime(total+radius*4)?1:0;
            primes+= Primes.isPrime(total+radius*6)?1:0;
            primes+= Primes.isPrime(total+radius*8)?1:0;
            total= Maths.pow(radius*2+1,2);
        }
        return radius*2+1;
    }
}
