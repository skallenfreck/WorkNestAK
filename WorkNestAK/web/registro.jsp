<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #D4C9BE;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: #123458;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
            width: 380px;
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #D4C9BE;
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
            margin: 8px 8px;
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
        button:hover {
            background-color: #F1EFEC;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 12px;
            color: #D4C9BE;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Nuevo Usuario</h2>
        <form action="registroServlet" method="post">
    <input type="text" name="identificacion" placeholder="Identificación" required>
    <input type="text" name="nombre" placeholder="Nombre" required>
    <input type="text" name="apellido" placeholder="Apellido" required>
    <input type="email" name="email" placeholder="Correo electrónico" required>
    <input type="text" name="usuario" placeholder="Usuario" required>
    <input type="password" name="clave" placeholder="Clave" required>
    <select name="id_perfil" required>
        <option value="">Seleccione perfil</option>
        <option value="1">Estudiante</option>
        <option value="2">Administrador</option>
    </select>
    <button type="submit">Registrar</button>
</form>

        <a href="login.jsp">Volver al login</a>
    </div>
</body>
</html>
