package pgrela.eulerproblem.common;

import pgrela.eulerproblem.common.geometry.LongRectangle;

public class LongFraction implements Comparable<LongFraction> {
    public static final LongFraction ONE = new LongFraction(1, 1);
    public static final LongFraction ZERO = new LongFraction(0, 1);
    public static final LongFraction MINUS_ONE = new LongFraction(-1, 1);
    private final long nominator;
    private final long denominator;
    private final int c;


    public long getNominator() {
        return nominator;
    }

    public long getDenominator() {
        return denominator;
    }

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

    private LongFraction multiplyInternal(LongFraction other) {
        return new LongFraction(nominator * other.nominator, denominator * other.denominator);
    }

    public LongFraction multiply(LongFraction other) {
        long gcdND = Maths.gcd(nominator, other.denominator);
        long gcdDN = Maths.gcd(denominator, other.nominator);
        return new LongFraction((nominator / gcdND) * (other.nominator / gcdDN), denominator / gcdDN * (other.denominator / gcdND));
    }

    public LongFraction inverse() {
        return new LongFraction(denominator, nominator);
    }

    public LongFraction substract(LongFraction other) {
        return add(other.multiplyInternal(MINUS_ONE));
    }

    public LongFraction divide(LongFraction other) {
        return multiplyInternal(other.inverse());
    }

    public LongFraction divide(long n) {
        return divide(natural(n));
    }

    public boolean isPow(int degree) {
        return Maths.isPow(nominator, Math.abs(degree)) && Maths.isPow(denominator, Math.abs(degree));
    }

    public LongFraction root(int degree) {
        if (degree < 0) return inverse().root(-degree);
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
        int result = Long.hashCode(nominator);
        result = 31 * result + Long.hashCode(denominator);
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
        if (n == 0) return Math.signum(nominator) * Math.signum(denominator) < 0;
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
        if (exponent == 0) {
            if (this.equals(ZERO)) {
                throw new IllegalArgumentException("Zero to zero");
            }
            return ONE;
        }
        if (exponent < 0) {
            return inverse().pow(-exponent);
        }
        return new LongFraction(Maths.pow(nominator, exponent), Maths.pow(denominator, exponent));
    }

    @Override
    public int compareTo(LongFraction o) {
        return Long.compare(this.nominator * o.denominator, o.nominator * this.denominator);
    }

    public double asDouble() {
        return nominator / (double) denominator;
    }

    public boolean isLessThan(LongFraction other) {
        return LongRectangle.rectangle(nominator, other.denominator).isSmallerThan(LongRectangle.rectangle(other.nominator, denominator));
    }
}
