public class Persona { //Clase persona que contendrá todos los valores de la base de datos 
    
    private int id;
    private String nombre;
    private String apellido;
    private String direccion;
    private String telefono;

    public Persona(){ //Constructor vació de Persona
        
    }
    
    //get y set de cada uno de los elementos de la clase "Persona"
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }    
}
