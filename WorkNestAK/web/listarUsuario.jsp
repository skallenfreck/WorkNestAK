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
        body { 
            font-family: Arial; 
            margin: 0; 
            padding: 0; 
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
        .contenedor {
            display: flex;
            height: calc(100vh - 120px);
        }
        .izquierda {
            width: 25%;
            background: #D4C9BE;
            padding: 20px;
            display: flex;
            flex-direction: column;      
            justify-content: center;     
            align-items: center;         
            gap: 15px;                   
            text-align: center; 
        }
        .derecha {
            background: #F1EFEC;
            width: 75%;
            display: flex;  
            flex-direction: column;
            justify-content: flex-start;       
            align-items: center; 
            padding: 40px 20px;
            overflow-y: auto;
            box-sizing: border-box;
        }
        .derecha table {
            width: 100%;
            max-width: 900px;
            border-collapse: collapse;
            background: white;
        }
        .derecha table th, 
        .derecha table td {
            padding: 10px;
            text-align: center;
            font-size: 15px;
        }
        .derecha table th {
            background: #123458;
            color: #D4C9BE;
        }
        h1 {
            color: #D4C9BE;
            font-size: 28px;
        }
        h2 {
            color: #123458;
            font-size: 22px;
            
        }
        h3 {
            color: #123458;
            font-size: 22px;
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
    <h1>Hola, <%= nombreUsuario %></h1>

    <div class="right-buttons">
        <form action="perfil.jsp" style="display:inline;">
            <button type="submit">Ver Perfil</button>
        </form>

        <form action="cerrarSesionServlet" method="get">
            <button type="submit" class="btnLogout">Cerrar sesión</button>
        </form>
    </div>
</header>

<div class="contenedor">
    <div class="izquierda">
        <h3>Panel Administrador</h3>
        
        <a href="agregarUsuario.jsp" class="btn-admin">Agregar Usuario</a>
        <a href="editarUsuario.jsp" class="btn-admin">Editar Usuario</a>
        <a href="usuarioServlet?accion=listar" class="btn-admin">Listar Usuarios</a>
        <a href="eliminarUsuario.jsp" class="btn-admin">Eliminar Usuario</a>
    </div>

    <div class="derecha">
        <h2>Lista de Usuarios</h2>

        <table border="1" cellpadding="10">
            <tr>
                <th>Identificación</th>
                <th>Nombre</th>
                <th>Apellido</th>
                <th>Usuario</th>
                <th>Perfil</th>
            </tr>

<%
    java.util.List<modelo.usuario> lista = 
        (java.util.List<modelo.usuario>) request.getAttribute("listaUsuario");

    if (lista != null) {
        for (modelo.usuario u : lista) {
%>
            <tr>
                <td><%= u.getIdentificacion() %></td>
                <td><%= u.getNombre() %></td>
                <td><%= u.getApellido() %></td>
                <td><%= u.getUsuario() %></td>
                <td><%= u.getIdPerfil() == 1 ? "Estudiante" : "Administrador" %></td>
            </tr>
<%
        }
    }
%>


</table>

</div></div>
</body>
</html>

