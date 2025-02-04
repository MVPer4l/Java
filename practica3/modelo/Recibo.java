package practica3.modelo;

import java.util.*;
//import java.time.Instant;

/**
 * 
 */
public class Recibo {

    /**
     * Default constructor
     */
    public Recibo()
    {
        llenarRecibo();
    }

    /**
     * @param id
     */
    public Recibo(String id)
    {
        // TODO implement here
        idCliente=id;
    }

    /**
     * 
     */
    private String idCliente;

    /**
     * 
     */
    private String periodoDeCobro;

    /**
     * 
     */
    private float importe;
    
    /**
     * 
     */
    private float porPagar;    

    /**
     * 
     */
    private Date fechaLimitePago;

    /**
     * 
     */
    private String estado;

    /**
     * 
     */
    private Date fechaCierre;

    /**
     * 
     */
    public void definirFechaCierre(Date d) {
        // TODO implement here
        fechaCierre=d;
    }

    /**
     * 
     */
    public void putPorPagar(float pp) {
        // TODO implement here
        porPagar=pp;
    }

   /**
     * 
     */
    public float getPorPagar() {
        // TODO implement here
        return porPagar;
    } 
      
    /**
     * @param e
     */
    public void modificarEstado(String e) {
        // TODO implement here
        estado=e;
    }

    /**
     * 
     */
    public void calcularFechaLimite() {
        // TODO implement here
        // 10 días= 864000000 ms
        fechaLimitePago= new Date(fechaCierre.getTime()+864000000L);
    }

    /**
     * @param imp
     */
    public void asignarImporte(float imp) {
        // TODO implement here
        importe=imp;
        porPagar=imp;
        defPeriodoDeCobro();
    }

    /**
     * @return
     */
    public float consultarImporte() {
        // TODO implement here
        return importe;
    }
    
    /**
     * @return
     */
    private void defPeriodoDeCobro()
    {
         // 30 días=2592000000 ms
         Date inicio= new Date(fechaCierre.getTime()-2592000000L);
         periodoDeCobro= new String("Del "+inicio.toString()+" al "+fechaCierre.toString());
    }
    /**
     * @return
     */
    public String toString()
    {
         String temp= new String("\tRECIBO DE SERVICIOS DE INTERNET\n\n");
         temp=temp+"Período de servicio: "+periodoDeCobro+"\n";
         temp=temp+"Fecha límite de pago: "+fechaLimitePago.toString()+"\n";
         temp=temp+"Importe del recibo: "+importe+"\n";
         temp=temp+"Estado del recibo: "+estado+"\n";
         return temp;
    }
        /**
     * @return
     */
   /*
    private String idCliente;
    private String periodoDeCobro;
    private float importe;
    private float porPagar;    
    private Date fechaLimitePago;
    private String estado;
    private Date fechaCierre;
   private Date actual()
   {
      Instant i= Instant.now();
   }
   */
     
    private void llenarRecibo()
    {
         Scanner in=new Scanner(System.in);
         System.out.print("Introduce el código de cliente: ");
         idCliente=in.nextLine();
         System.out.print("Introduce el importe del recibo: ");
         importe=in.nextFloat();
         porPagar=importe;
         fechaCierre=new Date();
         calcularFechaLimite();
         estado="Pendiente de pago";
         defPeriodoDeCobro();
    }
}
