package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class sesionDAO {
    private Connection conn;

    public sesionDAO() {
        conn = conexion.getConexion();
    }

    // AGREGAR
    public void agregarSesion(sesion s) {
        String sql = "INSERT INTO sesiones (nombre_sesion, descripcions, fecha_inicio, fecha_fin, lugar) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNombre_sesion());
            ps.setString(2, s.getDescripcions());
            ps.setString(3, s.getFecha_inicio());
            ps.setString(4, s.getFecha_fin());
            ps.setString(5, s.getLugar());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public sesion consultarSesion(String nombre) {
        conexion cn = new conexion();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        sesion s = null;

        String sql = "SELECT * FROM sesiones WHERE nombre_sesion=?";

        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();

            if(rs.next()) {
                s = new sesion();
                s.setNombre_sesion(rs.getString("nombre_sesion"));
                s.setDescripcions(rs.getString("descripcions"));
                s.setFecha_inicio(rs.getString("fecha_inicio"));
                s.setFecha_fin(rs.getString("fecha_fin"));
                s.setLugar(rs.getString("lugar"));


            }

        } catch (SQLException e) {
            System.out.println("Error al consultar usuario: " + e.getMessage());
        } finally {
            try { if(rs != null) rs.close(); } catch(Exception e) {}
            try { if(ps != null) ps.close(); } catch(Exception e) {}
            try { if(con != null) con.close(); } catch(Exception e) {}
        }
        return s;
    }
    
    // ACTUALIZAR
    public boolean actualizarSesion(sesion s) {
        String sql = "UPDATE sesiones SET nombre_sesion=?, descripcions=?, fecha_inicio=?, fecha_fin=?, lugar=? WHERE id_sesion=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, s.getNombre_sesion());
            ps.setString(2, s.getDescripcions());
            ps.setString(3, s.getFecha_inicio());
            ps.setString(4, s.getFecha_fin());
            ps.setString(5, s.getLugar());
            ps.setInt(6, s.getId_sesion());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ELIMINAR
    public boolean eliminarSesion(int id) {
        String sql = "DELETE FROM sesiones WHERE id_sesion=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // CONSULTAR
    public sesion consultarSesion(int id) {
        String sql = "SELECT * FROM sesiones WHERE id_sesion=?";
        sesion s = null;

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                s = mapSesion(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return s;
    }

    // LISTAR
    public List<sesion> listarSesion() {
        List<sesion> lista = new ArrayList<>();
        String sql = "SELECT * FROM sesiones";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                lista.add(mapSesion(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // MAPEO
    private sesion mapSesion(ResultSet rs) throws SQLException {
        sesion s = new sesion();

        s.setId_sesion(rs.getInt("id_sesion"));
        s.setNombre_sesion(rs.getString("nombre_sesion"));
        s.setDescripcions(rs.getString("descripcions"));
        s.setFecha_inicio(rs.getString("fecha_inicio"));
        s.setFecha_fin(rs.getString("fecha_fin"));
        s.setLugar(rs.getString("lugar"));

        return s;
    }
}
