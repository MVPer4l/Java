import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LowpassFilterRC {
    public static void main(String[] args) {

        // TEST
        assert testResults(LowpassFilterRC.getRCValues("1u", 100, 5), (new HashMap<>() {
            {
                put("Resistance", 1591.550);
                put("Time constant", 0.00159);
                put("Stable time", 0.007957);
                put("Voltaje in stable time", 4.9663);
                ;
            }
        }));
        // TEST_END

        // TEST
        assert testResults(LowpassFilterRC.getRCValues("0.1n", 150, 10), (new HashMap<>() {
            {
                put("Resistance", 10610338.5);
                put("Time constant", 0.001061);
                put("Stable time", 0.005305);
                put("Voltaje in stable time", 9.9326);
                ;
            }
        }));
        // TEST_END

        // TEST
        assert testResults(LowpassFilterRC.getRCValues("1000p", 60, 3), (new HashMap<>() {
            {
                put("Resistance", 2652584.625);
                put("Time constant", 0.0026525);
                put("Stable time", 0.01326);
                put("Voltaje in stable time", 2.979);
                ;
            }
        }));
        // TEST_END


        // TEST
        try {
            LowpassFilterRC.getRCValues(null, 100, 2);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            LowpassFilterRC.getRCValues("0.1u", 600, 2);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END     

        // TEST
        try {
            LowpassFilterRC.getRCValues("0.5t", 100, 2);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END     

    }

    public static Map<String, Double> getRCValues(String Capacitor, double CutoffFrequency, double InputDCVoltaje) {

        if (Capacitor == null) {

            throw new IllegalArgumentException("Capacitance cannot be negative");

        }

        if (CutoffFrequency < 0 || CutoffFrequency > 300) {

            throw new IllegalArgumentException(
                    "Cutoff frequency cant be negative and major than 300 Hz for RC Circuit");

        }

        if (InputDCVoltaje < 0 || InputDCVoltaje == 0) {

            throw new IllegalArgumentException("DC voltaje cannot be negative nor 0");

        }

        double PI_CONSTANT = 3.14159;
        double capacitorMagnitude = 0;
        double resistance;
        String capacitorPrefix;
        double timeConstant;
        double stableTime;
        double voltajeInStableTime;
        String CapacitorRegex = "([-+]?\\d*\\.?\\d+)([unp])";
        Pattern PatternOfCapacitor = Pattern.compile(CapacitorRegex);
        Matcher MatcherOfCapacitor = PatternOfCapacitor.matcher(Capacitor);

        if (MatcherOfCapacitor.find()) {

            capacitorMagnitude = Double.parseDouble(MatcherOfCapacitor.group(1));
            capacitorPrefix = MatcherOfCapacitor.group(2);
            

        } else {
            
            throw new IllegalArgumentException("Invalid format of capacitance");

            
        }

        double capacitance = capacitorMagnitude;

        if (capacitorMagnitude < 0) {

            throw new IllegalArgumentException("Capacitance cannot be negative");

        }

        switch (capacitorPrefix) {
            case "u":
                capacitance = capacitorMagnitude * Math.pow(10, -6);
                break;
            case "n":
                capacitance = capacitorMagnitude * Math.pow(10, -9);
                break;
            case "p":
                capacitance = capacitorMagnitude * Math.pow(10, -12);
                break;

            default:
                throw new IllegalArgumentException("Invalid format of prefix");
        }

        System.out.println(capacitance);

        resistance = 1 / (2 * PI_CONSTANT * CutoffFrequency * capacitance);
        timeConstant = resistance * capacitance;
        stableTime = 5 * timeConstant;
        voltajeInStableTime = InputDCVoltaje * (1 - Math.exp(-5));

        System.out.println(resistance);
        System.out.println(timeConstant);
        System.out.println(stableTime);
        System.out.println(voltajeInStableTime);
        System.out.println("--------------");

        Map<String, Double> RCValues = new HashMap<>() {
            {
                put("Resistance", resistance);
                put("Time constant", timeConstant);
                put("Stable time", stableTime);
                put("Voltaje in stable time", voltajeInStableTime);
            }
        };
        return RCValues;

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
