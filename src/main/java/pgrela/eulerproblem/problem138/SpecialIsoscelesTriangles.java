package pgrela.eulerproblem.problem138;

import pgrela.eulerproblem.common.Coordinates;
import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.Comparator;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class SpecialIsoscelesTriangles implements EulerSolver {

    public static void main(String[] args) {
        printSolution(SpecialIsoscelesTriangles.class);
    }

    public long solve() {
        int h = 1000;
        long x=Coordinates.square(4, 1, h, h)
                .filter(p -> p.x > p.y)
                .filter(p -> Maths.gcd(p.x, p.y) == 1)
                .map(p -> new Triangle(p.x, p.y))
                .sorted(Comparator.comparingLong(a -> a.c))
                .filter(this::isSpecial)
                .peek(System.out::println)
                .limit(12).mapToLong(t -> t.c).sum();
        int m,n=1;
        long s=0;
        for (int i = 0; i < 12; i++) {
            m=n+1;
            while(true){
                Triangle triangle = new Triangle(m, n);
                if(isSpecial(triangle)){
                    System.out.println(triangle);
                    n=m;
                    s+=triangle.c;
                    break;
                }
                ++m;
            }
        }
        return s;
    }
    private boolean isSpecial(Triangle t){
        return t.a * 2 + 1 == t.b || t.a * 2 - 1 == t.b;
    }

    static class Triangle {
        long a, b, c;
        long m;
        long n;

        Triangle(long m, long n) {
            this.m = m;
            this.n = n;
            b = m * m - n * n;
            a = 2 * m * n;
            c = m * m + n * n;
        }


        @Override
        public String toString() {
            return "{" + m + "," + n + "} -> {" + a + "," + b + "," + c + '}';
        }
    }

}

