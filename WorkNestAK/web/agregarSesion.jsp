<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Calendar" %>
<%@ page import="java.util.GregorianCalendar" %>

<%
    // Recuperar usuario en sesión
    HttpSession sesion = request.getSession(false);
    if (sesion == null || sesion.getAttribute("usuario") == null) {
        response.sendRedirect("login.jsp");
        return;
    }

    String nombreUsuario = (String) sesion.getAttribute("usuario");
%>

<%
    // === GENERAR CALENDARIO DEL MES ACTUAL ===
    Calendar cal = new GregorianCalendar();
    int year = cal.get(Calendar.YEAR);
    int month = cal.get(Calendar.MONTH); // 0 = Enero
    int today = cal.get(Calendar.DAY_OF_MONTH);

    cal.set(Calendar.DAY_OF_MONTH, 1);
    int firstDayOfWeek = cal.get(Calendar.DAY_OF_WEEK); // día en que inicia el mes
    int daysInMonth = cal.getActualMaximum(Calendar.DAY_OF_MONTH);

    String[] meses = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio",
            "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    String[] dias = {"Dom", "Lun", "Mar", "Mié", "Jue", "Vie", "Sáb"};
%>

<html>
<head>
    <title>Dashboard</title>

    <style>
        body {
            margin: 0;
            padding: 0;
            font-family: Arial;
            background: #f4f4f4;
        }

        .contenedor {
            display: flex;
            height: 100vh;
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
        
        /* PANEL IZQUIERDO */
        .izquierda {
            width: 25%;
            background: #D4C9BE;
            padding: 20px;
            box-shadow: 2px 0 5px rgba(0,0,0,0.1);
            overflow-y: auto;
        }

        .titulo-cal {
            text-align: center;
            color: #123458;
            font-size: 20px;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .contenedor-calendario {
            width: 100%;
            overflow-x: auto; /* evita desbordes */
        }

        table.calendario {
            width: 100%;
            max-width: 100%;
            border-collapse: collapse;
            table-layout: fixed;
            background: #fff;
        }

        table.calendario th {
            background: #263238;
            color: white;
            padding: 8px;
            border: 1px solid #ddd;
            text-align: center;
        }

        table.calendario td {
            padding: 10px;
            border: 1px solid #ddd;
            height: 45px;
            text-align: center;
        }

        .hoy {
            background: #00bcd4;
            color: white;
            font-weight: bold;
            border-radius: 5px;
        }

        /* PANEL DERECHO */
        .derecha {
            background: #F1EFEC;
            width: 75%;
            justify-content: center;       
            align-items: flex-start; 
            padding-top: 40px;
            padding-left: 20px;
            padding-right: 20px;
        }

        .titulo {
            font-size: 28px;
            color: #123458;
            margin-bottom: 10px;
            font-weight: bold;
        }

        .descripcion {
            font-size: 16px;
            color: #444;
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
        .form-container button {
            align-self: center;
            margin-top: 10px;
            margin-bottom: 5px;   /* Esto asegura que NO quede por fuera */
        }
        
        h2 {
            color: #123458;
            font-size: 26px;
            margin-bottom: 30px;
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

        <div class="titulo-cal">
            <%= meses[month] %> <%= year %>
        </div>

        <div class="contenedor-calendario">
            <table class="calendario">
                <tr>
                    <% for (String d : dias) { %>
                    <th><%= d %></th>
                    <% } %>
                </tr>

                <tr>
                    <% 
                        int col = 1;
                        for (int i = 1; i < firstDayOfWeek; i++) {
                    %>
                        <td></td>
                    <% col++; } %>

                    <% for (int day = 1; day <= daysInMonth; day++) { %>
                        <% 
                            boolean isToday = (day == today);
                        %>

                        <td class="<%= isToday ? "hoy" : "" %>">
                            <%= day %>
                        </td>

                        <% 
                            if (col % 7 == 0) out.print("</tr><tr>");
                            col++;
                        %>
                    <% } %>

                </tr>
            </table>
        </div>

    </div>

    <div class="derecha">
        <h2>Agregar Sesion</h2>
        
        <form action="SesionServlet" method="post">
                <input type="hidden" name="accion" value="agregar">
                
                <input type="text" name="nombre_sesion" placeholder="Nombre" required>
                <input type="text" name="descripciones" placeholder="Descripcion" required>
                <input type="date" name="fecha_inicio" placeholder="Inicio" required>
                <input type="date" name="fecha_fin" placeholder="Fin" required>
                <input type="text" name="lugar" placeholder="Lugar" required>
                
                <button type="submit">Agregar</button>
        </form>

    
    </div>

</div>

</body>
</html>