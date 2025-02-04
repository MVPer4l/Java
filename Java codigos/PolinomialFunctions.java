import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PolinomialFunctions {
    public static void main(String[] args) {


        //TEST
        assert getPolinomialFunction("6x^3", "x=2").equals("+48.0 ");
        //TEST_END

        //TEST
        assert getPolinomialFunction("6x^3", "derivate").equals("+18.0x^2 ");
        //TEST_END

        //TEST
        assert getPolinomialFunction("-2x^3+5x^2", "x=-1").equals("+7.0 ");
        //TEST_END

        //TEST
        assert getPolinomialFunction("-6x^3+4x^4+x", "derivate").equals("+1.0 -18.0x^2 +16.0x^3 ");
        //TEST_END

        //TEST
        try {
            PolinomialFunctions.getPolinomialFunction(null, "x=1");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        //TEST
        try {
            PolinomialFunctions.getPolinomialFunction("7x^4", "integrate");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        //TEST
        try {
            PolinomialFunctions.getPolinomialFunction("7t^4", "x=2");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        

        
    }

    public static String getPolinomialFunction(String expression, String election) {
        if (expression == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }
        if (!expression.matches("[\\d\\sx\\^\\+\\-\\*]*")) {
            throw new IllegalArgumentException("Invalid characters found in the function");
        }
        if (election == null) {
            throw new IllegalArgumentException("No operation selected");
        }

        String electionAUX= election.toLowerCase();
        Map<Integer, Double> polynomialMap = new HashMap<>();
        Pattern polynomialForm = Pattern.compile("([+-]?\\d*)x\\^?(\\d*)|([+-]?\\d+)");
        Matcher polynomialMatcher = polynomialForm.matcher(expression);
        double coefficient = 0;
        int exponent = 0;

        while (polynomialMatcher.find()) {
            String stringCoefficient = polynomialMatcher.group(1);
            String stringExponent = polynomialMatcher.group(2);
            String stringConstant = polynomialMatcher.group(3);

            if (stringConstant != null) {
                coefficient = Double.parseDouble(stringConstant);
                exponent = 0;
            } else {
                coefficient = stringCoefficient.isEmpty() || stringCoefficient.equals("+") ? 1
                        : stringCoefficient.equals("-") ? -1 : Double.parseDouble(stringCoefficient);
                exponent = stringExponent.isEmpty() ? 1 : Integer.parseInt(stringExponent);
            }

            polynomialMap.put(exponent, polynomialMap.getOrDefault(exponent, 0.0) + coefficient);
        }

        Map<Integer, Double> resultMap = new HashMap<>();
        Pattern pointForm = Pattern.compile("x=(-?\\d+(\\.\\d+)?)");
        Matcher matcherOfEvaluation = pointForm.matcher(electionAUX);

        if(electionAUX.equals("derivate")){
            double coeffDerivate=0;
            int expDerivate=0;
            
            for (Map.Entry<Integer, Double> entry : polynomialMap.entrySet()) {
                int expAux = entry.getKey();
                double coeffAux= entry.getValue();
                coeffDerivate = expAux*coeffAux ;
                expDerivate=expAux-1;
                resultMap.put(expDerivate, coeffDerivate);
            }


        } else if(matcherOfEvaluation.matches()){
            double functionEvaluated=0;
            String xValueStr = matcherOfEvaluation.group(1);
                double xValue = Double.parseDouble(xValueStr);
                System.out.println(xValue);

                for (Map.Entry<Integer, Double> entry : polynomialMap.entrySet()) {
                    int expAux = entry.getKey();
                    double coeffAux= entry.getValue();
                    functionEvaluated += coeffAux * Math.pow(xValue, expAux);
                }
                resultMap.put(0,functionEvaluated );
            System.out.println(functionEvaluated);

        } else{
            throw new IllegalArgumentException("invalid selection");
        }

        String Result=organizePolynomial(resultMap);
        System.out.println(Result);
        return Result;

    }

    private static String organizePolynomial(Map<Integer, Double> expression) {
        String currentExpression = "";

        while (!expression.isEmpty()) {
            int currentLowestCoefficient = Integer.MAX_VALUE;

            for (Map.Entry<Integer, Double> polynomial : expression.entrySet()) {
                if (currentLowestCoefficient > polynomial.getKey()) {
                    currentLowestCoefficient = polynomial.getKey();
                }
            }
            if (expression.get(currentLowestCoefficient) == 0) {
                expression.remove(currentLowestCoefficient);
                continue;
            }

            currentExpression += expression.get(currentLowestCoefficient) > 0
                    ? "+" + expression.get(currentLowestCoefficient)
                    : "" + expression.get(currentLowestCoefficient);

            currentExpression += currentLowestCoefficient == 0 ? ""
                    : currentLowestCoefficient == 1 ? "x" : "x^" + currentLowestCoefficient;

            expression.remove(currentLowestCoefficient);
            currentExpression += " ";
        }
        return currentExpression;
    }

}
