package dao;

import modelo.Actividad;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class ActividadDAO {

    public List<Actividad> listarActividadesPorUsuario(int usuarioId) {
        List<Actividad> actividades = new ArrayList<>();
        try (Connection con = Conexion.getConexion()) {
            // Realizamos una consulta con una JOIN entre actividades y gesactividad para filtrar por usuario
            String query = "SELECT a.id_actividad, a.nombre_actividad, a.descripcion, a.enlace "
                    + "FROM actividades a "
                    + "JOIN gesactividad ga ON a.id_actividad = ga.id_actividad "
                    + "WHERE ga.id_usuario = ?";  // Asegúrate de que la columna id_usuario sea la correcta
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, usuarioId);  // Obtener el ID del usuario desde la sesión
                try (ResultSet rs = ps.executeQuery()) {
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
            }
        } catch (SQLException e) {
            System.out.println("Error al listar actividades: " + e.getMessage());
        }
        return actividades;
    }

    // Método para eliminar una actividad por ID
    public int eliminarActividad(int idActividad) {
        int status = 0;
        try (Connection con = Conexion.getConexion()) {
            String query = "DELETE FROM actividades WHERE idActividad = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, idActividad);
                status = ps.executeUpdate();  // Ejecutar la eliminación
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar actividad: " + e.getMessage());
        }
        return status;
    }

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

    public int insertarRelacionGesActividad(int idActividad, String identificacion) {
        int status = 0;
        try (Connection con = Conexion.getConexion()) {
            // Insertar la relación entre la actividad y el usuario en gesactividad
            String query = "INSERT INTO gesactividad (id_actividad, identificacion) VALUES (?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, idActividad);
                ps.setString(2, identificacion);  // Usamos identificacion en vez de id_perfil
                status = ps.executeUpdate();  // Ejecutar la inserción de la relación
            }
        } catch (SQLException e) {
            System.out.println("Error al insertar relación en gesactividad: " + e.getMessage());
        }
        return status;
    }

    public int obtenerUltimoIdActividad() {
        int idActividad = 0;
        try (Connection con = Conexion.getConexion()) {
            String query = "SELECT LAST_INSERT_ID()";  // Obtener el último ID insertado
            try (PreparedStatement ps = con.prepareStatement(query); ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    idActividad = rs.getInt(1);  // Obtener el ID de la última actividad insertada
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener el último id_actividad: " + e.getMessage());
        }
        return idActividad;
    }

    // Método para obtener una actividad por ID
    public Actividad obtenerActividadPorId(int idActividad) {
        Actividad actividad = null;
        try (Connection con = Conexion.getConexion()) {
            String query = "SELECT * FROM actividades WHERE id_actividad = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, idActividad);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        actividad = new Actividad(
                                rs.getInt("id_actividad"),
                                rs.getString("nombre_actividad"),
                                rs.getString("descripcion"),
                                rs.getString("enlace")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener actividad: " + e.getMessage());
        }
        return actividad;
    }

// Método para actualizar una actividad
    public int editarActividad(Actividad actividad) {
        int status = 0;
        try (Connection con = Conexion.getConexion()) {
            String query = "UPDATE actividades SET nombre_actividad = ?, descripcion = ?, enlace = ? WHERE id_actividad = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, actividad.getNombreActividad());
                ps.setString(2, actividad.getDescripcion());
                ps.setString(3, actividad.getEnlace());
                ps.setInt(4, actividad.getIdActividad());
                status = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar actividad: " + e.getMessage());
        }
        return status;
    }

}
