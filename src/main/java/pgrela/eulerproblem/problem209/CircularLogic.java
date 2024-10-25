package pgrela.eulerproblem.problem209;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CircularLogic implements EulerSolver {


    public static void main(String[] args) {
        printSolution(CircularLogic.class);
    }

    @Override
    public long solve() {
        int[] loop = new int[64];
        for (int i = 0; i < 64; i++) {
            int a = (i >> 5) & 1;
            int b = (i >> 4) & 1;
            int c = (i >> 3) & 1;
            int j = ((i << 1) & 63) | (a ^ (b & c));
            loop[i]=j;
        }
        List<Integer> bits = IntStream.range(0, 64).boxed().collect(Collectors.toList());
        List<Integer> loops = new ArrayList<>();
        while(!bits.isEmpty()) {
            int length=0;
            int start = bits.iterator().next();
            int current = start;
            do{
                bits.remove(Integer.valueOf(current));
                current=loop[current];
                ++length;
            } while(current!=start);
            loops.add(length);
        }
        long fib[] = new long[64];
        fib[0] = 1;
        fib[1] = 2;
        for (int i = 2; i < fib.length; i++) {
            fib[i]=fib[i-1]+fib[i-2];
        }
        return loops.stream().map(d->ring(d,fib)).reduce(1L,(a,b)->a*b);
    }

    private long ring(int n, long[] fib) {
        if(n==1) return 1;
        if(n==2) return 3;
        return fib[n-1]+fib[n-3];
    }

}

