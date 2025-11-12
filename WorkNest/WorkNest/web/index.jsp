<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Página de Inicio</title>
</head>
<body>
    <h1>Bienvenido al Gestor Académico</h1>
    
    <p><a href="login.jsp">Iniciar sesión</a></p>
    
    <c:if test="${not empty mensajeError}">
        <p style="color: red;">${mensajeError}</p>
    </c:if>
</body>
</html>
