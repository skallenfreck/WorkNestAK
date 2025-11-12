package controlador;

import dao.UsuarioDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Usuario;
import java.io.IOException;

@WebServlet("/ControladorUsuario")
public class ControladorUsuario extends HttpServlet {
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");

        try {
            if ("agregar".equals(accion)) {
                agregarUsuario(request, response);
            } else if ("listar".equals(accion)) {
                listarUsuarios(request, response);
            } else {
                response.sendRedirect("error.jsp");
            }
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("mensajeError", "Error en la operación: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String identificacion = request.getParameter("identificacion");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            int idperfil = Integer.parseInt(request.getParameter("idperfil"));

            // Creamos un objeto Usuario
            Usuario usuarioNuevo = new Usuario(0, identificacion, nombre, apellido, email, usuario, clave, idperfil);

            // Usamos el DAO para agregar el usuario
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            int status = usuarioDAO.agregarUsuario(usuarioNuevo);

            if (status > 0) {
                response.sendRedirect("mensaje.jsp"); // Redirige a una página de éxito
            } else {
                request.setAttribute("mensajeError", "No se pudo agregar el usuario.");
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("mensajeError", "Error al agregar el usuario: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Usamos el DAO para obtener todos los usuarios
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            request.setAttribute("usuarios", usuarioDAO.listarUsuarios());
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);
        } catch (Exception e) {
            // Manejo de errores
            request.setAttribute("mensajeError", "Error al listar los usuarios: " + e.getMessage());
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
        return "Servlet para manejar operaciones de usuario";
    }
}
