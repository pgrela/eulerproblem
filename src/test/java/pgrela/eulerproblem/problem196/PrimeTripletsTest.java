package pgrela.eulerproblem.problem196;

import org.junit.Test;

import static org.junit.Assert.*;

public class PrimeTripletsTest {

    @Test
    public void solve() {
        assertEquals(322303240771079935L, new PrimeTriplets().solve());
    }
}