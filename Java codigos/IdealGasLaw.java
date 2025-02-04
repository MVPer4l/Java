import java.util.HashMap;
import java.util.Map;

public class IdealGasLaw {

    public static void main(String[] args) {

        // TEST
        assert testResults(IdealGasLaw.getIdealGasValues(5.0, null, 2.0, 1.0, 400.00), (new HashMap<>() {
            {
                put("Pressure", 5.0);
                put("Volume", 13.136);
                put("Gas mass", 2.0);
                put("Gas molar mass", 1.0);
                put("Number of moles", 2.0);
                put("Temperature", 400.0);
            }
        }));
        // TEST_END

        // TEST
        assert testResults(IdealGasLaw.getIdealGasValues(null, 2.00, 1.0, 2.0, 300.00), (new HashMap<>() {
            {
                put("Pressure", 6.1575);
                put("Volume", 2.0);
                put("Gas mass", 1.0);
                put("Gas molar mass", 2.0);
                put("Number of moles", 0.5);
                put("Temperature", 300.0);
            }
        }));
        // TEST_END

        // TEST
        assert testResults(IdealGasLaw.getIdealGasValues(1.0, 1.00, 3.0, null, 273.00), (new HashMap<>() {
            {
                put("Pressure", 1.0);
                put("Volume", 1.0);
                put("Gas mass", 3.0);
                put("Gas molar mass", 67.239);
                put("Number of moles", 0.0446);
                put("Temperature", 273.0);
            }
        }));
        // TEST_END

        // TEST
        try {
            IdealGasLaw.getIdealGasValues(1.0, 1.0, 1.0, 1.0, 1.0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            IdealGasLaw.getIdealGasValues(1.0, 1.0, 1.0, 0.0, null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            IdealGasLaw.getIdealGasValues(null, null, 1.0, 1.0, 1.0);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

    }

    public static Map<String, Double> getIdealGasValues(Double pressure, Double volume, Double gasMass,
            Double molarMass, Double temperature) {

        int nullCount = 0;

        if (pressure == null)
            nullCount++;
        if (volume == null)
            nullCount++;
        if (gasMass == null)
            nullCount++;
        if (molarMass == null)
            nullCount++;
        if (temperature == null)
            nullCount++;
        if (nullCount != 1) {
            throw new IllegalArgumentException("The function must have exactly one variable as null");
        }

        if (temperature != null && (temperature == 0 || temperature < 0)) {
            throw new IllegalArgumentException("Temperature must be in Kelvin units and cannot be 0 K");
        }

        if (gasMass != null && (gasMass == 0 || gasMass < 0)) {
            throw new IllegalArgumentException("Gas mass cannot be equals and less than 0");
        }

        if (molarMass != null && (molarMass == 0 || molarMass < 0)) {
            throw new IllegalArgumentException("Molar mass cannot be equals and less than 0");
        }

        double gasConstant = 0.0821;
        double numberOfMoles = 0;
        Double pressureCalc = pressure;
        Double volumeCalc = volume;
        Double temperatureCalc = temperature;
        Double gasMassCalc = gasMass;
        Double molarMassCalc = molarMass;

        if (gasMass != null && molarMass != null) {
            numberOfMoles = gasMass / molarMass;
        }

        if (pressure == null) {
            pressureCalc = (numberOfMoles * gasConstant * temperature) / volume;
        } else if (volume == null) {
            volumeCalc = (numberOfMoles * gasConstant * temperature) / pressure;
        } else if (temperature == null) {
            temperatureCalc = (pressure * volume) / (numberOfMoles * gasConstant);
        } else if (gasMass == null) {
            gasMassCalc = (pressure * volume * molarMass) / (gasConstant);
            numberOfMoles = gasMassCalc / molarMass;
        } else if (molarMass == null) {
            molarMassCalc = (gasMass * gasConstant * temperature) / (pressure * volume);
            numberOfMoles = gasMass / molarMassCalc;
        } else {
            throw new IllegalArgumentException("Invalid input");
        }

        double finalPressure = pressureCalc;
        double finalVolume = volumeCalc;
        double finalGasMass = gasMassCalc;
        double finalMolarMass = molarMassCalc;
        double finalNumberOfMoles = numberOfMoles;
        double finalTemperature = temperatureCalc;

        System.out.println(finalPressure);
        System.out.println(finalVolume);
        System.out.println(finalGasMass);
        System.out.println(finalMolarMass);
        System.out.println(finalNumberOfMoles);
        System.out.println(finalTemperature);

        Map<String, Double> idealGasValues = new HashMap<>() {
            {
                put("Pressure", finalPressure);
                put("Volume", finalVolume);
                put("Gas mass", finalGasMass);
                put("Gas molar mass", finalMolarMass);
                put("Number of moles", finalNumberOfMoles);
                put("Temperature", finalTemperature);

            }
        };
        return idealGasValues;

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
