package pgrela.eulerproblem.problem181;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Integers;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Primes;

import java.text.ParseException;
import java.util.Arrays;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RSAEncryption implements EulerSolver {

    public static void main(String[] args) throws ParseException {
        printSolution(RSAEncryption.class);
    }

    @Override
    public long solve() {
        System.out.println(Maths.gcd(1008,3642));
        int p = 37;
        int q = 19;
        int n = p * q;
        int phi = (p - 1) * (q - 1);
        int[] unconcealed = new int[n];
        int[][] uc = new int[phi][n];
        System.out.println("ee(p,q)="+Integers.extendedEuclidian(p,q));
        System.out.println("tot(phi)="+Maths.totient(phi));
        System.out.println("tot(n)="+Maths.totient(n));
        for (int e = 1; e < phi; e++) {
            if (Maths.gcd(e, phi) == 1) {
                for (int m = 0; m < n; m++) {
                    if (Maths.powMod(m, e, n) == m) {
                        ++unconcealed[e];
                        uc[e][m]=1;
                    }
                }
            }
        }
        for (int e = 0; e < phi; e++) {
            if (unconcealed[e] == 0) continue;
            System.out.print(e+":  \t");
            for (int m = 0; m < n; m++) {
                System.out.print(uc[e][m]==1?'#':'.');
            }
            System.out.println(" " + unconcealed[e]);
        }
        System.out.println(Arrays.stream(unconcealed).filter(i -> i != 0).min().getAsInt());
        for (int e = 0; e < unconcealed.length; e++) {
            if (unconcealed[e] == 0) continue;
            //if (unconcealed[e] != 9) continue;
            System.out.println(String.format("%d -> %d, tot=%d, ee(e,phi)=%s, ee(e,n)=%s, ee(e,p)=%s, ee(e,q)=%s %s",
                    e, unconcealed[e], Maths.totient(e),
                    Integers.extendedEuclidian(e, phi),
                    Integers.extendedEuclidian(e, n),
                    Integers.extendedEuclidian(e, p),
                    Integers.extendedEuclidian(e, q),
                    Primes.isPrime(e)));
        }
        for (int m = 2; m < phi; m++) {
            System.out.println(String.format("%d\t: %d", m, cycle(m,n)));
        }
        return 0;
    }

    private Object cycle(int m, int n) {
        int e=2;
        while(Maths.powMod(m,e,n)!=m)++e;
        return e;
    }

}

