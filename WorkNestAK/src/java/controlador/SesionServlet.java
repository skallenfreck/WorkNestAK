package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import modelo.sesion;
import modelo.sesionDAO;


//@WebServlet("/SesionServlet")
public class SesionServlet extends HttpServlet {

    sesionDAO dao = new sesionDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion == null) accion = "listar";

        switch (accion) {

            case "listar":
                listar(request, response);
                break;

            case "buscar":
                buscar(request, response);
                break;

            case "editar":
                editar(request, response);
                break;

            case "eliminar":
                eliminar(request, response);
                break;

            default:
                listar(request, response);
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
        }
    }

    // ======================
    // MÉTODOS CRUD
    // ======================

    private void listar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<sesion> lista = dao.listarSesion();
        request.setAttribute("listaSesion", lista);
        request.getRequestDispatcher("listarSesion.jsp").forward(request, response);
    }

    private void buscar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id_sesion"));
        sesion s = dao.consultarSesion(id);

        request.setAttribute("sesion", s);
        request.getRequestDispatcher("editarSesion.jsp").forward(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id_sesion"));
        sesion s = dao.consultarSesion(id);

        request.setAttribute("sesion", s);
        request.getRequestDispatcher("editarSesion.jsp").forward(request, response);
    }

    private void agregar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        sesion s = new sesion();

        s.setNombre_sesion(request.getParameter("nombre_sesion"));
        s.setDescripcions(request.getParameter("descripcions"));
        s.setFecha_inicio(request.getParameter("fecha_inicio"));
        s.setFecha_fin(request.getParameter("fecha_fin"));
        s.setLugar(request.getParameter("lugar"));

        dao.agregarSesion(s);

        response.sendRedirect("agregarSesion.jsp");
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        sesion s = new sesion();

        s.setId_sesion(Integer.parseInt(request.getParameter("id_sesion")));
        s.setNombre_sesion(request.getParameter("nombre_sesion"));
        s.setDescripcions(request.getParameter("descripcions"));
        s.setFecha_inicio(request.getParameter("fecha_inicio"));
        s.setFecha_fin(request.getParameter("fecha_fin"));
        s.setLugar(request.getParameter("lugar"));

        dao.actualizarSesion(s);

        response.sendRedirect("SesionServlet?accion=listar");
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        int id = Integer.parseInt(request.getParameter("id_sesion"));
        dao.eliminarSesion(id);

        response.sendRedirect("SesionServlet?accion=listar");
    }
}