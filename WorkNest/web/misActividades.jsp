<%@ page import="modelo.Actividad"%>
<%@ page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mis Actividades</title>
    </head>
    <body>
        <h2>Mis Actividades</h2>
        <h3>Listado de Actividades</h3>

        <%
            // Obtener las actividades desde el atributo de la solicitud
            List<Actividad> actividades = (List<Actividad>) request.getAttribute("actividades");
            if (actividades == null || actividades.isEmpty()) {
        %>
        <p>No tienes actividades registradas.</p>
        <%
        } else {
        %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Enlace</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    // Recorrer la lista de actividades y mostrarlas en la tabla
                    for (Actividad actividad : actividades) {
                %>
                <tr>
                    <td><%= actividad.getIdActividad()%></td>
                    <td><%= actividad.getNombreActividad()%></td>
                    <td><%= actividad.getDescripcion()%></td>
                    <td><%= actividad.getEnlace()%></td>
                    <td>
                        <!-- Enlace para eliminar la actividad -->
                        <a href="ControladorActividad?accion=eliminar&id=<%= actividad.getIdActividad()%>">Eliminar</a> |
                        <!-- Enlace para editar la actividad -->
                        <a href="ControladorActividad?accion=editar&id=<%= actividad.getIdActividad()%>">Editar</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        %>

        <br/>
        <a href="agregarActividad.jsp">Agregar nueva actividad</a><br/>
        <a href="dashboard.jsp">Volver al Dashboard</a>
    </body>
</html>
