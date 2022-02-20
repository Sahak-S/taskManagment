<%@ page import="taskManagment.model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: User
  Date: 20.02.2022
  Time: 19:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% List<User> users = (List<User>) request.getAttribute("users"); %>


<div>
<a href="/users">Back</a>
    Search Result:<br>
    <table border="1">
        <tr>
            <td>name</td>
            <td>surname</td>
            <td>email</td>
            <td>type</td>
            <td>action</td>
        </tr>
        <%
            for (User user : users) {%>
        <tr>

            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><%=user.getEmail()%>
            </td>
            <td><%=user.getType().name()%>
            </td>
            <td><a href="/deleteUser?id=<%=user.getId()%>">Delete</a> / <a
                    href="/editUser?id=<%=user.getId()%>">Edit</a></td>
        </tr>
        <%
            }
        %>
    </table>
</div>
</body>
</html>
