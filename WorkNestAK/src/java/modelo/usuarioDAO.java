package modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import modelo.usuario;

public class usuarioDAO {

   private Connection conn;

    public usuarioDAO() {
        conn = conexion.getConexion(); // 🔹 inicializamos la conexión
    }

    // =====================
    // Ejemplo agregarUsuario
    // =====================
    public void agregarUsuario(usuario u) {
        String sql = "INSERT INTO usuarios (identificacion, nombre, apellido, email, usuario, clave, id_perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, u.getIdentificacion());
            ps.setString(2, u.getNombre());
            ps.setString(3, u.getApellido());
            ps.setString(4, u.getEmail());
            ps.setString(5, u.getUsuario());
            ps.setString(6, u.getClave());
            ps.setInt(7, u.getIdPerfil());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // ------------------------------
    // ACTUALIZAR USUARIO
    // ------------------------------
    public boolean actualizarUsuario(usuario usuario) {
        String sql = "UPDATE usuarios SET nombre=?, apellido=?, email=?, usuario=? clave=?, id_perfil=? WHERE id=?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getApellido());
            ps.setString(3, usuario.getEmail());
            ps.setString(4, usuario.getUsuario());
            ps.setString(5, usuario.getClave());
            ps.setInt(6, usuario.getIdPerfil());
            ps.setInt(7, usuario.getId());
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
        String sql = "DELETE FROM usuarios WHERE id=?";

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
        String sql = "SELECT * FROM usuarios WHERE id=?";

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
        String sql = "SELECT * FROM usuarios WHERE email=?";

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
    public List<usuario> listarUsuario() {
        List<usuario> lista = new ArrayList<>();

        String sql = "SELECT * FROM usuarios";

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
