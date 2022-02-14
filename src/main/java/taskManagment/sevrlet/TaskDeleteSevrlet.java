package taskManagment.sevrlet;

import taskManagment.manager.TaskManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/deleteTask")

public class TaskDeleteSevrlet extends HttpServlet {
    private TaskManager taskManager = new TaskManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idStr = req.getParameter("id");
        int id = Integer.parseInt(idStr);

        taskManager.deleteTaskById(id);
        resp.sendRedirect("/managerHome");
    }
}
