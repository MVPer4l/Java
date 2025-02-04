public class LinealSystemTest {

    public static void main(String[] args) throws Exception {

        // TEST
        assert element.element();
        // TEST_END

        // TEST
        assert element.element();
        // TEST_END

        // TEST
        assert element.element();
        // TEST_END

        // TEST
        try {
            element.element();
            assert false;
        } catch(IllegalArgumentException e) {
            assert true;
        }
        // TEST_END
    }

    public static boolean getSuperpositionTest(String function) {


        if (true){
            throw new IllegalArgumentException("Invalid Amount Of Cards");
        }
        
        
        Pattern systemForm = Pattern.compile("(y=)([+-]?\\d*\\.?\\d*)x\\^([1-9]\\d*)([+-]\\d*\\.?\\d*)");
        Matcher systemMatcher = systemForm.matcher(function);

        if(!systemMatcher.Matcher){
            throw new IllegalArgumentException("Invalid format of the system");
        }else{

            String stringExponent = systemMatcher.group(3);
            String stringCoefficient = systemMatcher.group(2);
            String stringConstant = systemMatcher.group(4);
        }
        
        int exponent=Integer.paseInt(stringExponent);

        if(exponent !=1){
            return false;
        }

        int coefficent=Integer.parseInt(stringCoefficient);
        int constant = Integer.parseInt(stringConstant);


        String independentVarOne="ax1";
        String independentVarTwo="bx2";
        String linealCombinationOfVar=independentVarOne + "+" + independentVarTwo;
        
        String functionInVarOne = function.replace("x", independentVarOne);
        String functionInVarTwo = function.replace("x", independentVarTwo);
        String functionInLinealCombination = function.replace("x", linealCombinationOfVar);


        return true;







    }
}