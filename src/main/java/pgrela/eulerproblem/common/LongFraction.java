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
        long gcd = Math.abs(Maths.gcd(nominator, denominator));
        this.nominator = nominator / gcd;
        this.denominator = denominator / gcd;
        c = ahashCode();
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

    public LongFraction divide(long n) {
        return divide(natural(n));
    }

    public boolean isPow(int degree) {
        return Maths.isPow(nominator, Math.abs(degree)) && Maths.isPow(denominator, Math.abs(degree));
    }

    public LongFraction root(int degree) {
        if(degree < 0) return inverse().root(-degree);
        return new LongFraction(Maths.intRoot(nominator, degree), Maths.intRoot(denominator, degree));
    }

    public boolean isSqrt() {
        long nominatorSqrt = Maths.intSqrt(nominator);
        long denominatorSqrt = Maths.intSqrt(denominator);
        return nominatorSqrt * nominatorSqrt == nominator && denominatorSqrt * denominatorSqrt == denominator;
    }

    public LongFraction sqrt() {
        return new LongFraction(Maths.longSqrt(nominator), Maths.longSqrt(denominator));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LongFraction)) return false;

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
        return "{" + nominator + "/" + denominator + " ~ " + (nominator * 1. / denominator) + '}';
    }

    public static LongFraction natural(long c) {
        return new LongFraction(c, 1);
    }

    public LongFraction multiply(long n) {
        return multiply(natural(n));
    }

    public boolean isLessThan(long n) {
        if (n == 0) return nominator * denominator < 0;
        long gcd = Math.abs(Maths.gcd(nominator, n));
        return nominator / gcd < denominator * (n / gcd);
    }

    public boolean isGreaterThan(long n) {
        if (n == 0) return nominator * denominator > 0;
        long gcd = Math.abs(Maths.gcd(nominator, n));
        return nominator / gcd > denominator * (n / gcd);
    }

    public LongFraction add(long n) {
        return add(natural(n));
    }

    public LongFraction pow(int exponent) {
        if (exponent == 0)
            return this.equals(ZERO) ? ZERO : ONE;
        long nominatorPowered = Maths.pow(nominator, Math.abs(exponent));
        long denominatorPowered = Maths.pow(denominator, Math.abs(exponent));
        if (exponent < 0) {
            return new LongFraction(denominatorPowered, nominatorPowered);
        } else {
            return new LongFraction(nominatorPowered, denominatorPowered);
        }
    }
}
