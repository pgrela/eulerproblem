package pgrela.eulerproblem.common;

public class LongPair {
    public long a, b;

    public LongPair(long a, long b) {    if(a<0||b<0)throw new IllegalArgumentException();
        this.a = a;
        this.b = b;
    }

    public static LongPair pair(long a, long b) {
        return new LongPair(a, b);
    }
    @Override
    public String toString() {
        return "[" + a + "," + b + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongPair)) return false;

        LongPair pair = (LongPair) o;

        if (a != pair.a) return false;
        if (b != pair.b) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (a ^ (a >>> 32));
        result = 31 * result + (int) (b ^ (b >>> 32));
        return result;
    }
}
