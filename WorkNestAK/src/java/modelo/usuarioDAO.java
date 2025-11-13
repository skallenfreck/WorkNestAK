package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.usuario;

public class usuarioDAO {

    private Connection conn;

    public usuarioDAO(Connection conn) {
        this.conn = conn;
    }

    // ------------------------------
    // INSERTAR USUARIO
    // ------------------------------
    public boolean agregarUsuario(usuario usuario) {
        String sql = "INSERT INTO usuario (nombre, apellido, email, clave, id_perfil) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getIdPerfil());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ------------------------------
    // ACTUALIZAR USUARIO
    // ------------------------------
    public boolean actualizarUsuario(usuario usuario) {
        String sql = "UPDATE usuario SET nombre=?, apellido=?, email=?, clave=?, id_perfil=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getIdPerfil());
            ps.setInt(6, usuario.getId());
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ------------------------------
    // ELIMINAR USUARIO
    // ------------------------------
    public boolean eliminarUsuario(int id) {
        String sql = "DELETE FROM usuario WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // ------------------------------
    // BUSCAR USUARIO POR ID
    // ------------------------------
    public usuario obtenerUsuarioPorId(int id) {
        String sql = "SELECT * FROM usuario WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUsuario(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ------------------------------
    // BUSCAR USUARIO POR EMAIL (LOGIN)
    // ------------------------------
    public usuario obtenerPorEmail(String email) {
        String sql = "SELECT * FROM usuario WHERE email=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapUsuario(rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    // ------------------------------
    // LISTAR TODOS LOS USUARIOS
    // ------------------------------
    public List<usuario> listarUsuarios() {
        List<usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuario";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lista.add(mapUsuario(rs));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }

    // ------------------------------
    // MAPEO GENERAL DE USUARIO
    // ------------------------------
    private usuario mapUsuario(ResultSet rs) throws SQLException {
        usuario u = new usuario();
        u.setId(rs.getInt("id"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setEmail(rs.getString("email"));
        u.setClave(rs.getString("clave"));
        u.setIdPerfil(rs.getInt("id_perfil"));
        return u;
    }
}
