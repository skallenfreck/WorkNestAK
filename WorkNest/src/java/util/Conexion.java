package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Datos de conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/worknest";  // Cambia a la URL correcta
    private static final String USER = "root";  // Usuario de MySQL
    private static final String PASSWORD = "admin";  // Contraseña de MySQL

    // Método para obtener la conexión a la base de datos
    public static Connection getConexion() throws SQLException {
        // Establecer la conexión y devolverla
        try {
            // Cargar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Obtener la conexión
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Error al cargar el driver de MySQL: " + e.getMessage());
            throw new SQLException("Error al conectar a la base de datos.", e);
        }
    }
}
