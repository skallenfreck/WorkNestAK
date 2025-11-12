<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Usuario</title>
    </head>
    <body>
        <h2>Editar Usuario</h2>

        <!-- Mostrar mensaje de error si existe -->
    <c:if test="${not empty mensajeError}">
        <p style="color: red;">${mensajeError}</p>
    </c:if>

    <!-- Si hay un usuario, mostrar el formulario con los datos del usuario -->
    <form action="ControladorUsuario?accion=actualizar" method="post">
        <input type="hidden" name="id" value="${usuario.id}"/>

        <label for="identificacion">Identificación:</label>
        <input type="text" name="identificacion" value="${usuario.identificacion}" required/><br>

        <label for="nombre">Nombre:</label>
        <input type="text" name="nombre" value="${usuario.nombre}" required/><br>

        <label for="apellido">Apellido:</label>
        <input type="text" name="apellido" value="${usuario.apellido}" required/><br>

        <label for="email">Email:</label>
        <input type="email" name="email" value="${usuario.email}" required/><br>

        <label for="usuario">Usuario:</label>
        <input type="text" name="usuario" value="${usuario.usuario}" required/><br>

        <label for="clave">Clave:</label>
        <input type="password" name="clave" value="${usuario.clave}" required/><br>

        <label for="idperfil">Perfil:</label>
        <select name="idperfil" required>
            <option value="1" <c:if test="${usuario.idperfil == 1}">selected</c:if>>Administrador</option>
            <option value="2" <c:if test="${usuario.idperfil == 2}">selected</c:if>>Usuario</option>
        </select><br>

        <input type="submit" value="Actualizar Usuario"/>
    </form>


    <br/>
    <a href="dashboard.jsp">Volver al Dashboard</a>
</body>
</html>
