package components.satellitetracker;

/**
 * {@code SatelliteTracker} represented as two 3-element arrays of doubles: one
 * for position [x, y, z] and one for velocity [vx, vy, vz].
 *
 * @convention |position| = 3 and |velocity| = 3 and elements must be finite
 *             real numbers. this.position[i] is in kilometers (km),
 *             this.velocity[i] is in kilometers per second (km/s).
 * @correspondence this = (position = [x, y, z], velocity = [vx, vy, vz])
 */

public class Satellite1 extends SatelliteTrackerSecondary {

    /*
     * Private members --------------------------------------------------------
     */

    /*
     * Added these constants to help with calculation of orbital speed and
     * period
     */
    private static final double PI = 3.14159265;
    private static final double G = 6.67430e-11; //
    private static final double M = 5.972e24;
    private static final double GRAVITY = 9.81;
    private static final double EARTH_RADIUS_KM = 6371.0;
    private static final double MAX_ORBIT_RADIUS_KM = 384400.0;

    /**
     * Representation of {@code this}.
     */

    private double[] position; // 3D Cartesian Coordinates
    private double[] velocity; // Velocity components

    /**
     * Creator of initial representation.
     */

    /**
     * Creator of initial representation.
     */
    private void createNewRep() {
        this.position = new double[] { 0.0, 0.0, 0.0 };
        this.velocity = new double[] { 0.0, 0.0, 0.0 };
    }

    /**
     * @param x
     *            initial x coordinate
     *
     * @param y
     *            initial y coordinate
     *
     * @param z
     *            initial z coordinate
     *
     * @param vx
     *            initial velocity in x direction
     *
     * @param vy
     *            initial velocity in y direction
     *
     * @param vz
     *            initial velocity in z direction
     */
    private void createNewRep(double x, double y, double z, double vx,
            double vy, double vz) {
        this.position = new double[] { x, y, z };
        this.velocity = new double[] { vx, vy, vz };
    }

    /*
     * Constructors -----------------------------------------------------------
     */

    /**
     * No-argument constructor.
     */
    public Satellite1() {
        this.createNewRep();
    }

    /**
     * Constructor with parameters for position and velocity.
     */
    public Satellite1(double x, double y, double z, double vx, double vy,
            double vz) {
        this.createNewRep(x, y, z, vx, vy, vz);
    }

    /*
     * Standard methods -------------------------------------------------------
     */

    @Override
    public final SatelliteTracker newInstance() {
        try {
            return this.getClass().getConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            throw new AssertionError(
                    "Cannot construct object of type " + this.getClass());
        }
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    /**
     * @param source
     *            another satellite to be copied from
     */
    @Override
    public final void transferFrom(SatelliteTracker source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Satellite1 : ""
                + "Violation of: source is of dynamic type Satellite1";
        /*
         * This cast cannot fail since the assert above would have stopped
         * execution in that case.
         */
        Satellite1 localSource = (Satellite1) source;
        this.position = localSource.position;
        this.velocity = localSource.velocity;
        localSource.createNewRep();
    }

    /**
     *
     * @param time
     *            the other satellite object in comparison
     */
    public final void updatePosition(double time) {
        assert time >= 0 : "Violation of: time >= 0";
        assert this.velocity != null
                && this.velocity.length == 3 : "Violation of: this.velocity is not null";

        // Final Position = Initial position + (Velocity in that direction)*(time)
        this.position[0] += this.velocity[0] * time;
        this.position[1] += this.velocity[1] * time;
        this.position[2] += this.velocity[2] * time;
    }

    public final double[] getLiveLocation() {
        assert this.position != null
                && this.position.length == 3 : "Violation of: this.position is not null";

        /**
         * Returns live position in terms of x, y, z which are coordinates
         * actually used to represent objects with Earth as Frame of Reference
         * space science
         */
        return new double[] { this.position[0], this.position[1],
                this.position[2] };
    }

    public final double[] getVelocity() {
        assert this.velocity != null
                && this.velocity.length == 3 : "Violation of: this.velocity is not null";
        return new double[] { this.velocity[0], this.velocity[1],
                this.velocity[2] };
    }

    public final boolean isStable() {

        boolean isStable = false;

        // Estimate speed of the satellite in km/s
        double speed = Math.sqrt(this.velocity[0] * this.velocity[0]
                + this.velocity[1] * this.velocity[1]
                + this.velocity[2] * this.velocity[2]);

        if (speed >= 7.8 && speed <= 11.2) {
            isStable = true;
            // Rough LEO to escape velocity range (km/s)
        }
        return isStable;
    }

    public final boolean orbitsEarth() {

        boolean isWithinOrbit = false;

        // Radius is the distance between Earth and satellite
        double radius = Math.sqrt(this.position[0] * this.position[0]
                + this.position[1] * this.position[1]
                + this.position[2] * this.position[2]);

        /**
         * Satellite orbits Earth if radius is greater than Earth's LEO region
         * but less than or equal to the farthest satellite i.e. the Moon
         */
        if (radius > EARTH_RADIUS_KM && radius <= MAX_ORBIT_RADIUS_KM) {
            isWithinOrbit = true;
        }
        return isWithinOrbit;

    }
}
