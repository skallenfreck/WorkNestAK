<%@page import="java.util.List"%>
<%@page import="modelo.Usuario"%>
<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Identificación</th>
            <th>Nombre</th>
            <th>Apellido</th>
            <th>Email</th>
            <th>Usuario</th>
            <th>Perfil</th>
        </tr>
    </thead>
    <tbody>
        <% 
            List<Usuario> usuarios = (List<Usuario>) request.getAttribute("usuarios");
            for (Usuario usuario : usuarios) {
        %>
            <tr>
                <td><%= usuario.getId() %></td>
                <td><%= usuario.getIdentificacion() %></td>
                <td><%= usuario.getNombre() %></td>
                <td><%= usuario.getApellido() %></td>
                <td><%= usuario.getEmail() %></td>
                <td><%= usuario.getUsuario() %></td>
                <td><%= usuario.getIdperfil() %></td>
            </tr>
        <% } %>
    </tbody>
</table>
