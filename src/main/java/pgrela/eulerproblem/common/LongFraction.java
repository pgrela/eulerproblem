package pgrela.eulerproblem.common;

public class LongFraction {
    public static final LongFraction ONE = new LongFraction(1, 1);
    public static final LongFraction ZERO = new LongFraction(0, 1);
    public static final LongFraction MINUS_ONE = new LongFraction(-1, 1);
    private long nominator;
    private int c;

    public long getNominator() {
        return nominator;
    }

    public long getDenominator() {
        return denominator;
    }

    private long denominator;

    public LongFraction(long nominator, long denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Zero kann nicht here seien!");
        }
        if (nominator == 0) {
            denominator = 1;
        }
        if (denominator < 0) {
            nominator *= -1;
            denominator *= -1;
        }
        long gcd = Maths.gcd(nominator, denominator);
        this.nominator = nominator / gcd;
        this.denominator = denominator / gcd;
        c=ahashCode();
    }

    public LongFraction add(LongFraction other) {
        long lcm = Maths.lcm(denominator, other.denominator);
        return new LongFraction(nominator * (lcm / denominator) + other.nominator * (lcm / other.denominator), lcm);
    }

    public LongFraction multiply(LongFraction other) {
        return new LongFraction(nominator * other.nominator, denominator * other.denominator);
    }

    public LongFraction inverse() {
        return new LongFraction(denominator, nominator);
    }

    public LongFraction substract(LongFraction other) {
        return add(other.multiply(MINUS_ONE));
    }

    public LongFraction divide(LongFraction other) {
        return multiply(other.inverse());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        //if (!(o instanceof LongFraction)) return false;

        LongFraction that = (LongFraction) o;

        if (nominator != that.nominator) return false;
        return denominator == that.denominator;
    }

    @Override
    public int hashCode() {
        return c;
    }
    public int ahashCode() {
        int result = (int) (nominator ^ (nominator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "{" + nominator + "/" + denominator + '}';
    }
}
