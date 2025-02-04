import java.util.Arrays;

public class Matrixmultiplicator {
    public static void main(String[] args) {

        // TEST
        assert Arrays.deepEquals(
                Matrixmultiplicator.getMatrixMultiplicator(new int[][] { { 1, 2, 3 }, { 5, 6, 9 }, { 7, 6, 7 } },
                        new int[][] { { 6, 8, 7 }, { 5, 6, 4 }, { 4, 5, 1 } }),
                (new int[][] { { 28, 35, 18 }, { 96, 121, 68 }, { 100, 127, 80 } }));
        // TEST_END

        // TEST
        assert Arrays.deepEquals(
                Matrixmultiplicator.getMatrixMultiplicator(new int[][] { { -5, -7, 0 }, { 4, -4, 1 }, { 0, 5, 1 } },
                        new int[][] { { 1, 5, 10 }, { 2, -1, -1 }, { 0, 0, 0 } }),
                (new int[][] { { -19, -18, -43 }, { -4, 24, 44 }, { 10, -5, -5 } }));
        // TEST_END

        // TEST
        assert Arrays.deepEquals(
                Matrixmultiplicator.getMatrixMultiplicator(new int[][] { { -1, 0, 5 }, { -1, 1, -1 }, { 5, 0, -1 } },
                        new int[][] { { 1, 1, 5 }, { 1, 1, 1 }, { 5, 1, 1 } }),
                (new int[][] { { 24, 4, 0 }, { -5, -1, -5 }, { 0, 4, 24 } }));
        // TEST_END

        // TEST
        try {
            Matrixmultiplicator.getMatrixMultiplicator(new int[][] {{ -1, 0, 5 }, { -1, 1, -1 }, { 5, 0, -1 }, {6,6,6}}, new int[][] {{ -1, 0, 5 }, { -1, 1, -1 }, { 5, 0, -1 }});
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

        // TEST
        try {
            Matrixmultiplicator.getMatrixMultiplicator(new int[][] {{ -1, 1, -1 }}, new int[][] {{ -1, 0, 5 }, { -1, 1, -1 }, { 5, 0, -1 }});
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

         // TEST
         try {
            Matrixmultiplicator.getMatrixMultiplicator(new int[][] {{ -1, 5 }, { -1, 1, -1 }, { 5, 0, -1 }, {6,6,6}}, new int[][] {{ -1, 0, 5 }, { -1, 1, -1 }, { 5, 0, -1 }});
            assert false;
        } catch (IllegalArgumentException e) {
            assert true;
        }
        // TEST_END

    }

    public static int[][] getMatrixMultiplicator(int[][] A, int[][] B) {

        if (A.length < 3 || A.length > 3 || B.length < 3 || A.length > 3) {
            throw new IllegalArgumentException("A and B length must be 3 ");
        }

        if (A[0].length < 3 || A[1].length < 3 || A[2].length < 3 || B[0].length < 3 || B[1].length < 3
                || B[2].length < 3) {
            throw new IllegalArgumentException("A and B subarrays length cannot be less than 3");
        }

        if (A[0].length > 3 || A[1].length > 3 || A[2].length > 3 || B[0].length > 3 || B[1].length > 3
                || B[2].length > 3) {
            throw new IllegalArgumentException("A and B subarrays length cannot be more than 3");
        }

        int rowsA = A[0].length;
        int columnsA = A[1].length;
        int columnsB = B[1].length;
        int[][] c = { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } };

        for (int i = 0; i < rowsA; ++i) {
            for (int j = 0; j < columnsB; ++j) {
                for (int k = 0; k < columnsA; ++k) {
                    c[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        System.out.println(Arrays.toString(c[0]));
        System.out.println(Arrays.toString(c[1]));
        System.out.println(Arrays.toString(c[2]));

        return c;

    }
}