
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

public class ProjectileMotion {
    public static void main(String[] args) {
        // TEST
        assert testResults(ProjectileMotion.getProjectileMotion(4, 20),(new HashMap<>() {
            {
                put("Max Height", 0.09539);
                put("Flight Time", 0.27892);
                put("Final Position", 1.04838);
            }
        }));
        // TEST_END

        // TEST
        assert testResults(ProjectileMotion.getProjectileMotion(14, 45),(new HashMap<>() {
            {
                put("Max Height", 4.9949);
                put("Flight Time", 2.01825);
                put("Final Position", 19.97961);
            }
        }));
        // TEST_END

        // TEST
        assert testResults(ProjectileMotion.getProjectileMotion(0, 45),(new HashMap<>() {
            {
                put("Max Height", 0.0);
                put("Flight Time", 0.0);
                put("Final Position", 0.0);
            }
        }));
        // TEST_END

        // TEST
        try {
            ProjectileMotion.getProjectileMotion(-1, 0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ProjectileMotion.getProjectileMotion(3, -50);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ProjectileMotion.getProjectileMotion(5, -180);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END
    }

    final static double EARTH_GRAVITY = 9.81;

    public static Map<String, Double> getProjectileMotion(double initialVelocity, double shotAngle) {
        if (shotAngle < 0 || shotAngle > 90) {
            throw new IllegalArgumentException("Angle must be between 0 and 90 degrees");
        }

        if (initialVelocity < 0) {
            throw new IllegalArgumentException("Initial velocity cannot be less than 0");
        }

        double radiansShotAngle = Math.toRadians(shotAngle);

        double maxHeight = (initialVelocity * initialVelocity * Math.sin(radiansShotAngle) * Math.sin(radiansShotAngle))
                / (2 * EARTH_GRAVITY);
        double finalPosition = (initialVelocity * initialVelocity * Math.sin(2 * radiansShotAngle))
                / (EARTH_GRAVITY);
        double flightTime = 2 * initialVelocity * Math.sin(radiansShotAngle) / EARTH_GRAVITY;

        Map<String, Double> result = new HashMap<>() {
            {
                put("Max Height", maxHeight);
                put("Final Position", finalPosition);
                put("Flight Time", flightTime);
            }
        };
        return result;
    }


    private static boolean testResults(Map<String, Double> functionResult, Map<String, Double> expectedResult){
        for(Map.Entry<String, Double> entry : functionResult.entrySet()){
            if(Math.abs(entry.getValue() - expectedResult.get(entry.getKey())) > 1e-2){
                return false;
            }
        }
        return true;
    }
}