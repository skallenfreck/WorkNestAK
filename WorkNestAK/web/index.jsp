<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Bienvenido a WorkNest</title>
  <style>
    body {
      margin: 0;
      height: 100vh;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      background: linear-gradient(135deg, #89f7fe, #66a6ff);
      font-family: "Poppins", sans-serif;
      color: white;
      text-align: center;
    }
    h1 {
      font-size: 3em;
      margin-bottom: 10px;
    }
    p {
      font-size: 1.2em;
      margin-bottom: 30px;
    }
    .btn {
      background: white;
      color: #66a6ff;
      border: none;
      padding: 12px 25px;
      border-radius: 25px;
      font-size: 1.1em;
      cursor: pointer;
      transition: all 0.3s;
    }
    .btn:hover {
      background: #4d8ff7;
      color: white;
      transform: scale(1.05);
    }
  </style>
</head>
<body>
  <h1>WorkNest</h1>
  <p>Organiza tus tareas y mejora tu productividad estudiantil</p>
  <form action="login.jsp">
    <button class="btn" type="submit">Iniciar Sesión</button>
  </form>
</body>
</html>
