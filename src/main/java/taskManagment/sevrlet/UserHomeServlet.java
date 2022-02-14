package taskManagment.sevrlet;


import taskManagment.manager.TaskManager;
import taskManagment.model.Task;
import taskManagment.model.User;
import taskManagment.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userHome")
public class UserHomeServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getType()!= UserType.MANAGER){
            resp.sendRedirect("/index.jsp");
        }else {
            TaskManager taskManager = new TaskManager();
            List<Task> allTasksByUserId = taskManager.getAllTasksByUserId(user.getId());
            req.setAttribute("tasks",allTasksByUserId);
            req.getRequestDispatcher("/WEB-INF/user.jsp").forward(req,resp);
        }


    }
}
