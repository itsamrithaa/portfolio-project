package components.satellitetracker;

public class Satellite1 extends SatelliteTrackerSecondary {
    // Representation for the object
    // Should units be metric or something larger because we are talking space? -- Need to determine
    // Position coordinates in space
    private double[] position;
    private double[] velocity; // Velocity components
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

    public final boolean orbitsEarth() {
        double[] pos = this.getLiveLocation();
        double radius = Math
                .sqrt(pos[0] * pos[0] + pos[1] * pos[1] + pos[2] * pos[2]);
        final double earthRadius = 6371.0; // in km
        final double farthestOrbitRadius = 384400.0; // approx distance to Moon (km)

        return radius > earthRadius && radius <= farthestOrbitRadius; // Satellite must be outside Earth's surface

    }
}
