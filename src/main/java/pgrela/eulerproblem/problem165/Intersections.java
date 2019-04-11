package pgrela.eulerproblem.problem165;

import pgrela.eulerproblem.common.EulerSolver;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.LongRationalPoint;
import pgrela.eulerproblem.common.Point;
import pgrela.eulerproblem.problem163.CrossHatchedTriangles.Segment;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Set;

import static pgrela.eulerproblem.common.SolutionRunner.printSolution;

public class Intersections implements EulerSolver {


    public static void main(String[] args) throws ParseException {
        printSolution(Intersections.class);
    }

    static class BlumBlumShub {
        private static final int S_0 = 290797;
        private static final int S_MOD = 50515093;
        private static final int T_MOD = 500;
        long s = S_0;

        int next() {
            return (int) ((s = (s * s) % S_MOD) % T_MOD);
        }
    }

    public long solve() {
        Set<Segment> segments = new HashSet<>();
        BlumBlumShub blumBlumShub = new BlumBlumShub();
        for (int i = 0; i < 5000; i++) {
            segments.add(new Segment(Point.point(blumBlumShub.next(), blumBlumShub.next()), Point.point(blumBlumShub.next(), blumBlumShub.next())));
        }
        Set<LongRationalPoint> trueIntersections = new HashSet<>();
        for (Segment a : segments) {
            LongLine lineA = LongLine.fromSegment(a);
            for (Segment b : segments) {
                LongLine lineB = LongLine.fromSegment(b);
                if (!lineA.hasTheSameDirectionAs(lineB)) {
                    LongRationalPoint intersection = lineA.intersection(lineB);
                    if (isWithinBox(a, intersection) && isWithinBox(b, intersection)) {
                        trueIntersections.add(intersection);
                    }
                }
            }
        }
        return trueIntersections.size();
    }

    boolean isWithinBox(Segment segment, LongRationalPoint p) {
        long top = Math.max(segment.a.y, segment.b.y);
        long bottom = Math.min(segment.a.y, segment.b.y);
        long right = Math.max(segment.a.x, segment.b.x);
        long left = Math.min(segment.a.x, segment.b.x);
        if (segment.a.x == segment.b.x) {
            return p.y.isLessThan(top) && p.y.isGreaterThan(bottom);
        }
        if (segment.a.y == segment.b.y) {
            return p.x.isLessThan(right) && p.x.isGreaterThan(left);
        }
        return p.y.isLessThan(top) && p.y.isGreaterThan(bottom) && p.x.isLessThan(right) && p.x.isGreaterThan(left);
    }

    static class LongLine {
        // x*X + y*Y + c = 0;
        long x, y, c;

        public LongLine(long x, long y, long c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }

        static LongLine fromSegment(Segment s) {
            int x = -(s.b.y - s.a.y);
            int y = s.b.x - s.a.x;
            int c = -(s.a.x * x + s.a.y * y);
            return new LongLine(x, y, c);
        }

        boolean hasTheSameDirectionAs(LongLine other) {
            if (x == 0) return other.x == 0;
            if (y == 0) return other.y == 0;
            return other.x * y == x * other.y;
        }

        LongRationalPoint intersection(LongLine other) {
            LongFraction y;
            LongFraction x;
            if (this.x == 0 || other.x == 0) {
                if (this.x == 0) {
                    y = new LongFraction(-c, this.y);
                    x = y.multiply(other.y).add(other.c).divide(-other.x);
                } else {
                    y = new LongFraction(-other.c, other.y);
                    x = y.multiply(this.y).add(this.c).divide(-this.x);

                }
            } else {
                y = new LongFraction(other.c * this.x - c * other.x, other.x * this.y - this.x * other.y);
                x = y.multiply(this.y).add(LongFraction.natural(c)).divide(-this.x);
            }
            return new LongRationalPoint(x, y);
        }
    }

}

