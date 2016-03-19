package pgrela.eulerproblem.problem102;

import pgrela.eulerproblem.common.EulerSolver;

import java.util.stream.Stream;

import static pgrela.eulerproblem.common.Files.getLinesStream;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class TriangleContainment implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem102/triangles.txt";

    public static void main(String[] args) {
        printSolution(TriangleContainment.class);
    }

    public long solve() {
        return getLinesStream(RESOURCE_FILE)
                .map((String s) -> Stream.of(s.split(",")).mapToInt(Integer::valueOf).toArray())
                .filter(a -> triangleContainsCenter(a[0], a[1], a[2], a[3], a[4], a[5]))
                .count();
    }

    private boolean triangleContainsCenter(int ax, int ay, int bx, int by, int cx, int cy) {
        return doubleArea(ax, ay, bx, by, cx, cy) ==
                doubleArea(0, 0, bx, by, cx, cy) + doubleArea(ax, ay, 0, 0, cx, cy) + doubleArea(ax, ay, bx, by, 0, 0);
    }

    private long doubleArea(int ax, int ay, int bx, int by, int cx, int cy) {
        return Math.abs(ax * (by - cy) + bx * (cy - ay) + cx * (ay - by));
    }

}
