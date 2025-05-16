import components.satellitetracker.Satellite1;

public class OrbitalSimulation {

    private static void printArray(double[] arr) {
        System.out.printf("[%.2f, %.2f, %.2f]%n", arr[0], arr[1], arr[2]);
    }

    public static void main(String[] args) {
        // Simulate a satellite in Low Earth Orbit
        Satellite1 satellite = new Satellite1(7000.0, 0.0, 0.0, 7.8, 0.0, 0.0);

        System.out.println("Initial Position:");
        printArray(satellite.getLiveLocation());

        System.out.println("Initial Velocity:");
        printArray(satellite.getVelocity());

        // Update position after 60 seconds
        satellite.updatePosition(60);

        System.out.println("Updated Position (after 60 seconds):");
        printArray(satellite.getLiveLocation());

        // Check orbital conditions
        System.out.println("Is the satellite stable? " + satellite.isStable());
        System.out.println(
                "Is the satellite orbiting Earth? " + satellite.orbitsEarth());

        // Estimate orbital period
        double periodInDays = satellite.estimateOrbitalPeriod();
        System.out.printf("Estimated Orbital Period: %.4f days%n",
                periodInDays);
    }
}
