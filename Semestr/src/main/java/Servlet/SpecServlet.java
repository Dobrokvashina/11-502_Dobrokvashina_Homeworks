package Servlet;

import Classes.Speciality;
import Classes.Subject;
import Services.AdminService;
import Services.ServiceFactory;
import Services.UniversityService;
import Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Саоша on 09.11.2016.
 */
public class SpecServlet extends HttpServlet {

    private UniversityService serv;
    private UserService userService;
    private AdminService adminService;
    @Override
    public void init() throws ServletException {
        userService = ServiceFactory.getUserService();
        serv = ServiceFactory.getUniversityService();
        adminService = ServiceFactory.getAdminService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("id") != null && serv.getAllUnivWithSpec(Integer.parseInt(req.getParameter("id"))) != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            req.setAttribute("spec", serv.getSpeciality(id));
            req.setAttribute("univs", serv.getAllUnivWithSpec(id));
            if(req.getParameter("change")!= null && req.getSession().getAttribute("admin")!=null) {
                req.setAttribute("subjects", userService.getAllSubjects());
                req.setAttribute("change", "true");
            }
            req.getRequestDispatcher("/speciality.jsp").forward(req, resp);
        } else {
            req.setAttribute("specs", serv.getAllSpeialities());
            req.getRequestDispatcher("/specialities.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("id") != null && serv.getAllUnivWithSpec(Integer.parseInt(req.getParameter("id"))) != null) {
            int id = Integer.parseInt(req.getParameter("id"));
            Speciality speciality = serv.getSpeciality(id);
            speciality.setName(req.getParameter("specName"));
            speciality.setAbout(req.getParameter("specAbout"));
            List<Subject> subjects = userService.getAllSubjects();
            Iterator<Subject> it = subjects.iterator();
            Subject cur;
            List<Subject> res = new LinkedList<Subject>();
            while(it.hasNext()) {
                cur = it.next();
                if(req.getParameter(cur.getName())!= null) {
                    res.add(cur);
                }
            }
            speciality.setSubjects(res);
            adminService.updateSpeciality(speciality);
            resp.sendRedirect("/specialities?id="+ speciality.getId());
    }
    }
}
