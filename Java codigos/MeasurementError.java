import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MeasurementError {

    public static void main(String[] args) {

        // TEST
        assert testResults(MeasurementError.getMeasurementError(new double[] { 1.0, 2.0, 3, 4, 5, 6, 7, 8, 9, 10 },
                (new double[] { 1.0, 2.0, 3, 4, 5, 6, 7, 8, 9, 10 })), (new HashMap<>() {
                    {
                        put("Mean Absolut Error", 0.0);
                        put("Mean Relative Error", 0.0);
                        put("Mean Porcentual Error", 0.0);
                        put("Mean Square Error", 0.0);
                        put("Root Of Mean Square Error", 0.0);
                    }
                }));
        // TEST_END

        // TEST
        assert testResults(MeasurementError.getMeasurementError(new double[] { 1.0, 2.0, 3, 4, 5, 6, 7, 8, 9, 10 },
                (new double[] { 0.99, 1.97, 3.2, 3.98, 5.01, 6.2, 7.1, 7.99, 8.97, 9.89 })), (new HashMap<>() {
                    {
                        put("Mean Absolut Error", 0.072);
                        put("Mean Relative Error", 0.016186905);
                        put("Mean Porcentual Error", 1.618690476);
                        put("Mean Square Error", 0.01046);
                        put("Root Of Mean Square Error", 0.102274141);
                    }
                }));
        // TEST_END

        // TEST
        assert testResults(
                MeasurementError.getMeasurementError(new double[] { -3.5, -2.8, -1.5, 0, 0.5, 1.7, 2.55, 3.3, 4.5, 10 },
                        (new double[] { -3.27, -2.69, -1.7, 0.222, 0.44, 1.66, 2.33, 3.33, 4.29, 9.81 })),
                (new HashMap<>() {
                    {
                        put("Mean Absolut Error", 0.1512);
                        put("Mean Relative Error", 0.054289483);
                        put("Mean Porcentual Error", 5.428948307);
                        put("Mean Square Error", 0.02889);
                        put("Root Of Mean Square Error", 0.169995294);
                    }
                }));
        // TEST_END

        // TEST
        try {
            MeasurementError.getMeasurementError(null, null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END
        try {
            MeasurementError.getMeasurementError(null, new double[] {1,2,3,4});
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST_END
        try {
            MeasurementError.getMeasurementError(new double[] {1}, new double[] {1,2,3,4});
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END
    }

    public static Map<String, Double> getMeasurementError(double[] PredictedValue, double[] ObservedValue) {

        if (PredictedValue == null || ObservedValue == null) {

            throw new IllegalArgumentException("Number of values must be same");

        }

        if (PredictedValue.length != ObservedValue.length) {

            throw new IllegalArgumentException("Number of Measurements must be same");

        }

        int NumberOfMeasurements = PredictedValue.length;
        double[] DiferenceBetweenValues = new double[NumberOfMeasurements];
        double[] AbsoluteDiferenceBetweenValues = new double[NumberOfMeasurements];
        double[] SquareDiferenceBetweenValues = new double[NumberOfMeasurements];
        double[] AbsolutPredictedValue = new double[NumberOfMeasurements];
        double MeanAbsoluteError;
        double AUXMeanRelativeError = 0;
        double MeanRelativeError;
        double MeanPorcentualError;
        double MeanSquareError;
        double RootOfMeanSquareError;
        double SumOfSquareDifference = 0;
        double SumOfMeanAbsoluteError = 0;

        for (int i = 0; i < NumberOfMeasurements; i++) {
            AbsolutPredictedValue[i] = Math.abs(PredictedValue[i]);
            DiferenceBetweenValues[i] = (ObservedValue[i] - PredictedValue[i]);
            SquareDiferenceBetweenValues[i] = DiferenceBetweenValues[i] * DiferenceBetweenValues[i];
            AbsoluteDiferenceBetweenValues[i] = Math.abs(DiferenceBetweenValues[i]);

            if (PredictedValue[i] == 0) {
                AUXMeanRelativeError += 0;
            } else {
                AUXMeanRelativeError += (AbsoluteDiferenceBetweenValues[i] / AbsolutPredictedValue[i]);
            }
        }

        SumOfMeanAbsoluteError = (Arrays.stream(AbsoluteDiferenceBetweenValues).sum());
        MeanRelativeError = AUXMeanRelativeError / NumberOfMeasurements;
        MeanPorcentualError = MeanRelativeError * 100;
        SumOfSquareDifference = Arrays.stream(SquareDiferenceBetweenValues).sum();
        MeanSquareError = SumOfSquareDifference / NumberOfMeasurements;
        RootOfMeanSquareError = Math.sqrt(MeanSquareError);
        MeanAbsoluteError = SumOfMeanAbsoluteError / NumberOfMeasurements;

        Map<String, Double> Errors = new HashMap<>() {
            {
                put("Mean Absolut Error", MeanAbsoluteError);
                put("Mean Relative Error", MeanRelativeError);
                put("Mean Porcentual Error", MeanPorcentualError);
                put("Mean Square Error", MeanSquareError);
                put("Root Of Mean Square Error", RootOfMeanSquareError);
            }
        };
        System.out.println(MeanAbsoluteError);
        System.out.println(MeanRelativeError);
        System.out.println(MeanPorcentualError);
        System.out.println(MeanSquareError);
        System.out.println(RootOfMeanSquareError);
        System.out.println("------------");
        return Errors;

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
