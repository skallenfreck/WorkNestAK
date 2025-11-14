package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

import modelo.usuario;
import modelo.usuarioDAO;

@WebServlet("/usuarioServlet")
public class UsuarioServlet extends HttpServlet {

    usuarioDAO usuarioDAO = new usuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        usuarioDAO udao = new usuarioDAO();
        usuario u = new usuario();

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {
            
            case "buscar":
                String idBuscar = request.getParameter("identificacion");
                usuario usuarioBuscado = udao.consultarUsuario(idBuscar);

                if (usuarioBuscado != null) {
                    request.setAttribute("usuario", usuarioBuscado);
                    request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                } else {
                    request.setAttribute("msg", "Usuario no encontrado");
                    request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
                }
                break;
                
            case "listar":
                listarUsuario(request, response);
                break;

            case "editar":
                mostrarFormularioEditar(request, response);
                break;

            case "eliminar":
                eliminarUsuario(request, response);
                break;

            default:
                listarUsuario(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "agregar":
                agregarUsuario(request, response);
                break;

            case "actualizar":
                actualizarUsuario(request, response);
                break;

            default:
                listarUsuario(request, response);
        }
    }

    // =======================
    //      MÉTODOS
    // =======================

    private void listarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<usuario> lista = usuarioDAO.listarUsuario();
        request.setAttribute("listaUsuario", lista);
        request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String id = request.getParameter("id");
        usuario u = usuarioDAO.consultarUsuario(id);
        request.setAttribute("usuario", u);
        request.getRequestDispatcher("editarUsuario.jsp").forward(request, response);
    }

    private void agregarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        usuario u = new usuario();

        u.setIdentificacion(request.getParameter("identificacion"));
        u.setNombre(request.getParameter("nombre"));
        u.setApellido(request.getParameter("apellido"));
        u.setEmail(request.getParameter("email"));
        u.setUsuario(request.getParameter("noUsuario"));
        u.setClave(request.getParameter("clave"));
        u.setIdPerfil(Integer.parseInt(request.getParameter("id_perfil"))); // 1 o 2

        usuarioDAO.agregarUsuario(u);
        
        response.sendRedirect("agregarUsuario.jsp");

    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        usuario u = new usuario();

        u.setIdentificacion(request.getParameter("identificacion"));
        u.setNombre(request.getParameter("nombre"));
        u.setApellido(request.getParameter("apellido"));
        u.setEmail(request.getParameter("email"));
        u.setUsuario(request.getParameter("usuario"));
        u.setClave(request.getParameter("clave"));
        u.setIdPerfil(Integer.parseInt(request.getParameter("id_perfil")));

        usuarioDAO.actualizarUsuario(u);
        
        response.sendRedirect("editarUsuario.jsp");

    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        usuarioDAO.eliminarUsuario(id);
        
        response.sendRedirect("administradorDS.jsp");

    }
}