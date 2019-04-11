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
        int p = 13;
        int q = 17;
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
        for (int i = 0; i < phi; i++) {
            if (unconcealed[i] == 0) continue;
            System.out.print(i+":  \t");
            for (int j = 0; j < n; j++) {
                System.out.print(uc[i][j]==1?'#':'.');
            }
            System.out.println(" " + unconcealed[i]);
        }
        System.out.println(Arrays.stream(unconcealed).filter(i -> i != 0).min().getAsInt());
        for (int i = 0; i < unconcealed.length; i++) {
            if (unconcealed[i] == 0) continue;
            //if (unconcealed[i] != 9) continue;
            System.out.println(String.format("%d -> %d, tot=%d, ee(e,phi)=%s, ee(e,n)=%s, ee(e,p)=%s, ee(e,q)=%s %s",
                    i, unconcealed[i], Maths.totient(i),
                    Integers.extendedEuclidian(i, phi),
                    Integers.extendedEuclidian(i, n),
                    Integers.extendedEuclidian(i, p),
                    Integers.extendedEuclidian(i, q),
                    Primes.isPrime(i)));
        }
        return 0;
    }

}

