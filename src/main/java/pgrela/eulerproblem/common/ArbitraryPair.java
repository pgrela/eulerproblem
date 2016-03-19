package pgrela.eulerproblem.common;

public class ArbitraryPair<T> {
    public T a, b;

    public ArbitraryPair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public static <T> ArbitraryPair<T> pair(T a, T b) {
        return new ArbitraryPair<>(a, b);
    }

    @Override
    public String toString() {
        return "[" + a + "," + b + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ArbitraryPair)) return false;

        ArbitraryPair<?> that = (ArbitraryPair<?>) o;

        if (a != null ? !a.equals(that.a) : that.a != null) return false;
        return b != null ? b.equals(that.b) : that.b == null;

    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }
}
