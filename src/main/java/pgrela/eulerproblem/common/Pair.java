package pgrela.eulerproblem.common;

public class Pair {
    public int a, b;

    public Pair(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public static Pair pair(int a, int b) {
        return new Pair(a, b);
    }
    @Override
    public String toString() {
        return "[" + a + "," + b + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;

        Pair pair = (Pair) o;

        if (a != pair.a) return false;
        if (b != pair.b) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = a;
        result = 31 * result + b;
        return result;
    }
}
