package pgrela.eulerproblem.common.geometry;

public class Triangle {
    public long a, b, c;

    public Triangle(long a, long b, long c) {
        this.a = Math.min(a, Math.min(b, c));
        this.c = Math.max(a, Math.max(b, c));
        this.b = Math.max(Math.min(a, b), Math.min(Math.max(a, b), c));
    }

    public long perimeter() {
        return a + b + c;
    }


    @Override
    public String toString() {
        return "{" + a + "," + b + "," + c + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Triangle)) return false;

        Triangle triangle = (Triangle) o;

        return a == triangle.a && b == triangle.b && c == triangle.c;
    }

    @Override
    public int hashCode() {
        int result = (int) (a ^ (a >>> 32));
        result = 31 * result + (int) (b ^ (b >>> 32));
        result = 31 * result + (int) (c ^ (c >>> 32));
        return result;
    }
}
