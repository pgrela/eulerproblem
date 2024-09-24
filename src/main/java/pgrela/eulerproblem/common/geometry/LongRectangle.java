package pgrela.eulerproblem.common.geometry;

public class LongRectangle {
    final long a, b;

    public LongRectangle(long a, long b) {
        if (a < 0 || b < 0) throw new IllegalArgumentException("Less than zero?");
        this.a = a;
        this.b = b;
    }

    public static LongRectangle rectangle(long a, long b) {
        return new LongRectangle(a, b);
    }

    public boolean isSmallerThan(LongRectangle other) {
        return isSmallerRectangle(a, b, other.a, other.b);
    }

    private boolean isSmallerRectangle(long a1, long b1, long a2, long b2) {
        if (a1 == 0 || b1 == 0) return a2 > 0 && b2 > 0;
        if (a2 == 0 || b2 == 0) return false;
        if (a1 == a2) return b1 < b2;
        if (b1 == b2) return a1 < a2;
        if (a1 < a2 && b1 < b2) return true;
        if (a1 >= a2 && b1 >= b2) return false;
        long commonA = Math.min(a1, a2);
        long commonB = Math.min(b1, b2);
        return (a1 == commonA) ^ isSmallerRectangle(a1 + a2 - 2 * commonA, commonB, b1 + b2 - 2 * commonB, commonA);
    }
}
