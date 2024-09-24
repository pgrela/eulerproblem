package pgrela.eulerproblem.problem184;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.Point;
import pgrela.eulerproblem.common.geometry.Vector;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import static pgrela.eulerproblem.common.LongFraction.ZERO;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem184.TrianglesContainingTheOrigin.Quarter.*;

public class TrianglesContainingTheOrigin implements EulerSolver {

    private static final int I = 105;

    public static void main(String[] args) {
        printSolution(TrianglesContainingTheOrigin.class);
    }

    @Override
    public long solve() {
        int[] pointsArray = Point.points(-I, -I, I, I)
                .map(Vector::vector)
                .filter(v -> v.squareLength() != 0)
                .filter(v -> v.squareLength() < I * I)
                .collect(Collectors.toMap(Radius::fromVector, v -> 1, (a, b) -> a + b, TreeMap::new))
                .values()
                .stream()
                .mapToInt(i -> i)
                .toArray();
        int[] cumulativeArray = new int[pointsArray.length];
        cumulativeArray[0] = pointsArray[0];
        for (int i = 1; i < pointsArray.length; i++) {
            cumulativeArray[i] = cumulativeArray[i - 1] + pointsArray[i];
        }
        int halfPoints = pointsArray.length / 2;
        long counter = 0;
        for (int i = 0; i < pointsArray.length; i++) {
            for (int j = 0; j < pointsArray.length; j++) {
                if (i % halfPoints != j % halfPoints)
                    counter += pointsArray[i] * pointsArray[j] * sumBetween(cumulativeArray, pointsArray, i, j);
            }
        }
        return counter / 6;
    }

    private int sumBetween(int[] cumulativeArray, int[] pointsArray, int a, int b) {
        if (b < a) return sumBetween(cumulativeArray, pointsArray, b, a);
        int between = cumulativeArray[b] - cumulativeArray[a] - pointsArray[b];
        return Math.min(
                between,
                cumulativeArray[cumulativeArray.length - 1] - between - pointsArray[a] - pointsArray[b]
        );
    }

    enum Quarter {
        FIRST, SECOND, THIRD, FOURTH;
        static Map<Quarter, Quarter> opposites;

        static {
            opposites = new HashMap<>();
            opposites.put(FIRST, THIRD);
            opposites.put(SECOND, FOURTH);
            opposites.put(THIRD, FIRST);
            opposites.put(FOURTH, SECOND);
        }
    }

    static class Radius implements Comparable<Radius> {
        LongFraction direction;
        Quarter quarter;

        private Radius(LongFraction direction, Quarter quarter) {
            this.direction = direction;
            this.quarter = quarter;
        }

        static Radius fromVector(Vector vector) {
            int x = vector.x;
            int y = vector.y;
            if (y == 0) {
                if (x > 0) {
                    return new Radius(ZERO, FIRST);
                }
                return new Radius(ZERO, THIRD);
            }
            if (x == 0) {
                if (y > 0) {
                    return new Radius(ZERO, SECOND);
                }
                return new Radius(ZERO, FOURTH);

            }
            if (x * y > 0) {
                return new Radius(new LongFraction(x, y), x > 0 ? FIRST : THIRD);
            }
            return new Radius(new LongFraction(-x, y), x > 0 ? FOURTH : SECOND);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Radius)) return false;

            Radius radius = (Radius) o;

            return direction.equals(radius.direction) && quarter == radius.quarter;
        }

        @Override
        public int hashCode() {
            int result = direction.hashCode();
            result = 31 * result + quarter.hashCode();
            return result;
        }

        @Override
        public int compareTo(Radius o) {
            if (quarter.equals(o.quarter)) {
                return direction.compareTo(o.direction);
            }
            return Integer.compare(quarter.ordinal(), o.quarter.ordinal());
        }

        @Override
        public String toString() {
            return "Radius{" + "quarter=" + quarter + ", direction=" + direction + '}';
        }

    }
}

