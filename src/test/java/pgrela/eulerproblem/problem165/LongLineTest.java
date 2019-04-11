package pgrela.eulerproblem.problem165;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import pgrela.eulerproblem.common.LongFraction;
import pgrela.eulerproblem.common.LongRationalPoint;
import pgrela.eulerproblem.problem163.CrossHatchedTriangles.Segment;
import pgrela.eulerproblem.problem165.Intersections.LongLine;

import static pgrela.eulerproblem.common.LongFraction.natural;
import static pgrela.eulerproblem.common.Point.point;
import static pgrela.eulerproblem.problem165.Intersections.LongLine.*;

public class LongLineTest {

    @Test
    public void shouldCreateParallelLine() {
        // when
        LongLine longLine = fromSegment(new Segment(point(0, 0), point(2, 0)));

        // then
        Assertions.assertThat(longLine.c).isEqualTo(0);
        Assertions.assertThat(longLine.y).isNotEqualTo(0);
        Assertions.assertThat(longLine.x).isEqualTo(0);
    }

    @Test
    public void shouldCreateLine() {
        // when
        LongLine longLine = fromSegment(new Segment(point(0, 0), point(0, 2)));

        // then
        Assertions.assertThat(longLine.c).isEqualTo(0);
        Assertions.assertThat(longLine.y).isEqualTo(0);
        Assertions.assertThat(longLine.x).isNotEqualTo(0);
    }

    @Test
    public void shouldCreateDiagonalLine() {
        // when
        LongLine longLine = fromSegment(new Segment(point(0, 0), point(1, 2)));

        // then
        Assertions.assertThat(longLine.c).isEqualTo(0);
        Assertions.assertThat(longLine.y).isEqualTo(1);
        Assertions.assertThat(longLine.x).isEqualTo(-2);
    }
    @Test
    public void shouldCreateDiagonalLineAbove00() {
        // when
        LongLine longLine = fromSegment(new Segment(point(2, 2), point(6, 4)));

        // then
        Assertions.assertThat(longLine.c).isEqualTo(-4);
        Assertions.assertThat(longLine.y).isEqualTo(4);
        Assertions.assertThat(longLine.x).isEqualTo(-2);
    }

    @Test
    public void shouldDetectParallelLines(){
        // given
        LongLine line = new LongLine(3,4,9);
        LongLine otherLine = new LongLine(3,4,19);

        // when
        boolean sameDirectionAs = line.hasTheSameDirectionAs(otherLine);

        // then
        Assertions.assertThat(sameDirectionAs).isTrue();
    }
    @Test
    public void shouldDetectXEqual0Lines(){
        // given
        LongLine line = new LongLine(0,4,9);
        LongLine otherLine = new LongLine(0,4,19);

        // when
        boolean sameDirectionAs = line.hasTheSameDirectionAs(otherLine);

        // then
        Assertions.assertThat(sameDirectionAs).isTrue();
    }
    @Test
    public void shouldDetectYEqual0Lines(){
        // given
        LongLine line = new LongLine(5,0,9);
        LongLine otherLine = new LongLine(2,0,19);

        // when
        boolean sameDirectionAs = line.hasTheSameDirectionAs(otherLine);

        // then
        Assertions.assertThat(sameDirectionAs).isTrue();
    }
    @Test
    public void shouldDetectIntersectingLines(){
        // given
        LongLine line = new LongLine(5,3,9);
        LongLine otherLine = new LongLine(2,3,19);

        // when
        boolean sameDirectionAs = line.hasTheSameDirectionAs(otherLine);

        // then
        Assertions.assertThat(sameDirectionAs).isFalse();
    }
    @Test
    public void shouldDetectPerpendicularLines(){
        // given
        LongLine line = new LongLine(5,0,9);
        LongLine otherLine = new LongLine(0,2,19);

        // when
        boolean sameDirectionAs = line.hasTheSameDirectionAs(otherLine);

        // then
        Assertions.assertThat(sameDirectionAs).isFalse();
    }
    @Test
    public void shouldComputeIntersectionOfPerpendicularLines(){
        // given
        LongLine line = new LongLine(1,0,-1);
        LongLine otherLine = new LongLine(0,1,-1);

        // when
        LongRationalPoint intersection = line.intersection(otherLine);

        // then
        Assertions.assertThat(intersection).isEqualTo(new LongRationalPoint(LongFraction.ONE, LongFraction.ONE));

    }

    @Test
    public void shouldComputeIntersectionOfArbitraryLines() {
        // given
        LongLine line = new LongLine(2,3,-4);
        LongLine otherLine = new LongLine(5,8, -6);

        // when
        LongRationalPoint intersection = line.intersection(otherLine);

        // then
        Assertions.assertThat(intersection).isEqualTo(new LongRationalPoint(natural(14), natural(-8)));

    }
    @Test
    public void shouldBeOutsideBox() {
        // given
        Segment segment = new Segment(point(2, 1), point(5, 3));
        LongRationalPoint point = new LongRationalPoint(natural(2), natural(1));

        // when
        boolean isInsideBox = new Intersections().isWithinBox(segment, point);

        // then
        Assertions.assertThat(isInsideBox).isFalse();

    }
    @Test
    public void shouldBeInsideBox() {
        // given
        Segment segment = new Segment(point(2, 1), point(5, 3));
        LongRationalPoint point = new LongRationalPoint(natural(3), natural(2));

        // when
        boolean isInsideBox = new Intersections().isWithinBox(segment, point);

        // then
        Assertions.assertThat(isInsideBox).isTrue();

    }

    @Test
    public void shouldDiscoverTrueIntersection(){
        // given
        Segment segment = new Segment(point(2, 1), point(5, 3));
        Segment otherSegment = new Segment(point(1, 2), point(4, 2));
        LongLine line = LongLine.fromSegment(segment);
        LongLine otherLine = LongLine.fromSegment(otherSegment);
        LongRationalPoint intersection = line.intersection(otherLine);

        // when
        boolean withinBox = new Intersections().isWithinBox(segment, intersection);

        // then
        Assertions.assertThat(withinBox).isTrue();

    }
    @Test
    public void shouldDiscoverTrueIntersection2(){
        // given
        Segment segment = new Segment(point(2, 2), point(4, 2));
        Segment otherSegment = new Segment(point(3, 1), point(3, 3));
        LongLine line = LongLine.fromSegment(segment);
        LongLine otherLine = LongLine.fromSegment(otherSegment);
        LongRationalPoint intersection = line.intersection(otherLine);

        // when
        boolean withinBox = new Intersections().isWithinBox(segment, intersection);

        // then
        Assertions.assertThat(withinBox).isTrue();

    }
    @Test
    public void shouldDiscoverFakeIntersection(){
        // given
        Segment segment = new Segment(point(2, 1), point(5, 3));
        Segment otherSegment = new Segment(point(1, 2), point(4, 3));
        LongLine line = LongLine.fromSegment(segment);
        LongLine otherLine = LongLine.fromSegment(otherSegment);
        LongRationalPoint intersection = line.intersection(otherLine);

        // when
        boolean withinBox = new Intersections().isWithinBox(segment, intersection);

        // then
        Assertions.assertThat(withinBox).isFalse();

    }

}