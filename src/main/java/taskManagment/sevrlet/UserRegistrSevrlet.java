package taskManagment.sevrlet;


import taskManagment.manager.UserManager;
import taskManagment.model.User;
import taskManagment.model.UserType;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/userRegistr")
public class UserRegistrSevrlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String type = req.getParameter("type");


        UserManager userManager = new UserManager();
        userManager.addUser(User.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .password(password)
                .type(UserType.valueOf(type))
                .build());
        resp.sendRedirect("/managerHome");
    }
}
