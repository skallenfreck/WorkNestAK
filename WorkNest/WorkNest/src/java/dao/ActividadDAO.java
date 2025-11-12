package dao;

import modelo.Actividad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class ActividadDAO {

    public int agregarActividad(Actividad actividad) {
        int status = 0;
        try (Connection con = Conexion.getConexion()) {
            String query = "INSERT INTO actividades (nombre_actividad, descripcion, enlace) VALUES (?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, actividad.getNombreActividad());
                ps.setString(2, actividad.getDescripcion());
                ps.setString(3, actividad.getEnlace());
                status = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar actividad: " + e.getMessage());
        }
        return status;
    }

    public List<Actividad> listarActividades() {
        List<Actividad> actividades = new ArrayList<>();
        try (Connection con = Conexion.getConexion()) {
            String query = "SELECT * FROM actividades";
            try (PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Actividad actividad = new Actividad(
                            rs.getInt("id_actividad"),
                            rs.getString("nombre_actividad"),
                            rs.getString("descripcion"),
                            rs.getString("enlace")
                    );
                    actividades.add(actividad);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar actividades: " + e.getMessage());
        }
        return actividades;
    }
}
