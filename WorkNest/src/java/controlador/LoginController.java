package controlador;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;
import java.io.IOException;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            if ("login".equals(accion)) {
                autenticarUsuario(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("mensajeError", "Error en la autenticación: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void autenticarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");

            // Usamos el DAO para validar las credenciales del usuario
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario user = usuarioDAO.validarLogin(usuario, clave);

            if (user != null) {
                // Si el usuario existe, creamos la sesión
                HttpSession session = request.getSession(true);
                session.setAttribute("nUsuario", user.getUsuario());
                session.setAttribute("perfil", user.getIdperfil());
                // Almacenar el ID del usuario en la sesión
                session.setAttribute("usuarioId", user.getId());  // Asumiendo que getId() devuelve el ID del usuario

                // Redirigimos al dashboard
                response.sendRedirect("dashboard.jsp");
            } else {
                // Si no existe, mostramos un error
                request.setAttribute("mensajeError", "Usuario o clave incorrectos.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("mensajeError", "Error al autenticar: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar el inicio de sesión de los usuarios";
    }
}
