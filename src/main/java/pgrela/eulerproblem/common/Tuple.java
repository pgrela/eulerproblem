package pgrela.eulerproblem.common;

public class Tuple<FIRST, SECOND> {
    public FIRST a;
    public SECOND b;

    public Tuple(FIRST a, SECOND b) {
        this.a = a;
        this.b = b;
    }

    public static <T,U> Tuple<T,U> tuple(T a, U b) {
        return new Tuple<>(a, b);
    }

    @Override
    public String toString() {
        return "[" + a + "," + b + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tuple)) return false;

        Tuple<?, ?> tuple = (Tuple<?, ?>) o;

        if (a != null ? !a.equals(tuple.a) : tuple.a != null) return false;
        if (b != null ? !b.equals(tuple.b) : tuple.b != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a != null ? a.hashCode() : 0;
        result = 31 * result + (b != null ? b.hashCode() : 0);
        return result;
    }
}
