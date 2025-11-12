<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Cerrar sesión</title>
</head>
<body>
    <h2>Has cerrado sesión exitosamente.</h2>
    <p><a href="login.jsp">Volver al inicio de sesión</a></p>

    <%
        // Invalidar la sesión actual para cerrar sesión del usuario
        session.invalidate();
    %>
</body>
</html>
