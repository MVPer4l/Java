
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




public class Convoluciondisc {
    public static void main (String[] args) {
        int[] x1={1,4};
        int[] x2={1,2,3,4};
        int lx1=x1.length;
        int lx2=x2.length;
        int ly=x2.length + x1.length - 1;
        int[] y=new int[ly];


        for (int j = 0; j < ly; j++) {
            int sum = 0;
            for (int k = 0; k < lx1; k++) {
                int index = j - k;
                if (index >= 0 && index < lx2) {
                    sum += x1[k] * x2[index];
                }
            }
            y[j] = sum;
        }

        // Imprimir el resultado
        System.out.println("Resultado de la convolución:");
        for (int i = 0; i < ly; i++) {
            System.out.println(y[i]);
        }
    }

    }