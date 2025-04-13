package components.satellitetracker;

import components.standard.Standard;

public interface SatelliteTrackerKernel extends Standard<Satellite1> {

    /**
     * Updates the position of {@code this} based on the duration {@code this}
     * has moved in space
     *
     * @param time
     *            duration for which satellite has moved in days
     * @updates this
     * @requires time >= 0 and this.vx is initialized and this.vy is initialized
     *           and this.vz is initialized
     * @ensures this.x = #this.x + this.vx * time this.y = #this.y + this.vy *
     *          time this.z = #this.z + this.vz * time
     */
    void updatePosition(double time);

    /**
     * Reports the live location of {@code this} in x, y, z coordinates
     *
     * @return an array of {@code double} describing the object's coordinates
     * @requires this.x is initialized and this.y is initialized and this.z is
     *           initialized
     * @ensures <pre> \result[0] = this.x
     * \result[1] = this.y
     * \result[2] = this.z
     * </pre>
     */
    double[] getLiveLocation();

    /**
     * Reports whether satellite is in stable condition based on threshold
     * defining stability
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
     * @requires this.vx, this.vy, and this.vz are initialized
     * @ensures result[0] = vx, \result[1] = vy, \result[2] = vz
     */
    double[] getVelocity();
}