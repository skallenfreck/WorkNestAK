package modelo;

public class Actividad {
    private int idActividad;
    private String nombreActividad;
    private String descripcion;
    private String enlace;

    // Constructor vacío
    public Actividad() {}

    // Constructor con parámetros
    public Actividad(int idActividad, String nombreActividad, String descripcion, String enlace) {
        this.idActividad = idActividad;
        this.nombreActividad = nombreActividad;
        this.descripcion = descripcion;
        this.enlace = enlace;
    }

    // Getters y setters
    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEnlace() {
        return enlace;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }
}
