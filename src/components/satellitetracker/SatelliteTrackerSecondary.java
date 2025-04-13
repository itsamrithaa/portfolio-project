package components.satellitetracker;

import java.util.Objects;

/**
 * Layered implementations of secondary methods for {@code SatelliteTracker}.
 */
public abstract class SatelliteTrackerSecondary implements SatelliteTracker {

    // Constants
    protected static final double G = 6.67430e-11; // Gravitational constant
    protected static final double M = 5.972e24;    // Mass of Earth (kg)
    protected static final double PI = Math.PI;

    /*
     * Public methods (Enhanced)
     */

    @Override
    public final void adjustVelocity(double force, double direction, double mass) {
        assert mass > 0 : "Violation of: mass > 0";

        // Use kernel method getLiveLocation() to extract velocity indirectly
        // This assumes velocity is tracked internally and modified here
        // But since we can't access vx/vy/vz directly in an interface,
        // We assume this logic is implemented in the concrete class.
        // (So this method should be overridden in concrete class if needed.)
        double acceleration = force / mass;

        double deltaVx = acceleration * Math.cos(Math.toRadians(direction));
        double deltaVy = acceleration * Math.sin(Math.toRadians(direction));

        // Update assumed velocity (concrete class must handle it)
        this.internalAdjustVelocity(deltaVx, deltaVy);
    }

    /**
     * Internal helper method to adjust velocity components.
     * Must be implemented by concrete class.
     */
    protected abstract void internalAdjustVelocity(double deltaVx, double deltaVy);

    @Override
    public final double estimateOrbitalPeriod() {
        double[] pos = this.getLiveLocation();
        double radius = Math.sqrt(pos[0] * pos[0] + pos[1] * pos[1] + pos[2] * pos[2]);
        return 2 * PI * Math.sqrt(Math.pow(radius, 3) / (G * M));
    }

    @Override
    public final boolean willCollide(SatelliteTracker other) {
        double[] a = this.getLiveLocation();
        double[] b = other.getLiveLocation();

        double dx = a[0] - b[0];
        double dy = a[1] - b[1];
        double dz = a[2] - b[2];
        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        final double threshold = 192.2;
        return distance < threshold;
    }

    /*
     * Standard methods (from Object)
     */

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SatelliteTracker)) {
            return false;
        }

        SatelliteTracker other = (SatelliteTracker) obj;

        return Objects.deepEquals(this.getLiveLocation(), other.getLiveLocation());
    }

    @Override
    public final int hashCode() {
        return Objects.hash(this.getLiveLocation());
    }

    @Override
    public final String toString() {
        double[] pos = this.getLiveLocation();
        return String.format("Satellite[x=%.2f, y=%.2f, z=%.2f]", pos[0], pos[1], pos[2]);
    }
}

