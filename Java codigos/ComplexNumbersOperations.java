
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.lang.Math;

public class ComplexNumbersOperations {

    public static void main(String[] args) {

        ///////////////////////////////////////////////////////////// SUMA
        ///////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////
        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("+", "3+5i", "6+1i")).equalsIgnoreCase("9+6i");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("+", "1+5i", "-2-9i")).equalsIgnoreCase("-1-4i");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("+", "0-1i", "0-0i")).equalsIgnoreCase("0-1i");
        // TEST_END

        ///////////////////////////////////////////////////////////// RESTA
        ///////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////
        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("-", "1+5i", "-2-9i")).equalsIgnoreCase("3+14i");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("-", "3+5i", "6+1i")).equalsIgnoreCase("-3+4i");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("-", "-1+0i", "-26+1i")).equalsIgnoreCase("25-1i");
        // TEST_END

        ///////////////////////////////////////////////////////////// MULTIPLI
        ///////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////
        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("*", "0+0i", "0-0i")).equalsIgnoreCase("0+0i");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("*", "3+5i", "6+1i")).equalsIgnoreCase("13+33i");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("*", "-1+0i", "0+1i")).equalsIgnoreCase("0-1i");
        // TEST_END

        ///////////////////////////////////////////////////////////// ANGULO
        ///////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////
        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("Angle", "1+5i", "-2-9i"))
                .equalsIgnoreCase("1.326");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("Angle", "0+0i", "1+0i"))
                .equalsIgnoreCase("0.000");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("Angle", "1+5i", "-2-9i"))
                .equalsIgnoreCase("1.326");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("Angle", "0+0i", "5-10i"))
                .equalsIgnoreCase("-1.107");
        // TEST_END

        ///////////////////////////////////////////////////////////// MAGNITUD
        ///////////////////////////////////////////////////////////// /////////////////////////////////////////////////////////////////////////////////////
        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("Magnitude", "-1+0i", "-26+1i"))
                .equalsIgnoreCase("27.000");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("Magnitude", "5+0i", "0-5i"))
                .equalsIgnoreCase("25.000");
        // TEST_END

        // TEST
        assert (ComplexNumbersOperations.getComplexNumbersOperations("Magnitude", "0+0i", "0-0i"))
                .equalsIgnoreCase("0.000");
        // TEST_END

        // TEST
        try {
            ComplexNumbersOperations.getComplexNumbersOperations(null, null, null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ComplexNumbersOperations.getComplexNumbersOperations("+", "5", "5+3i");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ComplexNumbersOperations.getComplexNumbersOperations("Angle", "0+1i", "0+3i");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ComplexNumbersOperations.getComplexNumbersOperations("aiksmaskda", "0+1i", "0+3i");
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END
    }

    public static String getComplexNumbersOperations(String Operation, String ComplexNumberOne,
            String ComplexNumberTwo) {

        String OperationSelected = "0";

        if (Operation == null || ComplexNumberOne == null || ComplexNumberTwo == null) {

            throw new IllegalArgumentException("Operation and complex numbers cant be null");

        }

        if (Operation == "+" || Operation == "-" || Operation == "*" || Operation == "Angle"
                || Operation == "Magnitude") {

            OperationSelected = Operation;

        } else {

            throw new IllegalArgumentException("Invalid Operation");

        }

        int[] RealPartOfNumbers = new int[2];
        int[] ImaginaryPartOfNumbers = new int[2];
        int RealPartOfResult = 0;
        int ImaginaryPartOfResult = 0;
        double Angle = 0;
        double Magnitude = 0;
        String ResulOfOperation = "0";

        String StructureOfComplexNumber = "([-]?\\d+)([+-]\\d+)i";
        Pattern PatternOfComplexNumber = Pattern.compile(StructureOfComplexNumber);
        Matcher MatcherofComplexNumberOne = PatternOfComplexNumber.matcher(ComplexNumberOne);
        Matcher MatcherofComplexNumberTwo = PatternOfComplexNumber.matcher(ComplexNumberTwo);

        if (MatcherofComplexNumberOne.matches() && MatcherofComplexNumberTwo.matches()) {
            RealPartOfNumbers[0] = Integer.parseInt(MatcherofComplexNumberOne.group(1));
            ImaginaryPartOfNumbers[0] = Integer.parseInt(MatcherofComplexNumberOne.group(2));
            RealPartOfNumbers[1] = Integer.parseInt(MatcherofComplexNumberTwo.group(1));
            ImaginaryPartOfNumbers[1] = Integer.parseInt(MatcherofComplexNumberTwo.group(2));

        } else {
            throw new IllegalArgumentException("Invalid format of complex numbers");
        }

        if (OperationSelected == "+") {

            RealPartOfResult = RealPartOfNumbers[0] + RealPartOfNumbers[1];
            ImaginaryPartOfResult = ImaginaryPartOfNumbers[0] + ImaginaryPartOfNumbers[1];

            if (ImaginaryPartOfResult < 0) {

                ResulOfOperation = Integer.toString(RealPartOfResult) + Integer.toString(ImaginaryPartOfResult) + "i";
            } else {

                ResulOfOperation = Integer.toString(RealPartOfResult) + "+" + Integer.toString(ImaginaryPartOfResult)
                        + "i";

            }

        } else if (OperationSelected == "-") {

            RealPartOfResult = RealPartOfNumbers[0] + RealPartOfNumbers[1] * (-1);
            ImaginaryPartOfResult = ImaginaryPartOfNumbers[0] + ImaginaryPartOfNumbers[1] * (-1);
            ResulOfOperation = String.format("%.3f", Magnitude);

            if (ImaginaryPartOfResult < 0) {

                ResulOfOperation = Integer.toString(RealPartOfResult) + Integer.toString(ImaginaryPartOfResult) + "i";
            } else {

                ResulOfOperation = Integer.toString(RealPartOfResult) + "+" + Integer.toString(ImaginaryPartOfResult)
                        + "i";

            }

        } else if (OperationSelected == "*") {

            RealPartOfResult = RealPartOfNumbers[0] * RealPartOfNumbers[1]
                    - ImaginaryPartOfNumbers[0] * ImaginaryPartOfNumbers[1];
            ImaginaryPartOfResult = RealPartOfNumbers[0] * ImaginaryPartOfNumbers[1]
                    + RealPartOfNumbers[1] * ImaginaryPartOfNumbers[0];

            if (ImaginaryPartOfResult < 0) {

                ResulOfOperation = Integer.toString(RealPartOfResult) + Integer.toString(ImaginaryPartOfResult) + "i";
            } else {

                ResulOfOperation = Integer.toString(RealPartOfResult) + "+" + Integer.toString(ImaginaryPartOfResult)
                        + "i";

            }

        } else if (OperationSelected == "Angle") {

            int AuxRealSum = RealPartOfNumbers[0] + RealPartOfNumbers[1];
            if (AuxRealSum == 0) {

                throw new IllegalArgumentException("You cant divide by zero");

            }

            int AuxImagSum = ImaginaryPartOfNumbers[0] + ImaginaryPartOfNumbers[1];
            double AuxDivider = AuxImagSum / AuxRealSum;

            if (AuxDivider < 0) {
                Angle = Math.atan(-1 * AuxDivider);
                Angle = Angle * (-1);
                ResulOfOperation = String.format("%.3f", Angle);

            } else if (AuxDivider > 0 || AuxDivider == 0) {
                Angle = Math.atan(AuxDivider);
                ResulOfOperation = String.format("%.3f", Angle);

            }

        } else if (OperationSelected == "Magnitude") {

            int AuxRealSquare = (RealPartOfNumbers[0] + RealPartOfNumbers[1])
                    * (RealPartOfNumbers[0] + RealPartOfNumbers[1]);
            int AuxImagSquare = (ImaginaryPartOfNumbers[0] + ImaginaryPartOfNumbers[1])
                    * (ImaginaryPartOfNumbers[0] + ImaginaryPartOfNumbers[1]);

            Magnitude = Math.sqrt(AuxRealSquare * AuxImagSquare);

            ResulOfOperation = String.format("%.3f", Magnitude);

        }

        return ResulOfOperation;

    }

}
