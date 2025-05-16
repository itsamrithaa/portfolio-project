package components.satellitetracker;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * JUnit test fixture for {@code Satellite1}'s kernel and secondary methods.
 *
 * @author Amrithaa Ashok Kumar
 */
public class SatelliteTrackerTest {

    /**
     * Asserts each double element is equal with a tolerance of .0000001
     */
    private static final double DELTA = 1e-6;

    /**
     * TEST CASES - CONSTRUCTORS
     */
    @Test
    public void testConstructorNoArg() {
        Satellite1 s = new Satellite1();
        Satellite1 sCopy = new Satellite1();

        assertArrayEquals(s.getLiveLocation(), sCopy.getLiveLocation(), DELTA);
        assertArrayEquals(s.getVelocity(), sCopy.getVelocity(), DELTA);
        assertEquals(s, sCopy);
    }

    @Test
    public void testConstructorWithParameters() {
        Satellite1 s = new Satellite1(1200.0, 3400.0, 5600.0, 1.2, 2.3, 3.4);
        Satellite1 sCopy = new Satellite1(1200.0, 3400.0, 5600.0, 1.2, 2.3,
                3.4);

        assertArrayEquals(s.getLiveLocation(), sCopy.getLiveLocation(), DELTA);
        assertArrayEquals(s.getVelocity(), sCopy.getVelocity(), DELTA);
        assertEquals(s, sCopy);
    }

    /**
     * TEST CASES - getLiveLocation().
     */

    @Test
    public void testGetLiveLocationAllZero() {
        Satellite1 s = new Satellite1();
        double[] expected = { 0.0, 0.0, 0.0 };
        assertArrayEquals(expected, s.getLiveLocation(), DELTA);
    }

    @Test
    public void testGetLiveLocationRoutine() {
        Satellite1 s = new Satellite1(100.0, 200.0, 300.0, 0.0, 0.0, 0.0);
        double[] expected = { 100.0, 200.0, 300.0 };
        assertArrayEquals(expected, s.getLiveLocation(), DELTA);
    }

    @Test
    public void testGetLiveLocationNegativeCoordinates() {
        Satellite1 s = new Satellite1(-1.0, -2.5, -3.75, 0.0, 0.0, 0.0);
        double[] expected = { -1.0, -2.5, -3.75 };
        assertArrayEquals(expected, s.getLiveLocation(), DELTA);
    }

    @Test
    public void testGetLiveLocationBoundaryValues() {
        Satellite1 s = new Satellite1(Double.MAX_VALUE, 0.0, Double.MIN_VALUE,
                0.0, 0.0, 0.0);
        double[] expected = { Double.MAX_VALUE, 0.0, Double.MIN_VALUE };
        assertArrayEquals(expected, s.getLiveLocation(), DELTA);
    }

    /**
     * TEST CASES - getVelocity().
     */

    @Test
    public void testGetVelocityAllZero() {
        Satellite1 s = new Satellite1();
        double[] expected = { 0.0, 0.0, 0.0 };
        assertArrayEquals(expected, s.getVelocity(), DELTA);
    }

