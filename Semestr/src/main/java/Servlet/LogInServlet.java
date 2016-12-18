package Servlet;

import Classes.User;
import Services.AdminService;
import Services.ServiceFactory;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Саоша on 06.11.2016.
 */
public class LogInServlet extends HttpServlet {

    private UserService userService;
    private AdminService adminService;
    @Override
    public void init() throws ServletException {
        adminService = ServiceFactory.getAdminService();
        userService = ServiceFactory.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher("/logIn.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password1");

        if(adminService.checkAdmin(login, password)) {
            Cookie cookie = userService.generateCookies();
            cookie.setMaxAge(60*60);
            userService.saveToken(0, cookie.getValue());
            resp.addCookie(cookie);
            req.getSession().setAttribute("current_user", login);
            req.getSession().setAttribute("admin", "true");
            resp.sendRedirect("/myPage");
        } else {

        if (login.equals("") || password.equals("") || !userService.isRegistered(login) || !userService.checkPassword(login, password)){
            resp.sendRedirect("/login");
        }else{
            Cookie cookie = userService.generateCookies();
            cookie.setMaxAge(60*60);
            User user = userService.getUser(login);
            userService.saveToken(user.getId(), cookie.getValue());
            resp.addCookie(cookie);
            req.getSession().setAttribute("current_user", req.getParameter("login"));
            resp.sendRedirect("/myPage");
        }
        }
    }
}
