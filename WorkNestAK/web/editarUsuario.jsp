<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="modelo.usuario" %>
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
    <title>Editar Usuario</title>
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
        .form-container {
            background-color: #123458;
            padding: 20px 30px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
            width: 380px;
            margin-top: 0;
            display: flex;
            flex-direction: column;
            gap: 12px;
        }
        .contenedor {
            display: flex;
            height: calc(100vh - 70px);
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
            justify-content: center;       
            align-items: flex-start; 
            padding-top: 40px;
            padding-left: 20px;
            padding-right: 20px;
        }
        h1 {
            color: #D4C9BE;
            font-size: 28px;
        }
        h2 {
            color: #D4C9BE;
            font-size: 22px;
            
        }
        h3 {
            color: #123458;
            font-size: 22px;
        }
        input {
            background: #D4C9BE;
            width: 90%;
            padding: 12px;
            margin: 8px 8px;
            border-radius: 8px;
            border: 1px solid #ccc;
        }
        select {
            background: #D4C9BE;
            width: 97%;
            padding: 12px;
            margin: 10px 10px;
            border-radius: 8px;
            border: 1px solid #ccc;
        }
        button {
            display: block; 
            width: 40%;
            margin: 10px auto;
            tex-aling: center;
            background-color: #D4C9BE;
            color: #123458;
            padding: 10px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 15px;
        }
        label {
            color: #D4C9BE;
        }
        .form-container button {
            align-self: center;
            margin-top: 10px;
            margin-bottom: 5px;   /* Esto asegura que NO quede por fuera */
        }
        .btn-usuario:hover {
            background: #123460;
            transform: scale(1.03);
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
        <div class="form-container">
            <h2>Editar Usuario</h2>
            
            <%
            usuario u = (usuario) request.getAttribute("usuario");
        %>


            <% if (u == null) { %>
            <form method="get" action="usuarioServlet">
                <label>Ingrese la Identificación:</label>
                <input type="text" name="identificacion" required>
                <button type="submit" name="accion" value="buscar">Buscar</button>
            </form>
            <% } else { %>
            <form method="post" action="usuarioServlet">
                <input type="hidden" name="accion" value="actualizar">
                <input type="text" name="identificacion" value="<%= u.getIdentificacion() %>" readonly>
                <input type="text" name="nombre" value="<%= u.getNombre() %>" required>
                <input type="text" name="apellido" value="<%= u.getApellido() %>" required>
                <input type="email" name="email" value="<%= u.getEmail() %>" required>
                <input type="text" name="usuario" value="<%= u.getUsuario() %>" required>
                <input type="password" name="clave" value="<%= u.getClave() %>" required>
                
                <select name="id_perfil" required>
                    <option value="">Seleccione perfil</option>
                    <option value="1">Estudiante</option>
                    <option value="2">Administrador</option>
                </select>
          
                <button type="submit" name="accion" value="actualizar">Editar</button>
            </form>
        <% } %>
            
        </div>
        
</body>
</html>