    @Test
    public void testGetVelocityRoutine() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 1.1, 2.2, 3.3);
        double[] expected = { 1.1, 2.2, 3.3 };
        assertArrayEquals(expected, s.getVelocity(), DELTA);
    }

    @Test
    public void testGetVelocityNegative() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, -10.0, -5.5, -1.1);
        double[] expected = { -10.0, -5.5, -1.1 };
        assertArrayEquals(expected, s.getVelocity(), DELTA);
    }

    @Test
    public void testGetVelocityBoundaryValues() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, Double.MAX_VALUE, 0.0,
                Double.MIN_VALUE);
        double[] expected = { Double.MAX_VALUE, 0.0, Double.MIN_VALUE };
        assertArrayEquals(expected, s.getVelocity(), DELTA);
    }

    /**
     * TEST CASES - updatePosition()
     */
    @Test
    public void testUpdatePositionRoutine() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 1.0, 2.0, 3.0);
        s.updatePosition(10);
        Satellite1 sCopy = new Satellite1(10.0, 20.0, 30.0, 1.0, 2.0, 3.0);

        assertArrayEquals(s.getLiveLocation(), sCopy.getLiveLocation(), DELTA);
        assertArrayEquals(s.getVelocity(), sCopy.getVelocity(), DELTA);
        assertEquals(s, sCopy);
    }

    @Test
    public void testUpdatePositionZeroTime() {
        Satellite1 s = new Satellite1(100.0, 200.0, 300.0, 1.0, 1.0, 1.0);
        Satellite1 sCopy = new Satellite1(100.0, 200.0, 300.0, 1.0, 1.0, 1.0);

        s.updatePosition(0);
        assertEquals(s, sCopy);
    }

    @Test
    public void testUpdatePositionBoundaryLargeTime() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 1.0, 1.0, 1.0);
        s.updatePosition(1000000);

        Satellite1 sCopy = new Satellite1(1000000, 1000000, 1000000, 1.0, 1.0,
                1.0);
        assertEquals(s, sCopy);
    }

    /**
     * TEST CASES - isStable()
     */
    @Test
    public void testIsStableTrue() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 7.9, 0.0, 0.0);
        Satellite1 sCopy = new Satellite1(0.0, 0.0, 0.0, 7.9, 0.0, 0.0);

        assertEquals(s.isStable(), sCopy.isStable());
        assertEquals(true, s.isStable());
    }

    @Test
    public void testIsStableZeroVelocity() {
        Satellite1 s = new Satellite1();
        assertEquals(false, s.isStable());
    }

    @Test
    public void testIsStableAtLowerBound() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 7.8, 0.0, 0.0);
        assertEquals(true, s.isStable());
    }

    @Test
    public void testIsStableAtUpperBound() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 11.2, 0.0, 0.0);
        assertEquals(true, s.isStable());
    }

    @Test
    public void testIsStableAboveRange() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 12.0, 0.0, 0.0);
        assertEquals(false, s.isStable());
    }

    @Test
    public void testIsStableBelowRange() {
        Satellite1 s = new Satellite1(0.0, 0.0, 0.0, 5.0, 0.0, 0.0);
        assertEquals(false, s.isStable());
    }

    /**
     * TEST CASES - orbitsEarth()
     */
    @Test
    public void testOrbitsEarthTrue() {
        Satellite1 s = new Satellite1(7000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 sCopy = new Satellite1(7000.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        assertEquals(s.orbitsEarth(), sCopy.orbitsEarth());
        assertEquals(true, s.orbitsEarth());
    }

    @Test
    public void testOrbitsEarthWhenAtOrigin() {
        Satellite1 s = new Satellite1(); // position (0,0,0) => radius = 0
        assertEquals(false, s.orbitsEarth());
    }

    @Test
    public void testOrbitsEarthFalseTooClose() {
        Satellite1 s = new Satellite1(6000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 sCopy = new Satellite1(6000.0, 0.0, 0.0, 0.0, 0.0, 0.0);

        assertEquals(s.orbitsEarth(), sCopy.orbitsEarth());
        assertEquals(false, s.orbitsEarth());
    }

    @Test
    public void testOrbitsEarthAtEarthRadius() {
        Satellite1 s = new Satellite1(6371.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(false, s.orbitsEarth());
    }

    @Test
    public void testOrbitsEarthNearEarth() {
        Satellite1 s = new Satellite1(6375.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(true, s.orbitsEarth());
    }

    @Test
    public void testOrbitsEarthAtMaxRadius() {
        Satellite1 s = new Satellite1(384400.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(true, s.orbitsEarth());
    }

    @Test
    public void testOrbitsEarthInMiddle() {
        Satellite1 s = new Satellite1(20000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(true, s.orbitsEarth());
    }

    @Test
    public void testOrbitsEarthFalseTooFar() {
        Satellite1 s = new Satellite1(500000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        assertEquals(false, s.orbitsEarth());
    }

    /**
     * TEST CASES - clear()
     */
    @Test
    public void testClearRoutine() {
        Satellite1 s = new Satellite1(500.0, -200.0, 100.0, 2.0, 3.0, -1.0);
        s.clear();

        Satellite1 sCopy = new Satellite1();
        assertEquals(s, sCopy);
    }

    @Test
    public void testClearAlreadyZero() {
        Satellite1 s = new Satellite1();
        s.clear();

        Satellite1 sCopy = new Satellite1();
        assertEquals(s, sCopy);
    }

    /**
     * TEST CASES - transferFrom()
     */
    @Test
    public void testTransferFromRoutine() {
        Satellite1 source = new Satellite1(100.0, 200.0, 300.0, 1.0, 2.0, 3.0);
        Satellite1 target = new Satellite1();
        target.transferFrom(source);

        Satellite1 expectedTarget = new Satellite1(100.0, 200.0, 300.0, 1.0,
                2.0, 3.0);
        Satellite1 expectedSource = new Satellite1(); // reset

        assertEquals(expectedTarget, target);
        assertEquals(expectedSource, source);
    }

    /**
     * TEST CASES - newInstance().
     */

    @Test
    public void testNewInstanceRoutine() {
        SatelliteTracker s = new Satellite1();
        SatelliteTracker newS = s.newInstance();

        assertArrayEquals(s.getLiveLocation(), newS.getLiveLocation(), DELTA);
        assertArrayEquals(s.getVelocity(), newS.getVelocity(), DELTA);
        assertEquals(s, newS);
    }

    @Test
    public void testNewInstanceCreatesNewObject() {
        SatelliteTracker s = new Satellite1();
        SatelliteTracker newS = s.newInstance();

        // Should not be same reference
        assertEquals(false, s == newS);
    }

}