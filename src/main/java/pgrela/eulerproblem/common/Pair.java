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
}
