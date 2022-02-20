package taskManagment.sevrlet;


import taskManagment.manager.UserManager;
import taskManagment.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/search")
public class SearchUserSevrlet extends HttpServlet {

    private UserManager userManager = new UserManager();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String keyword = req.getParameter("keyword");
        List<User> users = userManager.searchUser(keyword);

        req.setAttribute("users",users);
        req.getRequestDispatcher("/WEB-INF/users.jsp").forward(req,resp);

    }
}
