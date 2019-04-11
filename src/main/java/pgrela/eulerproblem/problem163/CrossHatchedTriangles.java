package pgrela.eulerproblem.problem163;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.Maths;
import pgrela.eulerproblem.common.Point;

import java.text.ParseException;
import java.util.*;

import static pgrela.eulerproblem.common.Point.point;
import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class CrossHatchedTriangles implements EulerSolver {

    public static final int LEVELS = 36;

    public static void main(String[] args) throws ParseException {
        printSolution(CrossHatchedTriangles.class);
    }

    public long solve() {
        Structure structure = new Structure();
        for (int level = 0; level < LEVELS; level++) {
            int y = 6 * level;
            for (int triangle = 0; triangle <= level; triangle++) {
                int x = 6 * triangle;
                structure.addSegment(new Segment(point(x + 0, y + 0), point(x + 0, y + 3)));
                structure.addSegment(new Segment(point(x + 0, y + 3), point(x + 0, y + 6)));
                structure.addSegment(new Segment(point(x + 0, y + 6), point(x + 3, y + 6)));
                structure.addSegment(new Segment(point(x + 3, y + 6), point(x + 6, y + 6)));
                structure.addSegment(new Segment(point(x + 6, y + 6), point(x + 3, y + 3)));
                structure.addSegment(new Segment(point(x + 3, y + 3), point(x + 0, y + 0)));

                structure.addSegment(new Segment(point(x + 0, y + 0), point(x + 2, y + 4)));
                structure.addSegment(new Segment(point(x + 2, y + 4), point(x + 3, y + 6)));
                structure.addSegment(new Segment(point(x + 0, y + 6), point(x + 2, y + 4)));
                structure.addSegment(new Segment(point(x + 2, y + 4), point(x + 3, y + 3)));
                structure.addSegment(new Segment(point(x + 6, y + 6), point(x + 2, y + 4)));
                structure.addSegment(new Segment(point(x + 2, y + 4), point(x + 0, y + 3)));
            }
            for (int triangle = 0; triangle < level; triangle++) {
                int x = 6 * triangle;
                structure.addSegment(new Segment(point(x + 0, y + 0), point(x + 4, y + 2)));
                structure.addSegment(new Segment(point(x + 4, y + 2), point(x + 6, y + 3)));
                structure.addSegment(new Segment(point(x + 6, y + 0), point(x + 4, y + 2)));
                structure.addSegment(new Segment(point(x + 4, y + 2), point(x + 3, y + 3)));
                structure.addSegment(new Segment(point(x + 6, y + 6), point(x + 4, y + 2)));
                structure.addSegment(new Segment(point(x + 4, y + 2), point(x + 3, y + 0)));

            }
        }
        structure.computeAllPossibleSegments();
        return structure.allTriangles();
    }

    public static class Segment {
        public Point a, b;

        public Segment(Point a, Point b) {
            this.a = a;
            this.b = b;
        }

        Segment reversed() {
            return new Segment(b, a);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Segment)) return false;

            Segment segment = (Segment) o;

            if (a != null ? !a.equals(segment.a) : segment.a != null) return false;
            return b != null ? b.equals(segment.b) : segment.b == null;
        }

        @Override
        public int hashCode() {
            int result = a != null ? a.hashCode() : 0;
            result = 31 * result + (b != null ? b.hashCode() : 0);
            return result;
        }

        @Override
        public String toString() {
            return "{" + a + "," + b + '}';
        }
    }

    static class Structure {
        Set<Segment> segments = new HashSet<>();
        Set<Point> points = new HashSet<>();
        Map<Point, Set<Segment>> segmentsFromPoint = new HashMap<>();

        void addSegment(Segment segment) {
            segments.add(segment);
            Segment reversed = segment.reversed();
            segments.add(reversed);
            points.add(segment.a);
            points.add(segment.b);
            segmentsFromPoint.computeIfAbsent(segment.a, k -> new HashSet<>()).add(segment);
            segmentsFromPoint.computeIfAbsent(reversed.a, k -> new HashSet<>()).add(reversed);
        }

        void computeAllPossibleSegments() {
            for (Point p : points) {
                if (p.x == 0 || p.x == p.y) {
                    Segment[] cache = segmentsFromPoint.get(p).toArray(new Segment[0]);
                    for (Segment s : cache) {
                        List<Point> path = new ArrayList<>();
                        path.add(p);
                        Point previous = p;
                        Vector unit = new Vector(s.a, s.b).unit();
                        do {
                            Point next = null;
                            for (int i = 1; i < 5; i++) {
                                Point maybeNext = unit.multiply(i).addTo(previous);
                                if (this.segments.contains(new Segment(previous, maybeNext))) {
                                    next = maybeNext;
                                    break;
                                }
                            }
                            if (next != null) {
                                path.add(next);
                            }
                            previous = next;
                        } while (previous != null);
                        for (int i = 0; i < path.size(); i++) {
                            for (int j = i + 1; j < path.size(); j++) {
                                addSegment(new Segment(path.get(i), path.get(j)));
                            }
                        }
                    }
                }
            }
        }

        long allTriangles() {
            long d = 0;
            for (Point p : points) {
                for (Segment s : segmentsFromPoint.get(p)) {
                    for (Segment s2 : segmentsFromPoint.get(p)) {
                        if (segments.contains(new Segment(s.b, s2.b))) {
                            Point c = s2.b;
                            if ((s.b.x - s.a.x) * (c.y - s.a.y) - (s.b.y - s.a.y) * (c.x - s.a.x) != 0) {
                                ++d;
                            }
                        }
                    }
                }
            }
            return d / 6;
        }
    }

    static class Vector {
        int x, y;

        Vector(Point a, Point b) {
            x = b.x - a.x;
            y = b.y - a.y;
        }

        Vector(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Vector unit() {
            if (x == 0) return new Vector(0, 1);
            if (y == 0) return new Vector(1, 0);
            int gcd = Maths.gcd(x, y);
            return new Vector(x / gcd, y / gcd);
        }

        Vector multiply(int n) {
            return new Vector(x * n, y * n);
        }

        Point addTo(Point p) {
            return Point.point(p.x + x, p.y + y);
        }
    }

}

