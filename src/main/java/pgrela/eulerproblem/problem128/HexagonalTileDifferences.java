package pgrela.eulerproblem.problem128;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

import java.util.stream.LongStream;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Primes;

public class HexagonalTileDifferences implements EulerSolver {

    public static void main(String[] args) {
        printSolution(HexagonalTileDifferences.class);
    }

    public long solve() {
        return LongStream.iterate(0, i -> i + 1)
                .filter(i -> i % 2 == 0 ? checkTop(i / 2) : checkTopRight(i / 2))
                .map(i -> i % 2 == 0 ? getHexValueTop(i / 2) : getHexValueTop(i / 2 + 1) - 1)
                .skip(1999)
                .findFirst()
                .getAsLong();

    }

    private boolean checkTopRight(long layer) {
        return checkStream(getTopRightNeighbours(layer, getHexValueTop(layer + 1) - 1));
    }

    private boolean checkTop(long layer) {
        return checkStream(getTopNeighbours(layer, getHexValueTop(layer)));
    }

    private boolean checkStream(LongStream differences) {
        return differences.filter(p -> Primes.isPrime((int) p)).count() == 3;
    }

    private LongStream getTopNeighbours(long layer, long hexValueTop) {
        return LongStream.of(
                Math.abs(hexValueTop + 1 - hexValueTop),
                Math.abs(getHexValueTop(layer + 1) - 1 - hexValueTop),
                Math.abs(getHexValueTop(layer + 1) - hexValueTop),
                Math.abs(getHexValueTop(layer + 1) + 1 - hexValueTop),
                Math.abs(getHexValueTop(layer + 2) - 1 - hexValueTop),
                Math.abs(getHexValueTop(layer - 1) - hexValueTop)
        );
    }

    private LongStream getTopRightNeighbours(long layer, long hexValueTop) {
        return LongStream.of(
                Math.abs(getHexValueTop(layer + 1) - 2 - hexValueTop),
                Math.abs(getHexValueTop(layer) - hexValueTop),
                Math.abs(getHexValueTop(layer - 1) - hexValueTop),
                Math.abs(getHexValueTop(layer) - 1 - hexValueTop),
                Math.abs(getHexValueTop(layer + 2) - 1 - hexValueTop),
                Math.abs(getHexValueTop(layer + 2) - 2 - hexValueTop)
        );
    }

    private long getHexValueTop(long layer) {
        return (layer - 1) * layer * 3 + 2;
    }

}
