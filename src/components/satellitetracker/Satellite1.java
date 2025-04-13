package components.satellitetracker;

public class Satellite1 {
    // Representation for the object
    // Should units be metric or something larger because we are talking space? -- Need to determine
    private double x, y, z; // Position coordinates in space
    private double vx, vy, vz; // Velocity components
    private static final double GRAVITY = 9.81; // Placeholder for gravitational acceleration

    /*
     * Added these three constants to help with calculation of orbital speed and
     * period
     */
    private static final double PI = 3.14159265;
    private static final double G = 6.67430e-11; //
    private static final double M = 5.972e24;

    public Satellite1(double x, double y, double z, double vx, double vy,
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

    public final double[] getVelocity() {
        return new double[] { this.vx, this.vy, this.vz };
    }

    public final boolean isStable() {

        // need to look up formula to estimate orbital speed for stability
        // and compare it to threshold to return boolean for stability
        int estimate = 0;

        // need to look up threshold
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
        double radius = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2)
                + Math.pow(this.z, 2));
        double orbitalPeriod = 2 * PI * Math.sqrt(Math.pow(radius, 3) / G * M);
        return orbitalPeriod;
    }

    public final boolean willCollide(Satellite1 other) {

        // Use distance formula for 3D -- Eucledian formula

        // Similar to isStable, estimate the distance between two satellites using formula
        // The way I set up the constructors, we can compare two satellites
        // Ex. we can use other.x or other.y to compare this.x or this.y
        double xDistance = this.x - other.x;
        double yDistance = this.y - other.y;
        double zDistance = this.z - other.z;
        double distance = Math.sqrt((xDistance * xDistance)
                + (yDistance * yDistance) + (zDistance * zDistance));

        // need to look up threshold value for collision risk
        final double threshold = 192.2;
        boolean isClose = distance < threshold;
        return isClose; // Arbitrary threshold for collision risk
    }

    public static void main(String[] args) {
        Satellite1 sat1 = new Satellite1(7000, 0, 0, 0, 7.8, 0);
        Satellite1 sat2 = new Satellite1(7005, 0, 0, 0, 7.8, 0);

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