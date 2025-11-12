package modelo;

public class Perfil {
    private int id;
    private String nombrePerfil;

    // Constructor vacío
    public Perfil() {}

    // Constructor con parámetros
    public Perfil(int id, String nombrePerfil) {
        this.id = id;
        this.nombrePerfil = nombrePerfil;
    }

    // Getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrePerfil() {
        return nombrePerfil;
    }

    public void setNombrePerfil(String nombrePerfil) {
        this.nombrePerfil = nombrePerfil;
    }
}
