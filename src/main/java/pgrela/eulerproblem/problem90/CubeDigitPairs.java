package pgrela.eulerproblem.problem90;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.ArrayList;
import java.util.Arrays;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CubeDigitPairs implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CubeDigitPairs.class);
    }

    public long solve() {
        ArrayList<Cube> cubes = new ArrayList<>(44100);
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < j; k++) {
                    for (int l = 0; l < k; l++) {
                        for (int m = 0; m < l; m++) {
                            for (int n = 0; n < m; n++) {
                                cubes.add(new Cube(i, j, k, l, m, n));
                            }
                        }
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < cubes.size(); i++) {
            Cube firstCube = cubes.get(i);
            for (int j = 0; j <= i; j++) {
                Cube otherCube = cubes.get(j);
                if (possibleToShow(firstCube, otherCube, 0, 1)
                        && possibleToShow(firstCube, otherCube, 0, 4)
                        && possibleToShow(firstCube, otherCube, 0, 9)
                        && possibleToShow(firstCube, otherCube, 1, 6)
                        && possibleToShow(firstCube, otherCube, 2, 5)
                        && possibleToShow(firstCube, otherCube, 3, 6)
                        && possibleToShow(firstCube, otherCube, 4, 9)
                        && possibleToShow(firstCube, otherCube, 6, 4)
                        && possibleToShow(firstCube, otherCube, 8, 1)
                        ) {
                    ++count;
                }
            }
        }
        return count;
    }

    private boolean possibleToShow(Cube firstCube, Cube otherCube, int firstDigit, int secondDigit) {
        return (firstCube.containsDigit(firstDigit) && otherCube.containsDigit(secondDigit))
                || (otherCube.containsDigit(firstDigit) && firstCube.containsDigit(secondDigit));
    }

    public static class Cube {
        int[] f;

        public Cube(int... f) {
            this.f = f;
        }

        public boolean containsDigit(int i) {
            if (i == 6 || i == 9) return containsNumber(6) || containsNumber(9);
            return containsNumber(i);
        }

        private boolean containsNumber(int i) {
            for (int a : f) {
                if (a == i) return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Cube{f=" + Arrays.toString(f) + '}';
        }
    }
}
