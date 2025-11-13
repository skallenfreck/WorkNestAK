/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
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
public class usuarioServlet extends HttpServlet {

    usuarioDAO usuarioDAO = new usuarioDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) {
            accion = "listar";
        }

        switch (accion) {

            case "listar":
                listarUsuarios(request, response);
                break;

            case "editar":
                mostrarFormularioEditar(request, response);
                break;

            case "eliminar":
                eliminarUsuario(request, response);
                break;

            default:
                listarUsuarios(request, response);
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
                listarUsuarios(request, response);
        }
    }

    // =======================
    //      MÉTODOS
    // =======================

    private void listarUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<usuario> lista = usuarioDAO.listarUsuarios();
        request.setAttribute("listaUsuarios", lista);
        request.getRequestDispatcher("listarUsuario.jsp").forward(request, response);
    }

    private void mostrarFormularioEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        usuario u = usuarioDAO.obtenerUsuarioPorId(id);
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
        u.setUsuario(request.getParameter("usuario"));
        u.setClave(request.getParameter("clave"));
        u.setIdPerfil(Integer.parseInt(request.getParameter("id_perfil"))); // 1 o 2

        usuarioDAO.agregarUsuario(u);

        response.sendRedirect("UsuarioServlet?accion=listar");
    }

    private void actualizarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        usuario u = new usuario();

        u.setId(Integer.parseInt(request.getParameter("id")));
        u.setIdentificacion(request.getParameter("identificacion"));
        u.setNombre(request.getParameter("nombre"));
        u.setApellido(request.getParameter("apellido"));
        u.setEmail(request.getParameter("email"));
        u.setUsuario(request.getParameter("usuario"));
        u.setClave(request.getParameter("clave"));
        u.setIdPerfil(Integer.parseInt(request.getParameter("id_perfil")));

        usuarioDAO.actualizarUsuario(u);

        response.sendRedirect("UsuarioServlet?accion=listar");
    }

    private void eliminarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        usuarioDAO.eliminarUsuario(id);

        response.sendRedirect("UsuarioServlet?accion=listar");
    }
}