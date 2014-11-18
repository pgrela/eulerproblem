package pgrela.eulerproblem.problem100;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;

import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class ArrangedProbability implements EulerSolver {

    public static final long MINIMAL_CAPACITY = Maths.pow(10L, 12);

    public static void main(String[] args) {
        printSolution(ArrangedProbability.class);
    }

    public long solve() {
        return Stream.iterate(new Box(1, 1), box -> new Box(2 * box.getTotalDiscs() + 3 * box.getBlueDiscs() - 2, 3 * box.getTotalDiscs() + 4 * box.getBlueDiscs() - 3))
                .filter(box -> box.getTotalDiscs() > MINIMAL_CAPACITY)
                .findFirst().get().getBlueDiscs();
    }

    public static class Box {
        private final long blueDiscs;
        private final long totalDiscs;

        public Box(long blueDiscs, long totalDiscs) {
            this.blueDiscs = blueDiscs;
            this.totalDiscs = totalDiscs;
        }

        public long getBlueDiscs() {
            return blueDiscs;
        }

        public long getTotalDiscs() {
            return totalDiscs;
        }
    }
}
