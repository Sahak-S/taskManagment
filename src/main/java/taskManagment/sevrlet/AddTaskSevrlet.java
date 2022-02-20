package taskManagment.sevrlet;

import taskManagment.manager.TaskManager;
import taskManagment.model.Task;
import taskManagment.model.TaskStatus;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebServlet(urlPatterns = "/addTask")
public class AddTaskSevrlet extends HttpServlet {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String data = req.getParameter("data");
        String status = req.getParameter("status");
        int userid = Integer.parseInt(req.getParameter("user_id"));

        TaskManager taskManager = new TaskManager();
        try {
            taskManager.addTask(Task.builder()
                    .name(name)
                    .description(description)
                    .dataline(sdf.parse(data))
                    .taskStatus(TaskStatus.valueOf(status))
                    .userId(userid)
                    .build());
            resp.sendRedirect("/managerHome");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}


