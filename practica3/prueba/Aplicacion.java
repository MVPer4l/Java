package practica3.prueba;

import practica3.util.Arreglo;
import practica3.modelo.Recibo;
public class Aplicacion
{
   private Arreglo <Integer> arI;
   private Arreglo <Double> arF;
   private Arreglo <Recibo> arR;
   public static void main(String [] args)
   {
      Aplicacion ap= new Aplicacion();
      ap.inicio();
   }
   
   public void inicio()
   {

      arI= new Arreglo<Integer> ();
      arF= new Arreglo<Double> ();
      arR= new Arreglo<Recibo>();
      arI.add(23);
      arI.add(90);
      arF.add(15.6);
      arF.add(34.1);
      arR.add(new Recibo());
      arR.add(new Recibo());
      while(arI.hasNext())
      {
         System.out.println(arI.next());
      }                         
      while(arF.hasNext())
      {
         System.out.println(arF.next());
      }
      while(arR.hasNext())
      {
         System.out.println(arR.next());
      }
   }
}
