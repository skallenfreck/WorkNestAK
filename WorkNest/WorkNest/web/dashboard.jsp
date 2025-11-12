<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
</head>
<body>
    <h2>Bienvenido, <c:out value="${sessionScope.nUsuario}"/>!</h2>

    <h3>Panel de control</h3>
    <ul>
        <li><a href="ControladorUsuario?accion=listar">Ver usuarios</a></li>
        <li><a href="logout.jsp">Cerrar sesión</a></li>
    </ul>
</body>
</html>
