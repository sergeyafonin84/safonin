package ru.job4j.workwithusersservlet;

import ru.job4j.crudservlet.User;
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

public class AddUserServlet extends HttpServlet {

    private final UserStore users = UserStore.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("AddUserServlet doGet");
        req.setAttribute("users", UserStore.getInstance().getAllSql());
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/WEB-INF/views/NewAddUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("AddUserServlet doPost01");

        String back = req.getParameter("Back"); // gi

        HttpSession session = req.getSession();
        String currUserLogin = (String) session.getAttribute("login");

        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println("Back = " + back + LocalDateTime.now());

        if (back == null) {
            if (login.equals("") || name.equals("") || email.equals("") || password.equals("")) {
                req.setAttribute("error", "You must fill all fields!");
                this.doGet(req, resp);
            } else {
                if (currUserLogin.equals("root")) {
                    System.out.println("AddUserServlet doPost02");
                    resp.setContentType("text/html");
                    UserStore.getInstance().addSql(new User(name, login, email, password, LocalDateTime.now()));
                    this.doGet(req, resp);
                } else {
                    req.setAttribute("error", "For add user you must have the root role!");
                    this.doGet(req, resp);
                }
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/");
        }
    }
}