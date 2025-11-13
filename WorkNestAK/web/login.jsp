<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>WorkNest - Login</title>
    <style>
        body {
            font-family: 'Segoe UI', sans-serif;
            background: linear-gradient(135deg, #6aa6f8, #b3d1ff);
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .login-box {
            background-color: white;
            padding: 40px;
            border-radius: 15px;
            box-shadow: 0px 4px 10px rgba(0,0,0,0.2);
            width: 350px;
        }
        h1 {
            text-align: center;
            color: #333;
            margin-bottom: 30px;
        }
        input {
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
    <div class="login-box">
        <h1>WorkNest</h1>
        <% 
    String error = request.getParameter("error");
    if (error != null) {
        if (error.equals("1")) {
%>
            <p style="color:red; text-align:center;">Usuario o clave incorrectos</p>
<%      } else if (error.equals("2")) { %>
            <p style="color:red; text-align:center;">Error de conexión, intente más tarde</p>
<%      } else if (error.equals("perfil")) { %>
            <p style="color:red; text-align:center;">Perfil desconocido</p>
<%      }
    }
%>

        <form action="LoginServlet" method="post">
            <input type="text" name="usuario" placeholder="Usuario" required>
            <input type="password" name="clave" placeholder="Clave" required>
            <button type="submit">Ingresar</button>
        </form>
        <a href="registro.jsp">¿No tienes cuenta? Regístrate</a>
    </div>
</body>
</html>
