package pgrela.eulerproblem.problem61;

import static com.google.common.collect.Lists.newArrayList;
import static java.util.stream.IntStream.iterate;
import static java.util.stream.Stream.of;
import static pgrela.eulerproblem.common.FiniteStreamBuilder.stream;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.List;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import pgrela.eulerproblem.common.EulerSolver;

public class CyclicalFigurateNumbers implements EulerSolver {

    public static void main(String[] args) {
        printSolution(CyclicalFigurateNumbers.class);
    }

    public long solve() {
        Object[] sequences = Stream.<IntUnaryOperator>of(
                n -> n * (n + 1) / 2,
                n -> n * n,
                n -> n * (3 * n - 1) / 2,
                n -> n * (2 * n - 1),
                n -> n * (5 * n - 3) / 2,
                n -> n * (3 * n - 2)
        ).map(f -> stream(iterate(1, i -> i + 1)
                        .map(f).boxed())
                        .until(i -> i < 10000).stream()
                        .filter(i -> i > 999).mapToInt(i -> i).toArray()
        ).toArray();


        return s(sequences).stream().mapToInt(i -> i).sum();
    }

    List<Integer> s(Object[] streams) {
        int[] n = (int[]) streams[0];
        streams[0] = null;
        for (int i : n) {
            List<Integer> x = s(streams, i, i);
            if (x != null) {
                return x;
            }
        }
        return null;
    }

    List<Integer> s(Object[] streams, int firstNumber, int lastNumber) {
        if (of(streams).allMatch(Predicate.isEqual(null))) {
            if (canBeInSequence(firstNumber, lastNumber)) {
                return newArrayList(firstNumber);
            } else return null;
        }
        for (int j = 0; j < streams.length; j++) {
            int[] n = (int[]) streams[j];
            if (n == null) continue;
            streams[j] = null;
            for (int i : n) {
                if (!canBeInSequence(lastNumber, i)) {
                    continue;
                }
                List<Integer> x = s(streams, firstNumber, i);
                if (x != null) {
                    x.add(i);
                    return x;
                }
            }
            streams[j] = n;
        }
        return null;
    }

    private boolean canBeInSequence(int firstNumber, int lastNumber) {
        return firstNumber / 100 == lastNumber % 100;
    }

}

