<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Iniciar sesión</h2>

    <form action="LoginController" method="post">
        <input type="hidden" name="accion" value="login"/>

        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required/><br/><br/>

        <label for="clave">Contraseña:</label>
        <input type="password" id="clave" name="clave" required/><br/><br/>

        <input type="submit" value="Iniciar sesión"/>
    </form>

    <c:if test="${not empty mensajeError}">
        <p style="color: red;">${mensajeError}</p>
    </c:if>
</body>
</html>
