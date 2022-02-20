package taskManagment.sevrlet;

import taskManagment.manager.TaskManager;
import taskManagment.model.Task;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/taskSearch")
public class SearchTaskServlet extends HttpServlet {

    private TaskManager taskManager = new TaskManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<Task> tasks = taskManager.searchTask(keyword);

        req.setAttribute("tasks",tasks);
        req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req,resp);


    }

}
