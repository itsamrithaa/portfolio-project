import components.satellitetracker.Satellite1;

public class CollisionTracker {

    private static void printArray(double[] arr) {
        System.out.printf("[%.2f, %.2f, %.2f]%n", arr[0], arr[1], arr[2]);
    }

    public static void main(String[] args) {
        // Two satellites in nearby orbits
        Satellite1 satA = new Satellite1(8000.0, 0.0, 0.0, 0.0, 0.0, 0.0);
        Satellite1 satB = new Satellite1(8000.0, 300.0, 300.0, 0.0, 0.0, 0.0);

        System.out.println("Satellite A Position:");
        printArray(satA.getLiveLocation());

        System.out.println("Satellite B Position:");
        printArray(satB.getLiveLocation());

        boolean collisionRisk = satA.willCollide(satB);
        System.out.println("Collision Risk Detected: " + collisionRisk);
    }

}
