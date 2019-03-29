package pgrela.eulerproblem.problem145;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;

import java.text.ParseException;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class HowManyReversibleNumbersAreThereBelowOneBillion implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(HowManyReversibleNumbersAreThereBelowOneBillion.class);
    }

    public long solve() {
//        return LongStream.iterate(1, x -> x + 1)
//                .limit(Integers.ONE_BILLION) // time is money
//                .filter(n->n%10!=0)
//                .filter(n -> Arrays.stream(Longs.toDigitArray(Longs.reverse(n) + n))
//                        .allMatch(d -> d % 2 == 1))
//                .count();
        int c=0;
        for (int i = 1; i < Integers.ONE_BILLION; i++) {
            int n=i;
            if (n % 10 == 0) continue;
            int rn=n;
            int r=0;
            while(n>0){
                r=r*10+n%10;
                n/=10;
            }
            rn+=r;
            ++c;
            while(rn>0){
                if(rn%2==0){
                    --c;
                    break;
                }
                rn/=10;
            }
        }
        return c;
    }


}

