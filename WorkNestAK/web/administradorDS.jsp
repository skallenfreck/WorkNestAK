<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Obtener usuario desde sesión
    String usuario = (String) session.getAttribute("usuario");

    // Si no hay usuario, redirigir al login
    if (usuario == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Administrador - WorkNest</title>

    <style>
        body {
            margin: 0;
            font-family: 'Poppins', sans-serif;
            display: grid;
            grid-template-rows: 70px auto;
            height: 100vh;
        }

        /* Encabezado */
        header {
            background: #3f51b5;
            color: white;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 30px;
            font-size: 1.2em;
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }

        header .right-buttons button {
            margin-left: 15px;
            padding: 8px 15px;
            background: white;
            color: #3f51b5;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            transition: 0.3s;
        }

        header .right-buttons button:hover {
            background: #ccc;
        }

        /* Cuerpo */
        .contenido {
            padding: 40px;
            text-align: center;
        }

        h2 {
            font-size: 26px;
            margin-bottom: 30px;
        }

        .btn-admin {
            display: block;
            width: 260px;
            background: #3f51b5;
            color: white;
            padding: 15px;
            margin: 15px auto;
            text-align: center;
            border-radius: 8px;
            text-decoration: none;
            font-size: 16px;
            transition: 0.3s;
        }

        .btn-admin:hover {
            background: #2c3e94;
            transform: scale(1.03);
        }
    </style>
</head>

<body>

<header>
    <div>
        Panel de Administrador — Bienvenido, <strong><%= usuario %></strong>
    </div>

    <div class="right-buttons">
        <form action="perfil.jsp" style="display:inline;">
            <button type="submit">Ver Perfil</button>
        </form>

        <form action="logout.jsp" method="post" style="display:inline;">
            <button type="submit">Cerrar Sesión</button>
        </form>
    </div>
</header>

<div class="contenido">
    <h2>Gestión de Usuarios</h2>

    <a href="agregarUsuario.jsp" class="btn-admin">Agregar Usuario</a>
    <a href="editarUsuario.jsp" class="btn-admin">Editar Usuario</a>
    <a href="listarUsuarios.jsp" class="btn-admin">Listar Usuarios</a>
    <a href="eliminarUsuario.jsp" class="btn-admin">Eliminar Usuario</a>
</div>

</body>
</html>