<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agregar Actividad</title>
    </head>
    <body>
        <h2>Agregar Actividad</h2>

        <!-- Formulario para agregar actividad -->
        <form action="ControladorActividad?accion=agregar" method="post">
            <label for="nombreActividad">Nombre de la Actividad:</label>
            <input type="text" name="nombreActividad" required /><br>

            <label for="descripcion">Descripción:</label>
            <textarea name="descripcion" required></textarea><br>

            <label for="enlace">Enlace:</label>
            <input type="text" name="enlace" required /><br>

            <input type="submit" value="Agregar Actividad" />
        </form>


        <br/>
        <a href="dashboard.jsp">Volver al Dashboard</a>
    </body>
</html>
