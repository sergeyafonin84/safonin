package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

public class DeleteRoleServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteRoleServlet doGet");
        req.setAttribute("roles", UserStore.getInstance().getAllRolesSql());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/DeleteRole.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteRoleServlet doPost01");

        String back = req.getParameter("Back"); // gi

        HttpSession session = req.getSession();
        String currUserLogin = (String) session.getAttribute("login");

        String rolename = req.getParameter("rolename");
        String userlogin = req.getParameter("userlogin");

        System.out.println("Back = " + back + LocalDateTime.now());

        if (back == null) {
            if (rolename.equals("") || userlogin.equals("")) {
                req.setAttribute("error", "You must fill all fields!");
                this.doGet(req, resp);
            } else {
                if (currUserLogin.equals("root")) {
                    System.out.println("DeleteRoleServlet doPost02");
                    resp.setContentType("text/html");
                    UserStore.getInstance().deleteRole(rolename, userlogin);
                    this.doGet(req, resp);
                } else {
                    req.setAttribute("error", "For delete role you must have the root role!");
                    this.doGet(req, resp);
                }
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }

}
