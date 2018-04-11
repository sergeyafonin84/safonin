package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.UserStore;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

public class DeleteUserServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("DeleteUserServlet doGet");
        req.setAttribute("users", UserStore.getInstance().getAllSql());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/NewDeleteUserView.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("DeleteUserServlet doPost01");

        String back = req.getParameter("Back"); // gi

        HttpSession session = req.getSession();
        String currUserLogin = (String) session.getAttribute("login");

        String login = req.getParameter("login");

        System.out.println("Back = " + back + LocalDateTime.now());

        if (back == null) {
            if (login.equals("")) {
                req.setAttribute("error", "You must fill login field!");
                this.doGet(req, resp);
            } else {
                if (currUserLogin.equals("root")) {
                    System.out.println("DeleteUserServlet doPost02");
                    resp.setContentType("text/html");
                    UserStore.getInstance().delete(login);
                    this.doGet(req, resp);
                } else {
                    req.setAttribute("error", "For delete user you must have the root role!");
                    this.doGet(req, resp);
                }
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}