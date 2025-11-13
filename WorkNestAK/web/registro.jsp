<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Usuario</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background-color: #eef2f3;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .form-container {
            background-color: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
            width: 380px;
        }
        h2 {
            text-align: center;
            margin-bottom: 25px;
            color: #333;
        }
        input, select {
            width: 100%;
            padding: 12px;
            margin: 8px 0;
            border-radius: 8px;
            border: 1px solid #ccc;
        }
        button {
            width: 100%;
            background-color: #4a90e2;
            color: white;
            padding: 12px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            font-size: 15px;
        }
        button:hover {
            background-color: #357ab8;
        }
        a {
            display: block;
            text-align: center;
            margin-top: 12px;
            color: #4a90e2;
            text-decoration: none;
        }
    </style>
</head>
<body>
    <div class="form-container">
        <h2>Registrar Usuario</h2>
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
