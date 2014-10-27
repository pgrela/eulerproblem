package pgrela.eulerproblem.problem81;

import static java.util.stream.Stream.*;
import static pgrela.eulerproblem.common.Arrays.*;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;
import static pgrela.eulerproblem.problem81.PathSumTwoWays.ValuePoint.point;
import static pgrela.eulerproblem.problem81.PathSumTwoWays.ValuePoint.valuePoint;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Files;
import pgrela.eulerproblem.common.Point;

public class PathSumTwoWays implements EulerSolver {
    public static final String RESOURCE_FILE = "src/main/resources/problem81/matrix.txt";

    public static void main(String[] args) {
        printSolution(PathSumTwoWays.class);
    }

    public long solve() {
        return bottomRightCornerValue(
                new DjikstraSolver(Files.fileTo2DArray(",", RESOURCE_FILE),
                p-> of(
                        point(p.x + 1, p.y),
                        point(p.x, p.y + 1))
        ).startFrom(Point.point(0, 0)));
    }

    public static long bottomRightCornerValue(int[][] map) {
        return map[map.length-1][map[map.length-1].length-1];
    }

    public static class DjikstraSolver{
        int[][] map;
        int[][] costs;
        java.util.function.Function<Point,Stream<Point>> neighbourhood;
        private SortedSet<ValuePoint> queue;

        public DjikstraSolver(int[][] map, java.util.function.Function<Point, Stream<Point>> neighbourhood) {
            this.map = map;
            this.neighbourhood = neighbourhood;
            this.queue = new TreeSet<>();
            this.costs = copyArrayStructureAndFillWith(map, Integer.MAX_VALUE);
        }

        public int[][] startFrom(Point start){
            return startFrom(Lists.newArrayList(start));
        }
        public int[][] startFrom(List<Point> startPoints) {
            startPoints.stream().forEach(start -> queue.add(valuePoint(start.x, start.y, 0)));

            while (!queue.isEmpty()) {
                ValuePoint next = pickNext(queue);
                int value = next.value + map[next.y][next.x];
                if (costs[next.y][next.x] <= value) continue;

                costs[next.y][next.x] = value;

                of(point(next)).flatMap(neighbourhood).forEach(neighbour -> addToQueue(value, neighbour.x,neighbour.y));
            }
            return costs;
        }

        private ValuePoint pickNext(SortedSet<ValuePoint> set) {
            ValuePoint nextStep = set.first();
            set.remove(nextStep);
            return nextStep;
        }

        private void addToQueue(int value, int x, int y) {
            if (isWithinTheMap(x, y))
                queue.add(valuePoint(x, y, value));
        }

        private boolean isWithinTheMap(int x, int y) {
            return 0 <= y && y < map.length && 0 <= x && x < map[y].length;
        }
    }

    public static class ValuePoint extends pgrela.eulerproblem.common.Point implements Comparable{
        public int value;

        private ValuePoint(int x, int y, int value) {
            super(x,y);
            this.value = value;
        }

        public static ValuePoint valuePoint(int x, int y, int value) {
            return new ValuePoint(x, y, value);
        }
        public static Point point(ValuePoint mapPointUpdate){
            return Point.point(mapPointUpdate.x,mapPointUpdate.y);
        }

        @Override
        public int compareTo(Object o) {
            if (this == o) return 0;
            ValuePoint that = (ValuePoint) o;
            if(this.value==that.value){
                if(this.x==that.x){
                    return this.y-that.y;
                }
                return this.x-that.x;
            }
            return this.value-that.value;
        }
    }
}
