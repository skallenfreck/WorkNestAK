package dao;

import modelo.Usuario;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class UsuarioDAO {

    public int agregarUsuario(Usuario usuario) {
        int status = 0;
        try (Connection con = Conexion.getConexion()) {
            String query = "INSERT INTO usuarios (identificacion, nombre, apellido, email, usuario, clave, idperfil) VALUES (?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, usuario.getIdentificacion());
                ps.setString(2, usuario.getNombre());
                ps.setString(3, usuario.getApellido());
                ps.setString(4, usuario.getEmail());
                ps.setString(5, usuario.getUsuario());
                ps.setString(6, usuario.getClave());
                ps.setInt(7, usuario.getIdperfil());
                status = ps.executeUpdate();
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar usuario: " + e.getMessage());
        }
        return status;
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (Connection con = Conexion.getConexion()) {
            String query = "SELECT * FROM usuarios";
            try (PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Usuario usuario = new Usuario(
                            rs.getInt("id"),
                            rs.getString("identificacion"),
                            rs.getString("nombre"),
                            rs.getString("apellido"),
                            rs.getString("email"),
                            rs.getString("usuario"),
                            rs.getString("clave"),
                            rs.getInt("idperfil")
                    );
                    usuarios.add(usuario);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar usuarios: " + e.getMessage());
        }
        return usuarios;
    }

    public Usuario validarLogin(String usuario, String clave) {
        Usuario user = null;
        try (Connection con = Conexion.getConexion()) {
            String query = "SELECT * FROM usuarios WHERE usuario = ? AND clave = ?";
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setString(1, usuario);
                ps.setString(2, clave);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        user = new Usuario(
                                rs.getInt("id"),
                                rs.getString("identificacion"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                rs.getString("email"),
                                rs.getString("usuario"),
                                rs.getString("clave"),
                                rs.getInt("idperfil")
                        );
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
        }
        return user;
    }
}
