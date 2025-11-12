<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Listado de Usuarios</title>
</head>
<body>
    <h2>Lista de Usuarios</h2>

    <!-- Mostrar mensaje de error si existe -->
    <c:if test="${not empty mensajeError}">
        <p style="color: red;">${mensajeError}</p>
    </c:if>

    <!-- Tabla para mostrar los usuarios -->
    <table border="1">
        <thead>
            <tr>
                <th>ID</th>
                <th>Identificación</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Email</th>
                <th>Usuario</th>
                <th>Perfil</th>
            </tr>
        </thead>
        <tbody>
            <!-- Iterar sobre la lista de usuarios -->
            <c:forEach var="usuario" items="${usuarios}">
                <tr>
                    <td><c:out value="${usuario.id}"/></td>
                    <td><c:out value="${usuario.identificacion}"/></td>
                    <td><c:out value="${usuario.nombre}"/></td>
                    <td><c:out value="${usuario.apellido}"/></td>
                    <td><c:out value="${usuario.email}"/></td>
                    <td><c:out value="${usuario.usuario}"/></td>
                    <td><c:out value="${usuario.idperfil}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>

    <br/>
    <a href="index.jsp">Regresar al inicio</a>
</body>
</html>
