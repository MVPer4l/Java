import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FIRFilter {
    public static void main(String[] args) {

        // TEST
        assert testResults(FIRFilter.getFilterCoefficents("HIGHPASS", 2, 100, 3), (new HashMap<>() {
            {
                put("Ideal filter coefficents", new Double[] { -0.0791, 0.92, -0.0791 });
                put("Hamming window coefficents", new Double[] { -0.00628, 0.92, -0.00628 });

            }
        }));
        // TEST_END

        // TEST
        assert testResults(FIRFilter.getFilterCoefficents("HighPass", 15, 200, 5), (new HashMap<>() {
            {
                put("Ideal filter coefficents", new Double[] { -0.15136, -0.2575, 0.7, -0.2575, -0.15136 });
                put("Hamming window coefficents", new Double[] { -0.0121, -0.1390, 0.7, -0.1390, -0.0121 });

            }
        }));
        // TEST_END

        // TEST
        assert testResults(FIRFilter.getFilterCoefficents("lowpass", 15, 200, 5), (new HashMap<>() {
            {
                put("Ideal filter coefficents", new Double[] { 0.15136, 0.2575, 0.3, 0.2575, 0.15136 });
                put("Hamming window coefficents", new Double[] { 0.0121, 0.1390, 0.3, 0.1390, 0.0121 });

            }
        }));
        // TEST_END

        // TEST
        assert testResults(FIRFilter.getFilterCoefficents("LOWPASS", 350, 1000, 4), (new HashMap<>() {
            {
                put("Ideal filter coefficents", new Double[] { 0.0665, 0.51503, 0.51503, 0.0655 });
                put("Hamming window coefficents", new Double[] { 0.00524, 0.39657, 0.39657, 0.00524 });

            }
        }));
        // TEST_END

        // TEST
        try {
            FIRFilter.getFilterCoefficents(null, 5, 70, 5);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            FIRFilter.getFilterCoefficents("lowpass", 500, 10, 3);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            FIRFilter.getFilterCoefficents("lowpassADSsafs", 50, 170, 3);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END


        // TEST
        try {
            FIRFilter.getFilterCoefficents("highpass", 0, 170, 3);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            FIRFilter.getFilterCoefficents("highpass", 10, 170, 0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            FIRFilter.getFilterCoefficents("highpass", 35, 10000, 0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

    }

    public static Map<String, Double[]> getFilterCoefficents(String filter, double cutOffFreq, double sampleRate,
            int order) {

        if (filter == null) {
            throw new IllegalArgumentException("Filter type cannot be null");
        }

        if (order <= 0) {
            throw new IllegalArgumentException("Filter order must be greater than 0");
        }

        if (cutOffFreq <= 0) {
            throw new IllegalArgumentException("CutOff Frequency must be greater than 0");
        }

        if (sampleRate <= 0) {
            throw new IllegalArgumentException("Filter order must be greater than 0");
        }

        if (sampleRate < cutOffFreq) {
            throw new IllegalArgumentException("CutOff frequency cannot be major than Sample rate");
        }

        String filterType = filter.toLowerCase();
        if (!filterType.equals("lowpass") && !filterType.equals("highpass")) {
            throw new IllegalArgumentException("Invalid type of filter");
        }

        double PI_CONSTANT = 3.14159;
        Double[] idealFilterCoefficents = new Double[order];
        Double[] hammingWindow = new Double[order];
        Double[] nVector = new Double[order];
        double normalizedCutOff = (cutOffFreq) / (sampleRate / 2);
        double filterIndex = (order - 1.0) / 2.0;
        for (int i = 0; i < order; i++) {
            nVector[i] = i + 0.000;
        }

        switch (filterType) {
            case "lowpass":
                for (int n = 0; n < order; n++) {
                    if (n == filterIndex) {
                        idealFilterCoefficents[n] = 2 * normalizedCutOff;
                    } else {
                        double argAux = n - filterIndex;
                        double angle = 2 * PI_CONSTANT * normalizedCutOff * argAux;
                        double numerator = Math.sin(angle);
                        double denominator = PI_CONSTANT * (n - filterIndex);
                        idealFilterCoefficents[n] = numerator / denominator;
                    }
                }
                break;

            case "highpass":
                for (int n = 0; n < order; n++) {
                    if (n == filterIndex) {
                        idealFilterCoefficents[n] = 1 - 2 * normalizedCutOff;
                    } else {
                        double argAux = n - filterIndex;
                        double angle = 2 * PI_CONSTANT * normalizedCutOff * argAux;
                        double numerator = -1 * Math.sin(angle);
                        double denominator = PI_CONSTANT * (n - filterIndex);
                        idealFilterCoefficents[n] = numerator / denominator;
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Invalid filter");
        }

        for (int n = 0; n < order; n++) {
            hammingWindow[n] = idealFilterCoefficents[n]
                    * (0.54 - 0.46 * Math.cos(PI_CONSTANT * n / filterIndex));
        }

        Map<String, Double[]> filterResults = new HashMap<>() {
            {
                put("Ideal filter coefficents", idealFilterCoefficents);
                put("Hamming window coefficents", hammingWindow);
            }
        };

        System.out.println(Arrays.toString(idealFilterCoefficents));
        System.out.println(Arrays.toString(hammingWindow));

        return filterResults;

    }

    private static boolean testResults(Map<String, Double[]> functionResult, Map<String, Double[]> expectedResult) {
        for (Map.Entry<String, Double[]> entry : functionResult.entrySet()) {

            Double[] functionArray = entry.getValue();
            Double[] expectedArray = expectedResult.get(entry.getKey());

            for (int i = 0; i < functionArray.length; i++) {
                if (Math.abs(functionArray[i] - expectedArray[i]) > 1e-2) {
                    return false;
                }
            }
        }
        return true;
    }

}
