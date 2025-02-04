import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws Exception {
        // TEST
        assert ElipseParts.getElipseParts(new ElipseParts.Point(0, 0), 0, 0, 0)
                .equals(new ElipseParts.Elipse(null, null, null, null, null));
        // TEST_END

        // // TEST
        // assert ElipseParts.element();
        // // TEST_END

        // // TEST
        // assert ElipseParts.element();
        // // TEST_END

        // // TEST
        // try {
        // ElipseParts.element();
        // assert false;
        // } catch (IllegalArgumentException e) {
        // assert true;
        // }
        // // TEST_END
    }
}
