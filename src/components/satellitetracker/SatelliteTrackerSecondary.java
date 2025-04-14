package components.satellitetracker;

<<<<<<< Updated upstream
import java.util.Objects;

=======
>>>>>>> Stashed changes
/**
 * Layered implementations of secondary methods for {@code SatelliteTracker}.
 */
public abstract class SatelliteTrackerSecondary implements SatelliteTracker {

    // Constants
    protected static final double G = 6.67430e-11; // Gravitational constant
<<<<<<< Updated upstream
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

=======
    protected static final double M = 5.972e24; // Mass of Earth (kg)
    protected static final double PI = Math.PI;

    /*
     * Common methods (from Object)
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public final boolean equals(Object obj) {
        boolean isEqual = false;
        if (this == obj) {
            isEqual = true;
        }

        SatelliteTracker objCasted = (SatelliteTracker) obj;

        if (!(objCasted instanceof SatelliteTracker)
                && (this.getLiveLocation() != objCasted.getLiveLocation())
                && (this.getVelocity() != objCasted.getVelocity())
                && !objCasted.orbitsEarth()) {
            isEqual = false;
        }
        return isEqual;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public int hashCode() {
        final int sample = 3;
        final int a = 59;
        final int b = 17;
        int result = 0;
        int n = 0;
        double[] velocity = this.getVelocity();

        while (n < sample && n < velocity.length) {
            double v = velocity[n];
            int vHash = (int) (v * 1000); // scale
            result = a * result + b * vHash;
            n++;
        }

        return result;
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public final String toString() {
        double[] pos = this.getLiveLocation();
        double[] vel = this.getVelocity();
        StringBuilder returnStr = new StringBuilder();
        returnStr.append("<Position: ");
        returnStr.append(pos[0]).append(", ").append(pos[1]).append(", ")
                .append(pos[2]);
        returnStr.append(" | Velocity: ");
        returnStr.append(vel[0]).append(", ").append(vel[1]).append(", ")
                .append(vel[2]);
        returnStr.append(">");

        return returnStr.toString();
    }

    /*
     * Other non-kernel methods -----------------------------------------------
     */

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public final void adjustVelocity(double force, double direction,
            double mass) {
        assert mass > 0 : "Violation of: mass > 0";
        assert this.getVelocity() != null : "Violation of: this is initialized";

        // Use kernel method getVelocity to extract velocity indirectly

        double[] velocity = this.getVelocity();
        double vx = velocity[0];
        double vy = velocity[1];
        double acceleration = force / mass;
        vx += acceleration * Math.cos(Math.toRadians(direction));
        vy = acceleration * Math.sin(Math.toRadians(direction));

    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
    @Override
    public final double estimateOrbitalPeriod() {
        double[] pos = this.getLiveLocation();
        double radius = Math
                .sqrt(pos[0] * pos[0] + pos[1] * pos[1] + pos[2] * pos[2]);
        return 2 * PI * Math.sqrt(Math.pow(radius, 3) / (G * M));
    }

    // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
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

=======
}
>>>>>>> Stashed changes
