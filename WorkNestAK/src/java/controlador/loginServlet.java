package controlador;

import modelo.conexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("usuario");
        String pass = request.getParameter("clave");

        try (Connection con = conexion.getConexion()) {

            String sql = "SELECT usuario, nombre, id_perfil FROM usuario "
                       + "WHERE usuario=? AND clave=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, user);
            ps.setString(2, pass);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                String nombre = rs.getString("nombre");
                int perfil = rs.getInt("id_perfil");

                // Crear sesión
                HttpSession sesion = request.getSession();
                sesion.setAttribute("usuario", nombre);
                sesion.setAttribute("perfil", perfil);

                // 🔥 Redirección según rol
                if (perfil == 1) {
                    // Rol Estudiante
                    response.sendRedirect("estudianteDS.jsp");
                } else if (perfil == 2) {
                    // Rol Administrador
                    response.sendRedirect("administradorDS.jsp");
                } else {
                    // Si por alguna razón el perfil es desconocido
                    response.sendRedirect("login.jsp?error=perfil");
                }

            } else {
                // Usuario o clave incorrectos
                response.sendRedirect("login.jsp?error=1");
            }

        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.jsp?error=2");
        }
    }
}
