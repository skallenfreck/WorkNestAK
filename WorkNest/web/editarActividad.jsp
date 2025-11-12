<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="modelo.Actividad" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Actividad</title>
    </head>
    <body>
        <h2>Editar Actividad</h2>

        <%
            // Obtener la actividad desde el atributo de la solicitud
            Actividad actividad = (Actividad) request.getAttribute("actividad");
            if (actividad == null) {
                // Si la actividad no se encuentra, mostrar un mensaje de error
                out.println("<p>No se encontró la actividad.</p>");
            } else {
        %>

        <!-- Formulario para editar la actividad -->
        <form action="ControladorActividad?accion=editar" method="post">
            <input type="hidden" name="idActividad" value="<%= actividad.getIdActividad()%>" />

            <label for="nombreActividad">Nombre de la Actividad:</label>
            <input type="text" name="nombreActividad" value="<%= actividad.getNombreActividad()%>" required /><br>

            <label for="descripcion">Descripción:</label>
            <textarea name="descripcion" required><%= actividad.getDescripcion()%></textarea><br>

            <label for="enlace">Enlace:</label>
            <input type="text" name="enlace" value="<%= actividad.getEnlace()%>" required /><br>

            <input type="submit" value="Actualizar Actividad" />
        </form>

        <%
            }
        %>

        <br/>
        <a href="misActividades.jsp">Volver a mis Actividades</a>
    </body>
</html>
