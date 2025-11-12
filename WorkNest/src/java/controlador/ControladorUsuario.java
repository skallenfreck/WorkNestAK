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
        String accion = request.getParameter("accion");  // Obtener la acción de la solicitud

        try {
            if ("agregar".equals(accion)) {
                agregarUsuario(request, response);  // Llamada al método agregar usuario
            } else if ("listar".equals(accion)) {
                listarUsuarios(request, response);  // Llamada al método listar usuarios
            } else if ("actualizar".equals(accion)) {
                actualizarUsuario(request, response);  // Llamada al nuevo método actualizar usuario
            } else if ("eliminar".equals(accion)) {
                eliminarUsuario(request, response);  // Llamada al nuevo método eliminar usuario
            } else {
                response.sendRedirect("mensaje.jsp");  // Acción por defecto si no se reconoce la acción
            }
        } catch (Exception e) {
            // Manejo de errores generales
            request.setAttribute("mensajeError", "Error en la operación: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir a mensaje.jsp en caso de error
        }
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Lógica para agregar un nuevo usuario
            String identificacion = request.getParameter("identificacion");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            int idperfil = Integer.parseInt(request.getParameter("idperfil"));

            // Crear un nuevo usuario
            Usuario usuarioNuevo = new Usuario(0, identificacion, nombre, apellido, email, usuario, clave, idperfil);

            // Llamar al DAO para agregar el usuario
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            int status = usuarioDAO.agregarUsuario(usuarioNuevo);

            if (status > 0) {
                response.sendRedirect("mensaje.jsp"); // Redirigir a mensaje.jsp si el usuario fue agregado con éxito
            } else {
                request.setAttribute("mensajeError", "No se pudo agregar el usuario.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
            }
        } catch (Exception e) {
            // Manejo de excepciones durante la agregación
            request.setAttribute("mensajeError", "Error al agregar el usuario: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
        }
    }

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Llamar al DAO para listar los usuarios
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            request.setAttribute("usuarios", usuarioDAO.listarUsuarios());  // Pasar los usuarios al JSP
            request.getRequestDispatcher("listarUsuarios.jsp").forward(request, response);  // Redirigir a listarUsuarios.jsp
        } catch (Exception e) {
            // Manejo de errores al listar
            request.setAttribute("mensajeError", "Error al listar los usuarios: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
        }
    }

    // El nuevo método para actualizar usuario
    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los datos del formulario
            String identificacion = request.getParameter("identificacion");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");
            String email = request.getParameter("email");
            String usuario = request.getParameter("usuario");
            String clave = request.getParameter("clave");
            int idperfil = Integer.parseInt(request.getParameter("idperfil"));

            // Obtener el usuario por identificación (en vez de ID)
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuarioActualizar = usuarioDAO.obtenerUsuarioPorIdentificacion(identificacion);

            if (usuarioActualizar != null) {
                // Actualizar el usuario
                usuarioActualizar.setIdentificacion(identificacion);
                usuarioActualizar.setNombre(nombre);
                usuarioActualizar.setApellido(apellido);
                usuarioActualizar.setEmail(email);
                usuarioActualizar.setUsuario(usuario);
                usuarioActualizar.setClave(clave);
                usuarioActualizar.setIdperfil(idperfil);

                int status = usuarioDAO.actualizarUsuario(usuarioActualizar);

                if (status > 0) {
                    response.sendRedirect("mensaje.jsp?mensaje=Usuario actualizado con éxito"); // Redirigir a mensaje de éxito
                } else {
                    request.setAttribute("mensajeError", "No se pudo actualizar el usuario.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response); // Redirigir a mensaje.jsp con el error
                }
            } else {
                request.setAttribute("mensajeError", "Usuario no encontrado.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response); // Redirigir si no se encuentra el usuario
            }
        } catch (Exception e) {
            // Si hay algún error al intentar actualizar
            request.setAttribute("mensajeError", "Error al actualizar el usuario: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response); // Redirigir a mensaje.jsp con el error
        }
    }

    // Método en ControladorUsuario para eliminar usuario
    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener la identificación del usuario a eliminar
            String identificacion = request.getParameter("identificacion");

            // Usar el DAO para obtener el usuario por identificación
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            Usuario usuario = usuarioDAO.obtenerUsuarioPorIdentificacion(identificacion);

            if (usuario != null) {
                // Eliminar el usuario
                int status = usuarioDAO.eliminarUsuario(usuario.getId());

                if (status > 0) {
                    response.sendRedirect("mensaje.jsp?mensaje=Usuario eliminado con éxito"); // Redirigir a mensaje de éxito
                } else {
                    request.setAttribute("mensajeError", "No se pudo eliminar el usuario.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response); // Redirigir a página de error
                }
            } else {
                request.setAttribute("mensajeError", "Usuario no encontrado.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response); // Redirigir si no se encuentra el usuario
            }
        } catch (Exception e) {
            // Si hay algún error al intentar eliminar el usuario
            request.setAttribute("mensajeError", "Error al eliminar el usuario: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response); // Redirigir a mensaje.jsp con el error
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);  // Llamar al método que maneja las peticiones
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);  // Llamar al método que maneja las peticiones
    }

    @Override
    public String getServletInfo() {
        return "Servlet para manejar operaciones de usuario";  // Información del servlet
    }
}
