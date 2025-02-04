import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LaplaceTransform {
    public static void main(String[] args) {

        // TEST
        assert LaplaceTransform.mapsAreEqual(LaplaceTransform.getLaplaceTransform("5*t^2"),
                (new HashMap<>() {
                    {
                        put("Numerator", "10.0");
                        put("Denominator", "S^3");
                    }
                }));

        // TEST_END

        // TEST
        assert LaplaceTransform.mapsAreEqual(LaplaceTransform.getLaplaceTransform("1*SIN(2*t)"),
                (new HashMap<>() {
                    {
                        put("Numerator", "2.0");
                        put("Denominator", "S^2 + 4.0");
                    }
                }));

        // TEST_END

        // TEST
        assert LaplaceTransform.mapsAreEqual(LaplaceTransform.getLaplaceTransform("2*Cos(3*t)"),
                (new HashMap<>() {
                    {
                        put("Numerator", "2.0*S");
                        put("Denominator", "S^2 + 9.0");
                    }
                }));

        // TEST_END

        // TEST
        assert LaplaceTransform.mapsAreEqual(LaplaceTransform.getLaplaceTransform("1.3*Sinh(4*t)"),
                (new HashMap<>() {
                    {
                        put("Numerator", "5.2");
                        put("Denominator", "S^2 - 16.0");
                    }
                }));

        // TEST_END

        // TEST
        assert LaplaceTransform.mapsAreEqual(LaplaceTransform.getLaplaceTransform("17*cosh(17*t)"),
                (new HashMap<>() {
                    {
                        put("Numerator", "17.0*S");
                        put("Denominator", "S^2 - 289.0");
                    }
                }));

        // TEST_END



        // TEST
        try {
            LaplaceTransform.getLaplaceTransform(null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            LaplaceTransform.getLaplaceTransform("5*t^17");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            LaplaceTransform.getLaplaceTransform("2*Tan(5*t)");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

    }

    public static Map<String, String> getLaplaceTransform(String signalInput) {

        if(signalInput==null){

            throw new IllegalArgumentException("Invalid signal input");
        }

        String input = signalInput.toLowerCase();

        String structureOfTN = "([+-]?\\d+(\\.\\d+)?)?\\s*\\*\\s*t\\^\\s*([+-]?\\d+)";
        String structureOfExp = "([+-]?\\d+)?\\*exp\\(\\^([+-]?\\d+)\\*t\\)";
        String structureOfSin = "(\\d+(\\.\\d+)?)\\s*\\*\\s*sin\\(\\s*(\\d+(\\.\\d+)?)\\s*\\*\\s*t\\s*\\)";
        String structureOfCos = "(\\d+(\\.\\d+)?)\\s*\\*\\s*cos\\(\\s*(\\d+(\\.\\d+)?)\\s*\\*\\s*t\\s*\\)";
        String structureOfSenh = "(\\d+(\\.\\d+)?)\\s*\\*\\s*sinh\\(\\s*(\\d+(\\.\\d+)?)\\s*\\*\\s*t\\s*\\)";
        String structureOfTCosh = "(\\d+(\\.\\d+)?)\\s*\\*\\s*cosh\\(\\s*(\\d+(\\.\\d+)?)\\s*\\*\\s*t\\s*\\)";

        Pattern PatternOfTN = Pattern.compile(structureOfTN);
        Pattern PatternOfExp = Pattern.compile(structureOfExp);
        Pattern PatternOfSin = Pattern.compile(structureOfSin);
        Pattern PatternOfCos = Pattern.compile(structureOfCos);
        Pattern PatternOfSinh = Pattern.compile(structureOfSenh);
        Pattern PatternOfCosh = Pattern.compile(structureOfTCosh);

        Matcher matcherOfTN = PatternOfTN.matcher(input);
        Matcher matcherOfExp = PatternOfExp.matcher(input);
        Matcher matcherOfSin = PatternOfSin.matcher(input);
        Matcher matcherOfCos = PatternOfCos.matcher(input);
        Matcher matcherOfSinh = PatternOfSinh.matcher(input);
        Matcher matcherOfCosh = PatternOfCosh.matcher(input);

        String numerator = "";
        String denominator = "";

        if (matcherOfTN.find()) {

            String escalarStr = matcherOfTN.group(1);
            String nStr = matcherOfTN.group(3);

            Double escalar = (escalarStr != null) ? Double.parseDouble(escalarStr) : 1.0;
            int n = Integer.parseInt(nStr);
            int nFactorial = 1;
            int sExponent = n + 1;

            if (n <= 0) {
                throw new IllegalArgumentException("exponent must be positive");
            }

            if (n > 15) {
                throw new IllegalArgumentException("Infinite factorial");
            }

            for (int i = 1; i <= n; ++i) {
                nFactorial *= i;
            }

            double numeratorResult = nFactorial * escalar;

            numerator = (String.valueOf(numeratorResult));
            denominator = ("S^" + sExponent);

            System.out.println(numerator);
            System.out.println(denominator);

        } else if (matcherOfExp.find()) {
            String escalarStr = matcherOfExp.group(1);
            String matchedExp = matcherOfExp.group(2);
            Double escalar = (escalarStr != null) ? Double.parseDouble(escalarStr) : 1;
            int n = Integer.parseInt(matchedExp);

            numerator = (String.valueOf(escalar));

            if (n < 0) {
                denominator = ("S+" + Math.abs(n));

            } else {
                denominator = ("S-" + n);
            }
            System.out.println(numerator);
            System.out.println(denominator);

        } else if (matcherOfSin.matches()) {
            String escalarStr = matcherOfSin.group(1);
            String argumentMatched = matcherOfSin.group(3);
            Double escalar = (escalarStr != null) ? Double.parseDouble(escalarStr) : 1.0;
            Double argument = Double.parseDouble(argumentMatched);
            numerator = String.valueOf(escalar * argument);
            denominator = "S^2 + " + String.valueOf(argument * argument);
            System.out.println(numerator);
            System.out.println(denominator);

        } else if (matcherOfCos.matches()) {
            String escalarStr = matcherOfCos.group(1);
            String argumentMatched = matcherOfCos.group(3);
            Double escalar = (escalarStr != null) ? Double.parseDouble(escalarStr) : 1.0;
            Double argument = Double.parseDouble(argumentMatched);
            numerator = String.valueOf(escalar) + "*S";
            denominator = "S^2 + " + String.valueOf(argument * argument);
            System.out.println(numerator);
            System.out.println(denominator);

        } else if (matcherOfSinh.matches()) {
            String escalarStr = matcherOfSinh.group(1);
            String argumentMatched = matcherOfSinh.group(3);
            Double escalar = (escalarStr != null) ? Double.parseDouble(escalarStr) : 1.0;
            Double argument = Double.parseDouble(argumentMatched);
            numerator = String.valueOf(escalar * argument);
            denominator = "S^2 - " + String.valueOf(argument * argument);
            System.out.println(numerator);
            System.out.println(denominator);

        } else if (matcherOfCosh.matches()) {
            String escalarStr = matcherOfCosh.group(1);
            String argumentMatched = matcherOfCosh.group(3);
            Double escalar = (escalarStr != null) ? Double.parseDouble(escalarStr) : 1.0;
            Double argument = Double.parseDouble(argumentMatched);
            numerator = String.valueOf(escalar) + "*S";
            denominator = "S^2 - " + String.valueOf(argument * argument);
            System.out.println(numerator);
            System.out.println(denominator);
        } else {
            throw new IllegalArgumentException("Invalid signal input");
        }

        String fNumerator = numerator;
        String fDenominator = denominator;

        Map<String, String> laplaceTransformV = new HashMap<>() {
            {
                put("Numerator", fNumerator);
                put("Denominator", fDenominator);

            }
        };
        return laplaceTransformV;

    }

    private static boolean mapsAreEqual(Map<String, String> map1, Map<String, String> map2) {
        for (String key : map1.keySet()) {
            if (!map2.containsKey(key)) {
                return false;
            }
            String value1 = map1.get(key);
            String value2 = map2.get(key);
            if (!value1.equals(value2)) {
                return false;
            }
        }
        return true;
    }

}
