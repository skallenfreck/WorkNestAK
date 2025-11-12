<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <h2>Eliminar Usuario</h2>

        <!-- Mostrar mensaje de error si existe -->
        <c:if test="${not empty mensajeError}">
            <p style="color: red;">${mensajeError}</p>
        </c:if>

        <!-- Mostrar el formulario de eliminación -->
        <form action="ControladorUsuario?accion=eliminar" method="post">
            <label for="identificacion">Identificación del usuario a eliminar:</label>
            <input type="text" name="identificacion" required/><br>

            <input type="submit" value="Eliminar Usuario"/>
        </form>

        <br/>
        <a href="dashboard.jsp">Volver al Dashboard</a>
    </body>
</html>
