package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import modelo.conexion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "registroServlet", urlPatterns = {"/registroServlet"})
public class RegistroServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

        // Capturar los parámetros del formulario
        String identificacion = request.getParameter("identificacion");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String usuario = request.getParameter("usuario");
        String clave = request.getParameter("clave");
        String id_perfil = request.getParameter("id_perfil");

        try (PrintWriter out = response.getWriter()) {

            Connection con = conexion.getConexion();
            String sql = "INSERT INTO usuarios (identificacion, nombre, apellido, email, usuario, clave, id_perfil) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, identificacion);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, email);
            ps.setString(5, usuario);
            ps.setString(6, clave);
            ps.setInt(7, Integer.parseInt(id_perfil));

            int filas = ps.executeUpdate();

            if (filas > 0) {
                out.println("<script>");
                out.println("alert(' Usuario registrado con éxito');");
                out.println("window.location='index.jsp';");
                out.println("</script>");
            } else {
                out.println("<script>");
                out.println("alert('️ No se pudo registrar el usuario');");
                out.println("window.location='registrar.jsp';");
                out.println("</script>");
            }

            ps.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
        }
    }
}
