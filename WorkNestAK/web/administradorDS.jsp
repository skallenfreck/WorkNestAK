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
            background: #D4C9BE;
            margin: 0;
            font-family: 'Poppins', sans-serif;
            display: flex;
            flex-direction: column;
            height: 100vh;
        }

        .contenido {
            flex: 1;             
            display: flex;
            flex-direction: column;
            justify-content: center; 
            align-items: center;     
            padding: 40px;
        }

        /* Encabezado */
        header {
            background: #123458;
            color: #D4C9BE;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 40px;
            font-size: 1.5em;        
            height: 100px;           
            box-shadow: 0 2px 5px rgba(0,0,0,0.2);
        }

        header .right-buttons {
            display: flex;           
            gap: 15px;               
            align-items: center;     
        }
        
        header .right-buttons button {
            padding: 12px 20px;
            background: #D4C9BE;
            color: #123458;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            font-weight: bold;
            font-size: 14px;
            min-width: 140px;   
            transition: 0.3s;
        }

        header .right-buttons button:hover {
            background: #F1EFEC;
        }

        /* Cuerpo */
        .contenido {
            padding: 40px;
            text-align: center;
        }

        h2 {
            color: #123458;
            font-size: 26px;
            margin-bottom: 30px;
        }

        .btn-admin {
            display: block;
            width: 260px;
            background: #123458;
            color: #D4C9BE;
            padding: 15px;
            margin: 15px auto;
            text-align: center;
            border-radius: 8px;
            text-decoration: none;
            font-size: 16px;
            transition: 0.3s;
        }

        .btn-admin:hover {
            background: #123460;
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

        <form action="cerrarSesionServlet" method="get">
            <button type="submit" class="btnLogout">Cerrar sesión</button>
        </form>
    </div>
</header>

<div class="contenido">
    <h2>Gestión de Usuarios</h2>

    <a href="agregarUsuario.jsp" class="btn-admin">Agregar Usuario</a>
    <a href="editarUsuario.jsp" class="btn-admin">Editar Usuario</a>
    <a href="usuarioServlet?accion=listar" class="btn-admin">Listar Usuarios</a>
    <a href="eliminarUsuario.jsp" class="btn-admin">Eliminar Usuario</a>
</div>

</body>
</html>