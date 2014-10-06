package pgrela.eulerproblem.problem19;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.Calendar;

import static java.util.stream.IntStream.rangeClosed;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CountingSundays implements EulerSolver {
    public static void main(String[] args) {
        printSolution(CountingSundays.class);
    }

    public long solve() {
        return rangeClosed(1901, 2000)
                .boxed()
                .flatMap(year -> rangeClosed(0, 11)
                        .mapToObj(month -> new Calendar.Builder().setDate(year, month, 1).build()))
                .filter(c -> c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY)
                .count();
    }

}
