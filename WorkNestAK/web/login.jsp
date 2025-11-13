<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WorkNest | Iniciar Sesión</title>

    <style>
        * {
            box-sizing: border-box;
            font-family: "Poppins", sans-serif;
        }

        body {
            background: linear-gradient(135deg, #89f7fe, #66a6ff);
            height: 100vh;
            margin: 0;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .login-container {
            background: #fff;
            padding: 40px 35px;
            border-radius: 20px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
            width: 350px;
            text-align: center;
            transition: transform 0.3s ease;
        }

        .login-container:hover {
            transform: scale(1.03);
        }

        .login-container h1 {
            color: #2b2b2b;
            font-size: 2em;
            margin-bottom: 20px;
        }

        .input-group {
            margin-bottom: 18px;
            text-align: left;
        }

        .input-group label {
            font-size: 14px;
            color: #555;
            display: block;
            margin-bottom: 5px;
        }

        .input-group input {
            width: 100%;
            padding: 10px 12px;
            border: 1px solid #ccc;
            border-radius: 10px;
            font-size: 15px;
            outline: none;
            transition: border 0.3s;
        }

        .input-group input:focus {
            border: 1px solid #66a6ff;
        }

        .btn {
            width: 100%;
            background: #66a6ff;
            color: white;
            border: none;
            padding: 12px;
            border-radius: 10px;
            cursor: pointer;
            font-size: 16px;
            margin-top: 10px;
            transition: background 0.3s;
        }

        .btn:hover {
            background: #4d8ff7;
        }

        .extra-links {
            margin-top: 15px;
            font-size: 14px;
        }

        .extra-links a {
            color: #66a6ff;
            text-decoration: none;
            margin: 0 5px;
            transition: color 0.3s;
        }

        .extra-links a:hover {
            color: #3d73d9;
        }

        @media (max-width: 400px) {
            .login-container {
                width: 90%;
                padding: 30px 25px;
            }
        }
    </style>
</head>
<body>

<div class="login-container">
    <h1>WorkNest</h1>

    <!-- Formulario de login -->
    <form action="LoginServlet" method="post">
        <div class="input-group">
            <label for="usuario">Usuario</label>
            <input type="text" id="usuario" name="usuario" placeholder="Ingresa tu usuario" required>
        </div>

        <div class="input-group">
            <label for="clave">Contraseña</label>
            <input type="password" id="clave" name="clave" placeholder="Ingresa tu contraseña" required>
        </div>

        <button type="submit" class="btn">Ingresar</button>

        <div class="extra-links">
            <a href="registro.jsp">Registrarse</a> |
            <a href="recuperarClave.jsp">¿Olvidó su contraseña?</a>
        </div>
    </form>

    <% 
        // Mensaje de error (opcional)
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
        <p style="color: red; margin-top: 10px;"><%= error %></p>
    <% } %>
</div>

</body>
</html>
