package components.satellitetracker;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * JUnit test fixture for {@code Satellite1}'s kernel and secondary methods.
 *
 * @author Amrithaa Ashok Kumar
 */
public class SatelliteTrackerSecondaryTest {

    /**
     * Asserts each double element is equal with a tolerance of .0000001
     */
    private static final double DELTA = 1e-6;
    /**
     * Gravitational constant (m^3 kg^-1 s^-2)
     */
    private static final double G = 6.67430e-11;
    /**
     * Mass of Earth (kg)
     */
    private static final double M = 5.972e24;

    /**
     * Seconds in a day
     */
    private static final double SECONDS_IN_DAY = 86400.0;

    /**
     * TEST CASES - adjustVelocity()
     */

    @Test
    public void testAdjustVelocityZeroForce() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 3.0, 4.0, 0.0);
        Satellite1 sCopy = new Satellite1(0.0, 0.0, 0.0, 3.0, 4.0, 0.0);

        s.adjustVelocity(0.0, 45.0, 100.0);

        assertEquals(s, sCopy); // no change expected
    }

    @Test
    public void testAdjustVelocityRoutine() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 1.0, 1.0, 0.0);
        // cos(0°) = 1, sin(0°) = 0
        s.adjustVelocity(100.0, 0.0, 50.0);

        // (100/50 = 2 added to vx)
        Satellite1 sCopy = new Satellite1(0.0, 0.0, 0.0, 3.0, 1.0, 0.0);
        assertEquals(s, sCopy);
    }

    @Test
    public void testAdjustVelocityNegativeForce() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 0.0, 2.0, 0.0);
        s.adjustVelocity(-50.0, 270.0, 25.0); // sin(270°) = -1 → vy += 2

        double[] expected = { 0.0, 4.0, 0.0 };
        assertArrayEquals(expected, s.getVelocity(), 1e-5);
    }

    /**
     * TEST CASE - estimateOrbitalPeriod()
     */
    @Test
    public void testEstimateOrbitalPeriodLEO() {
        // Satellite in LEO at 6771 km
        Satellite1 s = new Satellite1(6771.0, 0.0, 0.0, 7.8, 0.0, 0.0);

        // Manually compute expected period
        double radiusMeters = 6771.0 * 1000.0;
        double expectedSeconds = 2 * Math.PI
                * Math.sqrt(Math.pow(radiusMeters, 3) / (G * M));
        double expectedDays = expectedSeconds / SECONDS_IN_DAY;
        double actualPeriod = s.estimateOrbitalPeriod();

        assertEquals(expectedDays, actualPeriod, DELTA); // Should be around 0.0642 days
    }

    @Test
    public void testEstimateOrbitalPeriodGEO() {
        Satellite1 s = new Satellite1(42157.0, 0.0, 0.0, 3.07, 0.0, 0.0);

        // Manually compute expected period
        double radiusMeters = 42157.0 * 1000.0;
        double expectedSeconds = 2 * Math.PI
                * Math.sqrt(Math.pow(radiusMeters, 3) / (G * M));
        double expectedDays = expectedSeconds / SECONDS_IN_DAY;
        double actualPeriod = s.estimateOrbitalPeriod();

        assertEquals(expectedDays, actualPeriod, DELTA); // Should be around 0.0642 days

    }

    @Test
    public void testEstimateOrbitalPeriodLunarBoundary() {
        Satellite1 s = new Satellite1(384400.0, 0.0, 0.0, 1.0, 0.0, 0.0);

        // Manually compute expected period
        double radiusMeters = 384400.0 * 1000.0;
        double expectedSeconds = 2 * Math.PI
                * Math.sqrt(Math.pow(radiusMeters, 3) / (G * M));
        double expectedDays = expectedSeconds / SECONDS_IN_DAY;
        double actualPeriod = s.estimateOrbitalPeriod();

        assertEquals(expectedDays, actualPeriod, DELTA); // Should be around 0.0642 days
    }

    /**
     * TEST CASES - willCollide().
     */

    @Test
    public void testWillCollideTrue() {
        Satellite1 s1 = new Satellite1(7000.0, 0.0, 0.0, 0.0, 0.0, 0.0); // radius = 7000 km
        Satellite1 s2 = new Satellite1(7000.0, 300.00, 300.0, 0.0, 0.0, 0.0); // still close enough

        boolean result = s1.willCollide(s2);
        assertEquals(true, result); // distance ~707 km
    }

    @Test
    public void testWillCollideFalseFarApart() {
        Satellite1 s1 = new Satellite1(7000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 s2 = new Satellite1(27000.0, 0.0, 0.0, 0.0, 0.0, 0.0); // far enough to avoid collision

        boolean result = s1.willCollide(s2);
        assertEquals(false, result);
    }

    @Test
    public void testWillCollideSamePosition() {
        Satellite1 s1 = new Satellite1(8000.0, 2000.0, 3000.0, 0.0, 0.0, 0.0);
        Satellite1 s2 = new Satellite1(8000.0, 2000.0, 3000.0, 0.0, 0.0, 0.0);

        boolean result = s1.willCollide(s2);
        assertEquals(true, result); // same spot
    }

    @Test
    public void testWillCollideDiagonalClose() {
        Satellite1 s1 = new Satellite1(8000.0, 8000.0, 8000.0, 0.0, 0.0, 0.0);
        Satellite1 s2 = new Satellite1(8005.0, 8005.0, 8005.0, 0.0, 0.0, 0.0);

        assertTrue(s1.orbitsEarth() && s2.orbitsEarth());
        assertEquals(true, s1.willCollide(s2));
    }

    @Test
    public void testWillCollideJustBelowThreshold() {
        Satellite1 s1 = new Satellite1(10000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 s2 = new Satellite1(10000.0, 499.9, 0.0, 0.0, 0.0, 0.0);

        assertTrue(s1.orbitsEarth() && s2.orbitsEarth());
        assertEquals(true, s1.willCollide(s2));
    }

    @Test
    public void testWillCollideJustAboveThreshold() {
        Satellite1 s1 = new Satellite1(15000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 s2 = new Satellite1(15000.0, 500.1, 0.0, 0.0, 0.0, 0.0);

        assertTrue(s1.orbitsEarth() && s2.orbitsEarth());
        assertEquals(false, s1.willCollide(s2));
    }

    @Test
    public void testWillCollideMiddleSatellite() {
        Satellite1 s1 = new Satellite1(15000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 s2 = new Satellite1(15000.0, 300.0, 0.0, 0.0, 0.0, 0.0); // close to s1
        Satellite1 s3 = new Satellite1(15000.0, 1000.0, 0.0, 0.0, 0.0, 0.0); // far from s1

        assertTrue(s1.willCollide(s2)); // true
        assertTrue(!s1.willCollide(s3)); // false
    }

    @Test(expected = IllegalArgumentException.class)
    public void testWillCollidePreconditionFails() {
        Satellite1 s1 = new Satellite1(5000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 s2 = new Satellite1(7000.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        s1.willCollide(s2); // throw IllegalArgumentException
    }

}
