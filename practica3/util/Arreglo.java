package practica3.util;
import java.util.Scanner;
import java.util.Iterator;
//import java.lang.reflect.Array;

public class Arreglo <T> implements Iterator<T>
{
   private T [] a;   // Datos del arreglo
   private int n;    // Tamaño del arreglo
   private int ne;   // Número de elementos en el arreglo
   private int i;    // Elemento actual
   
   public Arreglo()
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("¿Tamaño del arreglo?: ");
      n=sc.nextInt();
//      Class <T> c=null;     // Se crea el objeto arreglo
      a= (T[]) new Object[n]; // de tamaño n 
      ne=0;
      i=0;
   }  // Array.newInstance(c,n);
   
   public boolean hasNext()
   {
      boolean ind=false;
      if(ne>0 && i<ne)
         ind=true;
//      System.out.println("ne= "+ne+" i= "+i+" ind= "+ind);         
      return ind;
   }
   
   public T next()
   {
      T e=null;
      if(i<ne)
      {
         e=a[i];
         i++;
      }
      return e;
   }
   
   public void remove()
   {
      if(i<ne)
      {
         for(int j=i; j<ne; i++)
            a[i]=a[i+1];
         ne=ne-1;
         i=i-1;
      }
   }
   
   public void add(T e)
   {
      if(ne<n)
      {
//         System.out.println("Agregando:"+e);
         a[ne]=e;
         ne++;
      }
   }
}
