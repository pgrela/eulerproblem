package pgrela.eulerproblem.common.geometry;

public class PythagoreanTriangle extends Triangle {
    public long m;
    public long n;
    public int k;

    public PythagoreanTriangle(long m, long n) {
        this(m, n, 1);
    }

    public PythagoreanTriangle(long m, long n, int k) {
        super(2 * m * n * k, (m * m - n * n) * k, (m * m + n * n) * k);
        this.m = m;
        this.n = n;
        this.k = k;
    }

    @Override
    public String toString() {
        return "{" + m + "," + n + ",*" + k + "} -> " + super.toString();
    }

    public static PythagoreanTriangle triangle(long m, long n) {
        if (m < n) return new PythagoreanTriangle(n, m);
        return new PythagoreanTriangle(m, n);
    }
}
