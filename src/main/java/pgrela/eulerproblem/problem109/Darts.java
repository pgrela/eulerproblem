package pgrela.eulerproblem.problem109;

import com.google.common.collect.Lists;
import pgrela.eulerproblem.common.EulerSolver;

import java.util.List;
import java.util.stream.IntStream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Darts implements EulerSolver {

    public static void main(String[] args) {
        printSolution(Darts.class);
    }

    public long solve() {
        List<Integer> doubles = Lists.newArrayList();
        IntStream.rangeClosed(1, 20).forEach(i -> doubles.add(2 * i));
        doubles.add(2 * 25);

        List<Integer> anyPossibleLand = Lists.newArrayList();
        IntStream.rangeClosed(1, 20)
                .peek(anyPossibleLand::add)
                .peek(i -> anyPossibleLand.add(2 * i))
                .forEach(i -> anyPossibleLand.add(3 * i));
        anyPossibleLand.add(25);
        anyPossibleLand.add(2 * 25);

        int ways = doubles.size();
        for (int firstLand = 0; firstLand < anyPossibleLand.size(); firstLand++) {
            int score = anyPossibleLand.get(firstLand);
            for (Integer doubleLand : doubles) {
                int secondScore = score + doubleLand;
                if (secondScore < 100) {
                    ++ways;
                }
            }
            for (int secondLand = 0; secondLand <= firstLand; secondLand++) {
                int secondScore = score + anyPossibleLand.get(secondLand);
                for (Integer doubleLand : doubles) {
                    int thirdScore = secondScore + doubleLand;
                    if (thirdScore < 100) {
                        ++ways;
                    }
                }
            }
        }
        return ways;
    }

}
