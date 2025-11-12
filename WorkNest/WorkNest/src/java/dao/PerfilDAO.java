package dao;

import modelo.Perfil;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import util.Conexion;

public class PerfilDAO {

    public List<Perfil> listarPerfiles() {
        List<Perfil> perfiles = new ArrayList<>();
        try (Connection con = Conexion.getConexion()) {
            String query = "SELECT * FROM perfiles";
            try (PreparedStatement ps = con.prepareStatement(query);
                 ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Perfil perfil = new Perfil(
                            rs.getInt("id"),
                            rs.getString("nombre_perfil")
                    );
                    perfiles.add(perfil);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al listar perfiles: " + e.getMessage());
        }
        return perfiles;
    }
}
