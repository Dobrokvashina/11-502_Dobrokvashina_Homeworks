package Servlet;

import Classes.Achivement;
import Classes.Subject;
import Classes.User;
import Services.ServiceFactory;
import Services.UniversityService;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by Саоша on 09.11.2016.
 */
public class MyPageServlet extends HttpServlet {

    private UserService userService;
    @Override
    public void init() throws ServletException {

        userService = ServiceFactory.getUserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getSession().getAttribute("current_user") != null) {
            if(req.getParameter("exit") != null) {
                User user = userService.getUser((String)req.getSession().getAttribute("current_user"));
                userService.saveToken(user.getId(), null);
                req.getSession().setAttribute("current_user", null);
                req.getSession().setAttribute("admin", null);
                Cookie cookie = new Cookie("MSiteCookie", null);
                resp.addCookie(cookie);
                req.getRequestDispatcher("/login").forward(req,resp);
            } else {
                if(!req.getSession().getAttribute("current_user").equals("admin")) {
                    User user = userService.getUser((String) req.getSession().getAttribute("current_user"));
                    req.setAttribute("user", user);
                    req.setAttribute("subs", userService.getAllSubjectsWithUsers(user.getId()));
                    req.setAttribute("achs", userService.getAllAchivementsWithUsers(user.getId()));
                }
                req.getRequestDispatcher("/myPage.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/login").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = userService.getUser((String)req.getSession().getAttribute("current_user"));
        Iterator<Subject> iter = userService.getAllSubjects().iterator();
        List<Subject> subs = new LinkedList<Subject>();
        Subject cur;
        while (iter.hasNext()) {
            cur = iter.next();
            cur.setPoint(Integer.parseInt(req.getParameter(cur.getName())));
            subs.add(cur);
        }

        Iterator<Achivement> iter1 = userService.getAllAchivements().iterator();
        List<Achivement> achs = new LinkedList<Achivement>();
        Achivement curr;
        while (iter1.hasNext()) {
            curr = iter1.next();
            if (req.getParameter(curr.getType())!=null)
            curr.setChosen(true);
            achs.add(curr);
        }

        userService.SetSubsAndAchiv(user.getId(),subs,achs);
        resp.sendRedirect("/myPage");
    }
}
