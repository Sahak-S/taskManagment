<%@ page import="java.util.List" %>
<%@ page import="taskManagment.model.Task" %>

Created by IntelliJ IDEA.
User: User
Date: 13.02.2022
Time: 17:08
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% List<Task> tasks = (List<Task>) request.getAttribute("tasks"); %>

<div>
    All Task:<br>

    <table border="1">
        <tr>
            <td>name</td>
            <td>description</td>
            <td>dataline</td>
            <td>status</td>
            <td>user</td>
        </tr>
        <%
            for (Task task : tasks) {%>
        <tr>
            <td><%=task.getName()%>
            </td>
            <td><%=task.getDescription()%>
            </td>
            <td><%=task.getDataline()%>
            </td>
            <td><%=task.getTaskStatus().name()%>
            </td>
            <td><%=task.getUser().getName() + " " + task.getUser().getSurname()%>
            </td>
        </tr>
        <%
            }
        %>

    </table>

</div>


</body>
</html>
