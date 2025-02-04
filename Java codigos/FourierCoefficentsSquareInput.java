
public class FourierCoefficentsSquareInput {
    public static void main(String[] args) throws Exception {

        // TEST
        assert FourierCoefficentsSquareInput.getFourierCoeficents("triangle", 5, 1,
                3).equals("2.4317Sin(2.4317t)+0.2701Sin(0.2701t)+0.0972Sin(0.0972t)");
        // TEST_END

        // TEST
        assert FourierCoefficentsSquareInput.getFourierCoeficents("square", 5, 1,
                3).equals("3.8197Sin(1t)+1.2732Sin(3t)+0.7639Sin(5t)");
        // TEST_END

        // TEST
        assert FourierCoefficentsSquareInput.getFourierCoeficents("TRIANGLE", 7, 1,
                3).equals("2.4317Sin(2.4317t)+0.2701Sin(0.2701t)+0.0972Sin(0.0972t)+0.0496Sin(0.0496t)");
        // TEST_END

    
        // TEST
        try {
            FourierCoefficentsSquareInput.getFourierCoeficents("Sin", 3, 1, 1);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            FourierCoefficentsSquareInput.getFourierCoeficents("Square", 3, -5, 1);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END


        // TEST
        try {
            FourierCoefficentsSquareInput.getFourierCoeficents("Triangle", 0, 17, 1);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END









    }

    public static String getFourierCoeficents(String signalInput, int numberOfCoefficients, int period,
            int amplitude) {

        if (signalInput == null) {
            throw new IllegalArgumentException("Input signal cannot be null");
        }

        if (period == 0 || period <0) {
            throw new IllegalArgumentException("Period must be major than 0");
        }

        if (numberOfCoefficients < 0 || numberOfCoefficients == 0) {
            throw new IllegalArgumentException("Number of coefficents must be at least 1");
        }

        if (amplitude < 0 || amplitude == 0) {
            throw new IllegalArgumentException("Amplitude cannot be 0 or negative");
        }
        String input = signalInput.toLowerCase();
        double PI_CONSTANT = 3.14159;
        int[] nValues = new int[numberOfCoefficients];
        int counter = 0;
        StringBuilder fourierSerie = new StringBuilder();
        int decimalPlaces = 4;
        double scale = Math.pow(10, decimalPlaces);
        

        if (input.equals("square")) {

            for (int i = 0; i < numberOfCoefficients; ++i) {

                nValues[i] = i + 1;

                if (nValues[i] % 2 != 0) {

                    counter += 1;
                }
            }

            int[] oddValues = new int[counter];

            int oddIndex = 0;

            for (int i = 0; i < numberOfCoefficients; ++i) {
                if (nValues[i] % 2 != 0) {
                    oddValues[oddIndex] = nValues[i];
                    oddIndex++;
                }
            }

            double[] sinCoefficents = new double[oddValues.length];

            for (int k = 0; k < oddValues.length; k++) {

                sinCoefficents[k] = (4 * amplitude) / (PI_CONSTANT * oddValues[k]);
                sinCoefficents[k] = Math.floor(sinCoefficents[k] * scale) / scale;
            }

            for (int i = 0; i < oddValues.length; i++) {

                if (i > 0) {
                    fourierSerie.append("+");
                }

                fourierSerie.append(sinCoefficents[i]).append("Sin(").append(oddValues[i]).append("t)");
            }

            System.out.println(fourierSerie.toString());

            return (fourierSerie.toString());

        } else if (input.equals("triangle")) {

            for (int i = 0; i < numberOfCoefficients; ++i) {

                nValues[i] = i + 1;

                if (nValues[i] % 2 != 0) {

                    counter += 1;
                }
            }

            int[] oddValues = new int[counter];
            double[] argumentOfSin = new double[counter];

            int oddIndex = 0;

            for (int i = 0; i <numberOfCoefficients; ++i) {
                if (nValues[i] % 2 != 0) {
                    oddValues[oddIndex] = nValues[i];
                    oddIndex++;
                }
            }


            double[] sinCoefficents = new double[oddValues.length];

            for (int k = 0; k < oddValues.length; k++) {

                sinCoefficents[k] = (8 * amplitude) / (PI_CONSTANT * PI_CONSTANT * oddValues[k] * oddValues[k]);
                sinCoefficents[k] = Math.floor(sinCoefficents[k] * scale) / scale;

            }

            for (int k = 0; k < oddValues.length; k++) {

                argumentOfSin[k] = (2 * PI_CONSTANT * oddValues[k]) / period;
                argumentOfSin[k] = Math.floor(sinCoefficents[k] * scale) / scale;
            }

            for (int i = 0; i < oddValues.length; i++) {
                if (i > 0) {
                    fourierSerie.append("+");
                }
                fourierSerie.append(sinCoefficents[i]).append("Sin(").append(argumentOfSin[i]).append("t)");
            }

            System.out.println(fourierSerie.toString());

        } else {

            throw new IllegalArgumentException("Invalid input signal");

        }

        return (fourierSerie.toString());

    }
}
