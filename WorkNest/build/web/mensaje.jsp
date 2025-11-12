<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Mensaje</title>
</head>
<body>
    <h2>Resultado de la operación</h2>

    <!-- Mostrar mensaje de éxito o error -->
    <c:if test="${not empty mensajeError}">
        <p style="color: red;">${mensajeError}</p>
    </c:if>

    <c:if test="${not empty mensaje}">
        <p style="color: green;">${mensaje}</p>
    </c:if>

    <br/>
    <a href="dashboard.jsp">Volver al Dashboard</a>
</body>
</html>
