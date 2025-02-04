

public class MatrixDeterminant{

    public static void main(String[] args) {
        
    }

    public static double getDeterminant(int matrix[][]){

        int numOfRows = matrix.length;
        int numOfColumns = matrix[0].length;
    
        if(numOfColumns != numOfRows){
            throw new IllegalArgumentException("Matrix dimensions must have same length");
        }

        int N=numOfRows;
        double det;

        if(N>500){
            throw new IllegalArgumentException("");
        }


        switch (N) {
            case 1:
                det = matrix[0][0];
                break;
            case 2:
                det = matrix[0][0]*matrix[1][1] -matrix[1][0]*matrix[0][1];
                break;
            default:
                det=0;
                for (int j = 0; j < N; j++) {
                    int[][] submatrix = getSubmatrix(matrix, 0, j);
                    det += Math.pow(-1, j) * matrix[0][j] * getDeterminant(submatrix);
                }
                break;
        }
        
        return det;
    
    }

        

    private static int[][] getSubmatrix(int[][] matriz, int filaExcluir, int colExcluir) {
        int n = matriz.length;
        int[][] submatrix = new int[n - 1][n - 1];
        int filaSubmatrix = 0;

        for (int i = 0; i < n; i++) {
            if (i == filaExcluir) continue;
            int colSubmatrix = 0;
            for (int j = 0; j < n; j++) {
                if (j == colExcluir) continue;
                submatrix[filaSubmatrix][colSubmatrix] = matriz[i][j];
                colSubmatrix++;
            }
            filaSubmatrix++;
        }

        return submatrix;
    }
}


