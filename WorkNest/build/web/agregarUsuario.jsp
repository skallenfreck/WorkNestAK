<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Agregar Usuario</title>
</head>
<body>
    <h2>Agregar Nuevo Usuario</h2>

    <!-- Formulario para agregar un nuevo usuario -->
    <form action="ControladorUsuario" method="post">
        <input type="hidden" name="accion" value="agregar" />
        
        <label for="identificacion">Identificación:</label>
        <input type="text" id="identificacion" name="identificacion" required />
        <br/>

        <label for="nombre">Nombre:</label>
        <input type="text" id="nombre" name="nombre" required />
        <br/>

        <label for="apellido">Apellido:</label>
        <input type="text" id="apellido" name="apellido" required />
        <br/>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required />
        <br/>

        <label for="usuario">Usuario:</label>
        <input type="text" id="usuario" name="usuario" required />
        <br/>

        <label for="clave">Contraseña:</label>
        <input type="password" id="clave" name="clave" required />
        <br/>

        <label for="idperfil">Perfil:</label>
        <select id="idperfil" name="idperfil" required>
            <option value="1">Administrador</option>
            <option value="2">Usuario</option>
        </select>
        <br/>

        <button type="submit">Agregar Usuario</button>
    </form>

    <br/>
    <a href="dashboard.jsp">Regresar al panel de control</a>
</body>
</html>
