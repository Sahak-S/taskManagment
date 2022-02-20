<%@ page import="taskManagment.model.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/logout">Logout</a>
<% User user = (User) request.getAttribute("user"); %>

<div style="width: 50%;float: left">

    Edit Manager:<br>
    <form action="/editUser" method="post">
        <input type="hidden" name="id" value="<%=user.getId()%>" ><br>
        <input type="text" name="name" value="<%=user.getName()%>"><br>
        <input type="text" name="surname" value="<%=user.getSurname()%>"><br>
        <input type="text" name="email"value="<%=user.getEmail()%>"><br>
        <input type="text" name="password"><br>
        <select name="type">
            <option value="USER">USER</option>
            <option value="MANAGER">MANAGER</option>
        </select><br>
        <input type="submit" name="Edit User">
    </form>
</div>

</body>
</html>
