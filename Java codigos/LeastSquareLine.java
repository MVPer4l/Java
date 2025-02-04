
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

public class LeastSquareLine {

    public static void main(String[] args) {

        // TEST
        assert testResults(LeastSquareLine.getLeastSquareLine(new double[] { 4, 3.1, 3, 6, 5, 8, 10, 7, 5 },
                new double[] { 8, 6, 6, 12, 10, 14, 18, 13, 9 }), (new HashMap<>() {
                    {
                        put("Slope", 1.691371908);
                        put("Y_intercept", 1.063432836);
                    }
                }));
        // TEST_END

        // TEST
        assert testResults(LeastSquareLine.getLeastSquareLine(new double[] { 1, 10, 9, 5, 4, 3 },
                new double[] { 1, 10, 9, 5, 4, 3 }), (new HashMap<>() {
                    {
                        put("Slope", 1.0);
                        put("Y_intercept", 0.0);
                    }
                }));
        // TEST_END

        // TEST
        assert testResults(LeastSquareLine.getLeastSquareLine(new double[] { 6, 10, 4, 5, 11, 12, 22, 4, 15, 21, 18 },
                new double[] { 3, 4, 1, 1.8, 4.5, 5, 11, 0.9, 6.3, 9.1, 10 }), (new HashMap<>() {
                    {
                        put("Slope", 0.528858351);
                        put("Y_intercept", -0.987843552);
                    }
                }));
        // TEST_END

        //TEST
        try {
            LeastSquareLine.getLeastSquareLine(new double[] { 1, 10, 9, 5, 4, 3,6,5,6 }, new double[] { 1, 10, 9, 5, 4, 3,7,9,1,2,354,7 });
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        //TEST
        try {
            LeastSquareLine.getLeastSquareLine(new double[] { 1, 10, 9, 5, 4, 3 }, new double[] { 1, 10, 9, 5, 4, 3,7,9,1,2,354,7 });
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END




    }

    public static Map<String, Double> getLeastSquareLine(double[] X_Points, double[] Y_Points) {

        if (X_Points == null || Y_Points == null) {

            throw new IllegalArgumentException("X and Y points cannot be nulls");
        }

        if (X_Points.length < Y_Points.length || X_Points.length > Y_Points.length) {

            throw new IllegalArgumentException("The vector of points must have same length");
        }

        int NumberOfPoints = X_Points.length;
        double SumOfX = 0;
        double SumOfY = 0;
        double SumOfXY = 0;
        double SumOfXX = 0;
        double Slope;
        double Y_intercept;

        SumOfX = Arrays.stream(X_Points).sum();
        SumOfY = Arrays.stream(Y_Points).sum();

        for (int i = 0; i < NumberOfPoints; i++) {

            SumOfXY += X_Points[i] * Y_Points[i];
            SumOfXX += X_Points[i] * X_Points[i];

        }

        double NumeratorOfSlope = NumberOfPoints * SumOfXY - SumOfX * SumOfY;
        double DenominatorOfSlope = NumberOfPoints * SumOfXX - (SumOfX * SumOfX);

        double NumeratorOfY_intercept = SumOfY * SumOfXX - SumOfX * SumOfXY;
        double DenominatorOfY_intercept = DenominatorOfSlope;

        Slope = NumeratorOfSlope / DenominatorOfSlope;
        Y_intercept = NumeratorOfY_intercept / DenominatorOfY_intercept;

        Map<String, Double> result = new HashMap<>() {
            {
                put("Slope", Slope);
                put("Y_intercept", Y_intercept);
            }
        };

        System.out.println(Slope);
        System.out.println(Y_intercept);
        return result;

    }

    private static boolean testResults(Map<String, Double> functionResult, Map<String, Double> expectedResult) {
        for (Map.Entry<String, Double> entry : functionResult.entrySet()) {
            if (Math.abs(entry.getValue() - expectedResult.get(entry.getKey())) > 1e-2) {
                return false;
            }
        }
        return true;
    }

}