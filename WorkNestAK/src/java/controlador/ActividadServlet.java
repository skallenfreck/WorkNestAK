/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import modelo.actividad;
import modelo.actividadDAO;

//@WebServlet(name = "actividadServlet", urlPatterns = {"/actividadServlet"})
public class ActividadServlet extends HttpServlet {

    actividadDAO dao = new actividadDAO();
    actividad act = new actividad();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {

            case "listar":
                listar(request, response);
                break;

            case "nuevo":
                request.getRequestDispatcher("agregarActividad.jsp").forward(request, response);
                break;

            case "editar":
                cargarParaEditar(request, response);
                break;

            case "eliminar":
                eliminar(request, response);
                break;

            default:
                listar(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        switch (accion) {

            case "agregar":
                agregar(request, response);
                break;

            case "actualizar":
                actualizar(request, response);
                break;

            default:
                listar(request, response);
                break;
        }
    }

    // =================== MÉTODOS CRUD =====================

    // LISTAR -----------------------------------------------------------
    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<actividad> lista = dao.listar();
        request.setAttribute("listaActividad", lista);
        request.getRequestDispatcher("listarActividad.jsp").forward(request, response);
    }

    // AGREGAR ----------------------------------------------------------
    private void agregar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre_actividad");
        String desc = request.getParameter("descripciona");
        String enlace = request.getParameter("enlace");

        actividad a = new actividad();
        a.setNombre_actividad(nombre);
        a.setDescripciona(desc);
        a.setEnlace(enlace);

        dao.agregar(a);

        listar(request, response);
    }

    // CARGAR DATOS PARA EDITAR -----------------------------------------
    private void cargarParaEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        actividad a = dao.buscarPorId(id);

        request.setAttribute("actividad", a);
        request.getRequestDispatcher("editarActividad.jsp").forward(request, response);
    }

    // ACTUALIZAR --------------------------------------------------------
    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id_actividad"));
        String nombre = request.getParameter("nombre_actividad");
        String desc = request.getParameter("descripciona");
        String enlace = request.getParameter("enlace");

        actividad a = new actividad(id, nombre, desc, enlace);

        dao.actualizar(a);

        listar(request, response);
    }

    // ELIMINAR ----------------------------------------------------------
    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));
        dao.eliminar(id);

        listar(request, response);
    }
}