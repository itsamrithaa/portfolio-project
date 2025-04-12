package components.satellitetracker;

import java.util.Objects;

/**
 * Layered implementations of secondary methods for {@code SatelliteTracker}.
 */
public abstract class SatelliteTrackerSecondary implements SatelliteTracker {

    /*
     * Private members for position and velocity
     */
    protected double x, y, z;
    protected double vx, vy, vz;

    // Physical constants
    protected static final double PI = Math.PI;
    protected static final double G = 6.67430e-11; // gravitational constant
    protected static final double M = 5.972e24; // mass of Earth (kg)

    /*
     * Constructor
     */
    protected SatelliteTrackerSecondary(double x, double y, double z, double vx,
            double vy, double vz) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    /*
     * Enhanced Methods ------------------------------------------------------
     */

    @Override
    public final void adjustVelocity(double force, double direction,
            double mass) {
        assert mass > 0 : "Mass must be positive";
        double acceleration = force / mass;
        this.vx += acceleration * Math.cos(Math.toRadians(direction));
        this.vy += acceleration * Math.sin(Math.toRadians(direction));
    }

    @Override
    public final double estimateOrbitalPeriod() {
        double radius = Math
                .sqrt(this.x * this.x + this.y * this.y + this.z * this.z);
        return 2 * PI * Math.sqrt(Math.pow(radius, 3) / (G * M));
    }

    @Override
    public final boolean willCollide(SatelliteTracker other) {
        double[] otherCoords = other.getLiveLocation();
        double dx = this.x - otherCoords[0];
        double dy = this.y - otherCoords[1];
        double dz = this.z - otherCoords[2];
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);
        final double threshold = 192.2;
        return distance < threshold;
    }

    /*
     * Standard Methods ------------------------------------------------------
     */

    @Override
    public final boolean equals(Object obj) {
        boolean isEqual = true;

        if (obj == null || this.getClass() != obj.getClass()) {
            isEqual = false;
        }

        SatelliteTrackerSecondary other = (SatelliteTrackerSecondary) obj;
        if (Double.compare(this.x, other.x) == 0
                && Double.compare(this.y, other.y) == 0
                && Double.compare(this.z, other.z) == 0
                && Double.compare(this.vx, other.vx) == 0
                && Double.compare(this.vy, other.vy) == 0
                && Double.compare(this.vz, other.vz) == 0) {
            isEqual = true;
        }
        return isEqual;

    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.x, this.y, this.z, this.vx, this.vy, this.vz);
    }

    @Override
    public final String toString() {
        return String.format(
                "Satellite[x=%.2f, y=%.2f, z=%.2f, vx=%.2f, vy=%.2f, vz=%.2f]",
                this.x, this.y, this.z, this.vx, this.vy, this.vz);
    }
}
