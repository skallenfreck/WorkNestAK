package controlador;

import dao.ActividadDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Actividad;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ControladorActividad", urlPatterns = {"/ControladorActividad"})
public class ControladorActividad extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion"); // Obtenemos la acción desde la URL

        try {
            // Lógica para manejar la acción según el tipo de solicitud
            if ("listar".equals(accion)) {
                listarActividades(request, response);  // Llamar al método para listar actividades
            } else if ("eliminar".equals(accion)) {
                eliminarActividad(request, response);  // Llamar al método para eliminar actividad
            } else if ("agregar".equals(accion)) {
                agregarActividad(request, response);  // Llamar al método para agregar actividad
            } else if ("editar".equals(accion)) {
                editarActividad(request, response); // Llamar al método para editar actividad
            } else {
                response.sendRedirect("mensaje.jsp?mensaje=Acción no válida");
            }
        } catch (Exception e) {
            // Si ocurre algún error, redirigir al mensaje de error
            request.setAttribute("mensajeError", "Error en la operación: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        }
    }

    private void listarActividades(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener el ID del usuario desde la sesión
            int usuarioId = (Integer) request.getSession().getAttribute("usuarioId");

            // Llamar al DAO para obtener las actividades
            ActividadDAO actividadDAO = new ActividadDAO();
            List<Actividad> actividades = actividadDAO.listarActividadesPorUsuario(usuarioId);

            // Pasar las actividades al JSP
            request.setAttribute("actividades", actividades);
            request.getRequestDispatcher("misActividades.jsp").forward(request, response);
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Error al listar actividades: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        }
    }

    // Método para eliminar una actividad
    private void eliminarActividad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Obtener el ID de la actividad a eliminar desde la URL
            int idActividad = Integer.parseInt(request.getParameter("id"));

            // Llamar al DAO para eliminar la actividad
            ActividadDAO actividadDAO = new ActividadDAO();
            int status = actividadDAO.eliminarActividad(idActividad);

            if (status > 0) {
                response.sendRedirect("mensaje.jsp?mensaje=Actividad eliminada con éxito");
            } else {
                request.setAttribute("mensajeError", "No se pudo eliminar la actividad.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
            }
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Error al eliminar actividad: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
        }
    }

    private void agregarActividad(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Obtener los datos del formulario
            String nombreActividad = request.getParameter("nombreActividad");
            String descripcion = request.getParameter("descripcion");
            String enlace = request.getParameter("enlace");

            // Verificar si los datos no están vacíos
            if (nombreActividad == null || nombreActividad.trim().isEmpty()
                    || descripcion == null || descripcion.trim().isEmpty()
                    || enlace == null || enlace.trim().isEmpty()) {
                request.setAttribute("mensajeError", "Todos los campos son obligatorios.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                return;
            }

            // Obtener el ID del usuario desde la sesión
            String identificacion = (String) request.getSession().getAttribute("nUsuario");  // Usamos la identificación del usuario
            System.out.println("Identificación del usuario: " + identificacion);  // Imprimir la identificación para verificarla

            // Crear un nuevo objeto Actividad con los datos obtenidos
            Actividad nuevaActividad = new Actividad(0, nombreActividad, descripcion, enlace);

            // Usar el DAO para agregar la actividad a la tabla actividades
            ActividadDAO actividadDAO = new ActividadDAO();
            int status = actividadDAO.agregarActividad(nuevaActividad);

            if (status > 0) {
                // Obtener el ID de la actividad recién insertada
                int idActividad = actividadDAO.obtenerUltimoIdActividad();
                System.out.println("ID de la actividad recién insertada: " + idActividad);  // Imprimir el ID de la actividad para verificarlo

                // Ahora insertar la relación en la tabla gesactividad
                int statusRelacion = actividadDAO.insertarRelacionGesActividad(idActividad, identificacion);

                if (statusRelacion > 0) {
                    response.sendRedirect("mensaje.jsp?mensaje=Actividad agregada con éxito");  // Redirigir en caso de éxito
                } else {
                    request.setAttribute("mensajeError", "No se pudo establecer la relación entre usuario y actividad.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
                }
            } else {
                request.setAttribute("mensajeError", "No se pudo agregar la actividad.");
                request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
            }
        } catch (Exception e) {
            // Manejo de errores al agregar
            request.setAttribute("mensajeError", "Error al agregar la actividad: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);  // Redirigir en caso de error
        }
    }

    // Método para editar una actividad
    private void editarActividad(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Verificar si es una solicitud POST (cuando se envía el formulario)
            if ("POST".equals(request.getMethod())) {
                // Obtener los datos del formulario
                int idActividad = Integer.parseInt(request.getParameter("idActividad"));
                String nombreActividad = request.getParameter("nombreActividad");
                String descripcion = request.getParameter("descripcion");
                String enlace = request.getParameter("enlace");

                // Crear el objeto Actividad
                Actividad actividad = new Actividad(idActividad, nombreActividad, descripcion, enlace);

                // Llamar al método DAO para actualizar la actividad en la base de datos
                ActividadDAO actividadDAO = new ActividadDAO();
                int status = actividadDAO.editarActividad(actividad);

                if (status > 0) {
                    response.sendRedirect("mensaje.jsp?mensaje=Actividad actualizada con éxito");
                } else {
                    request.setAttribute("mensajeError", "No se pudo actualizar la actividad.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                }
            } else {
                // Obtener el ID de la actividad a editar
                int idActividad = Integer.parseInt(request.getParameter("id"));

                // Llamar al DAO para obtener los detalles de la actividad
                ActividadDAO actividadDAO = new ActividadDAO();
                Actividad actividad = actividadDAO.obtenerActividadPorId(idActividad);

                // Verificar si la actividad existe
                if (actividad == null) {
                    request.setAttribute("mensajeError", "Actividad no encontrada.");
                    request.getRequestDispatcher("mensaje.jsp").forward(request, response);
                    return;  // Terminar la ejecución para evitar enviar más datos
                }

                // Pasar la actividad al JSP para que se muestre en el formulario
                request.setAttribute("actividad", actividad);
                request.getRequestDispatcher("editarActividad.jsp").forward(request, response);
            }
        } catch (Exception e) {
            request.setAttribute("mensajeError", "Error al editar la actividad: " + e.getMessage());
            request.getRequestDispatcher("mensaje.jsp").forward(request, response);
        }
    }

    // Métodos HTTP GET y POST
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);  // Llamar al proceso de manejo de la solicitud
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);  // Llamar al proceso de manejo de la solicitud
    }

    // Descripción del servlet
    @Override
    public String getServletInfo() {
        return "Servlet para manejar operaciones de actividades";  // Información del servlet
    }
}
