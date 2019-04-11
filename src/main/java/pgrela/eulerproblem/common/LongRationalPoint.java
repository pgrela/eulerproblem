package pgrela.eulerproblem.common;

public class LongRationalPoint {
    public static LongRationalPoint POINT_00 = new LongRationalPoint(LongFraction.ZERO, LongFraction.ZERO);
    public LongFraction x, y;

    public LongRationalPoint(LongFraction x, LongFraction y) {
        this.x = x;
        this.y = y;
    }

    public static LongRationalPoint point(LongFraction x, LongFraction y) {
        return new LongRationalPoint(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongRationalPoint)) return false;

        LongRationalPoint that = (LongRationalPoint) o;

        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        return y != null ? y.equals(that.y) : that.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "[" + x + "," + y + "]";
    }
}
