package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class actividadDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;

    conexion cn = new conexion();

    // LISTAR -----------------------------------------------------
    public List<actividad> listar() {
        List<actividad> lista = new ArrayList<>();
        String sql = "SELECT * FROM actividades";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                actividad a = new actividad();
                a.setId_actividad(rs.getInt("id_actividad"));
                a.setNombre_actividad(rs.getString("nombre_actividad"));
                a.setDescripciona(rs.getString("descripciona"));
                a.setEnlace(rs.getString("enlace"));

                lista.add(a);
            }

        } catch (Exception e) {
            System.out.println("Error al listar actividades: " + e.getMessage());
        }

        return lista;
    }

    // AGREGAR -----------------------------------------------------
    public int agregar(actividad a) {
        String sql = "INSERT INTO actividades (nombre_actividad, descripciona, enlace) VALUES (?, ?, ?)";
        int r = 0;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, a.getNombre_actividad());
            ps.setString(2, a.getDescripciona());
            ps.setString(3, a.getEnlace());

            r = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al agregar actividad: " + e.getMessage());
        }

        return r;
    }

    // ACTUALIZAR -----------------------------------------------------
    public int actualizar(actividad a) {
        String sql = "UPDATE actividades SET nombre_actividad=?, descripciona=?, enlace=? WHERE id_actividad=?";
        int r = 0;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);

            ps.setString(1, a.getNombre_actividad());
            ps.setString(2, a.getDescripciona());
            ps.setString(3, a.getEnlace());
            ps.setInt(4, a.getId_actividad());

            r = ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al actualizar actividad: " + e.getMessage());
        }

        return r;
    }

    // ELIMINAR -----------------------------------------------------
    public void eliminar(int id) {
        String sql = "DELETE FROM actividades WHERE id_actividad=?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error al eliminar actividad: " + e.getMessage());
        }
    }

    // BUSCAR POR ID -----------------------------------------------------
    public actividad buscarPorId(int id) {
        String sql = "SELECT * FROM actividades WHERE id_actividad=?";
        actividad a = null;

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                a = new actividad();
                a.setId_actividad(rs.getInt("id_actividad"));
                a.setNombre_actividad(rs.getString("nombre_actividad"));
                a.setDescripciona(rs.getString("descripciona"));
                a.setEnlace(rs.getString("enlace"));
            }

        } catch (Exception e) {
            System.out.println("Error al buscar actividad: " + e.getMessage());
        }

        return a;
    }
}
