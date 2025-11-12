package modelo;

public class GesActividad {
    private int id;
    private int idActividad;
    private int idPerfil;

    // Constructor vacío
    public GesActividad() {}

    // Constructor con parámetros
    public GesActividad(int id, int idActividad, int idPerfil) {
        this.id = id;
        this.idActividad = idActividad;
        this.idPerfil = idPerfil;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public int getIdPerfil() {
        return idPerfil;
    }

    public void setIdPerfil(int idPerfil) {
        this.idPerfil = idPerfil;
    }
}
