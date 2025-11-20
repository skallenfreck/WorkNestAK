package modelo;

public class actividad {
    private int id_actividad;
    private String nombre_actividad;
    private String descripciona;
    private String enlace;

    public actividad() {
    }

    public actividad(int id_actividad, String nombre_actividad, String descripciona, String enlace) {
        this.id_actividad = id_actividad;
        this.nombre_actividad = nombre_actividad;
        this.descripciona = descripciona;
        this.enlace = enlace;
    }

    // GETTERS
    public int getId_actividad() {
        return id_actividad;
    }

    public String getNombre_actividad() {
        return nombre_actividad;
    }

    public String getDescripciona() {
        return descripciona;
    }

    public String getEnlace() {
        return enlace;
    }

    // SETTERS
    public void setId_actividad(int id_actividad) {
        this.id_actividad = id_actividad;
    }

    public void setNombre_actividad(String nombre_actividad) {
        this.nombre_actividad = nombre_actividad;
    }

    public void setDescripciona(String descripciona) {
        this.descripciona = descripciona;
    }

    public void setEnlace(String enlace) {
        this.enlace = enlace;
    }

}
