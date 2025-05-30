package components.satellitetracker;

/**
 * Layered implementations of secondary methods for {@code SatelliteTracker}.
 */
public abstract class SatelliteTrackerSecondary implements SatelliteTracker {

        /**
         * Constants.
         */
        protected static final double G = 6.67430e-11; // Gravitational constant.
        protected static final double M = 5.972e24; // Mass of Earth (kg).
        protected static final double PI = Math.PI;
        protected static final double SECONDS_IN_DAY = 86400.0;

        /*
         * Common methods (from Object)
         */

        // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
        @Override
        public final boolean equals(Object obj) {
                // Contract precondition from kernel
                assert this.getLiveLocation() != null : "Violation of: this.x,"
                                + " this.y, this.z are initialized.";

                assert this.getVelocity() != null : "Violation of: this.vx"
                                + " and this.vy are initialized.";

                boolean isEqual = true;

                if (this == obj) {
                        isEqual = true;
                }

                if (!(obj instanceof SatelliteTracker)) {
                        isEqual = false;
                }

                SatelliteTracker objCasted = (SatelliteTracker) obj;

                double[] pos1 = this.getLiveLocation();
                double[] vel1 = this.getVelocity();
                double[] pos2 = objCasted.getLiveLocation();
                double[] vel2 = objCasted.getVelocity();

                for (int i = 0; i < pos1.length; i++) {
                        if (pos1[i] != pos2[i]) {
                                isEqual = false;
                        }
                }

                for (int i = 0; i < vel1.length; i++) {
                        if (vel1[i] != vel2[i]) {
                                isEqual = false;
                        }
                }

                return isEqual;
        }

        // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
        @Override
        public int hashCode() {
                assert this.getVelocity() != null : "Violation of: this.vx and this.vy are initialized.";

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
                assert this.getLiveLocation() != null : "Violation of: this.x, this.y, this.z are initialized.";
                assert this.getVelocity() != null : "Violation of: this.vx, this.vy, this.vz are initialized.";

                double[] pos = this.getLiveLocation();
                double[] vel = this.getVelocity();
                StringBuilder returnStr = new StringBuilder();
                returnStr.append("((");
                returnStr.append(pos[0]).append(", ").append(pos[1])
                                .append(", ").append(pos[2]);
                returnStr.append(")(");
                returnStr.append(vel[0]).append(", ").append(vel[1])
                                .append(", ").append(vel[2]);
                returnStr.append("))");

                return returnStr.toString();
        }

        /*
         * Other non-kernel methods
         * -----------------------------------------------
         */

        // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
        @Override
        public void adjustVelocity(double force, double direction,
                        double mass) {
                assert mass > 0 : "Violation of: mass > 0";
                assert this.getVelocity() != null : "Violation of: this is initialized";

                // Implemented in concrete class

        }

        // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
        @Override
        public final double estimateOrbitalPeriod() {
                assert this.orbitsEarth() != false : "Violation of: this orbits Earth";
                assert this.getLiveLocation() != null : "Violation of: this.x, this.y, this.z are initialized.";

                double[] pos = this.getLiveLocation();
                double radius = Math.sqrt(pos[0] * pos[0] + pos[1] * pos[1]
                                + pos[2] * pos[2]);
                return (2 * Math.PI * Math
                                .sqrt(Math.pow(radius * 1000.0, 3) / (G * M)))
                                / SECONDS_IN_DAY;
        }

        // CHECKSTYLE: ALLOW THIS METHOD TO BE OVERRIDDEN
        @Override
        public final boolean willCollide(SatelliteTracker other) {
                // Check call to pre-condition follows contract
                assert this.orbitsEarth() != false || other
                                .orbitsEarth() != false : "Violation of: this and other are satellites of Earth";
                assert this.getLiveLocation() != null : "Violation of: this.x, this.y, this.z are initialized.";
                if (!this.orbitsEarth() || !other.orbitsEarth()) {
                        throw new IllegalArgumentException(
                                        "Both satellites must be orbiting Earth.");
                }

                double[] a = this.getLiveLocation();
                double[] b = other.getLiveLocation();

                double dx = a[0] - b[0];
                double dy = a[1] - b[1];
                double dz = a[2] - b[2];
                double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

                final double threshold = 500;
                return distance < threshold;
        }

}
