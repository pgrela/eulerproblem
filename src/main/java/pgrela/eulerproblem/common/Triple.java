package pgrela.eulerproblem.common;

import java.util.Objects;

public class Triple<FIRST, SECOND, THIRD> {
    public FIRST a;
    public SECOND b;
    public THIRD c;

    public Triple(FIRST a, SECOND b, THIRD c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static <FIRST, SECOND, THIRD> Triple<FIRST, SECOND, THIRD> triple(FIRST a, SECOND b, THIRD c) {
        return new Triple<>(a, b, c);
    }
    public static <FIRST, SECOND, THIRD> Triple<FIRST, SECOND, THIRD> triple(Tuple<FIRST, SECOND> tuple, THIRD c) {
        return new Triple<>(tuple.a, tuple.b, c);
    }

    @Override
    public String toString() {
        return "[" + a + "," + b + "," + c + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Triple<?, ?, ?> triple = (Triple<?, ?, ?>) o;
        return Objects.equals(a, triple.a) &&
                Objects.equals(b, triple.b) &&
                Objects.equals(c, triple.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }
}
