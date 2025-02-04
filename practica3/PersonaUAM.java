package practica3;

public class PersonaUAM {
    //Atributos
    protected String nombre;
    protected long telefono;
    protected String correo;


    //metodos

    
        //getters
    public String getNombre(){
        return nombre;
    }

    public long getTelefono(){
        return telefono;
    }

    public String getCorreo(){
        return correo;
    }

        //setters
    public void setNombre(String nombre){
        this.nombre=nombre;
    }

    public void setTelefono(long telefono){
        this.telefono=telefono;
    }


}
