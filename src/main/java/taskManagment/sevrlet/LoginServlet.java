package taskManagment.sevrlet;

import taskManagment.manager.UserManager;
import taskManagment.model.User;
import taskManagment.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        UserManager userManager = new UserManager();
        User user = userManager.getUserByEmail(email);
        if (user == null) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            if (user.getType() == UserType.MANAGER) {
                resp.sendRedirect("/managerHome");
            }else {
                resp.sendRedirect("/userHome");
            }
        }
    }
}
