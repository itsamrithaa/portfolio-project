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
     * @requires mass > 0 and mass is in kilograms (kg) and force is in newtons
     *           (kg*m/s) and direction is in degrees and this.velocity is not
     *           null
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
    boolean willCollide(SatelliteTracker other);

}
