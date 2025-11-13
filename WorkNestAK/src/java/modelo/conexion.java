package modelo;

import java.sql.Connection;
import java.sql.DriverManager;

public class conexion {
    public static Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/db_worknest", "root", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }
}
