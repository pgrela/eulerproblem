package pgrela.eulerproblem.problem14;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Streams;

import java.util.function.Function;
import java.util.stream.LongStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class LongestCollatzSequence implements EulerSolver {
    public static void main(String[] args) {
        printSolution(LongestCollatzSequence.class);
    }

    public long solve() {
        Function<Long, Long> sequenceLength = c -> StreamSupport.stream(
                Streams.takeWhile(
                        Stream.<Long>iterate(c, n -> n % 2 == 0 ? n / 2 : 3 * n + 1).spliterator(),
                        k -> k != 1L)
                , false).count();
        return LongStream.rangeClosed(1, 1000 * 1000).reduce((a, b) -> Long.compare(sequenceLength.apply(a), sequenceLength.apply(b)) > 0 ? a : b).getAsLong();
    }

}
