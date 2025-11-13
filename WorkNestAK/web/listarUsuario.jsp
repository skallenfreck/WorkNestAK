<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    // Recuperar usuario en sesión
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String nombreUsuario = (String) sesion.getAttribute("usuario");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Panel</title>
    <style>
        body { font-family: Arial; margin: 0; padding: 0; }
        header {
            background: #4A76A8; color: white;
            padding: 15px; display: flex; justify-content: space-between;
            align-items: center;
        }
        .btn-header {
            background: white; color: #4A76A8;
            padding: 8px 12px; border-radius: 5px;
            text-decoration: none; margin-left: 10px;
            font-weight: bold;
        }
        .contenedor {
            display: flex;
            height: calc(100vh - 70px);
        }
        .izquierda {
            width: 25%;
            background: #F0F0F0;
            padding: 20px;
        }
        .derecha {
            width: 75%;
            padding: 20px;
        }
        input, select {
            width: 90%; padding: 8px; margin-bottom: 10px;
        }
        button {
            padding: 10px 15px; background: #4A76A8;
            color: white; border: none; border-radius: 5px;
            cursor: pointer;
        }
    </style>
</head>
<body>

<header>
    <h2>Hola, <%= nombreUsuario %></h2>

    <div>
        <a class="btn-header" href="verPerfil.jsp">Ver Perfil</a>
        <a class="btn-header" href="cerrarSesionServlet">Cerrar Sesión</a>
    </div>
</header>

<div class="contenedor">
    <div class="izquierda">
        <h3>Panel Administrador</h3>

        <a href="agregarUsuario.jsp">Agregar Usuario</a><br><br>
        <a href="editarUsuario.jsp">Editar Usuario</a><br><br>
        <a href="listarUsuarios.jsp">Listar Usuarios</a><br><br>
        <a href="eliminarUsuario.jsp">Eliminar Usuario</a><br><br>
    </div>

    <div class="derecha">
<h2>Lista de Usuarios</h2>

<table border="1" cellpadding="10">
    <tr>
        <th>ID</th>
        <th>Identificación</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Email</th>
        <th>Usuario</th>
        <th>Perfil</th>
    </tr>

<%
    dao.UsuarioDAO dao = new dao.UsuarioDAO();
    java.util.List<modelo.Usuario> lista = dao.listarUsuarios();

    for (modelo.Usuario u : lista) {
%>
    <tr>
        <td><%= u.getId() %></td>
        <td><%= u.getIdentificacion() %></td>
        <td><%= u.getNombre() %></td>
        <td><%= u.getApellido() %></td>
        <td><%= u.getEmail() %></td>
        <td><%= u.getUsuario() %></td>
        <td><%= u.getId_perfil() == 1 ? "Estudiante" : "Administrador" %></td>
    </tr>
<%
    }
%>

</table>

</div></div>
</body>
</html>

