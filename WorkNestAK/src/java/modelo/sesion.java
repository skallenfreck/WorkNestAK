package modelo;

public class sesion {
    private int id_sesion;
    private String nombre_sesion;
    private String descripcions;
    private String fecha_inicio;
    private String fecha_fin;
    private String lugar;

    // Constructor
    public sesion(String nombre_sesion, String descripcions, String fecha_inicio, String fecha_fin, String lugar) {
        this.nombre_sesion = nombre_sesion;
        this.descripcions = descripcions;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.lugar = lugar;
    }

    public sesion() {
    }
    

    // Constructor con ID (para cuando se recupere de la base de datos)
    public sesion(int id_sesion, String nombre_sesion, String descripcions, String fecha_inicio, String fecha_fin, String lugar) {
        this.id_sesion = id_sesion;
        this.nombre_sesion = nombre_sesion;
        this.descripcions = descripcions;
        this.fecha_inicio = fecha_inicio;
        this.fecha_fin = fecha_fin;
        this.lugar = lugar;
    }

    public int getId_sesion() {
        return id_sesion;
    }

    public void setId_sesion(int id_sesion) {
        this.id_sesion = id_sesion;
    }

    public String getNombre_sesion() {
        return nombre_sesion;
    }

    public void setNombre_sesion(String nombre_sesion) {
        this.nombre_sesion = nombre_sesion;
    }

    public String getDescripcions() {
        return descripcions;
    }

    public void setDescripcions(String descripcions) {
        this.descripcions = descripcions;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(String fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
}
