package pgrela.eulerproblem.problem208;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Tuple;

import java.util.Arrays;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class RobotWalks implements EulerSolver {

    public static final int SECTIONS = 70;
    public static final int FIVES = SECTIONS / 5;

    public static void main(String[] args) {
        printSolution(RobotWalks.class);
    }

    @Override
    public long solve() {
        return Stream.iterate(Collections.singletonMap(new Directions(), 1L),
                        lastSteps -> lastSteps.entrySet().stream()
                                .flatMap(step -> Stream.of(Tuple.tuple(step.getKey().left(), step.getValue()), Tuple.tuple(step.getKey().right(), step.getValue())))
                                .filter(t -> t.first() != null)
                                .collect(Collectors.groupingBy(Tuple::first, Collectors.summingLong(Tuple::second))))
                .skip(SECTIONS).findFirst().orElseThrow().entrySet().stream()
                .filter(e -> e.getKey().recent() == 0)
                .mapToLong(Map.Entry::getValue).sum();
    }

}

record Directions(int recent, int[] directions) {
    Directions() {
        this(0, new int[5]);
    }

    Directions left() {
        return turn((recent + 4) % 5);
    }

    Directions right() {
        return turn((recent + 1) % 5);
    }

    private Directions turn(int next) {
        int[] newDirections = Arrays.copyOf(directions, directions.length);
        newDirections[next]++;
        if (newDirections[next] > RobotWalks.FIVES) return null;
        return new Directions(next, newDirections);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Directions that = (Directions) o;
        return recent == that.recent && Objects.deepEquals(directions, that.directions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recent, Arrays.hashCode(directions));
    }

    @Override
    public String toString() {
        return "Directions{" +
                "recent=" + recent +
                ", directions=" + Arrays.toString(directions) +
                '}';
    }
}

