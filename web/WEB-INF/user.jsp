<%@ page import="java.util.List" %>
<%@ page import="taskManagment.model.Task" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/logout" >Logout</a>


<% List<Task> tasks = (List<Task>) request.getAttribute("tasks"); %>
<div>

    All Tasks:<br>

    <table border="1" >
        <tr bgcolor="red">
            <td>name</td>
            <td>description</td>
            <td>dataline</td>
            <td>status</td>
            <td>user</td>
            <td>Update Status</td>
            <td>action</td>
        </tr>
        <% for (Task task : tasks) { %>
        <tr bgcolor="green">
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
            <td bgcolor="#00ced1">
                <form action="/changeTaskStatus" method="post" >
                    <input type="hidden" name="taskId"value="<%=task.getId()%>">
                    <select name="status">
                        <option value="NEW">NEW</option>
                        <option value="IN_PROGRESS">IN_PROGRESS</option>
                        <option value="FINISHED">FINISHED</option>
                    </select><input type="submit" value="ok">

                </form>
            <td bgcolor="black"><a href="/deleteUserTask?id=<%=task.getId()%>">Delete</a></td>
            </td>

        </tr>

        <%
            }
        %>
    </table>
<%--    <a href="/taskSearch"> Back</a>--%>
</div>


</body>
</html>
