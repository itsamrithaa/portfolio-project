package components.satellitetracker;

public interface SatelliteTracker extends SatelliteTrackerKernel {

    /**
     * Updates the velocity of {@code this} based on the force applied,
     * direction and mass of {@code this} has moved in space
     *
     * @param force
     *            force applied on {@code this} in newtons
     * @param direction
     *            direction in which force is applied in degrees
     * @param mass
     *            mass of {@code this} in kilograms
     * @updates this
     * @requires mass > 0 and this.vx is initialized and this.vy is initialized
     *           and this.vz is initialized
     * @ensures this.vx = #this.vx + (force / mass) * cos(direction) this.vy =
     *          #this.vy + (force / mass) * sin(direction)
     */
    void adjustVelocity(double force, double direction, double mass);

    /**
     * Returns the estimated time it takes for {@code this} to complete a full
     * orbit
     *
     * @return the time it takes in days to orbit Earth as a {@code double}
     * @requires {@code this} orbits Earth
     * @ensures <pre> 2 * PI *
     * Math.sqrt (Math.pow (Math.sqrt (this.x * this.x +
     * this.y * this.y + this.z * this.z), 3) / (G * M))
     * </pre>
     */
    double estimateOrbitalPeriod();

    /**
     * Reports whether {@code this} will collide with {@code other} based on
     * distance threshold
     *
     * @param other
     *            the other satellite object in comparison
     * @return true iff {@code this} is at risk of collision with {@code other}
     * @requires {@code this} and {@code other} are satellites of Earth
     * @ensures returns {@code true} the distance between {@code this} and
     *          {@code other} is greater than the threshold established for
     *          collision risk, or {@code false} otherwise
     */
    boolean willCollide(Satellite1 other);

public class SatelliteTracker {
    // Representation for the object
    // Should units be metric or something larger because we are talking space? -- Need to determine
    private double x, y, z; // Position coordinates in space
    private double vx, vy, vz; // Velocity components
    private static final double GRAVITY = 9.81; // Placeholder for gravitational acceleration

    public SatelliteTracker(double x, double y, double z, double vx, double vy,
            double vz) {

        // This is the constructor for SatelliteTracker object
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public final void updatePosition(double time) {
        // Final Position = Initial position + (Velocity in that direction)*(time)
        this.x += this.vx * time;
        this.y += this.vy * time;
        this.z += this.vz * time;
    }

    public final double[] getLiveLocation() {

        // Returns live position in terms of x, y, z
        /**
         * Can later modify in the context of actual units used to describe
         * position in space, if such units exist
         */
        return new double[] { this.x, this.y, this.z };
    }

    public final boolean isStable() {

        // need to look up formula to estimate orbital speed for stability
        // and compare it to threshold to return boolean for stability
        int estimate = 0;
        int threshold = 0;
        boolean isStable = estimate > threshold;
        return isStable;
    }

    public final void adjustVelocity(double force, double direction,
            double mass) {

        // find acceleration of satellite
        double acceleration = force / mass;

        // Final Velocity = Initial velocity + Acceleration in that direction
        this.vx += acceleration * Math.cos(direction);
        this.vy += acceleration * Math.sin(direction);
    }

    public final double estimateOrbitalPeriod() {
        // need to look up formula for orbital period to estimate the satellite's orbit
        double orbitalPeriod = 1.00;
        return orbitalPeriod;
    }

    public final boolean willCollide(SatelliteTracker other) {

        // Similar to isStable, estimate the distance between two satellites using formula
        // The way I set up the constructors, we can compare two satellites
        // Ex. we can use other.x or other.y to compare this.x or this.y
        double distance = 100.00;
        // need to look up threshold value for collision risk
        final double threshold = 192.2;
        boolean isClose = distance < threshold;
        return isClose; // Arbitrary threshold for collision risk
    }

    public static void main(String[] args) {
        SatelliteTracker sat1 = new SatelliteTracker(7000, 0, 0, 0, 7.8, 0);
        SatelliteTracker sat2 = new SatelliteTracker(7005, 0, 0, 0, 7.8, 0);

        sat1.updatePosition(60);
        sat2.updatePosition(60);

        // Gets the position as arrays and converts to string
        System.out.println("Satellite 1 Position: "
                + java.util.Arrays.toString(sat1.getLiveLocation()));
        System.out.println("Satellite 2 Position: "
                + java.util.Arrays.toString(sat2.getLiveLocation()));

        // returns whether sat1 will collide with sat2
        System.out.println("It is " + sat1.willCollide(sat2)
                + " that Satellite 1 and Satellite 2 will collide");

        // returns whether sat1 and sat2 are stable
        System.out.println(
                "It is " + sat1.isStable() + " that Satellite 1 is stable");
        System.out.println(
                "It is " + sat2.isStable() + " that Satellite 2 is stable");

        System.out.println("Old Vx: " + sat1.vx + " Old Vy: " + sat1.vy);
        sat1.adjustVelocity(100, 30, 8029);
        System.out.println("New Vx: " + sat1.vx + " New Vy: " + sat1.vy);
    }
}