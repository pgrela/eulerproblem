package pgrela.eulerproblem.common.geometry;

import junit.framework.TestCase;

import java.util.HashSet;

public class TriangleTest extends TestCase {
    public void testEquals() {
        HashSet<Triangle> triangles = new HashSet<>();
        triangles.add(new Triangle(1,2,3));
        triangles.add(new Triangle(1,3,2));
        triangles.add(new Triangle(2,1,3));
        triangles.add(new Triangle(2,3,1));
        triangles.add(new Triangle(3,1,2));
        triangles.add(new Triangle(3,2,1));
        assertEquals(1, triangles.size());

        triangles.add(new Triangle(5,5,6));
        triangles.add(new Triangle(5,6,5));
        triangles.add(new Triangle(6,5,5));
        assertEquals(2, triangles.size());

        triangles.add(new Triangle(5,5,4));
        triangles.add(new Triangle(5,4,5));
        triangles.add(new Triangle(4,5,5));
        assertEquals(3, triangles.size());

        triangles.add(new Triangle(9,9,9));
        triangles.add(new Triangle(9,9,9));
        assertEquals(4, triangles.size());
    }
}