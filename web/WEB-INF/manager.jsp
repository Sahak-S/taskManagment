<%@ page import="taskManagment.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="taskManagment.model.Task" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/logout">Logout</a>
<% List<User> users = (List<User>) request.getAttribute("users"); %>
<% List<Task> tasks = (List<Task>) request.getAttribute("tasks"); %>

<div style="width: 800px">
    <div style="width: 50%;float: left">

        Add User:<br>
        <form action="/register" method="post">
            <input type="text" name="name" placeholder="name"><br>
            <input type="text" name="surname" placeholder="surname"><br>
            <input type="text" name="email" placeholder="email"><br>
            <input type="text" name="password" placeholder="password"><br>
            <select name="type">
                <option value="USER">USER</option>
                <option value="MANAGER">MANAGER</option>
            </select><br>
            <input type="submit" name="registr">
        </form>
    </div>

    <div style="width: 50%;float: left">
        Add Task:<br>
        <form action="/addTask" method="post">
            <input type="text" name="name" placeholder="name"><br>
            <textarea name="description" placeholder="description"></textarea><br>
            <input type="date" name="data"><br>

            <select name="status">
                <option value="NEW">NEW</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="FINISHED">FINISHED</option>
            </select><br>

            <select name="user_id">

                <%
                    for (User user : users) {%>

                <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
                </option>
                <%
                    }
                %>


            </select><br>


            <input type="submit" name="ok">
        </form>
    </div>

    <div>

        <form action="/search" method="post">
            <input type="text" name="keyword">
            <input type="submit" value="search">
        </form>
        All Users:<br>
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
                <td><a href="/deleteUser?id=<%=user.getId()%>">Delete</a> / <a href="/editUser?id=<%=user.getId()%>">Edit</a></td>
            </tr>
            <%
                }
            %>
        </table>
    </div>

    <div>
        <form action="/taskSearch" method="post">
            <input type="text" name="keyword">
            <input type="submit" value="search">
        </form>
        All Tasks:<br>

        <table border="1">
            <tr>
                <td>name</td>
                <td>description</td>
                <td>dataline</td>
                <td>status</td>
                <td>user</td>
                <td>action</td>
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
                <td><a href="/deleteTask?id=<%=task.getId()%>">Delete</a></td>
            </tr>
            <%
                }
            %>

        </table>

    </div>

</div>


</body>
</html>
