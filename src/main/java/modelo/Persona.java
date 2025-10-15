package modelo;

/**
 *
 * @author alexg
 */
public abstract class Persona{
    protected String nombre;
    protected String dni;
    protected String telefono;

    public Persona(String nombre, String dni, String telefono) {
        this.nombre = nombre;
        this.dni = dni;
        this.telefono = telefono;
    }

    // Getters comunes para todas las personas
    public String getNombre() { return nombre; }
    public String getDni() { return dni; }
    public String getTelefono() { return telefono; }

    // MÉTODO ABSTRACTO: Obliga a todas las clases hijas a implementar este método,
    // diciendo qué rol cumplen en el sistema.
    public abstract String getRol();
}
