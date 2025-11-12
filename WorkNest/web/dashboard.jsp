<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dashboard</title>
</head>
<body>
    <h2>Bienvenido, <%= session.getAttribute("nUsuario") %>!</h2>

    <h3>Panel de control</h3>
    <ul>
        <% 
            // Obtener el perfil desde la sesión
            Integer perfil = (Integer) session.getAttribute("perfil");

            // Mostrar opciones basadas en el perfil
            if (perfil != null) {
                if (perfil == 1) { // Si el perfil es de Administrador (idperfil == 1)
        %>
                    <li><a href="ControladorUsuario?accion=listar">Ver usuarios</a></li>
                    <li><a href="agregarUsuario.jsp">Agregar usuario</a></li>
                    <li><a href="editarUsuario.jsp">Editar usuario</a></li>
                    <li><a href="eliminarUsuario.jsp">Eliminar usuario</a></li>
        <% 
                } else if (perfil == 2) { // Si el perfil es de Usuario (idperfil == 2)
        %>
                    <li><a href="misActividades.jsp">Mis actividades</a></li>
                    <li><a href="agregarActividad.jsp">Agregar actividad</a></li>
                    <li><a href="editarActividad.jsp">Editar actividad</a></li>
        <% 
                }
            }
        %>
        <!-- Opción común para todos los usuarios -->
        <li><a href="logout.jsp">Cerrar sesión</a></li>
    </ul>
</body>
</html>
