import java.util.Arrays;

public class ConvolutionSum {
    public static void main(String[] args) {
        // TEST
        assert Arrays.equals(ConvolutionSum.getConvolution(new int[] { 1, 4 }, new int[] { 1, 2, 3, 4 }),
                (new int[] { 1, 6, 11, 16, 16 }));
        // TEST_END

        // TEST
        assert Arrays.equals(ConvolutionSum.getConvolution(new int[] { 1 }, new int[] { 1 }), (new int[] { 1 }));
        // TEST_END

        // TEST
        assert Arrays.equals(ConvolutionSum.getConvolution(new int[] { 0 }, new int[] { 1, 2, 3, 4 }),
                (new int[] { 0, 0, 0, 0 }));
        // TEST_END

        // TEST
        assert Arrays.equals(
                ConvolutionSum.getConvolution(new int[] { 33, -45, -56, 12 }, new int[] { -32, -71, 80, 93, 57 }),
                (new int[] { -1056, -903, 7627, 3061, -7636, -6813, -2076, 684}));
        // TEST_END

        // TEST
        try {
            ConvolutionSum.getConvolution(null, new int[] { 33, -45, -56, 12 });
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ConvolutionSum.getConvolution(new int[] { 33, -45, -56, 12 }, null);
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ConvolutionSum.getConvolution(new int[] {}, new int[] { 33, -45, -56, 12 });
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            ConvolutionSum.getConvolution(new int[] { 1, 2, 3, 4 }, new int[] {});
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END
    }

    public static int[] getConvolution(int[] xSignal, int[] hSignal) {
        if (xSignal == null || hSignal == null) {
            throw new IllegalArgumentException("Arguments cannot be null");
        }

        if (xSignal.length == 0 || hSignal.length == 0) {
            throw new IllegalArgumentException("Vector cannot be null");
        }

        int lengthXSignal = xSignal.length;
        int lengthHSignal = hSignal.length;
        int outputSignalLenght = lengthXSignal + lengthHSignal - 1;
        int[] outputSignal = new int[outputSignalLenght];

        for (int i = 0; i < outputSignalLenght; ++i) {
            int convolutionSum = 0;
            for (int j = 0; j < lengthXSignal; ++j) {
                int index = i - j;
                if (index >= 0 && index < lengthHSignal) {
                    convolutionSum += xSignal[j] * hSignal[index];
                }
            }
            outputSignal[i] = convolutionSum;
        }
        System.out.println(Arrays.toString(outputSignal));
        return outputSignal;
    }
}