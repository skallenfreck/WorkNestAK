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
    <title>WorkNest - Dashboard</title>

    <!-- FullCalendar -->
    <link href='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/main.min.css' rel='stylesheet' />
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/main.min.js'></script>
    <script src='https://cdn.jsdelivr.net/npm/fullcalendar@6.1.8/locales/es.js'></script>

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
            display: grid;
            grid-template-columns: 350px auto;
            height: calc(100vh - 70px);
        }

        /* Calendario */
        #calendar {
            padding: 20px;
            background: #f4f6f9;
            overflow-y: auto;
            border-right: 2px solid #e0e0e0;
        }

        /* Panel derecho */
        .panel-derecho {
            padding: 30px;
        }

        h2 {
            margin-bottom: 20px;
        }

        .btn-menu {
            display: block;
            width: 220px;
            background: #3f51b5;
            color: white;
            padding: 14px;
            margin-bottom: 20px;
            text-align: center;
            border-radius: 8px;
            text-decoration: none;
            font-size: 16px;
            transition: 0.3s;
        }

        .btn-menu:hover {
            background: #2c3e94;
            transform: scale(1.03);
        }
        
        .btnLogout {
    background-color: #e74c3c;
    color: white;
    padding: 8px 15px;
    border-radius: 5px;
    text-decoration: none;
    border: none;
    cursor: pointer;
}

.btnLogout:hover {
    background-color: #c0392b;
}

    </style>
</head>

<body>

<header>
    <div>
        Hola, <strong><%= usuario %></strong>
    </div>

    <div class="right-buttons">
        <form action="perfil.jsp" style="display:inline;">
            <button type="submit">Ver Perfil</button>
        </form>

        <form action="cerrarSesionServlet" method="get">
            <button type="submit" class="btnLogout">Cerrar sesión</button>
        </form>
    </div>
</header>

<div class="contenido">

    <!-- Calendario izquierdo -->
    <div id="calendar"></div>

    <!-- Menú derecho -->
    <div class="panel-derecho">
        <h2>Panel Principal</h2>

        <!-- Estos enlaces pueden abrir el mismo dashboard con contenido a la derecha -->
        <a href="dashboard.jsp?modulo=actividad" class="btn-menu">Actividad</a>
        <a href="dashboard.jsp?modulo=sesion" class="btn-menu">Sesión</a>

        <%
            String modulo = request.getParameter("modulo");

            if ("actividad".equals(modulo)) {
        %>
                <h3>Gestión de Actividades</h3>
                <ul>
                    <li><a href="agregarActividad.jsp">Agregar Actividad</a></li>
                    <li><a href="editarActividad.jsp">Editar Actividad</a></li>
                    <li><a href="listarActividad.jsp">Listar Actividades</a></li>
                    <li><a href="eliminarActividad.jsp">Eliminar Actividad</a></li>
                </ul>
        <%
            } else if ("sesion".equals(modulo)) {
        %>
                <h3>Gestión de Sesiones</h3>
                <ul>
                    <li><a href="agregarSesion.jsp">Agregar Sesión</a></li>
                    <li><a href="editarSesion.jsp">Editar Sesión</a></li>
                    <li><a href="listarSesion.jsp">Listar Sesiones</a></li>
                    <li><a href="eliminarSesion.jsp">Eliminar Sesión</a></li>
                </ul>
        <%
            }
        %>
    </div>

</div>

<!-- Script para activar FullCalendar -->
<script>
    document.addEventListener('DOMContentLoaded', function () {
        var calendarEl = document.getElementById('calendar');

        var calendar = new FullCalendar.Calendar(calendarEl, {
            initialView: 'dayGridMonth',
            locale: 'es',
            height: 'auto',
        });

        calendar.render();
    });
</script>

</body>
</html>
