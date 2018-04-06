package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.User;
import ru.job4j.crudservlet.UserStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

public class NewUsersServlet  extends HttpServlet {

private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("NewUsersServlet doGet");
        req.setAttribute("users", UserStore.getInstance().getAllSql());
        req.setAttribute("roles", UserStore.getInstance().getAllRolesSql());
        req.getRequestDispatcher("/WEB-INF/views/NewUsersView.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("NewUsersServlet doPost");
        resp.setContentType("text/html");
        UserStore.getInstance().addSql(new User(req.getParameter("name"), req.getParameter("login"), req.getParameter("email"), req.getParameter("password"), LocalDateTime.now()));
        resp.sendRedirect(req.getContextPath() + "/");
    }
}