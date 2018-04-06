package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class AddRoleServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddRoleServlet doGet");
        req.setAttribute("roles", UserStore.getInstance().getAllRolesSql());
        req.getRequestDispatcher("/WEB-INF/views/AddRole.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddRoleServlet doPost01");

        String back = req.getParameter("Back"); // gi

        String currUserLogin = (String) req.getSession().getAttribute("login");

        String rolename = req.getParameter("rolename");
        String userlogin = req.getParameter("userlogin");

        System.out.println("Back = " + back + LocalDateTime.now());

        if (back == null) {
            if (rolename.equals("") || userlogin.equals("")) {
                req.setAttribute("error", "You must fill all fields!");
                this.doGet(req, resp);
            } else {
                if (currUserLogin.equals("root")) {
                    System.out.println("AddRoleServlet doPost02");
                    resp.setContentType("text/html");
                    UserStore.getInstance().addRoleSql(new Role(rolename, userlogin));
                    this.doGet(req, resp);
                } else {
                    req.setAttribute("error", "For add role you must have the root role!");
                    this.doGet(req, resp);
                }
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}