package components.satellitetracker;

import components.standard.Standard;

public interface SatelliteTrackerKernel extends Standard<SatelliteTracker> {

    /**
     * Updates the position of {@code this} based on the duration {@code this}
     * has moved in space.
     *
     * @param time
     *            duration for which satellite has moved in days
     * @updates this
     * @requires time >= 0 and time is in seconds (s) and this.velocity is not
     *           null
     * @ensures this.position[0] = #this.position[0] + this.velocity[0] * time
     *          this.velocity[1] = #this.position[1] + this.velocity[1] * time
     *          this.velocity[2] = #this.position[2] + this.velocity[2] * time
     */
    void updatePosition(double time);

    /**
     * Reports the live location of {@code this} in x, y, z coordinates.
     *
     * @return an array of {@code double} describing the object's coordinates
     * @requires this.position is not null
     * @ensures <pre> \result[0] = this.position[0]
     * \result[1] = this.position[1]
     * \result[2] = this.position[2]
     * </pre>
     */
    double[] getLiveLocation();

    /**
     * Reports whether satellite is in stable condition based on threshold
     * defining stability.
     *
     * @return true iff {@code this} is stable
     * @requires {@code this} is a satellite of Earth
     * @ensures returns {@code true} if orbital speed is less than the threshold
     *          value, or {@code false} otherwise
     */
    boolean isStable();

    /**
     * Reports the current velocity of the satellite in x, y, z directions.
     *
     * @return an array of {@code double} values: [vx, vy, vz]
     * @requires this.velocity is not null
     * @ensures result[0] = this.velocity[0], \result[1] = this.velocity[1],
     *          \result[2] = this.velocity[2]
     */
    double[] getVelocity();

    /**
     * Reports whether this satellite is in orbit around Earth.
     *
     * @return {@code true} if this satellite orbits Earth, {@code false}
     *         otherwise
     * @ensures result = (this is gravitationally bound to Earth)
     */
    boolean orbitsEarth();

}
