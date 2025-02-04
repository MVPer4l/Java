import java.util.Arrays;

public class Freefall {

    public static void main(String[] args) {

        // TEST

        assert Math.abs(Freefall.getFreefall(4, 20)[0] - 2042) < 1e-2;
        assert Math.abs(Freefall.getFreefall(4, 20)[1] - 200.2) < 1e-2;

        // TEST_END

        // TEST

        assert Math.abs(Freefall.getFreefall(-1, 10)[0] - 480.5) < 1e-2;
        assert Math.abs(Freefall.getFreefall(-1, 10)[1] - 97.1) < 1e-2;

        // TEST_END

        // TEST

        assert Math.abs(Freefall.getFreefall(4, 5)[0] - 142.625) < 1e-2;
        assert Math.abs(Freefall.getFreefall(4, 5)[1] - 53.05) < 1e-2;

        // TEST_END

        // TEST
        try {
            Freefall.getFreefall(5, -5);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            Freefall.getFreefall(2, -10);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END
    }

    public static double[] getFreefall(double initialvelocity, double timeoffall) {

        if (timeoffall <= 0) {
            throw new IllegalArgumentException("Time of fall must be major than 0");

        }

        double Gravityconstant = 9.81;
        double distance;
        double finalvelocity;
        double[] results = new double[] { 0, 0 };

        distance = initialvelocity * timeoffall + (Gravityconstant * timeoffall * timeoffall) / 2;

        finalvelocity = initialvelocity + Gravityconstant * timeoffall;

        results[0] = distance;
        results[1] = finalvelocity;

        System.out.println(Arrays.toString(results));

        return results;

    }

}
